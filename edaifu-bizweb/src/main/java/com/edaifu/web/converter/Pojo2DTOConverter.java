package com.edaifu.web.converter;

import com.edaifu.db.pojo.SysRoleBiz;
import com.edaifu.web.dto.SysRoleBizDTO;
import com.edaifu.web.vo.PageInfoResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Pojo2DTOConverter<P,D> {

    public abstract D convert(P accountBiz);

    /**
     * Pojo 对象列表转换成 DTO 对象列表
     * @param list
     * @return
     */
    public  List<D> covert(List<P> list){
        return list.stream().map(e->
                convert(e)
        ).collect(Collectors.toList());
    }

    /**
     * Pojo 页面对象转换成 DTO 页面对象
     * @param page
     * @return
     */
    public PageInfoResultVO<D> covert(Page<P> page){
        return new PageInfoResultVO<D>(page.getTotalElements(),covert(page.getContent()));
    }

}
