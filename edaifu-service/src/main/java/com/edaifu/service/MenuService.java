package com.edaifu.service;

import com.edaifu.db.pojo.SysMenuBiz;
import com.edaifu.db.repository.SysMenuBizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private SysMenuBizRepository menuRepository;

    @Cacheable("menus")
    public List<SysMenuBiz> getMenus() {
        return menuRepository.findAll();
    }
}
