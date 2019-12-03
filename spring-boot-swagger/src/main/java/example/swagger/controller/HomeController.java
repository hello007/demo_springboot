package example.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Home接口")
@RestController
public class HomeController {

    @ApiOperation(value = "home", notes = "home注意事项", response = String.class)
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String home() {
        return "Hello Swagger!";
    }

    @ApiOperation(value = "接口的功能介绍", notes = "提示接口使用者注意事项")
    @ApiImplicitParam(dataType = "string", name = "name", value = "姓名", required = true)
    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.POST})
    public String hello(String name) {
        return "Hello " + name;
    }

}
