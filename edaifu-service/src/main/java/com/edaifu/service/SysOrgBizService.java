package com.edaifu.service;

import com.edaifu.db.pojo.SysOrgBiz;
import com.edaifu.db.repository.SysOrgBizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOrgBizService {

    @Autowired
    private SysOrgBizRepository repository;


    @Cacheable("orgs")
    public List<SysOrgBiz> queryAllOrgList(){
        return repository.findAll();
    }

    @Cacheable("org_tree")
    public SysOrgBiz queryAllOrgTree(String orgId){
        return repository.findOne(orgId);
    }
}
