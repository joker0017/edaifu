package com.edaifu.service;

import com.edaifu.db.pojo.SysAccountBiz;
import com.edaifu.db.repository.SysAccountBizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SysAccountBizService {

    @Autowired
    private SysAccountBizRepository repository;
    public Page<SysAccountBiz> queryAccountBizPage(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }
}
