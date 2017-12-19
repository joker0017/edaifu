package com.edaifu.db.base;

public class No implements Cloneable {
	private String noid;
	private String noname;
	private int nolength;
	private int notype;
	private String prefix;
	private String postfix;

	public String getNoid() {
		return this.noid;
	}

	public String getNoname() {
		return this.noname;
	}

	public int getNolength() {
		return this.nolength;
	}

	public int getNotype() {
		return this.notype;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public String getPostfix() {
		return this.postfix;
	}

	public void setNoid(String noid) {
		this.noid = noid;
	}

	public void setNoname(String noname) {
		this.noname = noname;
	}

	public void setNolength(int nolength) {
		this.nolength = nolength;
	}

	public void setNotype(int notype) {
		this.notype = notype;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println(o);
		}
		return o;
	}
}