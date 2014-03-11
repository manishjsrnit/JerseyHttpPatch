package com.smanish.rest.managers;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


import com.smanish.rest.model.Track;

/**
 * @author manishs
 * Interface which defines PatchManager role contract
 */
public interface JSONPatchManager {

	/**
	 * @param track - Current state of the object in the system on which patch will be applied.
	 * @param patchObject - The Patch object received may be a single JSONObject or an array of JSONObject.
	 * @return
	 * @throws JSONException
	 * 
	 * This method applies the json patch to the system object. It has the necessary merger logic for it.
	 * 
	 */
	Track applyPatch(Track track, JSONObject patchObject) throws JSONException;
}

