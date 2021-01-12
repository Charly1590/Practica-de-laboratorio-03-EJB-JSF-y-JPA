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

import ec.edu.ups.ejb.BodegaFacade;
import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.Categoria;
import ec.edu.ups.entidades.Producto;


@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@RequestScoped
public class BodegaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private BodegaFacade ejbBodegas;
	@EJB
	private CategoriaFacade ejbCategorias;
	@EJB
	private ProductoFacade ejbProducto;
	private List<Bodega> bodegas;
	private int bodegaActual;	
	private boolean editar=true;
	private String nombreProducto;
	private float precioProducto;
	private int stockProducto;
	private char estadoProducto;
	
	public BodegaBean () {
		
	}
	@PostConstruct
	public void init(){
		bodegas=ejbBodegas.findAll();
	}
	public List<Bodega> getBodegas() {
		return bodegas;
	}
	public void setBodegas(List<Bodega> bodegas) {
		this.bodegas = bodegas;
	}
	public List<Categoria> getCategoriasBodega(){
		return ejbCategorias.findAll();
	}
	public String[] getNombreCategoria() {
		String[] lista= {"Limpieza","Jardineria","Cuidado personal","Cocina","Electricidad", 
				"Herramientas","Electrodomesticos","Licores"};
		return lista;
	}
	public List<Producto> getProductosBodega(){
		return bodegas.get(bodegaActual).getProductos();
	}
	public int getBodegaActual() {
		return bodegaActual;
	}
	public String setBodegaActual(int bodegaActual) {
		this.bodegaActual = bodegaActual;		
		return null;
	}
	public BodegaFacade getEjbBodegas() {
		return ejbBodegas;
	}
	public void setEjbBodegas(BodegaFacade ejbBodegas) {
		this.ejbBodegas = ejbBodegas;
	}
	public boolean isEditar() {
		return editar;
	}	

	public String setEditar(boolean editar) {
		this.editar = editar;
		return null;
	}
	public void cambiarEditar(Producto p) {
		editar=!editar;		
		ejbProducto.edit(p);			
	}
	public void crearProducto() {
		System.out.println("Bodega Actual:"+bodegaActual);
		Producto productoAuxiliar= new Producto(nombreProducto, precioProducto, stockProducto, estadoProducto);
		ejbProducto.create(productoAuxiliar);		
		bodegas.get(bodegaActual).addProductos(productoAuxiliar);
		ejbBodegas.edit(bodegas.get(bodegaActual));
	}
	public void eliminarEditar(Producto p) {			
		bodegas.get(bodegaActual).delelteProducto(p);
		ejbBodegas.edit(bodegas.get(bodegaActual));
		ejbProducto.remove(p);			
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public float getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(float precioProducto) {
		this.precioProducto = precioProducto;
	}
	public int getStockProducto() {
		return stockProducto;
	}
	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}
	public char getEstadoProducto() {
		return estadoProducto;
	}
	public void setEstadoProducto(char estadoProducto) {
		this.estadoProducto = estadoProducto;
	}
	public String[] getNombresBodega() {
		String [] nombres=new String [bodegas.size()];
		for(int i=0;i<bodegas.size();i++) {
			nombres[i]=bodegas.get(i).getNombre();
		}
		return nombres;
	}
	
}
