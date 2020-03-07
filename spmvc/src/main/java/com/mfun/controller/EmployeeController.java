package com.mfun.controller;

import com.mfun.bean.Department;
import com.mfun.bean.Employee;
import com.mfun.dao.DepartmentDao;
import com.mfun.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    private final EmployeeDao employeeDao;
    private final DepartmentDao departmentDao;

    @Autowired
    public EmployeeController(EmployeeDao employeeDao, DepartmentDao departmentDao) {
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
    }

    @GetMapping("/employees")
    public String getEmployees(ModelMap map) {
        Collection<Employee> employees = employeeDao.getAll();
        map.addAttribute("employees", employees);
        return "list";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAdd(ModelMap map, HttpServletRequest request) {
        Collection<Department> departments = departmentDao.getAll();
        map.addAttribute("departments", departments);
        if (request.getAttribute("employee") == null) {
            map.addAttribute("employee", new Employee());
        }
        return "add";
    }

    @PostMapping("/employee")
    public String addEmployee(@Valid Employee employee, BindingResult bindingResult, ModelMap map) {
        // 判断是否有校验错误
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            map.addAttribute("errors", errorMap);
           return "forward:/add";
        } else {
            employeeDao.save(employee);
            return "redirect:/employees";
        }
    }

    @ResponseBody
    @GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String toEdit(@PathVariable int id, ModelMap map) {
        Employee employee = employeeDao.getOne(id);
        map.addAttribute("employee", employee);
        map.addAttribute("departments", departmentDao.getAll());
        System.out.println();
        return "edit";
    }

    // 路径参数也会被用于注入员工属性，所以，封装的对象具有 id 属性，但没有 name 属性，需要预读取
    @PutMapping("/employee/{id}")
    public String updateEmployee(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/employees";
    }

    // 虽然 ModelAttribute 方法可以解析 PathVariable，但是，由于它会在所有请求之前执行，不携带该路径参数的请求就会因没有该参数而报错
    // RequestParam 则可以设置 required 的属性，只在携带所需参数时才预加载数据
    @ModelAttribute
    public void preGetEmployee(@RequestParam(value = "id", required = false) Integer id, ModelMap map) {
        if (id != null) {
            map.addAttribute("employee", employeeDao.getOne(id));
        }
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/employees";
    }

    @PostMapping("/quickadd")
    public String quickAdd(@RequestParam("empinfo") Employee employee) {
        employeeDao.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/entity")
    public ResponseEntity<Employee> getEntity() {
        Employee body = new Employee(0, "测试", "测试@tt.com", 10, departmentDao.getOne(101));
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("ContentType", "application/json");
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(body, headers, status);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        // 1. 获取要下载的文件
        String realPath = servletContext.getRealPath("WEB-INF/path/to/file");
        String fileName = realPath.substring(realPath.lastIndexOf("/") + 1);
        try(FileInputStream in = new FileInputStream(realPath)) {
            byte[] temp = new byte[in.available()];
            in.read(temp);
            // 2. 设置响应头
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            // 3. 将文件内容作为响应体
            return new ResponseEntity<>(temp, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
