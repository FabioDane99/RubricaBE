package com.jac.javadb.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jac.javadb.domain.dto.request.UtenteReqDTO;
import com.jac.javadb.domain.dto.response.ContattoResponseDTO;
import com.jac.javadb.domain.dto.response.UtenteResponseDTO;
import com.jac.javadb.service.ContattoService;
import com.jac.javadb.service.UtenteService;
import com.jac.javadb.util.JwtTokenUtil;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/utente")
public class UtenteController {
	
	@Autowired 
	UtenteService userService;
	
	@Autowired
	ContattoService contattoService;
	
	@Autowired
	JwtTokenUtil jwtConversion;

	@PostMapping("/register")
	public ResponseEntity<UtenteResponseDTO> signin(
			@RequestBody UtenteReqDTO utente
			){
		
		try {
			
			return ResponseEntity.status(201).body(userService.register(utente));
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	
	// delete my account
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteAccount(
			@PathVariable int id,
			@RequestHeader (name="Authorization") String token
			){
		
		Map<String, String> response= new HashMap<>();
		
		try {
			
			/* magari dobbiamo rimuovere il BEARER */
			token= token.substring(7);
			String username= jwtConversion.getUsernameFromToken(token);
			int idUtente= userService.getUtenteResByUsername(username).getIdUtente();
			userService.deleteUtente(idUtente);
			response.put("msg", "The user with id: "+idUtente+" has been succesfull delete");
			return ResponseEntity.status(200).body(response);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			response.put("msg", "Error: "+e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UtenteResponseDTO> updateUser(
			@PathVariable int id,
			@RequestBody UtenteReqDTO utente
			){
		try {
			
			return ResponseEntity.status(200).body(null);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(null);
		}
	}
		
}
