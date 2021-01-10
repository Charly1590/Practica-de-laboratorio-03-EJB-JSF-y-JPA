package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidades.Producto;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@RequestScoped
public class ProductoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<Producto> productos;
	
	@EJB
	private ProductoFacade ejbProducto;
	
	public ProductoBean() {
	}
	
	@PostConstruct
	public void init(){
		productos=ejbProducto.findAll();
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	

}
