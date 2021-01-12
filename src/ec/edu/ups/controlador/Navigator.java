package ec.edu.ups.controlador;

import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

@Named
@RequestScoped
public class Navigator {
	
	public Navigator() {
		
	}
	
	public String reglaRedir( String page ) {
		
		if(page.equals("CreacionFactura")) {
			return "crearFactura";
		}else if(page.equals("listarFactura")) {
			return "listarFactura";
		}else if(page.equals("crearCliente")) {
			return "crearCliente";
		}else if(page.equals("menuPrincial")) {
			return "menuPrincial";
		}else if(page.equals("bodega1")) {
			return "bodega1";
		}else if(page.equals("bodega2")) {
			return "bodega2";
		}else if(page.equals("bodega3")) {
			return "bodega3";
		}
		
		return page;
	}
}
