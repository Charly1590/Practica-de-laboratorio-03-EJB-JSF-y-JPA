package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.FacturaCabeceraFacade;
import ec.edu.ups.entidades.FacturaCabecera;
import ec.edu.ups.entidades.FacturaDetalle;
import ec.edu.ups.entidades.Pedido;
import ec.edu.ups.entidades.Producto;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@RequestScoped
public class FacturaCabeceraBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FacturaCabeceraFacade ejbFacturaCabecera;
	
	private List<FacturaCabecera> cabeceras = new ArrayList<FacturaCabecera>();
	private List<FacturaDetalle> detalles = new ArrayList<FacturaDetalle>();
	
	@PostConstruct
	public void init(){
		cabeceras=ejbFacturaCabecera.facturasCabeceraReves();
	}
	
	public void sacarDetalles(FacturaCabecera facCabecera) {
		detalles=facCabecera.getFacturasDetalle();
	}
	
	public void cancelarFactura(FacturaCabecera facCabecera) {
		facCabecera.setEstado('C');
		ejbFacturaCabecera.edit(facCabecera);
		cabeceras=ejbFacturaCabecera.facturasCabeceraReves();
	}

	public FacturaCabeceraFacade getEjbFacturaCabecera() {
		return ejbFacturaCabecera;
	}

	public void setEjbFacturaCabecera(FacturaCabeceraFacade ejbFacturaCabecera) {
		this.ejbFacturaCabecera = ejbFacturaCabecera;
	}

	public List<FacturaCabecera> getCabeceras() {
		return cabeceras;
	}

	public void setCabeceras(List<FacturaCabecera> cabeceras) {
		this.cabeceras = cabeceras;
	}

	public List<FacturaDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<FacturaDetalle> detalles) {
		this.detalles = detalles;
	}
	
}
