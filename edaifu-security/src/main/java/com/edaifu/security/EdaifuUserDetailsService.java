/**
 * 
 */
package com.edaifu.security;

import com.edaifu.db.pojo.SysAccountBiz;
import com.edaifu.db.repository.SysAccountBizRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * @author JOKER
 *
 */
@Component
class EdaifuUserDetailsService implements UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(EdaifuUserDetailsService.class);

	@Autowired
	private SysAccountBizRepository sysAccountBizRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysAccountBiz sysAccountBiz = sysAccountBizRepository.findOne(username);
		if(sysAccountBiz==null) {
			throw new UsernameNotFoundException("该用户不存在");
		}
		return sysAccountBiz;
	}

}
