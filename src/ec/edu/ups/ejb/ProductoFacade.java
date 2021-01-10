package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.Producto;
import ec.edu.ups.entidades.Provincia;

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
}
