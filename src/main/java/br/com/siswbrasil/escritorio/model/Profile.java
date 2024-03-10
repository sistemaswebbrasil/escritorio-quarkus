package br.com.siswbrasil.escritorio.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
	private Long id;
	private String name;
	private String email;
	private String token;
	private Set<String> roles;
}
