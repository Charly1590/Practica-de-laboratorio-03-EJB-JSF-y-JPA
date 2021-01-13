package ec.edu.ups.controlador;
import java.io.Serializable;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

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
@ApplicationScoped
public class BodegaBean2 implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private BodegaFacade ejbBodegas;
	@EJB
	private CategoriaFacade ejbCategorias;
	@EJB
	private ProductoFacade ejbProducto;
	private List<Bodega> bodegas;
	private int bodegaActual;	
	private String nombreProducto;
	private float precioProducto;
	private int stockProducto;
	private char estadoProducto;
	private String categoria;
	
	
	public BodegaBean2 () {
		
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
	public void setBodegaActual(int bodegaActual) {
		this.bodegaActual = bodegaActual;
	}
	public BodegaFacade getEjbBodegas() {
		return ejbBodegas;
	}
	public void setEjbBodegas(BodegaFacade ejbBodegas) {
		this.ejbBodegas = ejbBodegas;
	}
	public void editarProducto(Producto p) {		
		ejbProducto.edit(p);
	}
	public void crearProducto() {		
		Producto productoAuxiliar= new Producto(0,nombreProducto, precioProducto, stockProducto, estadoProducto,buscarCategoria(categoria));		
		ejbProducto.create(productoAuxiliar);		
		bodegas.get(bodegaActual).addProductos(productoAuxiliar);
		ejbBodegas.edit(bodegas.get(bodegaActual));
	}
	public void eliminarProducto(Producto p) {		
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Categoria buscarCategoria(String nombre) {
		int salida=0;		
		for(int i=0;i<this.getCategoriasBodega().size();i++) {
			System.out.println("Categoria!!"+this.getCategoriasBodega().get(i).getNombre());
			if(this.getCategoriasBodega().get(i).getNombre()==nombre) {
				salida=i;
			}
		}
		System.out.println(this.getCategoriasBodega().get(salida).getNombre());
		return this.getCategoriasBodega().get(salida);
	}
	
}
