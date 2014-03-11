package com.smanish.rest.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.smanish.rest.constants.PatchManagerConstants;
import com.smanish.rest.model.Track;

/**
 * @author manishs
 * 
 * Class responsible for applying json patch to the system objects.
 *
 */
public class JSONPatchManagerImpl implements JSONPatchManager{

	private static final String FILE_PATH = "d:\\user.json";

	/* (non-Javadoc)
	 * @see com.smanish.rest.managers.JSONPatchManager#applyPatch(com.smanish.rest.model.Track, org.codehaus.jettison.json.JSONObject)
	 */
	@Override
	public Track applyPatch(Track track,
			JSONObject patchObject)
					throws JSONException {
		ObjectMapper pojoToJsonmapper = new ObjectMapper();
		JSONArray opArray = new JSONArray("["+patchObject.toString()+"]");
		Track updatedSite = null;
		File file = null;
		try {
			file  = new File(FILE_PATH);
			pojoToJsonmapper.writeValue(file, track);
			BufferedReader fileReader = new BufferedReader(
					new FileReader(FILE_PATH));
			JsonNode rootNode = pojoToJsonmapper.readTree(fileReader);
			for(int jsonObjCounter = 0; jsonObjCounter < opArray.length(); jsonObjCounter++){
				String operation = opArray.getJSONObject(jsonObjCounter).getString(PatchManagerConstants.OP);
				String path = opArray.getJSONObject(jsonObjCounter).getString(PatchManagerConstants.PATH);
				if (PatchManagerConstants.REPLACE.equals(operation)) {
					((ObjectNode)rootNode).put(path, opArray.getJSONObject(jsonObjCounter).getString(PatchManagerConstants.VALUE));

				} else if (PatchManagerConstants.REMOVE.equals(operation)) {
					((ObjectNode)rootNode).putNull(path);
				}
			}
			updatedSite = pojoToJsonmapper.readValue(rootNode, Track.class);
			if(file != null){
				file.delete();
			}
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return updatedSite;
	}

}
