package tobyspring.config.autoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tobyspring.config.MyConfigurationProperties;

@Getter
@Setter
@MyConfigurationProperties(prefix = "server")
public class ServerProperties {

    @Value("${contextPath:}")
    String contextPath;

    @Value("${port:9090}") // 기본값 8080
    int port;
}
