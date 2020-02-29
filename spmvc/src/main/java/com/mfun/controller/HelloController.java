package com.mfun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class HelloController {

    @RequestMapping(value = "/hello", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String hello(String username) {
        System.out.println("你好：" + username);
        return "<h3>你好：" + username + "</h3>";
    }

//    @ModelAttribute
//    public void getBook(Map<String, Object> map) {
//        Book book = new Book();
//        book.setUsername("仔仔");
//        book.setAge(20);
//        book.setGender("女");
//        System.out.println("getBook：" + book);
//        map.put("book", book);
//    }

    @ModelAttribute
    public Book getBook() {
        Book book = new Book();
        book.setUsername("仔仔");
        book.setAge(20);
        book.setGender("女");
        System.out.println("getBook：" + book);
        return book;
    }

    @RequestMapping("/page")
    public String page(Book book, ModelMap map) {
        System.out.println("页面：" + book);
        map.put("name", book.getUsername());
        return "hello";
    }

    // 输出 json 对象的方式
    // 方式1：手动创建 MappingJackson2JsonView
    // 必须引入 jackson-databind
    @RequestMapping("/my_book")
    @ResponseBody
    public ModelAndView getMyBook() {
        // 会自动将模型映射中的数据放入响应中，键就是类名首字母小写。
        // 如果要添加其他数据，可创建一个 Map 并传给 ModelAndView 的构造函数
        // 例如：
//        Map<String, Book> map = new HashMap<>();
//        Book book = new Book();
//        book.setUsername("东东");
//        book.setGender("未知");
//        book.setAge(92);
//        map.put("myBook", book);
//        return new ModelAndView(new MappingJackson2JsonView(), map);
        // 上面返回的 json 对象会包含两个键 ModelMap中已有的 book，新追加的 myBook
        // 只要是 jackson 能转换的对象就可作为映射的值
        return new ModelAndView(new MappingJackson2JsonView());
        // 使用 @ResponseBody 且视图为 MappingJackson2JsonView 时会自动将响应的内容类型设为 application/json;charset=UTF-8
    }

    // 方式 2：SpringMVC 自动转换
    // 直接为响应体返回非默认类型的对象，SpringMVC 会自动转为 json
    // 必须引入 jackson-databind，必须在 spring 配置文件中配置 <mvc:annotation-driven />
    @RequestMapping("/books")
    @ResponseBody
    public Map<String, Book> getBooks() {
        Map<String, Book> map = new HashMap<>();
        Book book = new Book();
        book.setUsername("东东");
        book.setGender("未知");
        book.setAge(92);
        map.put("books", book);
        return map;
        // 只要是 jackson 能转换的对象就可作为返回值
        // 响应体就是返回值转换后的 json 对象，不会包含模型映射中的属性
        // 要包含模型映射中的属性，可接收 Map 或 ModelMap 参数，并将其返回
    }

    // 方式3：注册视图解析器
    // 阅读官方文档后补充

    @RequestMapping("/mav")
    public ModelAndView mav() {
        // 创建对象时传入目标视图，视图解析器会解析页面的地址
        // ModelAndView hello = new ModelAndView("hello");
        // 也可以在创建后再设置视图
        ModelAndView hello = new ModelAndView();
        hello.setViewName("hello");
        hello.addObject("msg", "你好");
        return hello;
    }

}
