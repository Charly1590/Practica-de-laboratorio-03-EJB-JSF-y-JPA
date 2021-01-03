package ec.edu.ups.ejb;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.Provincia;

@Stateless
public class ProvinciaFacade extends AbstractFacade<Provincia> {

    @PersistenceContext(unitName = "Ejemplo.EJB.JSF.JPA")
    private EntityManager em;

    public ProvinciaFacade() {
        super(Provincia.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

