package net;

import java.lang.annotation.*;
import java.lang.reflect.Field;

public class ReflectionAnnotation {

    public static void main(String[] args) throws Exception {
        Class<?> eClass = Class.forName("net.Employee");

        // 通过反射获得注解
        Annotation[] annotations = eClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        // 获取 Table 的参数 value 的值
        DbTable dbTable = eClass.getAnnotation(DbTable.class);
        String value = dbTable.value();
        System.out.println(value);

        // 获取字段的注解
        Field nameField = eClass.getDeclaredField("name");
        DbField dbField = nameField.getAnnotation(DbField.class);
        System.out.println(dbField.columnName() + "-" + dbField.type() + "-" + dbField.length());

    }

}

@DbTable("db_employee")
class Employee {
    @DbField(columnName = "db_id", type = "int", length = 10)
    private int id;
    @DbField(columnName = "db_age", type = "int", length = 4)
    private int age;
    @DbField(columnName = "db_name", type = "varchar", length = 3)
    private String name;

    public Employee() {
    }

    public Employee(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface DbTable {
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface DbField {
    String columnName();
    String type();
    int length();
}