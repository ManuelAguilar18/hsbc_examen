/**
 * 
 */
package com.hsbc.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.hsbc.service.ReservaService;
import com.hsbc.service.impl.ReservaServiceImpl;

import io.muserver.rest.ApiResponse;
import io.muserver.rest.Description;

/**
 * 
 */
@Path("/restaurante")
public class ReservaController {
    
    ReservaService reservaService;
	
    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Description("Creates a new user")
    @ApiResponse(code = "201", message = "The user was created")
    @ApiResponse(code = "400", message = "The ID or name was not specified")
    public String create(String reservaReq) {
    	
    	reservaService = new ReservaServiceImpl();
    	
    	JSONObject json = new JSONObject();
    	
    	json = reservaService.addReserva(reservaReq);

    	
        return json.toString();
    }
    
    @GET
    @Path("/buscar/{fecha}")
    @Produces("application/json")
    public String buscarReserva(@PathParam("fecha") String fecha) {
    	
    	reservaService = new ReservaServiceImpl();
    	
    	JSONObject json = new JSONObject();
    	
    	json = reservaService.buscarReserva(fecha);

    	
        return json.toString();
    }
	
}
