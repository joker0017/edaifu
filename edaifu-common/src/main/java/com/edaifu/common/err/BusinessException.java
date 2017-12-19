package com.edaifu.common.err;

import java.util.HashMap;
import java.util.Map;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 2760262957562783066L;
	private String terrcode;
	private Object[] args;
	private Map<String, Object> errFields = new HashMap<String, Object>();

	public BusinessException(String terrcode, String msg, Object[] args) {
		super(msg);
		this.terrcode = terrcode;
		this.args = args;
	}

	public BusinessException(String terrcode, String msg) {
		super(msg);
		this.terrcode = terrcode;
	}

	public BusinessException(String terrcode, String errFieldName, String msg,
			Object[] args) {
		super(msg);
		this.terrcode = terrcode;
		this.errFields.put(errFieldName, msg);
		this.args = args;
	}

	public String getTerrcode() {
		return this.terrcode;
	}

	public void setTerrcode(String terrcode) {
		this.terrcode = terrcode;
	}

	public Object[] getArgs() {
		return this.args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Map<String, Object> getErrFields() {
		return this.errFields;
	}

	public void setErrFields(Map<String, Object> errFields) {
		this.errFields = errFields;
	}
}