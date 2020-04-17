package objects;

import annotations.Component;
import annotations.IntValue;
import annotations.StringValue;
import objects.interfaces.Playable;

import java.lang.reflect.Array;

@Component
public class Dog implements Playable {

    @StringValue("Boris")
    private String name;

    @StringValue("Black")
    private String color;

    @IntValue
    private int age;

    public Dog(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public Dog() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void play() {
        System.out.println(this.name + " plays with a bone");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }
}
