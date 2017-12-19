package com.edaifu.core.config;

import com.edaifu.core.props.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * 启用系统配置
 * @author JOKER
 *
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class EdaifuCoreConfig {
	
}
