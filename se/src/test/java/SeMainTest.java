import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Date;

public class SeMainTest {

    @Test
    public void test() {
        String msg = MessageFormat.format("{0}-{1}", new Date(), "to");
        System.out.println(msg);
    }



}
