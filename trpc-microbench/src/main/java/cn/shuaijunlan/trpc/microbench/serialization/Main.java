package cn.shuaijunlan.trpc.microbench.serialization;

import cn.shuaijunlan.serizlization.java.JavaNativeSerialization;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 6:36 PM 3/1/19.
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Student student = new Student();
        student.setEducations(new LinkedList<>());
        for (int j = 0; j < 10; j++){
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++){
                student.setName(String.valueOf(i));
                student.setAge(22+i);
                student.setHeight(1.80+i);
                student.setPhone(15927175619L+i);
                student.setLikes(new String[]{"swimming", "running", "eating"});

                List<String> list = student.getEducations();
                list.add(String.valueOf(i));
                student.setEducations(list);
                // System.out.println(student.toString());
                assert student.toString().equals(
                        JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION.deserialize(
                                JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION.serialize(student)));
            }
            System.out.println(System.currentTimeMillis()-start);
        }


    }
}
