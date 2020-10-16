package com.oracle.labs.helidon.coherence.clients.test.rest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

public class AuthBuilder implements ClientRequestFilter {
	private String user, password;

	public AuthBuilder(String user, String password) {
		this.user = user;
		this.password = password;
	}

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		MultivaluedMap<String, Object> headers = requestContext.getHeaders();
		// remove any existing authorization
		headers.remove(HttpHeaders.AUTHORIZATION);
		// Build a new authorization header
		String auth = user + ":" + password;
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.ISO_8859_1));
		String authHeader = "Basic " + new String(encodedAuth);
		// apply the new authorization header
		headers.add(HttpHeaders.AUTHORIZATION, authHeader);
	}
}