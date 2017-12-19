/**
 * 
 */
package com.edaifu.validate.code.sms;

import com.edaifu.security.authentication.mobile.SmsAuthenticationFilter;
import com.edaifu.security.authentication.mobile.SmsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


/**
 * @author JOKER
 *
 */
@Component
public class SmsCodeAuthenticationSecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	@Autowired
	private AuthenticationSuccessHandler securityAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler securityAuthenticationFailureHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
		smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		smsAuthenticationFilter.setAuthenticationSuccessHandler(securityAuthenticationSuccessHandler);
		smsAuthenticationFilter.setAuthenticationFailureHandler(securityAuthenticationFailureHandler);
		
		SmsAuthenticationProvider authenticationProvider = new SmsAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		
		http.authenticationProvider(authenticationProvider).addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
