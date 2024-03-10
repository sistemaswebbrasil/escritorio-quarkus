package br.com.siswbrasil.escritorio.util;

import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author ard333
 */
@ApplicationScoped
public class EncoderUtil {

	public String encode(String secret) {
		return BcryptUtil.bcryptHash(secret);
	}

	public boolean matches(String secret, String secret2) {
		return BcryptUtil.matches(secret, secret2);
	}

}
