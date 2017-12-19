package com.edaifu.db.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "sys_account_bus")
@Getter
@Setter
public class SysAccountBiz implements Serializable,UserDetails{

	@Id
	private String accountId;
	private String password;
	private Integer status;
	private Date invDate;
	private String name;
	private String roleids;
	private String orgid;
	private String groupids;
	private String tel;
	private String phone;
	private String email;
	private String address;
	private Integer sex;
	private String position;
	private String logname;
	private String trunks;


	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinTable(name = "sys_account_role_biz",
			joinColumns = {@JoinColumn(name = "account_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")})
	private Set<SysRoleBiz> roles = new HashSet<SysRoleBiz>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		for (SysRoleBiz role : roles) {
			GrantedAuthority grantedAuthority = role.generateGrantedAuthority();
			list.add(grantedAuthority);
			for (SysPermissionBiz permission : role.getPermissions()) {
				list.add(permission);
			}
		}
		return list;
	}

	@Override
	public String getUsername() {
		return accountId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}