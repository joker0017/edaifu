package com.edaifu.db.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role_bus")
@Getter
@Setter
public class SysRoleBiz implements java.io.Serializable {


	@Id
	private String roleId;
	private String roleName;
	private String roleCode;
	private String roleDesc;
	private String proleId;
	private String rightId;
	private Integer roleType;
	private Integer roleEnabled;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_permission_biz",
			joinColumns = {@JoinColumn(name = "role_id")},
			inverseJoinColumns = {@JoinColumn(name = "permission_id")})
	private Set<SysPermissionBiz> permissions = new HashSet<SysPermissionBiz>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinTable(name = "sys_account_role_biz",
			joinColumns = {@JoinColumn(name = "role_id")},
			inverseJoinColumns = {@JoinColumn(name = "account_id")})
	private Set<SysAccountBiz> users = new HashSet<SysAccountBiz>();

	public GrantedAuthority generateGrantedAuthority() {
		return new GrantedAuthority() {
			public String getAuthority() {
				return roleCode;
			}
		};
	}
}