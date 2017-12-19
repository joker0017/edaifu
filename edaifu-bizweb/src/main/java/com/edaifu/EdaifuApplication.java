package com.edaifu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JOKER
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class EdaifuApplication {
    public static void main(String[] args) {
        SpringApplication.run(EdaifuApplication.class, args);
    }
}
