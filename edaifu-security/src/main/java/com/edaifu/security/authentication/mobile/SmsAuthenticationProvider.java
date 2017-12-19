
package com.edaifu.security.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsAuthenticationProvider implements AuthenticationProvider {

	private UserDetailsService userDetailsService;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken)authentication;
		UserDetails user = userDetailsService.loadUserByUsername((String)smsAuthenticationToken.getPrincipal());
		if(user==null) {
			throw new InternalAuthenticationServiceException("无法获取到用户信息");
		}
		SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(user, user.getAuthorities());
		authenticationResult.setDetails(smsAuthenticationToken.getDetails());
		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
}
