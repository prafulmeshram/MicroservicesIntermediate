/**
 * 
 */
package com.practice.microservice.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

/**
 * @author jack
 *
 */
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter> {


	
	
	public AuthorizationHeaderFilter() {
		super();
		
	}


	@Autowired
	private Environment environment;
	

	@Override
	public GatewayFilter apply(AuthorizationHeaderFilter config) {

		return (exchange, chain) -> {

			ServerHttpRequest request = exchange.getRequest();
			if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
				return onError(exchange, "No Authorization Header", HttpStatus.UNAUTHORIZED);

			String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			String jwt = authorizationHeader.replace("Bearer", "");

			if (!this.validateJwtToken(jwt))
				return onError(exchange, "Authorization is not valid", HttpStatus.UNAUTHORIZED);

			return chain.filter(exchange);
		};
	}

	private Mono<Void> onError(ServerWebExchange exchange, String string, HttpStatus httpStatus) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);
		return response.setComplete();
	}

	private boolean validateJwtToken(String jwt) {
		boolean result = true;
		String subject = Jwts.parser().setSigningKey(environment.getProperty("token.secret")).parseClaimsJws(jwt)
				.getBody().getSubject();

		if (subject == null || subject.isEmpty())
			result = false;

		return result;
	}

	

}
