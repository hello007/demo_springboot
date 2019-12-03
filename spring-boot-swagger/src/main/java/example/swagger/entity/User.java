package example.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户实体类", description = "表示一个用户对象")
public class User {
    @ApiModelProperty(hidden =  true)
    private String id;

    @ApiModelProperty(value = "用户名", example = "liu", required = true)
    private String name;

    @ApiModelProperty(value = "用户密码", example = "111111", required = true)
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
