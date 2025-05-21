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
import org.springframework.transaction.annotation.Transactional;

@HelloBootTest
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
