package com.jac.javadb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jac.javadb.domain.dto.request.JwtAuthenticationReqDTO;
import com.jac.javadb.domain.dto.response.JwtAuthenticationRespDTO;
import com.jac.javadb.service.UtenteService;
import com.jac.javadb.util.JwtTokenUtil;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@ApiOperation(value ="/", tags="Authorization Login")
public class JwtAuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private UtenteService userDetailsService;

    @ApiOperation(value="Login", response=JwtAuthenticationRespDTO.class)
    @PostMapping(path = "/auth")
    public ResponseEntity<JwtAuthenticationRespDTO> createAuthenticationToken(@RequestBody JwtAuthenticationReqDTO authenticationRequest) throws Exception {
        
        JwtAuthenticationRespDTO jwt = new JwtAuthenticationRespDTO();
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        
        jwt.setToken(token);
        
        return ResponseEntity.ok(jwt);
    }

    private void authenticate(String username, String password) throws Exception {
        
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            
    }
} 