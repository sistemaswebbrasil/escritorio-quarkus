package br.com.siswbrasil.escritorio.resource;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author ard333
 */
@NoArgsConstructor @AllArgsConstructor @ToString
public class  AuthRequest {
	
	public String username;
	public String password;

}
