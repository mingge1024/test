package com.example.webjar.contronller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @文件名 WebjarsController
 * @描述 WebjarsController类
 * @auther xuzhiming
 * @创建日期 2019/10/12
 */
@Slf4j
@Controller
@RequestMapping ("/api")
public class WebjarsController {

    @NacosInjected
    private NamingService namingService;

    @NacosValue(value = "${server.port}", autoRefreshed = true)
    private String useLocalCache;

    @RequestMapping("/get")
    public String get() {
        log.info(useLocalCache);
        return useLocalCache;
    }

    @GetMapping("/index")
    public ModelAndView index(){
        log.info("------index访问jsp----------");
        return new ModelAndView("webjars");
    }

    @GetMapping ("/index1")
    public String index1(Model model){
        log.info("------访问html----------");
        model.addAttribute("test", "admin");
        return "webjars";
    }
}