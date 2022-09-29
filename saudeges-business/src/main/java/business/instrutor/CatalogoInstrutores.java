package business.instrutor;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import facade.exceptions.ApplicationException;

@Stateless
public class CatalogoInstrutores {
	
	@PersistenceContext
	private EntityManager em;
	
	public Instrutor getInstrutorByNumero(int nrInstrutor) throws ApplicationException {
		TypedQuery<Instrutor> query = em.createNamedQuery (Instrutor.FIND_BY_NUMERO, Instrutor.class);
		query.setParameter(Instrutor.INSTRUTOR_NUMERO, nrInstrutor);
		try {
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Could not find a instructor with the number \"" + nrInstrutor + "\"", e);
		}
	}
	
	/**
	 * Returns a list with all the existing instructor
	 * @return A list with all the existing instructor
	 */
	public  List<Instrutor> getAllInstructors() {
		try {
			TypedQuery<Instrutor> query = em.createNamedQuery(Instrutor.GET_ALL, Instrutor.class);
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<>(); 
		}	
	}

	/**
	 * Returns a list with all the existing instructor names
	 * @return A list with all the existing instructor names
	 */
	public List<String> getAllInstructorNames () {
		try {
			TypedQuery<String> query = em.createNamedQuery(Instrutor.GET_ALL_NAMES, String.class);
			return query.getResultList();
		} catch (Exception e) {
			return new ArrayList<>(); 
		}	
	}

	

}
