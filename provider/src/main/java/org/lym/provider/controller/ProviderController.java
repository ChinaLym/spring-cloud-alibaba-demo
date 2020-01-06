package org.lym.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lym
 */
@RefreshScope
@RestController
@RequestMapping("/provider")
public class ProviderController {


    /** 测试 naocs 动态配置 */
    @Value("${nacosDynamicConfig:default}")
    String nacosDynamicConfig;

    @GetMapping("/hello")
    public String helloNacos(){
        return "nacosDynamicConfig 当前值为：" + nacosDynamicConfig;
    }

    @EventListener(value = ContextRefreshedEvent.class)
    public void out(){
        System.out.println("启动时 nacosDynamicConfig 配置：" + nacosDynamicConfig);

    }
}
