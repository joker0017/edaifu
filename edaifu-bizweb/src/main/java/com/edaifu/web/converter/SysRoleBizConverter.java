package com.edaifu.web.converter;

import com.edaifu.db.pojo.SysAccountBiz;
import com.edaifu.db.pojo.SysRoleBiz;
import com.edaifu.web.dto.SysAccountBizDTO;
import com.edaifu.web.dto.SysRoleBizDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SysRoleBizConverter extends Pojo2DTOConverter<SysRoleBiz,SysRoleBizDTO> {

    /**
     * Pojo 对象转换成 DTO对象
     * @param accountBiz
     * @return
     */
    public SysRoleBizDTO convert(SysRoleBiz accountBiz){
        SysRoleBizDTO sysRoleBizDTO = new SysRoleBizDTO();
        BeanUtils.copyProperties(accountBiz,sysRoleBizDTO);
        return sysRoleBizDTO;
    }
}
