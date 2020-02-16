import com.mfun.proxy.Calculator;
import com.zaxxer.hikari.HikariConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AOPTest {

    @Autowired
    private Calculator calculator;

    @Test
    void aopTest() {
        System.out.println(calculator.div(10, 2));
    }
}
