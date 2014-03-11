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

/**
 * @author manishs
 * 
 * This is Rest service class. It provides Http PATCH method support alogn with GET and POST.
 * The annotated method with Patch verb will be called for Http Patch requests.
 *
 */
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

		String result = "Track saved : " + track;
		return Response.status(201).entity(result).build();
	}

	/**
	 * Patch verb annotated method. It gets called for Http Patch requests. It triggers the applying of patch to
	 * current state of a system object.
	 * @param patchObject
	 * @return
	 * @throws JSONException
	 */
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