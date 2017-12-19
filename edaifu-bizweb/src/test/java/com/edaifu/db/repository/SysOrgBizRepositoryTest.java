package com.edaifu.db.repository;

import com.edaifu.db.pojo.SysAccountBiz;
import com.edaifu.db.pojo.SysOrgBiz;
import com.edaifu.db.pojo.SysRoleBiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysOrgBizRepositoryTest {

    @Autowired
    private SysOrgBizRepository repository;

    @Test
    public void findOneTest(){
        SysOrgBiz sysOrgBiz = repository.findOne("000");
        System.out.println(sysOrgBiz);
    }
}