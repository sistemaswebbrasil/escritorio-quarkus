package br.com.siswbrasil.escritorio.resource;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.siswbrasil.escritorio.entity.User;
import br.com.siswbrasil.escritorio.repository.UserRepository;
import br.com.siswbrasil.escritorio.util.PBKDF2Encoder;
import br.com.siswbrasil.escritorio.util.TokenUtils;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;




@Path("/auth")
public class AuthenticationREST {

	@Inject
	PBKDF2Encoder passwordEncoder;

	@Inject
	private UserRepository repository;

	@ConfigProperty(name = "com.ard333.quarkusjwt.jwt.duration")
	public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer")
	public String issuer;

	@PermitAll
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(AuthRequest authRequest) throws Exception {
		User u = repository.findByEmail(authRequest.username);
		System.out.println("1#############################");
		System.out.println(passwordEncoder.encode(authRequest.password));
		System.out.println(u.getRoles());
		System.out.println("2#############################"); 
		if (u != null && u.getPassword().equals(passwordEncoder.encode(authRequest.password))) {
			return Response.ok(new AuthResponse(TokenUtils.generateToken(u.getName(), u.getRoles(), duration, issuer)))
					.build();			
		}
		return Response.status(401).build();
	}

}