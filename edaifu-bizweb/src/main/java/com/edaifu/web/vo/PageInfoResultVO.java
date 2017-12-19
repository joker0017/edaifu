package com.edaifu.web.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageInfoResultVO<D> {
    private long total = 0;
    private List<D> rows = new ArrayList<D>();

    public PageInfoResultVO(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }
}
