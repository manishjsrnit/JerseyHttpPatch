package com.smanish.rest.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientGet {

	public static void main(String[] args) {
		try {
			Client client = Client.create();
			WebResource webResource = client
					.resource("http://localhost:8086/RESTfulExample/rest/json/tracks/get");
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}
			System.out.println("Response from Jersey Server .... \n");
			System.out.println(response.getEntity(String.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}