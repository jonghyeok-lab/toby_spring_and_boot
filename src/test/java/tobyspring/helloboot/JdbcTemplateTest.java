package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HelloBootTest
public class JdbcTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    @DisplayName("")
    @Test
    void test() {
        // given
        jdbcTemplate.update("insert into hello values(?, ?)", "toby", 3);
        jdbcTemplate.update("insert into hello values(?, ?)", "boot", 1);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
         assertThat(count).isEqualTo(2);
    }
}
