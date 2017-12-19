package com.edaifu.core.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * 安全模块的系统配置
 * @author JOKER
 *
 */
@ConfigurationProperties(prefix="edaifu.security")
public class SecurityProperties {
	
	private AuthProperties auth = new AuthProperties();
	
	private ValidateCodeProperties code = new ValidateCodeProperties();

	public AuthProperties getAuth() {
		return auth;
	}

	public void setAuth(AuthProperties auth) {
		this.auth = auth;
	}

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}
	
}
