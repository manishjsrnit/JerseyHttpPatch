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

import com.smanish.rest.constants.ServiceConstants;
import com.smanish.rest.model.Track;

/**
 * @author manishs
 * 
 * Class for applying json patch to the system objects.
 *
 */
public class JSONPatchManagerImpl implements JSONPatchManager{

	@Override
	public Track applyPatch(Track site,
			JSONObject patchObject)
			throws JSONException {
		ObjectMapper siteToJsonmapper = new ObjectMapper();
		JSONArray opArray = new JSONArray("["+patchObject.toString()+"]");
		Track updatedSite = null;
		try {
			siteToJsonmapper.writeValue(new File("d:\\user.json"), site);
			BufferedReader fileReader = new BufferedReader(
					new FileReader("d:\\user.json"));
			JsonNode rootNode = siteToJsonmapper.readTree(fileReader);
			for(int jsonCounter = 0; jsonCounter < opArray.length(); jsonCounter++){
				String operation = opArray.getJSONObject(jsonCounter).getString(ServiceConstants.OP);
				String path = opArray.getJSONObject(jsonCounter).getString(ServiceConstants.PATH);
				if (ServiceConstants.REPLACE.equals(operation)) {
					((ObjectNode)rootNode).put(path, opArray.getJSONObject(jsonCounter).getString(ServiceConstants.VALUE));

				} else if (ServiceConstants.REMOVE.equals(operation)) {
					((ObjectNode)rootNode).putNull(path);
				}
			}
			updatedSite = siteToJsonmapper.readValue(rootNode, Track.class);
			
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
