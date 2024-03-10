package br.com.siswbrasil.escritorio.resource;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;

import br.com.siswbrasil.escritorio.entity.User;
import br.com.siswbrasil.escritorio.model.AuthRequest;
import br.com.siswbrasil.escritorio.model.Profile;
import br.com.siswbrasil.escritorio.repository.UserRepository;
import br.com.siswbrasil.escritorio.util.EncoderUtil;
import br.com.siswbrasil.escritorio.util.TokenUtils;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/auth")
public class AuthenticationREST {

	@Inject
	private EncoderUtil encoderUtil;

	@Inject
	private UserRepository repository;

	@ConfigProperty(name = "com.ard333.quarkusjwt.jwt.duration")
	public Long duration;

	@ConfigProperty(name = "mp.jwt.verify.issuer")
	public String issuer;

	@Inject
	SecurityIdentity securityIdentity;

	@Inject
	JsonWebToken jsonWebToken;

	@PermitAll
	@POST
	@Path("/token")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(AuthRequest authRequest) throws Exception {
		User user = repository.findByEmail(authRequest.getEmail());
		var matche = encoderUtil.matches(authRequest.getPassword(), user.getPassword());
		if (matche) {
			var response = TokenUtils.responseLogged(user, duration, issuer);
			return Response.ok(response).build();
		}
		return Response.status(401).build();
	}

	@GET
	@Path("/profile")
	@RolesAllowed({"USER", "ADMIN"})
	public Profile profile() {		
		var roles = jsonWebToken.getGroups();
		String bearer = jsonWebToken.getRawToken();
		String email = jsonWebToken.getClaim("email");
		String name = jsonWebToken.getClaim("givenName");
		String userId = jsonWebToken.getSubject();
		Long id = Long.valueOf(userId);
		var profile = new Profile();
		profile.setRoles(roles);
		profile.setToken(bearer);
		profile.setEmail(email);
		profile.setName(name);
		profile.setId(id);
		return profile;
	}

}