/**
 * 
 */
package com.edaifu.validate.code;

import com.edaifu.core.props.SecurityProperties;
import com.edaifu.validate.code.image.ImageValidateCodeGenerator;
import com.edaifu.validate.code.sms.DefaultSmsValidateCodeSender;
import com.edaifu.validate.code.sms.SmsValidateCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author JOKER
 *
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name="imageValidateCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageValidateCodeGenerator imageValidateCodeGenerator = new ImageValidateCodeGenerator();
		imageValidateCodeGenerator.setSecurityProperties(securityProperties);
		return imageValidateCodeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsValidateCodeSender.class)
	public SmsValidateCodeSender smsValidateCodeSender() {
		return new DefaultSmsValidateCodeSender();
	}
}
