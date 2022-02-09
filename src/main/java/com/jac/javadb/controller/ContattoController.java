package com.jac.javadb.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jac.javadb.domain.dto.request.ContattoReqDTO;
import com.jac.javadb.domain.dto.response.ContattoResponseDTO;
import com.jac.javadb.domain.dto.response.PaginationContattiDTO;

import com.jac.javadb.service.ContattoService;
import com.jac.javadb.service.UtenteService;
import com.jac.javadb.util.JwtTokenUtil;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/contatto")
public class ContattoController {
	
	@Autowired
	JwtTokenUtil jwtConversion;
	
	@Autowired
	ContattoService service;
	
	@Autowired
	UtenteService userService;
	/*
	@PostMapping(value = "image/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file,
			@PathVariable long id) throws IOException {
		try {
		
		imageService.uploadImage(file, idUtente);
		
		return ResponseEntity.status(HttpStatus.OK);
		
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	*/
	/*
	@GetMapping(path = { "/image" }, produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[]  getImage(
			@RequestHeader (name="Authorization") String token
			
			) throws IOException, UserNotFoundException{
		token= token.substring(7);
		String username= jwtConversion.getUsernameFromToken(token);
		
		int idUtente= utenteService.getUtenteByUsername(username).getIdUtente();
		
		return imageService.getImageUtente(idUtente).getPicByte();
		 
		
	}*/
	
	/*Get Contatto By Id*/
	@GetMapping("/{id}")
	public ResponseEntity<ContattoResponseDTO> getRisorsa(@PathVariable int id){
		
		// Aggiungere un controllo
		return ResponseEntity.ok(service.getContattoById(id));
	}
	
	/*Get Contatti Paginati e Filtrabili */
	/*** CRUD AREA ***/
	@GetMapping("/pagination")
	public ResponseEntity<PaginationContattiDTO> getPaginationOfRisorse(
			@RequestParam(required=true) Integer page,
			@RequestParam( defaultValue="10") Integer pageSize,
			@RequestParam(defaultValue="" )String contatto,
			@RequestHeader (name="Authorization") String token
			){
		
		try {
			
			/* magari dobbiamo rimuovere il BEARER */
			token= token.substring(7);
			String username= jwtConversion.getUsernameFromToken(token);
			int userId= userService.getUtenteResByUsername(username).getIdUtente();
			
			return ResponseEntity.ok(service.getPaginatedContatti(pageSize, page, contatto, userId));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	
	
	/*Cancella Contatto*/
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteContatto(
			@PathVariable int id,
			@RequestHeader (name="Authorization") String token){
		
		try {
			
			token= token.substring(7);
			String username= jwtConversion.getUsernameFromToken(token);
			int userId= userService.getUtenteResByUsername(username).getIdUtente();
			
			if(!service.checkContattoById(id, userId)) {
				return ResponseEntity.status(400).body("Unauthorized: puoi cancellare solo i tuoi contatti");
			}
		
			service.deleteContatto(id);
			
			return ResponseEntity.ok("Contatto con id= "+id+" eliminato correttamente");
		}catch(Exception e) {
			return ResponseEntity.status(400).body("Si è verificato il seguente errore: "+e.getMessage());
		}
	}
	
	/* Modifica Info Contatto*/
	@PutMapping("/{id}")
	public ResponseEntity<ContattoResponseDTO> updateContatto(@PathVariable int id, 
			@RequestBody ContattoReqDTO contatto,
			@RequestHeader (name="Authorization") String token){
		try {
			token= token.substring(7);
			String username= jwtConversion.getUsernameFromToken(token);
			return ResponseEntity.ok(service.updateContatto(id, contatto, username));
		}catch(Exception e) {
			
			return ResponseEntity.status(400).body(null);
		}
		
	}
	
	
	/*crea un nuovo contatto */
	@PostMapping("/")
	public ResponseEntity<ContattoResponseDTO> createRisorsa(
			@RequestBody ContattoReqDTO risorsaRequestDTO,
			@RequestHeader (name="Authorization") String token
			){
		
		try {
			
			
			token= token.substring(7);
			String username= jwtConversion.getUsernameFromToken(token);
			int userId= userService.getUtenteResByUsername(username).getIdUtente();
			return ResponseEntity.status(201).body(service.creaContatto(risorsaRequestDTO, userId, username));
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	
}	

