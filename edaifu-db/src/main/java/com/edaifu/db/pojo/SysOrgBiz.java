package com.edaifu.db.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "sys_org_bus" )
@Getter
@Setter
public class SysOrgBiz implements java.io.Serializable {


	@Id
	private String orgid;
	private String orgnm;
	private String porgid;
	private String email;
	private String remarks;
	private Timestamp createTime;

	@OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(name = "porgid",referencedColumnName = "orgid")
	private Set<SysOrgBiz> childOrgs = new HashSet<>();

}