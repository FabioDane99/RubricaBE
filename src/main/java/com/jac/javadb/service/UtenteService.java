package com.jac.javadb.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jac.javadb.domain.dto.request.UtenteReqDTO;
import com.jac.javadb.domain.dto.response.UtenteResponseDTO;
import com.jac.javadb.domain.model.Contatto;
import com.jac.javadb.domain.model.Utente;
import com.jac.javadb.exception.DatabaseException;
import com.jac.javadb.exception.UserNotFoundException;
import com.jac.javadb.repository.ContattoDao;
import com.jac.javadb.repository.UtenteDao;




@Service
public class UtenteService  implements UserDetailsService{
	private static final Logger log = LogManager.getLogger(UtenteService.class);

	@Autowired
	UtenteDao utenteDao;
	
	@Autowired
	ContattoDao contattoDao;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Utente user = utenteDao.getUtenteByUsername(username);
        if (user == null) {
        	throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        String roles = gruppoService.getRolesGruppo(user.getGruppo());
//        String[] tokens = roles.split(";");
//        for(String role : tokens) {
//        	grantedAuthorities.add(new SimpleGrantedAuthority(role));
//        }
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EDIT"));
        
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_DETAIL"));
//        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EDIT"));
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);    
	}
	
	

	
	
	/**
	 * Sign-In: controlla se l'username inviato è univoco se non lo è verra lanciata un eccezione, se non lo è verra creato
	 * un nuovo oggetto user e applicato l'Hash alla password.
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor = Exception.class)
	public UtenteResponseDTO register(UtenteReqDTO userDTO) throws Exception {
		try {
			
			Utente user= new Utente(userDTO.getUsername(), 
					userDTO.getPassword(), 
					userDTO.getIndirizzo(), 
					userDTO.getTelefono(), 
					userDTO.getEmail());
			
			
			
			
			Utente utenteInDb= getUtenteByUsername(user.getUsername());
			
			if(utenteInDb!=null) {
				
				throw new Exception("Username is already used");
			}
			
			/*lancio eccezione se l'username non è univoco*/
			
			String passwordCriptata= encoder.encode(user.getPassword());
			user.setPassword(passwordCriptata);
			user.setDataInserimento(new Date());
			user.setUtenteInserimento(user.getUsername());
		
			utenteDao.save(user);
			return new UtenteResponseDTO(user.getIdUtente(), user.getUsername(), user.getIndirizzo(), user.getTelefono(), user.getEmail());
		
		}catch( UserNotFoundException e) {
			e.getMessage();
			return null;
		}
	}
	
	/** READY TO TEST
	 * Cerca nel database un Utente che abbia un username uguale a quella passata
	 * come parametro
	 * @param username
	 * @return Utente
	 * @throws UserNotFoundException
	 */
	@Transactional(rollbackFor = Exception.class)
	public Utente getUtenteByUsername(String username) throws UserNotFoundException{
		Utente user=utenteDao.getUtenteByUsername(username);
		/*if(user==null) {
			throw new UserNotFoundException("Utente con username"+username+"non trovato");
		}*/
		return user;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Utente getUtenteResByUsername(String username) throws UserNotFoundException{
		Utente user=utenteDao.getUtenteByUsername(username);
		if(user==null) {
			throw new UserNotFoundException("Utente con username"+username+"non trovato");
		}
		
		return user;
	}
	

	/*@Transactional(rollbackFor = Exception.class)
	public Utente updateUser(Utente userUpdate) {
		try {
			Utente userInDb=getUtenteByUsername(userUpdate.getUsername());
			
		
			if(userUpdate.getCognome()==null && userInDb.getCognome()==null) {
				userUpdate.setCognome(userInDb.getCognome());
			}
			
			if(userUpdate.getNome()==null && userInDb.getNome()!=null) {
				userUpdate.setNome(userInDb.getNome());
			}
			
			if(userUpdate.getPassword()==null && userInDb.getPassword()!=null) {
				userUpdate.setPassword(userInDb.getPassword());
			}
			
			utenteDao.update(userUpdate);
			
			return userUpdate;
			
		}catch(UserNotFoundException | DatabaseException e) {
			
			log.error(e.getMessage());
			return null;
		}
		
	}*/
	
	@Transactional(rollbackFor = Exception.class)
	public UtenteResponseDTO getUtenteById(int idUtente) {
		Utente utente= utenteDao.getUtenteById(idUtente);
		return null;
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void deleteUtente(int idUtente) {
		
		Utente ut= utenteDao.getUtenteById(idUtente);
		
		if(ut!=null){
		
			// trova tutti i suoi contatti e cancellali
			List<Contatto> listaContatti = contattoDao.findAllContattoByUserId(idUtente);
			for(Contatto c: listaContatti) {
				contattoDao.delete(c);
			}
			// trovat tutte le immagini dei contatti e cancellali
		
		
		utenteDao.delete(ut);
		}
		
		throw new UsernameNotFoundException("Utente non trovato");
	}
}
