package com.jac.javadb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.jac.javadb.domain.model.Utente;
import com.jac.javadb.repository.GenericDao;

@Repository
public class UtenteDao extends GenericDao{
	
	private static final Logger log = LogManager.getLogger(UtenteDao.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	
	public Utente getUtenteById(int idUtente) {
		
		return super.findItemById(idUtente, Utente.class);
		
	}
	
	
	/**
	 * riceve l'utente tramite username essendo questo univoco mi aspetto di ottenere un Utente [JPA]
	 * @param username
	 * @return
	 */
	public Utente getUtenteByEmail(String email) {
		
		List<Utente> result= em.createQuery("from Utente u where u.email= :email", Utente.class)
				.setParameter("email", email)
				.getResultList();
		
		log.debug("found [" + result.size() + "] entities");

		if(result.size()==1) {
			
			return result.get(0);	
		}
		
		return null;
		
	}
	
	/**
	 * riceve l'utente tramite username essendo questo univoco mi aspetto di ottenere un Utente [JPA]
	 * @param username
	 * @return
	 */
	public Utente getUtenteByUsername(String username) {
		
		List<Utente> result= em.createQuery("from Utente u where u.username= :username", Utente.class)
				.setParameter("username", username)
				.getResultList();
		
		log.debug("found [" + result.size() + "] entities");

		if(result.size()==1) {
			
			return result.get(0);	
		}
		
		return null;
		
	}
	
	/**
	 * Trova tutti gli utenti per stato
	 * SELECT * FROM utenti WHERE stato=? [JPA]
	 * @param stato
	 * @return
	 */
	public List<Utente> findUserByState(String stato) {
		
		List<Utente> result= em.createQuery("from Utente where :stato ", Utente.class)
				.setParameter("stato", stato)
				.getResultList();
		
		log.debug("found [" + result.size() + "] entities");
		
		return result;
		
	}
	
	public List<Utente> paginationUtenti(int firstIndex, int pageSize){
		
		
		TypedQuery<Utente> nativeQuery = this.em.createQuery("from Utente ", Utente.class);
		
		nativeQuery.setFirstResult(firstIndex);
		nativeQuery.setMaxResults(pageSize);
		
		List<Utente> list = nativeQuery.getResultList();
		return list;
		
	}
	
	public int totalUtentiInDb() {
		
		TypedQuery<Integer> nativeQuery = this.em.createQuery("select idUtente from Utente ", Integer.class);
		
		List<Integer> list = nativeQuery.getResultList();
		return list.size();
		
	}
	
	
	
	
}
