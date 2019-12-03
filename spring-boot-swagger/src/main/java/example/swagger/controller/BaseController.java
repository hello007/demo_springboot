package example.swagger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class BaseController {

    @RequestMapping("/*")
    public String base(HttpServletRequest request, HttpServletResponse response) {
        return "base controller!!";
    }
}
