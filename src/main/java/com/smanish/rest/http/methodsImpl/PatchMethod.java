package com.smanish.rest.http.methodsImpl;

import org.apache.commons.httpclient.methods.PutMethod;

/**
 * @author manishs
 * Class which inherits from Http PostMethod class.
 */
public class PatchMethod extends PutMethod{

	public PatchMethod(String url){
		super(url);
	}

	@Override
	public String getName() {
		return "PATCH";
	}
}
