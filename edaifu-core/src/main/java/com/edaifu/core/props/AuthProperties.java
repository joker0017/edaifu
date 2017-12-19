package com.edaifu.core.props;

/**
 * 授权  登录管理模块的系统配置
 * @author JOKER
 *
 */
public class AuthProperties {
	
	private String loginPage = "/login.html";

	private String loginSuccessPage = "/index.html";

	private LoginType loginType = LoginType.JSON;
	
	private int rememberMeSeconds = 3600;

	public String getLoginSuccessPage() {
		return loginSuccessPage;
	}

	public void setLoginSuccessPage(String loginSuccessPage) {
		this.loginSuccessPage = loginSuccessPage;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}


}
