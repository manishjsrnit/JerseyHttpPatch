package com.smanish.rest.managers;


import org.codehaus.jettison.json.JSONObject;
import org.json.JSONException;

import com.smanish.rest.model.Track;

/**
 * @author manishs
 * Interface for PatchManager role
 *
 */
public interface JSONPatchManager {

	/**
	 * @param site
	 * @param patchObject
	 * @return
	 * @throws JSONException
	 * 
	 * This method applies the json patch to the site object. 
	 */
	Track applyPatch(Track site, JSONObject patchObject) throws JSONException;
}

