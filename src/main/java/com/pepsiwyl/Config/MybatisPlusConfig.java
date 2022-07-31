package com.pepsiwyl.Config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author by pepsi-wyl
 * @date 2022-07-30 14:32
 */

@Configuration                             // MybatisPlus配置类
@MapperScan("com.pepsiwyl.mapper")         // 扫描Mapper
@EnableTransactionManagement               // 事务管理器
public class MybatisPlusConfig {

}
