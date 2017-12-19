package com.edaifu.validate.code.sms;


import com.edaifu.core.props.SecurityProperties;
import com.edaifu.validate.code.ValidateCode;
import com.edaifu.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;


@Component
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {
	@Autowired
	private SecurityProperties properties;
	
	@Override
	public ValidateCode generator(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(properties.getCode().getSms().getLength());
		ValidateCode validateCode = new ValidateCode(code, properties.getCode().getSms().getExpireIn());
		return validateCode;
	}

}
