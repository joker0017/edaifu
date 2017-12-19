package com.edaifu.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
	
	public ValidateCode generator(ServletWebRequest request);
}
