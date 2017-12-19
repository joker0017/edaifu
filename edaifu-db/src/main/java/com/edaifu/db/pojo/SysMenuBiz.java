package com.edaifu.db.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_menu_bus")
@Getter
@Setter
public class SysMenuBiz implements java.io.Serializable {

	@Id
	private String menuid;
	private String menuCode;
	private String superMenuCode;
	private String href;
	private String iClass;
	private String spanValue;
	private Integer sortid;

}