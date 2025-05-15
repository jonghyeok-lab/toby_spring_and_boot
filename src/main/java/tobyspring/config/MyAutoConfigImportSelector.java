package tobyspring.config;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * DeferredImportSelector 동적으로 Confituration 을 등록
 */
public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//        return new String[] {
//                "tobyspring.config.autoconfig.DispatcherServletConfig",
//                "tobyspring.config.autoconfig.TomcatWebServerConfig"
//        };

        // 어디서 읽냐? -> load 메서드에 들어가보면 META-INF/spiring/패키지이름을 포함한.imports
        ImportCandidates candidate = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        return StreamSupport.stream(candidate.spliterator(), false).toArray(String[]::new);
    }

    // BeanClassLoader
//    @Override
//    public void setBeanClassLoader(ClassLoader classLoader) {
//
//    }
}
