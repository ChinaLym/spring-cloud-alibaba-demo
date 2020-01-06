package org.lym.provider.dubbo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.apache.dubbo.config.annotation.Service;
import org.lym.api.DubboApi;

/**
 * dubbo 提供方
 * @author Admin
 */
@Service
public class DubboApiImpl implements DubboApi {

    @Override
    @SentinelResource("provider-hello")
    public String hello() {
        System.out.println("provider: consumer invoked.");
        return "dubbo test";
    }
}
