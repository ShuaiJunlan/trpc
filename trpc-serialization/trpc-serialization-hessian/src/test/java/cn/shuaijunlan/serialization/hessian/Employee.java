package cn.shuaijunlan.serialization.hessian;

import java.io.Serializable;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 6:36 PM 2/28/19.
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 10000L;
    String name;
    Integer age;
    Integer height;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}