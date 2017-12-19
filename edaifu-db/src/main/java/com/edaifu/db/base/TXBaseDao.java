package com.edaifu.db.base;


import com.edaifu.core.utils.BaseUtils;
import jdk.nashorn.internal.scripts.JD;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Log4j
@Repository
public abstract class TXBaseDao implements TXDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public NamedParameterJdbcTemplate getJdbcTemplateForMap() {
		return new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	protected int save(String sql, Map<String, Object> p) {
		return getJdbcTemplateForMap().update(sql, p);
	}

	protected int delete(String sql, Map<String, Object> p) {
		return getJdbcTemplateForMap().update(sql, p);
	}

	protected int update(String sql, Map<String, Object> p) {
		return getJdbcTemplateForMap().update(sql, p);
	}

	protected int[] batchUpdate(String sql, List<Map<String, Object>> batchValues) {
		return getJdbcTemplateForMap().batchUpdate(sql, (Map[]) batchValues.toArray());
	}

	protected List<Map<String, Object>> queryForList(String sql, Map<String, Object> p) {
		return getJdbcTemplateForMap().queryForList(sql, p);
	}

	protected List<Map<String, Object>> queryForListByPage(String sql, Map<String, Object> p) {
		return getJdbcTemplateForMap().queryForList(sql, p);
	}

	protected Map<String, Object> queryForMap(String sql, Map<String, Object> p) {
		return getJdbcTemplateForMap().queryForMap(sql, p);
	}

	protected long count(String sql, Map<String, Object> p) {
		Number n = (Number) getJdbcTemplateForMap().queryForObject(sql, p, new RowMapper() {
					public Number mapRow(ResultSet rs, int arg1) throws SQLException {
						ResultSetMetaData rsmd = rs.getMetaData();
						int nrOfColumns = rsmd.getColumnCount();
						if (nrOfColumns != 1) 
							throw new IncorrectResultSetColumnCountException(1, nrOfColumns);
						return ((Number) JdbcUtils.getResultSetValue(rs, 1, Number.class));
					}
				});
		return ((n == null) ? 0 : n.longValue());
	}

	protected <T> T queryForObject(String sql, Map<String, Object> p,
			Class<T> requiredType) {
		return getJdbcTemplateForMap().queryForObject(sql, p, requiredType);
	}

	protected <T> List<T> queryForList(String sql, Map<String, Object> p, RowMapper<T> rowMapper) {
		return getJdbcTemplateForMap().query(sql, rowMapper);
	}

	public <T> List<T> queryForList(Map<String, Object> p, Class<T> requiredType) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public <T> List<T> queryForListByPage(Map<String, Object> p,
			Class<T> requiredType) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public <T> T queryForObject(Map<String, Object> p, Class<T> requiredType) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public int save(Map<String, Object> p) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public int deleteByPk(Map<String, Object> p) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public int updateByPk(Map<String, Object> p) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public int delete(Map<String, Object> p) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public int update(Map<String, Object> p) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public int saveOrUpdate(Map<String, Object> p) {
		if (count(p) > 0)
			return update(p);
		return save(p);
	}

	public int[] batchSave(List<Map<String, Object>> batchValues) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public int[] batchUpdate(List<Map<String, Object>> batchValues) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public List<Map<String, Object>> queryForList(Map<String, Object> p) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public List<Map<String, Object>> queryForListByPage(Map<String, Object> p) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public Map<String, Object> queryForMap(Map<String, Object> p) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public long count(Map<String, Object> p) {
		throw new RuntimeException("此函数为适配函数，不允许直接调用，如果需要调用，请在子类进行覆盖重写");
	}

	public static Map<String, Object> map(Object[] keyAndValues) {
		return BaseUtils.map(keyAndValues);
	}

	public boolean checkParameter(Map<String, Object> p, String[] primaryKeys,
			String[] fieldNames) {
		if ((p == null) || (p.isEmpty())) {
			throw new RuntimeException(
					"传入到当前Dao的参数为空,如果调用了update或者delete函数,将可能导致整张表数据表被更新或者删除,属于高危代码,请安排检查代码,保证调用更新操作前,代码对必输域做了控制!");
		}

		for (int i = 0; i < primaryKeys.length; ++i)
			if (p.containsKey(primaryKeys[i]))
				return true;

		this.log.warn("传入到当前Dao的参数不为空,但是没有主键数据,如果调用了update或者delete函数,存在将整张数据表被更新或者删除的可能性,请安排检查代码,保证调用更新操作前,代码对必输域做了控制!");
		for (int i = 0; i < fieldNames.length; ++i)
			if (p.containsKey(fieldNames[i]))
				return true;

		throw new RuntimeException(
				"传入到当前Dao的参数不为空,但都不是sql中已定义的参数,如果调用了update或者delete函数,将可能导致整张表数据表被更新或者删除,属于高危代码,请安排检查代码,保证调用更新操作前,代码对必输域做了控制!");
	}
}