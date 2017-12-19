package com.edaifu.validate.code;

import org.springframework.security.core.AuthenticationException;

@SuppressWarnings("serial")
public class ValidateCodeException extends AuthenticationException{


	public ValidateCodeException(String msg) {
		super(msg);
	}

}
