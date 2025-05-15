package tobyspring.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import tobyspring.config.autoconfig.DispatcherServletConfig;
import tobyspring.config.autoconfig.TomcatWebServerConfig;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//@Import({DispatcherServletConfig.class, TomcatWebServerConfig.class})
@Import(MyAutoConfigImportSelector.class) // ImportSelector를 구현한 메소드의 반환값(String [])만을 Configuration에 등록.
public @interface EnableMyAutoConfiguration {
}
