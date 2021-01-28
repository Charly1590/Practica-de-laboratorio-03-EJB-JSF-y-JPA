package ec.edu.ups.rest;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.entidades.Persona;

@Path("/anularCuenta/")
public class anularCuenta {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    @EJB
    private PersonaFacade ejbPersona;

    /**
     * Default constructor. 
     */
    public anularCuenta() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of anularCuenta
     * @return an instance of String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        // TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of anularCuenta
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response post(@FormParam("cedula") String cedula) throws IOException {
    	Persona aux=ejbPersona.buscarPorCedula(cedula);
    	if(aux==null) {
    		return Response.ok("Persona no encontrada!").build();
    	}else {
    		aux.setRol('X');
    		ejbPersona.edit(aux);
    		return Response.ok("Persona editada!").build();
    	}
    	
    }
}