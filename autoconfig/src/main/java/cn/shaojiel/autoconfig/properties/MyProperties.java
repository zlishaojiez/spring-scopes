package cn.shaojiel.autoconfig.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "autoconfig")
@Getter
@Setter
public class MyProperties {

    private String name;
}
