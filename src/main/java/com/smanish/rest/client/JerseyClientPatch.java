package com.smanish.rest.client;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.smanish.rest.http.methodsImpl.PatchMethod;

public class JerseyClientPatch {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		HttpClient client = new HttpClient();
		PatchMethod patchMethod = new PatchMethod("http://localhost:8086/RESTfulExample/rest/json/tracks/applyPatch");
		String input = "{\"op\":\"replace\",\"path\":\"title\",\"value\":\"Pink Floyd\"}";
		StringRequestEntity requestEntity = new StringRequestEntity(input, "application/json", "UTF8");
		patchMethod.setRequestEntity(requestEntity);
		int status = client.executeMethod(patchMethod);
		String text = patchMethod.getResponseBodyAsString(); 
		System.out.println("Server responded with status "+status);
		System.out.println(text);
	}



}
