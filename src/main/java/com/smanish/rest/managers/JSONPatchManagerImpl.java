package com.smanish.rest.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

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
			org.codehaus.jettison.json.JSONObject patchObject)
			throws JSONException {
		ObjectMapper siteToJsonmapper = new ObjectMapper();
		JSONArray opArray = new JSONArray(patchObject);
		Track updatedSite = null;
		try {
			siteToJsonmapper.writeValue(new File("c:\\user.json"), site);
			BufferedReader fileReader = new BufferedReader(
					new FileReader("c:\\user.json"));
			JsonNode rootNode = siteToJsonmapper.readTree(fileReader);
			for(int jsonCounter = 0; jsonCounter < opArray.length(); jsonCounter++){
				String operation = opArray.getJSONObject(jsonCounter).getString(ServiceConstants.OP);
				JsonNode pathNode = rootNode.path(opArray.getJSONObject(0).getString(ServiceConstants.PATH));
				if (ServiceConstants.REPLACE.equals(operation)) {
					((ObjectNode)rootNode).put(pathNode.getTextValue(), opArray.getJSONObject(0).getString(ServiceConstants.VALUE));

				} else if (ServiceConstants.REMOVE.equals(operation)) {
					((ObjectNode)rootNode).putNull(pathNode.getTextValue());
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
