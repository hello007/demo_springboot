package example.blog;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuyang
 * E-mail: liuyang06@agree.com.cn
 * 创建时间: 2019/12/2 11:02
 */
@RestController
public class MappingManager {

    @RequestMapping("/addLiu")
    public String add(String in) {
        return in + "addMapping!";
    }
}
