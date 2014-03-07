package com.rest.client;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;

public class JerseyClientPatch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ClientConfig clientConfig = new DefaultClientConfig();
	    clientConfig.getProperties().put(URLConnectionClientHandler.PROPERTY_HTTP_URL_CONNECTION_SET_METHOD_WORKAROUND, true);
	    clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);       
	    Client client = Client.create(clientConfig);
		WebResource webResource = client
				.resource("http://localhost:8086/RESTfulExample/rest/json/metallica/patch");

		String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
				   .method("PATCH",ClientResponse.class, input);

		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		System.out.println("Output from Server .... \n");
		String output = response.getEntity(String.class);
		System.out.println(output);
	}

}
