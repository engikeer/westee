import com.mfun.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;


public class CommonTest {
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");

    @Test
    public void test() {
        // ApplicationContext 代表 IoC 容器
        // 实现类是类路径下的 xml 配置的 IoC 容器，传入配置文件在类路径下的位置
        // 通过 id 获取 bean
        Person person01 = (Person) ioc.getBean("person01");
        System.out.println(person01.getCar());
    }
}
