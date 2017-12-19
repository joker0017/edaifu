package com.edaifu.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysRoleBizDTO implements java.io.Serializable {

	private String roleId;
	private String roleName;
	private String roleCode;
	private String roleDesc;
	private String proleId;
	private String rightId;
	private Integer roleType;
	private Integer roleEnabled;

}