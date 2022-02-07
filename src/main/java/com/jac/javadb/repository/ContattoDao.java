package com.jac.javadb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.jac.javadb.domain.model.Contatto;
import com.jac.javadb.repository.GenericDao;

@Repository
public class ContattoDao extends GenericDao{
	
	private static final Logger log = LogManager.getLogger(ContattoDao.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	
	public Contatto getContattoById(int id) {
		
		return  super.findItemById(id, Contatto.class);
		
	}
	
	
	/**
	 * riceve l'utente tramite username essendo questo univoco mi aspetto di ottenere un Utente [JPA]
	 * @param username
	 * @return
	 */
	public Contatto getContattoByEmail(String email) {
		
		List<Contatto> result= em.createQuery("from Utente u where u.email= :email", Contatto.class)
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
	public Contatto getUtenteByUsername(String username) {
		
		List<Contatto> result= em.createQuery("from Contatto u where u.username= :username", Contatto.class)
				.setParameter("username", username)
				.getResultList();
		
		log.debug("found [" + result.size() + "] entities");

		if(result.size()==1) {
			
			return result.get(0);	
		}
		
		return null;
		
	}
	

	
	public List<Contatto> paginationContatti(int firstIndex, int pageSize, String userToFind, int idUtente){
		
		
		TypedQuery<Contatto> nativeQuery = this.em.createQuery("from Contatto c where c.nome like concat('%',:userToFind, '%') and c.userId=:idUser", Contatto.class);
		
		nativeQuery.setParameter("userToFind", userToFind);
		nativeQuery.setParameter("idUser", idUtente);
		nativeQuery.setFirstResult(firstIndex);
		nativeQuery.setMaxResults(pageSize);
		
		List<Contatto> list = nativeQuery.getResultList();
		return list;
		
	}
	
	public int totalContatti(String userToFind, int idUtente) {
		
		TypedQuery<Integer> nativeQuery = this.em.createQuery("select id from Contatto c where c.nome like concat('%',:userToFind, '%') and c.userId=:idUser", Integer.class);
		nativeQuery.setParameter("userToFind", userToFind);
		nativeQuery.setParameter("idUser", idUtente);
		List<Integer> list = nativeQuery.getResultList();
		return list.size();
		
	}
	
	public void updateContatto(Contatto contatto) {
		
		super.update(contatto);
	}
	
	public List<Contatto> findAllContattoByUserId(int userId){
		
		List<Contatto> result= em.createQuery("from Utente u where u.userId= :userId", Contatto.class)
				.setParameter("userId", userId)
				.getResultList();
		return result;
		
	}
	
	
	
	
}