package tobyspring.config.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class DispatcherServletConfig {

    /**
     * ApplicationContextAware 를 구현
     * -> 그러면, 스프링컨텍스트가 빈으로 등록할 때, ApplicationContextAware를 구현하고 있는
     * 애들에게 스스로(스프링컨텍스트(애플리케이션컨텍스트))를 setter 메서드로 주입해준다.
     */
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

}
