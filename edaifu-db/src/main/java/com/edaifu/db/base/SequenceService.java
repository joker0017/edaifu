package com.edaifu.db.base;

import java.util.List;

import com.edaifu.db.dao.SequenceDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceService {

	@Autowired
	private SequenceDao sequenceDao;
	static Log log = LogFactory.getLog(SequenceService.class);

	public String getGlobalFlowNo() {
		return this.sequenceDao.generate("全局事件跟踪号", "_globalFlowNo", 10, 1, "GL{yyyy}{mm}{dd}", "");
	}

	public String getTablePK(String tableName, String colunm) {
		return this.sequenceDao.generate("数据库表格的某个字段的序号", "_" + tableName + "_" + colunm, 10, 1, "TB", "");
	}

	public String getTableFlowNo(String tableName, String colunm) {
		return this.sequenceDao.generate("数据库表格的某个字段的序号", "_" + tableName + "_" + colunm, 8, 4, "{yyyy}{mm}{dd}", "");
	}

	public String getTableSquence(String tableName, String colunm) {
		return this.sequenceDao.generate("数据库表格的某个字段的序号", "_" + tableName + "_" + colunm, 10, 1, "0", "");
	}

	public String getSessionKey() {
		return null;
	}


	public String generate(String seqDefid) {
		return this.sequenceDao.generate(seqDefid);
	}

	public List batchGenerate(String seqDefid, int count) {
		return this.sequenceDao.batchGenerate(seqDefid, count);
	}
}