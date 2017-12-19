package com.edaifu.web.controller;

import com.edaifu.db.pojo.SysRoleBiz;
import com.edaifu.db.repository.SysRoleBizRepository;
import com.edaifu.service.SysRoleBizService;
import com.edaifu.web.converter.SysRoleBizConverter;
import com.edaifu.web.dto.SysRoleBizDTO;
import com.edaifu.web.utils.ResultVOUtil;
import com.edaifu.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private SysRoleBizService roleBizService;

    @Autowired
    private SysRoleBizConverter roleBizConverter;

    @RequestMapping("/roleList")
    public ResultVO<SysRoleBizDTO> getPage(){
        return ResultVOUtil.success(roleBizConverter.covert(roleBizService.querySysRoleBizList()));
    }

}
