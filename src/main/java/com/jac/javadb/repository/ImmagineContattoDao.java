package com.jac.javadb.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.jac.javadb.domain.model.ImmagineContatto;

@Repository
public class ImmagineContattoDao extends GenericDao{
	
	private static final Logger log = LogManager.getLogger(ImmagineContattoDao.class);
	@PersistenceContext
	private EntityManager em;
	
	public void deleteImage(ImmagineContatto img) {
		super.delete(img);
	}
	
	public void saveImage(ImmagineContatto img) {
		super.save(img);
	}
	
	public ImmagineContatto findImageById(int id) {
		return super.findItemById(id, ImmagineContatto.class);
	}
	
	public ImmagineContatto findImageByUsername(String name) {
		
		List<ImmagineContatto> result= em.createQuery("from ImageUtente where :name ", ImmagineContatto.class)
				.setParameter("name", name)
				.getResultList();
		
		log.debug("found [" + result.size() + "] entities");

		if(result.size()==1) {
			
			return result.get(0);	
		}
		
		return null;
		
	}

	public Optional<ImmagineContatto> findImageByIdUtente(int idUtente) {

		List<ImmagineContatto> result= em.createQuery("from ImageUtente where idUtente= :idUser ", ImmagineContatto.class)
				.setParameter("idUser", idUtente)
				.getResultList();
		
		log.debug("found [" + result.size() + "] entities");

		if(result.size()==1) {
			
			 Optional<ImmagineContatto> imgEntity=  Optional.of(result.get(0));
			return 	imgEntity;
		}
		
		return null;
		
	}
}
