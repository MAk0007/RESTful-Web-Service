package com.ws;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class TestGetClient {
	private static final String webServiceURI = "http://localhost";

	public static void main(String[] args) {

		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		URI serviceURI = UriBuilder.fromUri(webServiceURI).port(8080).path("RestfulWS1").build();
		WebTarget webTarget = client.target(serviceURI);

		/*System.out.println(webTarget.path("rest").path("CheckQuantity").path("in").queryParam("id", 1).queryParam("quantity", 50).request()
				.accept(MediaType.TEXT_PLAIN).get(Response.class).toString());*/
		// text
		System.out.println(webTarget.path("rest").path("CheckQuantity").path("in").queryParam("id", 1).queryParam("quantity", 25).request().accept(MediaType.TEXT_PLAIN).get(String.class));


		

	}
}
