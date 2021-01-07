package ec.edu.ups.ejb;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.Persona;

@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "Practica-de-laboratorio-03-EJB-JSF-y-JPA")
    private EntityManager em;

    public PersonaFacade() {
        super(Persona.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

