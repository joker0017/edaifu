package com.edaifu.service;

import com.edaifu.db.pojo.SysRoleBiz;
import com.edaifu.db.repository.SysRoleBizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleBizService {

    @Autowired
    private SysRoleBizRepository repository;

    public Page<SysRoleBiz> queryAccountBizPage(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Cacheable("roles")
    public List<SysRoleBiz> querySysRoleBizList(){
        return repository.findAll();
    }
}
