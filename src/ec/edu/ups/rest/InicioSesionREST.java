package ec.edu.ups.rest;

import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ec.edu.ups.controlador.LoguinBean;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.entidades.Persona;
@Path("/iniciosesionREST/")
public class InicioSesionREST {    
	@EJB
	private PersonaFacade ejbPersona;
    public InicioSesionREST() {
        
    }    
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response post(@FormParam("usuario") String usuario, @FormParam("clave") String clave) throws IOException {		
    	Persona sta = ejbPersona.inicioSesion(usuario, clave);
    	if(sta==null) {
    		return Response.ok("Persona no encotrada").build();
    	}else {
    		return Response.ok("Inicio de sesion exitoso!!").build();
    	}    	
    }
}