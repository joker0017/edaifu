package com.edaifu.db.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edaifu.db.base.SequenceService;
import com.edaifu.db.base.Sql;
import static com.edaifu.db.base.Sql.*;
import com.edaifu.db.base.TXBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import static com.edaifu.db.base.Sql.field;


/**
 * 自动化工具生成数据库操作类
 * 表名:sys_account_bus
 * 主键:
 * account_id
 **/
@Repository
public class SysAccountBizDao extends TXBaseDao {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SequenceService sequenceService;

	/**当前所有字段名**/
	String[] fieldNames=new String[]{"account_id","password","status","inv_date","name","roleids","orgid","groupids","tel","phone","email","address","sex","position"};
	/**当前主键(包括多主键)**/
	String[] primaryKeys=new String[]{"account_id"};
	
	@Override
	public int save(Map<String,Object> p) {	
				if(p.get("password")!=null){
					p.put("password",passwordEncoder.encode((String) p.get("password")));
				}
				p.put("account_id",sequenceService.getTableFlowNo("sys_account_bus", "account_id"));

		String sql= Sql.create("insert into sys_account_bus (")
				.append(field("account_id "))
				.append(field("password ",hasKey(p,"password")))
				.append(field("status ",hasKey(p,"status")))
				.append(field("inv_date ",hasKey(p,"inv_date")))
				.append(field("name ",hasKey(p,"name")))
				.append(field("roleids ",hasKey(p,"roleids")))
				.append(field("orgid ",hasKey(p,"orgid")))
				.append(field("groupids ",hasKey(p,"groupids")))
				.append(field("tel ",hasKey(p,"tel")))
				.append(field("phone ",hasKey(p,"phone")))
				.append(field("email ",hasKey(p,"email")))
				.append(field("address ",hasKey(p,"address")))
				.append(field("sex ",hasKey(p,"sex")))
				.append(field("position ",hasKey(p,"position")))
				.append(") values (")
				.append(field(":account_id "))
				.append(field(":password ",hasKey(p,"password")))
				.append(field(":status ",hasKey(p,"status")))
				.append(field(":inv_date ",hasKey(p,"inv_date")))
				.append(field(":name ",hasKey(p,"name")))
				.append(field(":roleids ",hasKey(p,"roleids")))
				.append(field(":orgid ",hasKey(p,"orgid")))
				.append(field(":groupids ",hasKey(p,"groupids")))
				.append(field(":tel ",hasKey(p,"tel")))
				.append(field(":phone ",hasKey(p,"phone")))
				.append(field(":email ",hasKey(p,"email")))
				.append(field(":address ",hasKey(p,"address")))
				.append(field(":sex ",hasKey(p,"sex")))
				.append(field(":position ",hasKey(p,"position")))
				.append(")")
				.toString();
		
			return save(sql, p);
		
	}
	
	
	public int savein(Map<String,Object> p) {			
		//p.put("account_id",sequenceService.getTableFlowNo("sys_account_bus", "account_id"));
		if(p.get("password")!=null){
			p.put("password",passwordEncoder.encode((String) p.get("password")));
		}
		String sql=Sql.create("insert into sys_account_bus (")
				.append(field("account_id "))
				.append(field("password ",hasKey(p,"password")))
				.append(field("status ",hasKey(p,"status")))
				.append(field("inv_date ",hasKey(p,"inv_date")))
				.append(field("name ",hasKey(p,"name")))
				.append(field("roleids ",hasKey(p,"roleids")))
				.append(field("orgid ",hasKey(p,"orgid")))
				.append(field("groupids ",hasKey(p,"groupids")))
				.append(field("tel ",hasKey(p,"tel")))
				.append(field("phone ",hasKey(p,"phone")))
				.append(field("email ",hasKey(p,"email")))
				.append(field("address ",hasKey(p,"address")))
				.append(field("sex ",hasKey(p,"sex")))
				.append(field("position ",hasKey(p,"position")))
				.append(field("logname ",hasKey(p,"logname")))
				.append(field("trunks ",hasKey(p,"trunks")))
				.append(") values (")
				.append(field(":account_id "))
				.append(field(":password ",hasKey(p,"password")))
				.append(field(":status ",hasKey(p,"status")))
				.append(field(":inv_date ",hasKey(p,"inv_date")))
				.append(field(":name ",hasKey(p,"name")))
				.append(field(":roleids ",hasKey(p,"roleids")))
				.append(field(":orgid ",hasKey(p,"orgid")))
				.append(field(":groupids ",hasKey(p,"groupids")))
				.append(field(":tel ",hasKey(p,"tel")))
				.append(field(":phone ",hasKey(p,"phone")))
				.append(field(":email ",hasKey(p,"email")))
				.append(field(":address ",hasKey(p,"address")))
				.append(field(":sex ",hasKey(p,"sex")))
				.append(field(":position ",hasKey(p,"position")))
				.append(field(":trunks ",hasKey(p,"trunks")))
				.append(field(":logname ",hasKey(p,"logname")))
				.append(")")
				.toString();
		printSql(sql,p);
		return save(sql, p);
	}
	
	
	
