package com.jac.javadb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.jac.javadb.exception.DatabaseException;


@Primary
@Repository
public class GenericDao {

	private static final Logger log = LogManager.getLogger(GenericDao.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	

	
	/**
	 * Find Item T by Id  [JPA]
	 * @param <T>
	 * @param id
	 * @param cls
	 * @return
	 */
	public <T> T findItemById(int id, Class<T> cls) {
		return em.find(cls, id);
	}
	
	

	/**
	 * Find All Item T [JPA]
	 * @param <T>
	 * @param cls
	 * @return
	 */
	public <T> List<T> findAll(Class<T> cls) {
		/*Table table = (Table) cls.getAnnotation(Table.class);
		String tableName = table.name();*/

		//log.debug(" tabele Name:"+tableName);
		log.debug("try to find all entities");
		
		return em.createQuery("from "+cls.getSimpleName(), cls).getResultList();
	}
	
	
	
	/**
	 * Find All Item T and paginate them throught firstIndex and pageSize [JPA]
	 * @param <T>
	 * @param firstIndex
	 * @param pageSize
	 * @param cls
	 * @return
	 */
	public <T> List<T> findLimitResults(int firstIndex, int pageSize, Class cls) {

		/*Table table = (Table) cls.getAnnotation(Table.class);
		String tableName = table.name();*/
		
		log.debug("try to find subset [" + firstIndex + ", "+ pageSize + "]");

		TypedQuery<T> nativeQuery = this.em.createQuery("from "+cls.getSimpleName(), cls);
		
		nativeQuery.setFirstResult(firstIndex);
		nativeQuery.setMaxResults(pageSize);
		
		List<T> list = nativeQuery.getResultList();
		
		log.debug("found [" + list.size() + "] entities");
		
		return list;

	}
	
	

	/**
	 * Save Object [JPA]
	 * @param obj
	 * @return
	 * @throws DatabaseException
	 */
	public Object save(Object obj) throws DatabaseException{
		
			log.info("try to save item " + obj);

			this.em.persist(obj);
			
			return obj;
		}
		

	
	/**
	 * Update obj 
	 * @param obj
	 * @param id
	 * @throws DatabaseException
	 */
	public void update(Object obj) throws DatabaseException{

		em.merge(obj);
		
	}

	
	
	/**
	 * Delete obj [JPA]
	 * @param obj
	 * @throws DatabaseException
	 */
	public void delete(Object obj) throws DatabaseException{

		em.remove(obj);
	}

	/*
	public <T> List<T>  getAll(Class<T> cls, int limit, int offset) { 
		// mysql_query("SELECT * FROM {$statement} ORDER BY datetime ASC LIMIT {$limit} OFFSET {offset} per paginazione 
		
		log.info("CHIAMATA METODO PER PAGINAZIONE");
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			Table table = (Table) cls.getAnnotation(Table.class);
			String tableName = table.name();
			NativeQuery<T> q  = session.createNativeQuery("SELECT * FROM :table ORDER BY datetime ASC LIMIT :limit OFFSET :offset",cls);
			q.setParameter("table",tableName);
			q.setParameter("limit", limit);
			q.setParameter("offset", offset);
			
			List<T> l = q.list();
			
			return l;
		}
		
	}*/
	
}