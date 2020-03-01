import com.mfun.bean.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BookTest {

    @Autowired
    private Book book;

    @Test
    public void test() {
        if (book.getCar() != null) {
            System.out.println(book.getCar().getName());
        } else {
            System.out.println("==== Car 未注入");
        }
    }

}