	/***
	 * 根据主键删除一条数据.
	 * 主键为必输项
	 **/
	@Override
	public int deleteByPk(Map<String,Object> p) {		
		String sql=Sql.create("delete from sys_account_bus where 1=1 ")
				.append(and("account_id = :account_id"))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}
	
	/***
	 * 删除一条或者多条数据,慎用此函数.
	 * 此函数当传入的条件为空的时候,有可能会导致整张数据表被删除,因此,在执行此函数前,请一定对传入的参数进行非空检验,以防数据被误删.
	 * 项目组务必对使用此函数的代码进行代码走查,防止漏洞发生,防止被攻击.
	 **/
	@Override
	public int delete(Map<String,Object> p) {
		checkParameter(p,primaryKeys,fieldNames);		
		String sql=Sql.create("delete from sys_account_bus where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("password = :password",hasKey(p,"password")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("inv_date = :inv_date",hasKey(p,"inv_date")))
				.append(and("name = :name",hasKey(p,"name")))
				.append(and("roleids = :roleids",hasKey(p,"roleids")))
				.append(and("orgid = :orgid",hasKey(p,"orgid")))
				.append(and("groupids = :groupids",hasKey(p,"groupids")))
				.append(and("tel = :tel",hasKey(p,"tel")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("email = :email",hasKey(p,"email")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("position = :position",hasKey(p,"position")))
				.toString();
		 printSql(sql,p);
		return delete(sql, p);
	}	

	@Override
	public int updateByPk(Map<String,Object> p) {
		if(p.get("password")!=null){
			p.put("password",passwordEncoder.encode((String) p.get("password")));
		}
		String sql=Sql.create("update sys_account_bus set ")
				.append(field("password = :password",hasKey(p,"password")))
				.append(field("status = :status",hasKey(p,"status")))
				.append(field("inv_date = :inv_date",hasKey(p,"inv_date")))
				.append(field("name = :name",hasKey(p,"name")))
				.append(field("roleids = :roleids",hasKey(p,"roleids")))
				.append(field("orgid = :orgid",hasKey(p,"orgid")))
				.append(field("groupids = :groupids",hasKey(p,"groupids")))
				.append(field("tel = :tel",hasKey(p,"tel")))
				.append(field("phone = :phone",hasKey(p,"phone")))
				.append(field("email = :email",hasKey(p,"email")))
				.append(field("address = :address",hasKey(p,"address")))
				.append(field("sex = :sex",hasKey(p,"sex")))
				.append(field("position = :position",hasKey(p,"position")))
				.append(field("logname = :logname",hasKey(p,"logname")))
				.append(field("trunks = :trunks",hasKey(p,"trunks")))
				.append(" where 1=1 ")
				.append(and("account_id = :account_id"))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}
	
	public List<Map<String, Object>> queryAuthorizeList(Map<String,Object> p) {
		String sql="select a.account_id,a.name, o.orgnm from sys_account_bus a, sys_org o where 1=1 and a.account_id not in("+p.get("ids")+") and a.orgid=o.orgid";
		printSql(sql,p);
		return queryForList(sql, p);
	}
	
	@Override
	public int update(Map<String,Object> p) {
		if(p.get("password")!=null){
			p.put("password",passwordEncoder.encode((String) p.get("password")));
		}
		checkParameter(p,primaryKeys,fieldNames);	
		String sql=Sql.create("update sys_account_bus set ")
				.append(field("password = :password",hasKey(p,"password")))
				.append(field("status = :status",hasKey(p,"status")))
				.append(field("inv_date = :inv_date",hasKey(p,"inv_date")))
				.append(field("name = :name",hasKey(p,"name")))
				.append(field("roleids = :roleids",hasKey(p,"roleids")))
				.append(field("orgid = :orgid",hasKey(p,"orgid")))
				.append(field("groupids = :groupids",hasKey(p,"groupids")))
				.append(field("tel = :tel",hasKey(p,"tel")))
				.append(field("phone = :phone",hasKey(p,"phone")))
				.append(field("email = :email",hasKey(p,"email")))
				.append(field("address = :address",hasKey(p,"address")))
				.append(field("sex = :sex",hasKey(p,"sex")))
				.append(field("position = :position",hasKey(p,"position")))
				.append(" where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("password = :password",hasKey(p,"password")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("inv_date = :inv_date",hasKey(p,"inv_date")))
				.append(and("name = :name",hasKey(p,"name")))
				.append(and("roleids = :roleids",hasKey(p,"roleids")))
				.append(and("orgid = :orgid",hasKey(p,"orgid")))
				.append(and("groupids = :groupids",hasKey(p,"groupids")))
				.append(and("tel = :tel",hasKey(p,"tel")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("email = :email",hasKey(p,"email")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("position = :position",hasKey(p,"position")))
				.toString();
		printSql(sql,p);
		return update(sql, p);
	}
	

	@Override
	public int saveOrUpdate(Map<String,Object> p) {
		
		return 0;
	}

	@Override
	public List<Map<String, Object>> queryForList(Map<String,Object> p) {
		String sql=Sql.create("select * from sys_account_bus where 1=1")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("password = :password",hasKey(p,"password")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("inv_date = :inv_date",hasKey(p,"inv_date")))
				.append(and("name = :name",hasKey(p,"name")))
				.append(and("roleids = :roleids",hasKey(p,"roleids")))
				.append(and("orgid = :orgid",hasKey(p,"orgid")))
				.append(and("groupids like :groupid",hasKey(p,"groupid")))
				.append(and("tel = :tel",hasKey(p,"tel")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("email = :email",hasKey(p,"email")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("position = :position",hasKey(p,"position")))
				//.append(orderBySql())
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}
	
	/**
	 * 需要进行扣量的引流用户查询
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryForListCl(Map<String,Object> p) {
		String sql=Sql.create("select * from sys_account_bus where 1=1")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("password = :password",hasKey(p,"password")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("inv_date = :inv_date",hasKey(p,"inv_date")))
				.append(and("name = :name",hasKey(p,"name")))
				.append(and("roleids = :roleids",hasKey(p,"roleids")))
				.append(and("orgid = :orgid",hasKey(p,"orgid")))
				.append(and("groupids like :groupid",hasKey(p,"groupid")))
				.append(and("tel = :tel",hasKey(p,"tel")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("email = :email",hasKey(p,"email")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("position = :position",hasKey(p,"position")))
				.append(" and trunks > 0 ")
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p); 
	}
	
	
	/**
	 * 找出存在roleid的参数
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryForListLocate(Map<String,Object> p) {
		String sql=Sql.create("select * from sys_account_bus where 1=1")
				.append(and(" LOCATE(:roleids,roleids)",hasKey(p,"roleids")))
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}
	
	public List<Map<String, Object>> queryForListGroup(Map<String,Object> p) {
		String sql=Sql.create("select groupids from sys_account_bus where 1=1")
				.append(and(" groupids <> ''"))
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}
	
	
	public List<Map<String, Object>> queryForListOrgid(Map<String,Object> p) {
		String sql=Sql.create("select name,orgid,account_id from sys_account_bus where 1=1")
				.append(and(" orgid <> ''"))
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}
	
	public List<Map<String, Object>> queryInorgidcheck(Map<String,Object> p) {
		String sql=Sql.create("select st.account_id,st.name ,isnull(tn.account_id) as checked  from sys_account_bus st LEFT JOIN t_fileassign tn on st.account_id=tn.account_id and tn.type='1'")
				.append(and("tn.fileid= :fileid",hasKey(p, "fileid")))
				.append("where 1=1")
				.append(and("st.orgid = :orgid",hasKey(p,"orgid")))
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}
	
	
	public List<Map<String, Object>> queryInfo(Map<String,Object> p) {
		String sql="select a.account_id,a.name,o.orgnm from sys_account_bus a, sys_org o where 1=1 and  a.orgid=o.orgid";
		printSql(sql,p);
		return queryForList(sql, p);
	}
	
	/*
	 * by duzx
	 * desc 针对cus010接口进行查询操作
	 */
	public Map<String, Object> selectacc(Map<String,Object> p) {
		String sql=Sql.create("select sa.name as account_nm,sa.email,so.orgnm")
				.append("from sys_account_bus sa, sys_org so")
				.append("where sa.orgid = so.orgid")
				.append("and sa.account_id = :account")
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}
	
	public Map<String, Object> queryForNameById(Map<String,Object> p) {
		Map<String,Object> map=new HashMap();
		String sql=Sql.create("select name from sys_account_bus where 1=1")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.toString();
		 printSql(sql,p);
		List<Map<String, Object>> list = queryForList(sql, p);
		if(list.size()>0){
			return list.get(0);
		}
		return map;
	}
	

	
	public List<Map<String, Object>> queryForListDev(Map<String,Object> p) {
		String sql=Sql.create("select * from sys_account_bus where 1=1")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("password = :password",hasKey(p,"password")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("inv_date = :inv_date",hasKey(p,"inv_date")))
				.append(and("name like :name",hasKey(p,"name")))
				.append(and("roleids = :roleids",hasKey(p,"roleids")))
				.append(and("orgid = :orgid",hasKey(p,"orgid")))
				.append(and("groupids = :groupids",hasKey(p,"groupids")))
				.append(and("tel = :tel",hasKey(p,"tel")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("email = :email",hasKey(p,"email")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("position = :position",hasKey(p,"position")))
				.toString();
		 printSql(sql,p);
		return queryForList(sql, p);
	}
	
	@Override
	public List<Map<String, Object>> queryForListByPage(Map<String,Object> p) {			
//		Sql sql=Sql.create("select * from sys_account_bus where 1=1")
//			.append(and("account_id = :account_id",hasKey(p,"account_id")))
//						.append(and("password = :password",hasKey(p,"password")))
//						.append(and("status = :status",hasKey(p,"status")))
//						.append(and("inv_date = :inv_date",hasKey(p,"inv_date")))
//						.append(and("name = :name",hasKey(p,"name")))
//						.append(and("roleids = :roleids",hasKey(p,"roleids")))
//						.append(and("orgid = :orgid",hasKey(p,"orgid")))
//						.append(and("groupids = :groupids",hasKey(p,"groupids")))
//						.append(and("tel = :tel",hasKey(p,"tel")))
//						.append(and("phone = :phone",hasKey(p,"phone")))
//						.append(and("email = :email",hasKey(p,"email")))
//						.append(and("address = :address",hasKey(p,"address")))
//						.append(and("sex = :sex",hasKey(p,"sex")))
//						.append(and("position = :position",hasKey(p,"position")))
//			;
//
//		String sqlStr=sql.toString();
//		printSql(sqlStr,p);
//		long count = count("select count(*) from ("+sqlStr +")  as t123321", p);
//		PageInfo pageInf = MfpContextHolder.getPageInfo();
//		pageInf.setITotalDisplayRecords(count);
//		MfpContextHolder.setPageInfo(pageInf);
//		sql.append(orderBySql()).append(pageSql());
//		sqlStr=sql.toString();
//		printSql(sqlStr,p);
//		return queryForList(sqlStr, p);
		return null;
	}
	
	/**
	 * 查询一条唯一记录,如果没有查到或者查询到两条以上记录会报异常,服务层应该扑捉此类异常进行特别处理.
	 *
	 ***/
	@Override
	public Map<String, Object> queryForMap(Map<String,Object> p) {
		String sql=Sql.create("select * from sys_account_bus where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("password = :password",hasKey(p,"password")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("inv_date = :inv_date",hasKey(p,"inv_date")))
				.append(and("name like :name",hasKey(p,"name")))
				.append(and("roleids = :roleids",hasKey(p,"roleids")))
				.append(and("orgid = :orgid",hasKey(p,"orgid")))
				.append(and("groupids = :groupids",hasKey(p,"groupids")))
				.append(and("tel = :tel",hasKey(p,"tel")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("email = :email",hasKey(p,"email")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("position = :position",hasKey(p,"position")))
				.toString();
		printSql(sql,p);
		return queryForMap(sql, p);
	}

	public Map<String, Object> queryForMapExist(Map<String,Object> p) {
		String sql=Sql.create("select * from sys_account_bus where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("password = :password",hasKey(p,"password")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("inv_date = :inv_date",hasKey(p,"inv_date")))
				.append(and("name like :name",hasKey(p,"name")))
				.append(and("roleids = :roleids",hasKey(p,"roleids")))
				.append(and("orgid = :orgid",hasKey(p,"orgid")))
				.append(and("groupids = :groupids",hasKey(p,"groupids")))
				.append(and("tel = :tel",hasKey(p,"tel")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("email = :email",hasKey(p,"email")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("position = :position",hasKey(p,"position")))
				.toString();
		printSql(sql,p);
		try {
			return queryForMap(sql, p);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public long count(Map<String,Object> p) {
		String sql = Sql.create("select count(*) from sys_account_bus where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("password = :password",hasKey(p,"password")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("inv_date = :inv_date",hasKey(p,"inv_date")))
				.append(and("name = :name",hasKey(p,"name")))
				.append(and("roleids = :roleids",hasKey(p,"roleids")))
				.append(and("orgid = :orgid",hasKey(p,"orgid")))
				.append(and("groupids = :groupids",hasKey(p,"groupids")))
				.append(and("tel = :tel",hasKey(p,"tel")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("email = :email",hasKey(p,"email")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("position = :position",hasKey(p,"position")))
				.toString();
		printSql(sql,p);
		return count(sql, p);	
	}
	
	public long countbyorgid(Map<String,Object> p) {
		String sql = Sql.create("select count(orgid) from sys_account_bus where 1=1 ")
				.append(and("account_id = :account_id",hasKey(p,"account_id")))
				.append(and("password = :password",hasKey(p,"password")))
				.append(and("status = :status",hasKey(p,"status")))
				.append(and("inv_date = :inv_date",hasKey(p,"inv_date")))
				.append(and("name = :name",hasKey(p,"name")))
				.append(and("roleids = :roleids",hasKey(p,"roleids")))
				.append(and("orgid = :orgid",hasKey(p,"orgid")))
				.append(and("groupids = :groupids",hasKey(p,"groupids")))
				.append(and("tel = :tel",hasKey(p,"tel")))
				.append(and("phone = :phone",hasKey(p,"phone")))
				.append(and("email = :email",hasKey(p,"email")))
				.append(and("address = :address",hasKey(p,"address")))
				.append(and("sex = :sex",hasKey(p,"sex")))
				.append(and("position = :position",hasKey(p,"position")))
				.toString();
		printSql(sql,p);
		return count(sql, p);	
	}
	



	public static int STATUS = 1;//状态（0无效1有效）
	public static String IS_ONLINE = "1";//是否在线
	//查询风控在线并且是有效岗位的审批人员
	public List<Map<String,Object>> queryForLists(Map<String,Object> p){
		Sql sql = Sql.create("SELECT s.account_id,s.`status`,c.is_online,s.name FROM sys_account_bus s "
				+ "LEFT JOIN cli_account c ON s.account_id = c.account_id WHERE 1=1")
				.append(and("s.roleids = :roleids",hasKey(p,"roleids")))
				.append(and("s.status = :status",hasKey(p,"status")))
				.append(and("c.is_online = :is_online",hasKey(p,"is_online")))
				;
		String sqlStr = sql.toString(); 
		printSql(sqlStr,p);
		return queryForList(sqlStr, p);
	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	public List<Map<String, Object>> queryForListByOrgid( Map<String, Object> p) {
			Sql sql = Sql.create("select * from sys_account_bus where 1=1 ")
						 .append(and("orgid in ("+p.get("orgs")+")",hasKey(p,"orgs")));
			String sqlStr = sql.toString(); 
			printSql(sqlStr,p);
			return queryForList(sqlStr, p);
	}
}
