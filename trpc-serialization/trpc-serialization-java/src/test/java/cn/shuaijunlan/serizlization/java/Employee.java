package cn.shuaijunlan.serizlization.java;

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

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}