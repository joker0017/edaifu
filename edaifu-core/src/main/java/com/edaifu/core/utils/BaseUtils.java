package com.edaifu.core.utils;


import java.util.HashMap;
import java.util.Map;
import org.springframework.security.crypto.codec.Base64;

public class BaseUtils {
	public static Map<String, Object> map(Object[] keyAndValues) {
		if (keyAndValues.length % 2 != 0)
			throw new RuntimeException("传入的参数必须成对出现,如[account_id,admin,account_name,管理员]");
		int l = keyAndValues.length / 2;
		Map p = new HashMap();
		for (int i = 0; i < l; ++i)
			p.put((String) keyAndValues[(2 * i)], keyAndValues[(2 * i + 1)]);

		return p;
	}

	public static String encodeBase64(String value) {
		StringBuilder sb = new StringBuilder(new String(Base64.encode(value.getBytes())));
		while (sb.charAt(sb.length() - 1) == '=')
			sb.deleteCharAt(sb.length() - 1);

		return sb.toString();
	}
}