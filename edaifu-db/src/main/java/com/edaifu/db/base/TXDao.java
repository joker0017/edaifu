package com.edaifu.db.base;

import java.util.List;
import java.util.Map;

public abstract interface TXDao
{
  public abstract int save(Map<String, Object> paramMap);

  public abstract int delete(Map<String, Object> paramMap);

  public abstract int update(Map<String, Object> paramMap);

  public abstract int saveOrUpdate(Map<String, Object> paramMap);

  public abstract int[] batchUpdate(List<Map<String, Object>> paramList);

  public abstract int[] batchSave(List<Map<String, Object>> paramList);

  public abstract Map<String, Object> queryForMap(Map<String, Object> paramMap);

  public abstract long count(Map<String, Object> paramMap);

  public abstract List<Map<String, Object>> queryForListByPage(Map<String, Object> paramMap);

  public abstract List<Map<String, Object>> queryForList(Map<String, Object> paramMap);

  public abstract <T> T queryForObject(Map<String, Object> paramMap, Class<T> paramClass);

  public abstract <T> List<T> queryForList(Map<String, Object> paramMap, Class<T> paramClass);

  public abstract <T> List<T> queryForListByPage(Map<String, Object> paramMap, Class<T> paramClass);

  public abstract int deleteByPk(Map<String, Object> paramMap);

  public abstract int updateByPk(Map<String, Object> paramMap);
}