package example.blog.mapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuyang
 * E-mail: liuyang06@agree.com.cn
 * 创建时间: 2019/12/2 14:59
 */
@RestController
public class BlogController {

    @RequestMapping("/blog")
    public String blog() {
        return "blog";
    }
}
