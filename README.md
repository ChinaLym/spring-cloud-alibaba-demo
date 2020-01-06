# spring-cloud-alibaba-demo

# 介绍

该分支为 seata demo分支，在默认分支基础上，演示如何引入 seata。基础入门请切换至默认分支查看。

# Spring Cloud Alibaba Seata

# 1 简介
官方文档: http://seata.io/zh-cn/docs/user/quickstart.html
github:https://github.com/seata/seata/

## 2 下载

https://github.com/seata/seata/releases

## 3.初始化

创建依赖数据表

```sql
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

## 4.启动



## 5.应用接入

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-alibaba-seata</artifactId>
</dependency>
```

```java
@Configuration
public class FescarConfig {
    @Bean
    public GlobalTransactionScanner globalTransactionScanner(){
        return new GlobalTransactionScanner("dubbo-gts-fescar-example", "my_test_tx_group");
    }
}
```

```java
@Reference(version = "1.0.0")
private StorageDubboService storageDubboService;

@Reference(version = "1.0.0")
private OrderDubboService orderDubboService;
 
@GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-fescar-example")
public ObjectResponse handleBusiness(BusinessDTO businessDTO) {
	//1.扣减库存
	ObjectResponse storageResponse = storageDubboService.decreaseStorage(commodityDTO);
	//2.创建订单
	ObjectResponse<OrderDTO> response = orderDubboService.createOrder(orderDTO);
	return objectResponse;
}
```


## 7.常见错误

windows下启动提示“输入行太长”，可修改seata-server.bat文件内set CLASSPATH="%BASEDIR%"\conf;"%REPO%"\*

## 8.参考示例

https://github.com/spring-cloud-incubator/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/seata-example/readme-zh.md


# 七、演示源码

## Gitee
https://gitee.com/ChinaLym/spring-cloud-alibaba-demo

## Github
https://github.com/ChinaLym/spring-cloud-alibaba-demo
