package com.smanish.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.smanish.rest.annotations.PATCH;
import com.smanish.rest.managers.JSONPatchManagerImpl;
import com.smanish.rest.model.Track;

@Path("/json/tracks")
public class JSONService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrackInJSON() {

		Track track = new Track();
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");

		return track;

	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(JSONObject track) throws JSONException {

		track.put("title", "Numb");
		String result = "Track saved : " + track;
		return Response.status(201).entity(result).build();
		
	}
	
	@PATCH
	@Path("/applyPatch")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateJSON(JSONObject patchObject) throws JSONException {
		JSONPatchManagerImpl patchManager = new JSONPatchManagerImpl();
		System.out.println("Inside Patch. Object received : "+patchObject);
		Track track = new Track();
		track.setTitle("glee");
		track.setSinger("David gilmor");
		System.out.println("Track before Update : "+track);
		Track updatedTrack = patchManager.applyPatch(track, patchObject);
		String result = "Track after update : " + updatedTrack;
		return Response.status(201).entity(result).build();

	}
	
}