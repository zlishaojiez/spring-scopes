package cn.shaojiel.autoconfig.config;

import cn.shaojiel.autoconfig.properties.MyProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@EnableConfigurationProperties(MyProperties.class)
@ComponentScan("cn.shaojiel.autoconfig")
public class MyAutoConfiguration {
}
