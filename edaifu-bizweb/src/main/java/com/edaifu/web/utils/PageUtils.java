package com.edaifu.web.utils;

import com.edaifu.web.vo.PageInfoResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class PageUtils {
    /**
     * 构建PageRequest
     * @param pageNumber
     * @param pagzSize
     * @return
     */
    public static PageRequest buildPageRequest(int pageNumber, int pagzSize) {
        return new PageRequest(pageNumber - 1, pagzSize, null);
    }

    public static PageInfoResultVO convertPage2PageInfoVo(Page page){
        return new PageInfoResultVO(page.getTotalElements(),page.getContent());
    }
}
