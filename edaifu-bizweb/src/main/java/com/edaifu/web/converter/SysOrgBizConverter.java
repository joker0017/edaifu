package com.edaifu.web.converter;

import com.edaifu.db.pojo.SysOrgBiz;
import com.edaifu.db.pojo.SysRoleBiz;
import com.edaifu.web.dto.SysOrgBizDTO;
import com.edaifu.web.dto.SysRoleBizDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SysOrgBizConverter extends Pojo2DTOConverter<SysOrgBiz,SysOrgBizDTO> {

    /**
     * Pojo 对象转换成 DTO对象
     * @param sysOrgBiz
     * @return
     */
    public SysOrgBizDTO convert(SysOrgBiz sysOrgBiz){
        SysOrgBizDTO sysOrgBizDTO = new SysOrgBizDTO();
        BeanUtils.copyProperties(sysOrgBiz,sysOrgBizDTO);
        if(!sysOrgBiz.getChildOrgs().isEmpty()){
            List<SysOrgBizDTO> childOrgs = sysOrgBiz.getChildOrgs().stream().map(e->convert(e)).collect(Collectors.toList());
            sysOrgBizDTO.setChildOrgs(childOrgs);
        }
        return sysOrgBizDTO;
    }
}
