package ec.edu.ups.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.Producto;

@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {
	@PersistenceContext(unitName = "Practica-de-laboratorio-03-EJB-JSF-y-JPA")
    private EntityManager em;

    public ProductoFacade() {
        super(Producto.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Producto> buscarPorNombre (String nombre) {
    	List<Producto> productos=new ArrayList<Producto>();
    	String consulta = "Select p From Producto p Where p.nombre like '%"+nombre+"%'";
    	try {
    		productos= em.createQuery(consulta).getResultList();
    	}catch(Exception e) {
    		System.out.println(">>>Warning (ProductoFacade:buscarPorNombre: )"+e.getMessage());
    	}
    	return productos;
    }
}
