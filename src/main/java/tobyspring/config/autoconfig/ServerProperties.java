package tobyspring.config.autoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class ServerProperties {

    @Value("${contextPath:}")
    String contextPath;

    @Value("${port:8080}") // 기본값 8080
    int port;
}
