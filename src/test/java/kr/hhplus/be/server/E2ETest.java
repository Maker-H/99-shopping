package kr.hhplus.be.server;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
public class E2ETest {

    @Value("${test.mysql.enabled:false}")
    private static boolean mysqlEnabled;

    static boolean isMysqlEnabled() {
        return mysqlEnabled;
    }
}
