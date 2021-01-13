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
import ec.edu.ups.entidades.Pedido;
import ec.edu.ups.entidades.Producto;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@RequestScoped
public class ProductoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public static List<Producto> productos;
	public static boolean centinela=true;
	public static List<Pedido> pedidos=new ArrayList<Pedido>();
	private String nombreBusq;
	
	@EJB
	private ProductoFacade ejbProducto;
	
	
	@PostConstruct
	public void init(){
		pedidos.clear();
		if(centinela){
			productos=ejbProducto.findAll();
			System.out.println("tyamana: "+productos.get(0).getBodegas().get(0).getNombre());
			for(Producto producto : productos) {
				Pedido pedido=new Pedido(producto, "");
				pedidos.add(pedido);
			}
		}
	}
	
	public void buscarPorNombre() {
		centinela=false;
		productos=ejbProducto.buscarPorNombre(this.getNombreBusq());
		pedidos=new ArrayList<Pedido>();
		if(productos==null || productos.size() == 0) {
			productos=ejbProducto.findAll();
		}
		for(Producto producto : productos) {
			Pedido pedido=new Pedido(producto, "");
			pedidos.add(pedido);
		}
		
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public String getNombreBusq() {
		return nombreBusq;
	}

	public void setNombreBusq(String nombreBusq) {
		this.nombreBusq = nombreBusq;
	}
	
	

}
