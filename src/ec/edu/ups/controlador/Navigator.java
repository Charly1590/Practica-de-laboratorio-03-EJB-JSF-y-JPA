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
		}
		return page;
	}
}
