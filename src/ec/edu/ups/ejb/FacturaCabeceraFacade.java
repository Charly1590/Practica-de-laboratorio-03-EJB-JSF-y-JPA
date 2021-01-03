package ec.edu.ups.ejb;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.FacturaCabecera;

@Stateless
public class FacturaCabeceraFacade extends AbstractFacade<FacturaCabecera> {

    @PersistenceContext(unitName = "Ejemplo.EJB.JSF.JPA")
    private EntityManager em;

    public FacturaCabeceraFacade() {
        super(FacturaCabecera.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

