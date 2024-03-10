package br.com.siswbrasil.escritorio.util;

import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.microprofile.jwt.Claims;

import br.com.siswbrasil.escritorio.entity.Role;
import br.com.siswbrasil.escritorio.entity.User;
import br.com.siswbrasil.escritorio.model.Profile;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

/**
 *
 * @author ard333
 */
public class TokenUtils {
	

	public static Profile responseLogged(User user, Long duration, String issuer) throws Exception {
		String privateKeyLocation = "/privatekey.pem";
		PrivateKey privateKey = readPrivateKey(privateKeyLocation);
		long currentTimeInSecs = currentTimeInSecs();
		
        String token =
                Jwt.issuer(issuer) 
                  .groups(user.getRoles()) 
                  .claim(Claims.sub, user.getId().toString())                  
                  .claim(Claims.preferred_username, user.getName())                  
                  .claim(Claims.email, user.getEmail())
                  .claim(Claims.given_name, user.getName())
                  .claim(Claims.exp,currentTimeInSecs + duration)
                  .claim(Claims.iat, currentTimeInSecs)
                  .claim(Claims.iss, issuer)
                  .claim(Claims.upn, user.getEmail())
                  .jws().signatureKeyId(privateKeyLocation)
                .sign(privateKey);		
		
		
		var profile = new Profile(user.getId(),user.getName(), user.getEmail(), token, user.getRoles());
		return profile;
		
	}

	public static String generateToken(String username, Set<Role> roles, Long duration, String issuer) throws Exception {
		String privateKeyLocation = "/privatekey.pem";
		PrivateKey privateKey = readPrivateKey(privateKeyLocation);
		
		JwtClaimsBuilder claimsBuilder = Jwt.claims();
		long currentTimeInSecs = currentTimeInSecs();
		
		Set<String> groups = new HashSet<>();
		for (Role role : roles) groups.add(role.toString());	

		claimsBuilder.issuer(issuer);
		claimsBuilder.subject(username);
		claimsBuilder.issuedAt(currentTimeInSecs);
		claimsBuilder.expiresAt(currentTimeInSecs + duration);
		claimsBuilder.groups(groups);

		return claimsBuilder.jws().signatureKeyId(privateKeyLocation).sign(privateKey);
	}

	public static PrivateKey readPrivateKey(final String pemResName) throws Exception {
		try (InputStream contentIS = TokenUtils.class.getResourceAsStream(pemResName)) {
			byte[] tmp = new byte[4096];
			int length = contentIS.read(tmp);
			return decodePrivateKey(new String(tmp, 0, length, "UTF-8"));
		}
	}

	public static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
		byte[] encodedBytes = toEncodedBytes(pemEncoded);

		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(keySpec);
	}

   public static byte[] toEncodedBytes(final String pemEncoded) {
		final String normalizedPem = removeBeginEnd(pemEncoded);
		return Base64.getDecoder().decode(normalizedPem);
	}

	public static String removeBeginEnd(String pem) {
		pem = pem.replaceAll("-----BEGIN (.*)-----", "");
		pem = pem.replaceAll("-----END (.*)----", "");
		pem = pem.replaceAll("\r\n", "");
		pem = pem.replaceAll("\n", "");
		return pem.trim();
	}

	public static int currentTimeInSecs() {
		long currentTimeMS = System.currentTimeMillis();
		return (int) (currentTimeMS / 1000);
	}



}
