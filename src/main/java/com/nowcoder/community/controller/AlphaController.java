package com.nowcoder.community.controller;


import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired // 注入依赖 从业务请求注入依赖
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String SayHello(){
        return "hello spring boot    first blood";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    // 获得请求对象和响应数据
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        // GET
        System.out.println(request.getMethod()); // 请求数据
        // /alpha/http
        System.out.println(request.getServletPath()); // 请求的路径
        // 请求行的key， 得到的是一个迭代器  （k-v）
        Enumeration<String> enumeration = request.getHeaderNames();
        // 请求得到若干行的数据
        // print the kv
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        // 得到返回的参数，在网址后面加问号，然后用键值对的形式返回
        System.out.println(request.getParameter("code"));

        // response 向浏览器返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 基于request 接受请求数据
    // 两种获取服务器数据的方式
    // GET 请求，用于获取某些数据，默认是GET
    // 查询所有学生 /student？current=1 & limit=20 ，第几页，一页几个
    // 表示访问的路径和明确的请求方式
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    // 基于responce 处理返回数据
    @ResponseBody
    public String getStudents(@RequestParam(name = "current", required = false, defaultValue = "1") int current,
                              @RequestParam(name = "limit", required = false, defaultValue = "10")int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some student"; // 在网页上显示
    }

    // 查询一个学生，根据id /studnet/{id}  通过网页的网址返回后面id的参数
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id")int id){
        System.out.println(id);
        return "a student";
    }

    // 浏览器向服务器提交数据  通过浏览器输入数据，将数据返回给服务器
    // POST请求
    // 访问路径http://localhost:8080/community/html/student.html  因为在static.html提交了一个网页表单，网页表单中的内容应该和此处的关键词一致
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

//    //failed 失败了暂时没有找到原因 这里ModelAndView中add的对象内容名字必须和模板表单中的名字一致
//    // 向浏览器响应动态html数据 因为返回html，所以不加responsebody
//    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
//    public ModelAndView getTeacher(){ // 返回model和视图
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("name", "张三");
//        mav.addObject("age", 30);
//        mav.setViewName("/demo/view");
//        return mav;
//    }
//    // 下面这个方法相较于上面更快捷
//    @RequestMapping(path = "/school", method = RequestMethod.GET)
//    public String getSchool(Model model){
//        model.addAttribute("name", "北大");
//        model.addAttribute("age", 80);
//        return "/demo/view";
//    }

    // 响应HTML数据 from nowcoder

    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "北京大学");
        model.addAttribute("age", 80);
        return "/demo/view";
    }


    // succ
    // 异步请求当中响应json数据  当前网页不刷新，但是还是访问了数据库
    // json是从java对象到JS的桥梁
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody  // map->json 返回json字符串
    public Map<String, Object> getMap(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", "20");
        emp.put("Salary", "8000");
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody  // map->json 返回json字符串
    public List<Map<String, Object>> getMaps(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", "20");
        emp.put("Salary", "8000");
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name", "李四");
        emp.put("age", "20");
        emp.put("Salary", "8000");

        list.add(emp);
        return list;
    }


}


