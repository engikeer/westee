import com.mfun.proxy.Calculator;
import com.mfun.proxy.MathCalculator;
import com.mfun.proxy.LogProxy;
import org.junit.jupiter.api.Test;

public class ProxyTest {

    @Test
    void calTest() {
        Calculator calculator = new MathCalculator();
        System.out.println(calculator.div(5, 7));
        // 动态代理执行
        Calculator proxy = new LogProxy<Calculator>().getProxy(calculator);
        System.out.println(proxy.div(5, 7));
    }
}
