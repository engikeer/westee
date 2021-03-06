import com.mfun.bean.Person;
import com.mfun.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@ExtendWith(PersonResolver.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CommonTest {
    @Autowired
    private Person person;
//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private PersonService personService;

    @Test
    void service() {
        System.out.println(personService.getPerson().getName());
    }

    @Test
    public void test() {
        System.out.println(person.getCar());
    }

    @Test
    public void getPerson(Person wan) {
        System.out.println(wan.getName());
    }

//    @Test
//    public void getConnection() throws SQLException {
//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bs_user");
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                String username = resultSet.getString("username");
//                System.out.println("user: " + username);
//            }
//
//        }
//    }
}
