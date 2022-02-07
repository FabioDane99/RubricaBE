package com.jac.javadb.domain.dto.response;

public class JwtAuthenticationRespDTO {
	
	private String token;
	//private String gruppo;
	
	public JwtAuthenticationRespDTO() { }
	
	public JwtAuthenticationRespDTO(String token/*, String gruppo*/){
		
		this.token=token;
		//this.gruppo=gruppo;
	}
	
	/*public String getGruppo() {
		return gruppo;
	}
	public void setGruppo(String gruppo) {
		this.gruppo = gruppo;
	}*/
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
