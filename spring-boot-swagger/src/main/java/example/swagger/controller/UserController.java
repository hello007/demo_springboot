package example.swagger.controller;

import example.swagger.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Api(tags = "用户操作")
@RestController
public class UserController {

    private AtomicLong atomicLong = new AtomicLong(0);

    private List<User> userList = new ArrayList<>();

    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping("/getAll")
    public List<User> getAll() {
        return userList;
    }

    @ApiOperation(value = "新增用户1", notes = "新增用户1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("addUser")
    public User addUser(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setId(atomicLong.incrementAndGet() + "");
        userList.add(user);
        return user;
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping("/add")
    public User add(@RequestBody @ApiParam(name = "用户对象", value = "json格式", required = true) User user) {
        user.setId(atomicLong.incrementAndGet() + "");
        userList.add(user);
        return user;
    }
}
