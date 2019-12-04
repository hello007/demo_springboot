package example.swagger.controller;

import example.swagger.annotation.ExposeSwaggerApi;
import example.swagger.annotation.ExposeSwaggerMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "统一入口")
@RestController
@ExposeSwaggerApi
public class BaseController {

    /**
     * 定义多种请求方式，可以使用RequestMapping注解的method属性
     *
     * @return
     */
    @ApiOperation(value = "home", notes = "home注意事项", response = String.class)
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String home() {
        return "Hello Swagger!";
    }

    @ApiOperation(value = "接口的功能介绍", notes = "提示接口使用者注意事项")
    @ApiImplicitParam(dataType = "string", name = "name", value = "姓名", required = true)
    @RequestMapping(value = "/hello", method = {RequestMethod.GET})
    @ExposeSwaggerMethod
    public String hello(String name) {
        return "Hello " + name;
    }

    @RequestMapping("/*")
    public String base(HttpServletRequest request, HttpServletResponse response) {
        return "base controller!!";
    }
}
