package com.jac.javadb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jac.javadb.domain.dto.request.ContattoReqDTO;
import com.jac.javadb.domain.dto.response.ContattoResponseDTO;
import com.jac.javadb.domain.dto.response.PaginationContattiDTO;

import com.jac.javadb.domain.model.Contatto;
import com.jac.javadb.repository.ContattoDao;

@Service
public class ContattoService {

	@Autowired
	ContattoDao contattoDao;
	
	
	
	/*Get Contatto By Id*/
	 @Transactional(rollbackFor = Exception.class)
	public ContattoResponseDTO getContattoById( int id) {
		
		Contatto contattoDB= contattoDao.findItemById(id, Contatto.class);
		
		return new ContattoResponseDTO( 
				contattoDB.getId(),
				contattoDB.getNome(), 
				contattoDB.getCognome(), 
				contattoDB.getEmail(), 
				contattoDB.getIndirizzo(), 
				contattoDB.getTelefono(), 
				contattoDB.getFoto());
	}
	
		/*Get Contatto By Id*/
	 @Transactional(rollbackFor = Exception.class)
	public boolean checkContattoById( int id, int idUser) {
		
		Contatto contattoDB= contattoDao.findItemById(id, Contatto.class);
		
		if(contattoDB.getIdUser()==idUser) {
			return true;
		}
		
		return false;
	} 
	 
	 
	 
	/*Get Contatti Paginati e Filtrabili */
	
	 @Transactional(rollbackFor = Exception.class)
	public PaginationContattiDTO getPaginatedContatti( int pageSize, int pageIndex, String usernameFiltring, int idUtente) throws Exception {
		
		int totalElements = contattoDao.totalContatti(usernameFiltring, idUtente);
		int lastPageTemp = (int) ((float)totalElements / (float)pageSize);
		boolean isLastPage= false;
			 
		if(pageIndex>lastPageTemp) {
				 
			throw new Exception("Page not Available");
				 
		}else if(pageIndex== lastPageTemp) {
				 
				 isLastPage=true;
		}
			 
			 PaginationContattiDTO result= new PaginationContattiDTO();
			 
			 int pageStart= pageSize*pageIndex;
			 //prima era page Index
			 List<Contatto> listaContatto= contattoDao.paginationContatti(pageIndex, pageSize, usernameFiltring, idUtente);
			 
				for(Contatto co : listaContatto) {
					
					result.getContent().add(new ContattoResponseDTO( 
							co.getId(),co.getNome(), 
							co.getCognome(), 
							co.getEmail(), 
							co.getIndirizzo(), 
							co.getTelefono(), 
							co.getFoto()));
				}
			 
				result.setLastPage(isLastPage);
				result.setPageNumber(pageIndex);
				result.setPageSize(pageSize);
				result.setTotalElements(totalElements);
				result.setTotalPages(lastPageTemp+1);
				
				return result;
		
	}
	
	/*Cancella Contatto*/
	 @Transactional(rollbackFor = Exception.class)
	public void deleteContatto(int id) {
		contattoDao.delete(contattoDao.findItemById(id, Contatto.class));
		
	}
	
	/* Modifica Info Contatto*/
	 @Transactional(rollbackFor = Exception.class)
	public ContattoResponseDTO updateContatto(int idContatto, ContattoReqDTO contatto, String username) throws Exception {
		 
		 Contatto contattoDB= contattoDao.getContattoById(idContatto);
			
			if(contattoDB==null) {
				throw new Exception(" Risorsa not found");
			}
		
			/* controllo aggiuntivo*/
			if(contatto.getCognome()!=null) { contattoDB.setCognome(contatto.getCognome());}
			if(contatto.getEmail()!=null) { contattoDB.setEmail(contatto.getEmail()); }
			if(contatto.getIndirizzo()!=null) { contattoDB.setIndirizzo(contatto.getIndirizzo()); }
			if(contatto.getNome()!=null) { contattoDB.setNome(contatto.getNome());}
			if(contatto.getTelefono()!=null) {  contattoDB.setTelefono(contatto.getTelefono());}
			contattoDB.setDataModifica(new Date());
			contattoDB.setUtenteModifica(username);
			contattoDao.updateContatto(contattoDB);
			return new ContattoResponseDTO( 
					contattoDB.getId(),
					contattoDB.getNome(), 
					contattoDB.getCognome(), 
					contattoDB.getEmail(), 
					contattoDB.getIndirizzo(), 
					contattoDB.getTelefono(), 
					contattoDB.getFoto());
		
	}
	 
	 @Transactional(rollbackFor = Exception.class)
	public ContattoResponseDTO creaContatto(ContattoReqDTO contattoCreate, int idUtente, String username ) throws Exception {
		 
		 Contatto contattoDB= new Contatto();
		
		
			/* controllo aggiuntivo*/
			if(contattoCreate.getCognome()!=null) { contattoDB.setCognome(contattoCreate.getCognome());}
			if(contattoCreate.getEmail()!=null) { contattoDB.setEmail(contattoCreate.getEmail()); }
			if(contattoCreate.getIndirizzo()!=null) { contattoDB.setIndirizzo(contattoCreate.getIndirizzo()); }
			if(contattoCreate.getNome()!=null) { contattoDB.setNome(contattoCreate.getNome());}
			if(contattoCreate.getTelefono()!=null) {  contattoDB.setTelefono(contattoCreate.getTelefono());}
			if(contattoCreate.getFoto()!=null) {  contattoDB.setFoto(contattoCreate.getFoto());}
			contattoDB.setIdUser(idUtente);
			contattoDB.setUtenteInserimento(username);
			contattoDB.setDataInserimento(new Date());
			contattoDao.save(contattoDB);
			
			return new ContattoResponseDTO( 
					contattoDB.getId(),
					contattoDB.getNome(), 
					contattoDB.getCognome(), 
					contattoDB.getEmail(), 
					contattoDB.getIndirizzo(), 
					contattoDB.getTelefono(), 
					contattoDB.getFoto());
		
	}
	 
	 
	 
}
