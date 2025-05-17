package tobyspring.study;

import static org.assertj.core.api.Assertions.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.diff.Delta.TYPE;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalTest {


    @DisplayName("")
    @Test
    void test() {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

        // Condition true
//        ac.register(Config.class);
//        ac.refresh();
//
//        MyBean bean = ac.getBean(MyBean.class);
//        Assertions.assertThat(bean).isNotNull();

        // Condition False
        ac.register(Config2.class);
        ac.refresh();

        assertThatThrownBy(() -> ac.getBean(MyBean.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @DisplayName("")
    @Test
    void spring() {
        // 스프링 전용 테스트
        // true
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(MyBean.class);
                    assertThat(context).hasSingleBean(Config.class);
                });

        // false
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(MyBean.class);
                    assertThat(context).doesNotHaveBean(Config2.class);
                });

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional{
        boolean value();
    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(
                    BooleanConditional.class.getName());
            return (Boolean) annotationAttributes.get("value");
        }
    }

    @Configuration
    @BooleanConditional(true)
    static class Config {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
//    @Conditional(FalseCondition.class)
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }

    }

    static class MyBean {

    }

    static class TrueCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }
}
