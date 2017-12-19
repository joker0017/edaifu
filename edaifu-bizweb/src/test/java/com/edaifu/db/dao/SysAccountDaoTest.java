package com.edaifu.db.dao;

import com.edaifu.db.pojo.SysAccountBiz;
import com.edaifu.db.repository.SysAccountBizRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysAccountDaoTest {
    @Autowired
    private SysAccountBizRepository sysAccountBizRepository;

    @Test
    public void queryForMap(){
        Map<String,Object> map = new HashMap<>();
//        map.put("account_id","cfadmin");
        SysAccountBiz res = sysAccountBizRepository.findOne("cfadmin");
        System.out.println(res.getAccountId());
    }
}
