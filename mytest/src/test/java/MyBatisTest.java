import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mfun.bean.Employee;
import com.mfun.bean.Student;
import com.mfun.dao.EmployeeDao;
import com.mfun.dao.StudentDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
            EmployeeDao employeeDao = session.getMapper(EmployeeDao.class);

            PageHelper.startPage(4, 30);
            List<Employee> employees =  employeeDao.getAllEmployee();
            PageInfo<Employee> pageInfo = new PageInfo<>(employees, 6);
            for (Employee employee :pageInfo.getList()) {
                System.out.println(employee);
            }
//            System.out.println(employees.size());

        }
    }

    @Test
    public void mbgTest() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/generatorConfig.xml");
        System.out.println(configFile.canRead());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Test
    public void inserts() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> employees = new ArrayList<>();
            for (int i = 1; i <= 1000; i++) {
                String name = "emp-" + i;
                int gender = Math.random() < 0.5 ? 0 : 1;
                String email = name + "@cc.com";
                employees.add(new Employee(null, name, gender, email));
            }
            employeeDao.insertEmployees(employees);
        }
    }

}
