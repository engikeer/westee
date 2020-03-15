import com.mfun.bean.Student;
import com.mfun.dao.EmployeeDao;
import com.mfun.dao.StudentDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class MyBatisTest {

    @Test
    public void testHello() throws IOException {
        // 1. 通过全局配置文件构建 SqlSessionFactory
        // SqlSessionFactory 负责创建 SqlSession 对象
        // 一个 SqlSession 就代表与数据库的一次会话
        // resource 是全局配置文件在路径下的相对路径
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 全局配置文件中如果有非 ascii 字符，需要指定字符集并使用 reader 来读取配置文件
//        Resources.setCharset(StandardCharsets.UTF_8);
//        Reader reader = Resources.getResourceAsReader(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        // 2. 从 SqlSessionFactory 中获取 SqlSession
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            // 3. 使用 SqlSession 与数据库交互
            // 获取 Dao 接口的实现（MyBatis 根据 mapper 创建实现类）
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            // 使用 Dao 对象实现数据库操作
//            Employee employee = employeeDao.getEmpById(1);
//            System.out.println(employee);
//            employee.setEmail("xizi@eto.org");
//            employeeDao.updateEmployee(employee);
            // 新增
//            employeeDao.insertEmployee(new Employee(null, "十强", 1, "Mark@cc.cc"));
            // 删除
//            employeeDao.deleteEmployee(2);
//            sqlSession.commit();
        }
    }

    @Test
    public void testMapper() throws Exception {
        String resource = "mybatis-config.xml";
        Resources.setCharset(StandardCharsets.UTF_8);
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        try (SqlSession session = sessionFactory.openSession(true)) {
            StudentDao studentDao = session.getMapper(StudentDao.class);

            Student student = new Student(3, null, 1);
            System.out.println(studentDao.getStudentByCondition(student).size());
        }
    }
}
