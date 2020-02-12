import com.mfun.bean.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PersonResolver.class)
public class ParameterTest {

    @Test
    void getPersonTest(Person person) {
        System.out.println(person.getName());
    }
}
