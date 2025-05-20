package tobyspring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

public class MyConfigurationPropertiesImportSelector implements DeferredImportSelector {

    /**
     * @EnableMyConfigurationProperties)을 기준으로 동적으로 설정 클래스(또는 빈 정의)를 등록하는 데 사용
     *
     */
    // @EnableMyConfigurationProperties 이 붙은 클래스들중에서 value에 해당하는 값이 있는 것들을 후보군에서 빈으로 등록(@Import)시킨다.
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> attr = importingClassMetadata.getAllAnnotationAttributes(
                EnableMyConfigurationProperties.class.getName());

        Class propertyClass = (Class) attr.getFirst("value");

        return new String[]{propertyClass.getName()};
    }
}
