# 总结日志

* 学习了Spring boot、Spring MVC还有mybatis
** **

## Spring boot 
__作用__：通过IDEA在浏览器上进行字符串的展示。同时能决定打开哪个网页网址的内容；  
__特点__：快；配置少，自动配置更简单；利于项目管理；尽可能减少XML文件配置，做到“开箱即用”；让程序远做到尽可能关注业务而不是配置；最大特点：约定大于配置；  

__学习内容__: 创建一个新的java项目，如何从头配置，设置路径，新项目中各部分的含义。有四种方式可以是程序扫描到Bean容器。
`@Repository(), @Controller, @Service, @Configuration`四种特殊的注解。
`@RequestMapping(“a addrest”)`代表哪个网页可以访问到这个对象

### 基于url的传参方法：
从浏览器url上获取数据
```java   
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
```
`@RequestMapping(path ,method)`,method是get的两种传参方式。第一种是可以通过判断哪些参数必须要有，没有的话默认值是多少
第二种是通过注解`@PathVariable()`来指定需要的参数并传进来

`@RequestMapping(path = "/students", method = RequestMethod.GET)` method有HTTP请求方法（GET、POST 方法等）
直接在浏览器地址栏输入某个地址、点击链接、表单默认的提交方式都是会发送get请求进入@GetMapping注解的方法中


__get请求的特点__：

（1）请求参数会添加到请求资源路径的后面，只能添加少量参数（只能存放2K左右的数据）

（2）请求参数会显示在浏览器地址栏，路由器会记录请求地址

__post请求的特点__:

（1）请求参数添加到实体内容里面，可以添加大量的参数（浏览器输入地址时我们只填写了URL，所以参数是不能加入Http包的实体当中）

（2）相对安全（post请求不会对请求参数进行加密处理，可以使用https协议来保证数据安全）


### 浏览器通过表单返回数据
```java     
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
```
配合`html`文件
```html
<form method="post" action="/community/alpha/student">
        <p>
            姓名： <input type="text" name="name"> 
            <!__ type代表类型，name关键字代表对应的上面java函数的参数,注意拼写错误__>
        </p>
        <p>
            年龄 <input type="text" name="age">
        </p>
        <p>
            <input type="submit" value="保存">
            <!__ input 代表有个上传提交的操作， value是按钮的名称__>
        </p>
    </form>
```

** **

### Spring MVC
M: Module(模型层)指工程中的JavaBean,用来处理数据库映射数据
JavaBean分为两类:
一类是实体类Bean: 专门存储业务数据的,如: Student、User
一类是业务逻辑Bean: 指Service与Dao类,用来处理业务之间的逻辑和数据访问

V: View(视图层) 指html与jsp等页面,用于与用户进行交互,展示数据

C: Controller(控制层) 指的是Servlet程序,用于接收客户端发送的请求与响应浏览器


@RequestMapping的注解功能
从名称我们可以看出,RequestMapping是将请求和处理请求的控制器关联起来
SpringMVC接收到请求,就会来找关系映射中对应的控制器方法来处理请求

@RequestMapping标识一个类: 设置映射请求的请求路径的起始路径
@RequestMapping标识一个方法: 设置映射请求的请求路径的实际文件
```@Controller
@RequestMapping("/test")
public class HelloController2 {
    //此时的请求路径为: /test/index
    @RequestMapping("/index")  //  /表示请求地址为: http://ip:port/工程路径/
    public String index(){
        //返回字符串index, 表示当前访问带/下的路径后,请求映射的访问地址: 视图前缀+视图名称+视图后缀
        return "target";
    }
}
```


### @Reponsebody 注解
__概念__:注解 @ResponseBody，使用在控制层（controller）的方法上。  
__作用__：将方法的返回值，以特定的格式写入到response的body区域，进而将数据返回给客户端。
当方法上面没有写ResponseBody,底层会将方法的返回值封装为ModelAndView对象。
如果返回值是字符串，那么直接将字符串写到客户端；如果是一个对象，会将对象转化为json串，然后写到客户端。

```java  
   @RequestMapping("/login")
　　@ResponseBody
　　public User login(User user){
　　　　return user;
　　}
　　User字段：userName pwd;
　　那么在前台接收到的数据为：'{"userName":"xxx","pwd":"xxx"}'

　　效果等同于如下代码：
　　@RequestMapping("/login")
　　public void login(User user, HttpServletResponse response){
              //通过response对象输出指定格式的数据
　　　　response.getWriter.write(JSONObject.fromObject(user).toString());
　　}

```
#### `@Responsebody`两个作用  
+ `ModelAndView`对象可以返回数据在浏览器上
```java  
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
```

不写@Responbady默认是返回html
通过html来声明数据
```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<!__ 声明thymeleaf模板 __>
<head>
    <meta charset="UTF-8">
    <title>Teacher</title>
</head>
<body>
    <p th:text="${name}"></p>
    <p th:text="${age}"></p>
</body>
</html>
```
+ 响应json数据 （在异步请求中，当前网页不刷新，悄悄的访问服务器并返回结果，用@ResponBody注解 json起到衔接的作用，将java对象可以转为js对象（跨语言，客户端需要一个局部验证的结果，是否成功失败）
  实例化map，向对象里面存一些值，返回（就对象转为json字符串）
```java  
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
```