package com.edaifu.db.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;

import java.util.Map;

public class Sql {
	static Log log = LogFactory.getLog(Sql.class);
	public StringBuffer sqlBuffer = new StringBuffer();

	public static Sql create(String sql) {
		Sql sqlInstance = new Sql();
		sqlInstance.sqlBuffer.append(sql);
		return sqlInstance;
	}

	public Sql append(String sql) {
		this.sqlBuffer.append(sql);
		return this;
	}

	public static boolean isExistNull(Object... values) {
		if (values == null)
			return true;

		for (int i = 0; i < values.length; ++i)
			if (isNull(values[i]))
				return true;

		return false;
	}

	public static boolean isNull(Object value) {
		if (value == null) {
			return true;
		}

		return ((value instanceof String) && ("".equals(((String) value).trim())));
	}

	public static String and(String sql, Object... values) {
		if ((isExistNull(values)) || (isNull(sql)))
			return "";

		return " and (" + sql + ")";
	}

	public static String and(String sql) {
		if (isNull(sql))
			return "";

		return " and (" + dofilter(sql) + ")";
	}

	public static String or(String sql, Object... values) {
		if ((isExistNull(values)) || (isNull(sql)))
			return "";

		return " or (" + sql + ") ";
	}

	public static String or(String sql) {
		if (isNull(sql))
			return "";

		return " or (" + dofilter(sql) + ") ";
	}

	public static String field(String sql, Object... values) {
		if ((isExistNull(values)) || (isNull(sql)))
			return "";

		return "," + sql;
	}

	public static String dofilter(String sql) {
		if (isNull(sql))
			return "";

		String tempSql = sql.toLowerCase();
		String trimSql = tempSql.trim();
		if (trimSql.startsWith("and")) {
			int indexAnd = tempSql.indexOf("and");
			sql = sql.substring(indexAnd);
		} else if (trimSql.startsWith("or")) {
			int indexOr = tempSql.indexOf("or");
			sql = sql.substring(indexOr);
		}
		return sql;
	}

	public String toString() {
		String sql = this.sqlBuffer.toString();

		sql = sql.replace("(,", "(");
		sql = sql.replace("set ,", "set ");

		return sql;
	}

	public void printSql(Map<String, ?> p) {
		if (log.isDebugEnabled()) {
			String sql = this.sqlBuffer.toString();
			String sqlWithParamters = NamedParameterUtils.substituteNamedParameters(sql, new MapSqlParameterSource(p));
			log.debug(sqlWithParamters);
		}
	}

	public static void printSql(String sql, Map<String, ?> p) {
		if (log.isDebugEnabled()) {
			String sqlWithParamters = NamedParameterUtils.substituteNamedParameters(sql, new MapSqlParameterSource(p));
			log.debug(sqlWithParamters);
		}
	}

	public static Object hasKey(Map map, String key) {
		return ((map.containsKey(key)) ? "ok" : null);
	}

}