package tobyspring.config.autoconfig;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Driver;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations") // 클래스 패쓰에서 해당 라이브러리가 있는지 찾고있음
@EnableMyConfigurationProperties(MyDataSourceProperties.class) //
@EnableTransactionManagement // Enable은 구성 정보 관련 클래스들을 Import
public class DataSourceConfig {

    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari")
    @ConditionalOnMissingBean
    DataSource hikariDataSource(MyDataSourceProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName((properties.getDriverClassName()));
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;

    }

    @Bean
    @ConditionalOnMissingBean
    DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    @ConditionalOnSingleCandidate(DataSource.class) // 단 하나의 빈이 있는 것을 조건으로 해서 빈 생성
    @Bean
    @ConditionalOnMissingBean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @ConditionalOnSingleCandidate(DataSource.class) // 단 하나의 빈이 있는 것을 조건으로 해서 빈 생성
    @Bean
    @ConditionalOnMissingBean
    JdbcTransactionManager jdbcTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }
}
