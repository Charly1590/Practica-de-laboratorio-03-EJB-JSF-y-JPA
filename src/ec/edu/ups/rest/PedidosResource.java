package ec.edu.ups.rest;

import java.io.IOException;
//import java.math.BigDecimal;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Date;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.controlador.BodegaBean;
import ec.edu.ups.controlador.PedidosCabecera;

import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.FacturaDetalleFacade;
import ec.edu.ups.ejb.PedidoCabeceraFacade;
import ec.edu.ups.ejb.PedidoDetalleFacade;
import ec.edu.ups.ejb.PersonaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.PedidoCabecera;
import ec.edu.ups.entidades.PedidoDetalle;
import ec.edu.ups.entidades.Persona;
import ec.edu.ups.entidades.Producto;




@Path("/cliente/")
public class PedidosResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCliente(@PathParam("id") Integer id) {
	return "User [" + id + ",PathParam]";
    }

    @EJB
	PedidoCabeceraFacade ejbPedidoCabecera;
	@EJB
	PedidoDetalleFacade ejbPedidoDetalle;
  
    @EJB
    PersonaFacade ejbPersona;
    
    @EJB
    ProductoFacade ejbProducto;

    @EJB
    FacturaDetalleFacade ejbFactura;
    
    @EJB
    BodegaFacade ejbBodega;
    // Ejemplo con JSON dasdasdasd

    @GET
    @Path("/query2/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Integer id) {
	Jsonb jsonb = JsonbBuilder.create();
	Persona person = new Persona(1, "Pepito", "Alcachofa", "0105895866", "Zamora", "2250109","pepito@test.com", "1234", 'U');
	return Response.ok(jsonb.toJson(person)).build();
    }

    

    @POST
    @Path("/verEstados/{cedula}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response verEstados(@PathParam("cedula") String cedula) throws IOException {
	
    	List<PedidoCabecera> sta = ejbPedidoCabecera.pedidosCabeceraFiltrada(cedula);
    	
    	
    	
    	Jsonb jsonb = JsonbBuilder.create();
    	return Response.ok(jsonb.toJson(sta))
    		.header("Access-Control-Allow-Origin", "*")
    		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
    		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
    }
    
    
    
    
    @POST
    @Path("/postPedido")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postPedido(@FormParam("cedula") String cedula ,@FormParam("nombre") String nombre, @FormParam("cantidad") Integer cantidad, @FormParam("bodega") Integer idbod) throws IOException {
	
	
	Persona sta = ejbPersona.buscarPorCedula(cedula);
	Bodega staB = ejbBodega.find(idbod);
	List<Producto> productosTemp = staB.getProductos();
	Producto ste = null;
	
	for (Producto producto : productosTemp) {
		if (producto.getNombre().equals(nombre)) {
			ste = producto;
		}
	}
	
	
	
	if(sta==null || staB==null || ste ==null) {
		
		return Response.ok("Los datos no son correctos").build();
	
	
	}else {
		
		PedidoCabecera pedCab = new PedidoCabecera(0, new Date(),(float) 400,(float) 500,(float) 0.12, "Enviado", sta);
		PedidoDetalle pedDetalle = new PedidoDetalle(0, 2, 400, pedCab, ste);
		ejbPedidoCabecera.create(pedCab);
		ejbPedidoDetalle.create(pedDetalle);
		
		
		return Response.ok("Creacion exitosa!!").build();
	}    	
	
	
	//Persona sta = ejbPersona.inicioSesion(usuario, clave);
	
	
	
	
	//Persona person = jsonb.fromJson(jsonPerson, Persona.class);

    }
    
    
    
    
    

    @DELETE
    @Path("delete/{id}")
    public Response delete(@PathParam("id") Integer id) {
	System.out.println("REST/client:delete-->" + id);
	return Response.status(204).entity("Usuario borrado..." + id).build();
    }

    @GET
    @Path("/listprods/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listprods(@PathParam("id") Integer id) {
	
    	//PostmanProds bod = new PostmanProds();
    	
    	
    	List<Producto> productos = new ArrayList<Producto>();
    	
    	ArrayList<Integer> numeros = new ArrayList<Integer>();
    	
    	
    	
    	
    	Bodega sta = ejbBodega.find(id);
    	List<Producto> productosTemp = sta.getProductos();
    	int n = sta.getProductos().size();
    	System.out.println("Total PRoductos: "+ n);
    	
    	for (int i = 0; i < n ; i++) {
			numeros.add(sta.getProductos().get(i).getCategoria().getId());
			
		}
    	
    	Collections.sort(numeros);
    	System.out.println(numeros);
    	
    	boolean y =true;
    	
    	for (int i = 0; i < n ; i++) {
    		y=true;
    		for (int j = 0; j < productosTemp.size() ; j++) {
        		
        		if ( y && numeros.get(i).equals(productosTemp.get(j).getCategoria().getId())) {
					productos.add(productosTemp.get(j));
					productosTemp.remove(j);
					
        			y=false;
				} else {

				}
    			
    			
    		}
			
		}
    	
    	
    	
    	
    	//productos.add(ste);
    	
    	Jsonb jsonb = JsonbBuilder.create();
	
	
	//List<Persona> list = new ArrayList<Persona>();
	
	
	
	//Persona person1 = new Persona(1, "Pepito", "Alcachofa", "0105895866", "Zamora", "2250109","pepito@test.com", "1234", 'U');
	//Persona person2 = new Persona(1, "Juanito", "Alcachofa", "0105895866", "Zamora", "2250109","pepito@test.com", "1234", 'U');
	//Persona person3 = new Persona(1, "Guayusa", "Alcachofa", "0105895866", "Zamora", "2250109","pepito@test.com", "1234", 'U');
	///list.add(person1);
	//list.add(person2);
	//list.add(person3);
	
	
	
	
	
	// para evitar el error del CORS se agregan los headers
	return Response.ok(jsonb.toJson(productos))
		.header("Access-Control-Allow-Origin", "*")
		.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
		.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE").build();
    }
}