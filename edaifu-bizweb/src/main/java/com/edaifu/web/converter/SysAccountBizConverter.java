package com.edaifu.web.converter;

import com.edaifu.db.pojo.SysAccountBiz;
import com.edaifu.db.pojo.SysRoleBiz;
import com.edaifu.web.dto.SysAccountBizDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SysAccountBizConverter extends Pojo2DTOConverter<SysAccountBiz,SysAccountBizDTO> {

    /**
     * Pojo 对象转换成 DTO对象
     * @param accountBiz
     * @return
     */
    public SysAccountBizDTO convert(SysAccountBiz accountBiz){
        SysAccountBizDTO accountBizDTO = new SysAccountBizDTO();
        BeanUtils.copyProperties(accountBiz,accountBizDTO);
        Set<SysRoleBiz> roleBizSet = accountBiz.getRoles();
        List<String> roleNames = roleBizSet.stream().map(e->e.getRoleName()).collect(Collectors.toList());
        String roleNameStr = StringUtils.collectionToDelimitedString(roleNames,",");
        accountBizDTO.setRolesNames(roleNameStr);
        return accountBizDTO;
    }
}
