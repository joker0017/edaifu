package com.edaifu.web.controller;

import com.edaifu.db.pojo.SysOrgBiz;
import com.edaifu.service.SysOrgBizService;
import com.edaifu.web.converter.SysOrgBizConverter;
import com.edaifu.web.dto.SysOrgBizDTO;
import com.edaifu.web.utils.ResultVOUtil;
import com.edaifu.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/org")
public class OrgController {

    @Autowired
    private SysOrgBizService orgBizService;

    @Autowired
    private SysOrgBizConverter orgBizConverter;

    @RequestMapping("/orgList")
    public ResultVO<SysOrgBizDTO> getOrgList(){
        List<SysOrgBizDTO> res = new ArrayList<>();
        res.add(orgBizConverter.convert(orgBizService.queryAllOrgTree("000")));
        return ResultVOUtil.success(res);
    }
}
