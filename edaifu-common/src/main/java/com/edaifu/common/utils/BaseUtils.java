package com.edaifu.common.utils;

import com.edaifu.common.err.BusinessException;

import java.util.HashMap;
import java.util.Map;

public class BaseUtils {
    public static Map<String, Object> map(Object[] keyAndValues) {
        if (keyAndValues.length % 2 != 0)
            throw new BusinessException("E0002", "传入的参数必须成对出现,如[account_id,admin,account_name,管理员]");
        int l = keyAndValues.length / 2;
        Map p = new HashMap();
        for (int i = 0; i < l; ++i)
            p.put((String) keyAndValues[(2 * i)], keyAndValues[(2 * i + 1)]);
        return p;
    }
}