package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok h = new HelloLombok();
        h.setName("정창훈");
        h.setAge(31);
        System.out.println(h.getName());
        System.out.println(h.getAge());
        System.out.println("h = " + h);
    }
}
