package test.prop.autowired.entity;

public class Pig implements ISay {

    @Override
    public void say() {
        System.out.println("Pig from registrar");
    }
}
