package org.lym.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.apache.dubbo.config.annotation.Reference;
import org.lym.api.DubboApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lym
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    /** check = false 就可以关闭启动时依赖检查，否则必须先启动服务提供者才能启动消费者 */
    @Reference(check = false)
    private DubboApi dubboApi;

    @SentinelResource("consumer-dubbo-test")
    @GetMapping("/dubbo")
    public Object dubbo() {
        return dubboApi.hello();
    }

}
