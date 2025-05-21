package tobyspring.helloboot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HellobootApplication.class)
@TestPropertySource("classpath:/application.properties")
// properties 같은 외부 설정은 스프링이 아닌 스프링 부트 초기화 과정에서 발생하기 때문에 위 어노테이션은 스프링용이기 때문이며 Test용 Property를 주입해주었다.
@Transactional
public @interface HelloBootTest {
}
