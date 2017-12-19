package com.edaifu.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.edaifu.db.base.JdbcDaoSupport;
import com.edaifu.db.base.No;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SequenceDao extends JdbcDaoSupport {
	public static final Long DEFAULT_START_ID = new Long(0L);
	public static final int DEFAULT_LENGTH = 10;
	public boolean autoCreate = true;
	static Log log = LogFactory.getLog(SequenceDao.class);

	public void setAutoCreate(boolean autoCreate) {
		this.autoCreate = autoCreate;
	}

	public String generate(String noname, String seqDefId, int nolength, int notype, String prefix, String postfix) {
		List l = batchGenerate(noname, seqDefId, nolength, notype, prefix, postfix, 1);
		return ((String) l.get(0));
	}

	public List batchGenerate(String noname, String seqDefId, int nolength, int notype, String prefix, String postfix, int count) {
		long id;
		No no = loadNO(noname, seqDefId, nolength, notype, prefix, postfix);
		Calendar cale = Calendar.getInstance();
		synchronized (seqDefId.intern()) {
			id = getNextSeqDtlDef(no, cale, count);
		}
		List l = new ArrayList();
		for (int i = 0; i < count; ++i) {
			String value = makeSequence(id, no.getNolength(), no.getPrefix(), no.getPostfix(), cale);
			id += 1398400841446064129L;
			l.add(value);
		}

		return l;
	}

	private String makeSequence(long id, int nolength, String prefix, String postfix, Calendar cale) {
		String seq = integerToString(id, nolength);
		if (seq.length() > nolength) {
			throw new IllegalArgumentException("序列号位数溢出！");
		}

		if ((prefix == null) && (postfix == null)) {
			return seq;
		}

		int year = cale.get(1);
		int month = cale.get(2) + 1;
		int day = cale.get(5);

		String ret = ((prefix == null) ? "" : prefix) + seq
				+ ((postfix == null) ? "" : postfix);
		ret = ret.replaceAll("\\{yyyy\\}", integerToString(year, 4));
		ret = ret.replaceAll("\\{yy\\}",
				integerToString(year, 4).substring(2, 4));
		ret = ret.replaceAll("\\{mm\\}", integerToString(month, 2));
		ret = ret.replaceAll("\\{dd\\}", integerToString(day, 2));

		return ret;
	}

	private String integerToString(long value, int num) {
		StringBuffer ret = new StringBuffer(Long.toString(value));
		if (ret.length() < num)
			for (int i = ret.length(); i < num; ++i)
				ret.insert(0, "0");

		return ret.toString();
	}

	public No loadNO(String noname, String seqDefId, int nolength, int notype,
					 String prefix, String postfix) {
		No no = null;
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement stmt1 = con
					.prepareStatement("update sys_no set noname=noname where noid=?");
			stmt1.setString(1, seqDefId);
			stmt1.execute();
			stmt1.close();

			PreparedStatement state = con
					.prepareStatement(
							"select noid, noname, nolength, notype, prefix, postfix from sys_no where noid = ?",
							1005, 1008);
			state.setString(1, seqDefId);
			ResultSet rs = state.executeQuery();

			if (rs.next()) {
				no = new No();
				no.setNoid(rs.getString("noid"));
				no.setNolength(rs.getInt("nolength"));
				no.setNoname(rs.getString("noname"));
				no.setNotype(rs.getInt("notype"));
				no.setPostfix(rs.getString("postfix"));
				no.setPrefix(rs.getString("prefix"));
			} else if (this.autoCreate) {
				rs.moveToInsertRow();

				rs.updateString("noid", seqDefId);
				rs.updateString("noname", noname);
				rs.updateLong("notype", notype);
				rs.updateLong("nolength", nolength);
				rs.updateString("prefix", prefix);
				rs.updateString("postfix", postfix);
				rs.insertRow();

				no = new No();
				no.setNoid(seqDefId);
				no.setNoname(noname);
				no.setNotype(notype);
				no.setNolength(nolength);
				no.setPostfix(postfix);
				no.setPrefix(prefix);
			}

			rs.close();
			state.close();
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		} finally {
			releaseConnection(con);
		}

		return no;
	}

	public long getNextSeqDtlDef(No no, Calendar cale, int number) {
		long nextid = 0L;
		int year = cale.get(1);
		int month = cale.get(2) + 1;
		int day = cale.get(5);
		String sqlStr = "SELECT noid, noyear, nomonth, noday, prefix, postfix, nextid FROM sys_nodtl WHERE noid=? ";

		int type = no.getNotype();
		switch (type) {
		case 1:
		case 2:
		case 3:
		case 4:
			sqlStr = sqlStr + " AND noday=?";
			sqlStr = sqlStr + " AND nomonth=?";
			sqlStr = sqlStr + " AND noyear=?";
			break;
		default:
			throw new RuntimeException("不支持的序列定义类型！");
		}

		log.debug(sqlStr);
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement state = con.prepareStatement(sqlStr, 1005, 1008);
			state.setString(1, no.getNoid());

			switch (type) {
			case 4:
				state.setInt(2, day);
				state.setInt(3, month);
				state.setInt(4, year);
				break;
			case 3:
				state.setInt(2, 0);
				state.setInt(3, month);
				state.setInt(4, year);
				break;
			case 2:
				state.setInt(2, 0);
				state.setInt(3, 0);
				state.setInt(4, year);
				break;
			case 1:
				state.setInt(2, 0);
				state.setInt(3, 0);
				state.setInt(4, 0);
				break;
			default:
				throw new RuntimeException("不支持的序列定义类型！");
			}

			ResultSet rs = state.executeQuery();

			if (rs.next()) {
				nextid = rs.getLong("nextid");
				rs.updateLong("nextid", nextid + number);
				rs.updateRow();
			} else {
				nextid = DEFAULT_START_ID.longValue();
				rs.moveToInsertRow();

				if (type == 4) {
					rs.updateLong("noday", day);
					rs.updateLong("nomonth", month);
					rs.updateLong("noyear", year);
				} else if (type == 3) {
					rs.updateLong("noday", 0);
					rs.updateLong("nomonth", month);
					rs.updateLong("noyear", year);
				} else if (type == 2) {
					rs.updateLong("noday", 0);
					rs.updateLong("nomonth", 0);
					rs.updateLong("noyear", year);
				} else {
					rs.updateLong("noday", 0);
					rs.updateLong("nomonth", 0);
					rs.updateLong("noyear", 0);
				}

				rs.updateLong("nextid", nextid + number);
				rs.updateString("noid", no.getNoid());

				String prefix = no.getPrefix();
				if (prefix == null)
					rs.updateNull("prefix");
				else {
					rs.updateString("prefix", prefix);
				}

				String postfix = no.getPostfix();
				if (postfix == null)
					rs.updateNull("postfix");
				else
					rs.updateString("postfix", postfix);

				rs.insertRow();
			}

			rs.close();
			state.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			releaseConnection(con);
		}

		return nextid;
	}

	public String generate(String seqDefid) {
		List l = batchGenerate(seqDefid, 1);
		return ((String) l.get(0));
	}

	public List batchGenerate(String seqDefid, int count) {
		long id;
		No no = loadNO("auto create", seqDefid, 10, 1, "", "");
		Calendar cale = Calendar.getInstance();

		synchronized (seqDefid.intern()) {
			id = getNextSeqDtlDef(no, cale, count);
		}

		Object l = new ArrayList();
		for (int i = 0; i < count; ++i) {
			String value = makeSequence(id, no.getNolength(), no.getPrefix(),
					no.getPostfix(), cale);
			id += 1;
			((List) l).add(value);
		}

		return ((List) l);
	}
}