package tobyspring.helloboot;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HellobootApplication.class)
@TestPropertySource("classpath:/application.properties") // properties 같은 외부 설정은 스프링이 아닌 스프링 부트 초기화 과정에서 발생하기 때문에 위 어노테이션은 스프링용이기 때문이며 Test용 Property를 주입해주었다.
public class DataSourceTest {

    @Autowired
    DataSource dataSource;

    @DisplayName("")
    @Test
    void test() throws SQLException {
        // given
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
