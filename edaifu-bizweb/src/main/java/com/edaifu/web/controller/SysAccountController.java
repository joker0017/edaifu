package com.edaifu.web.controller;

//import com.edaifu.db.pojo.SysAccountBiz;
import com.edaifu.db.pojo.SysAccountBiz;
import com.edaifu.service.SysAccountBizService;
import com.edaifu.web.utils.PageUtils;
import com.edaifu.web.converter.SysAccountBizConverter;
import com.edaifu.web.vo.PageInfoResultVO;
import com.edaifu.web.vo.SysUserSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping(value="/account")
public class SysAccountController {

    @Autowired
    private SysAccountBizConverter converter;

    @Autowired
    private SysAccountBizService accountBizService;

    @RequestMapping("/accountPageList")
    public PageInfoResultVO getAccountPageList(SysUserSearchVO pageInfoVo){
        PageRequest pageRequest = PageUtils.buildPageRequest(pageInfoVo.getPage(),pageInfoVo.getRows());
        Page<SysAccountBiz> page = accountBizService.queryAccountBizPage(pageRequest);

        return converter.covert(page);
    }
}
