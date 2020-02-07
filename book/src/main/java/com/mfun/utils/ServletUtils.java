package com.mfun.utils;

import com.mfun.pojo.Cart;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ServletUtils {

    /**
     * 通过反射将请求中的参数封装为对象
     * @param cls 封装对象的类对象
     * @param <T> 封装对象的类型
     * @return 封装对象
     */
    public static <T> T param2bean(HttpServletRequest req, Class<T> cls)
            throws InvocationTargetException, IllegalAccessException, InstantiationException {
        // 手动实现的过程
        // 1. 获取类中声明的所有字段名
//        Field[] fields = cls.getDeclaredFields();
//        T t = cls.newInstance();
//        // 2. 通过字符串获取所有的 setAttrName 方法
//        for (Field field : fields) {
//            String name = field.getName();
//            // 通过字符取子串实现首字母大写
//            // String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
//            // 通过 ascii 码迁移实现首字母大写
//            char[] chars = name.toCharArray();
//            chars[0] -= 32;
//            String methodName = "set" + String.valueOf(chars);
//            Class<?> type = field.getType();
//            // 3. 调用 setter 方法设置字段的值
//            Method method = t.getClass().getMethod(methodName, type);
//            method.invoke(t, req.getParameter(name));
//        }

        // 通过 beanutils 实现
        // 存储属性时根据 setter 的名称设置，如果 name 与 setter 不对应则无法设置属性
        // 该工具包能实现自动将传入的属性值转为字段所需的类型，如果无法转换会赋予默认值

        // 通过 setProperty 实现
//        Field[] fields = cls.getDeclaredFields();
//        T t = cls.newInstance();
//        for (Field field : fields) {
//            String name = field.getName();
//            BeanUtils.setProperty(t, name, req.getParameter(name));
//        }

        // 通过 populate 实现
        T t = cls.newInstance();
        Map<String, String[]> parameterMap = req.getParameterMap();
        BeanUtils.populate(t, parameterMap);
        return t;
    }

    public static Cart getCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
