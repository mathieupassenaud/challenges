package fr.mathieupassenaud.busroutechallenge.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import fr.mathieupassenaud.busroutechallenge.model.Routes;

@Path("api")
public class API {

	@Produces("application/json")
	@Path("direct")
	@GET
	public CallBack find(@QueryParam("dep_sid") int dep_sid, @QueryParam("arr_sid") int arr_sid){
		return new CallBack(dep_sid, arr_sid, new Routes().routeExist(dep_sid, arr_sid));
	}
}
