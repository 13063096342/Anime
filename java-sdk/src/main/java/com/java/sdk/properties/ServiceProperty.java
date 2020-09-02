package com.java.sdk.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenfh
 * @date 2020-04-29 13:58
 */
@ConfigurationProperties("server")
@Configuration
@Data
public class ServiceProperty {
    private Integer port;
    private String contextPath;
}
