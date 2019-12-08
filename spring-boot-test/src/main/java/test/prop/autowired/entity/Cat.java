package test.prop.autowired.entity;

public class Cat implements ISay {
    @Override
    public void say() {
        System.out.println("喵喵喵！ ImportSelect!!");
    }
}
