package test.prop.autowired;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import test.prop.autowired.entity.ISay;

@Component
@ConfigurationProperties(prefix = "person")
public class Person implements ISay {
    private String name;

    private int age;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void say() {
        System.out.println(String.format("name=%s,age=%s,address=%s",
                getName(), getAge(), getAddress()));
    }
}
