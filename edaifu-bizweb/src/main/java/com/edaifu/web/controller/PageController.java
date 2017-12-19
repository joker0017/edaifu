package com.edaifu.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pages")
public class PageController {

    @GetMapping("/{pageName}")
    public String getPage(@PathVariable String pageName){
        return pageName;
    }

    @GetMapping("/{prefixStr}/{pageName}")
    public String getUserMngPage(@PathVariable String prefixStr,@PathVariable String pageName){
        return prefixStr+"/"+pageName;
    }
}
