package cn.shuaijunlan.trpc.microbench.serialization;

import cn.shuaijunlan.serialization.fastjson.FastjsonSerialization;
import cn.shuaijunlan.serizlization.java.JavaNativeSerialization;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:37 PM 3/1/19.
 * TODO: reference:https://www.xncoding.com/2018/01/09/java/jsons.html
 */
@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.SECONDS)
// @BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SerializationBenchmark {
    private Student student = new Student();


    @Param({"1"})
    private int count;

    @Param({"1", "10", "100"})
    private int strs;

    @Benchmark
    public void fastJsonSerialization() throws IOException, ClassNotFoundException {
        student.setEducations(new LinkedList<>());

        for (int i = 0; i < count; i++){
            student.setName(String.valueOf(i));
            student.setAge(22+i);
            student.setHeight(1.80+i);
            student.setPhone(15927175619L+i);
            student.setLikes(new String[]{"swimming", "running", "eating"});

            List<String> list = student.getEducations();
            for (int j = 0; j < strs; j ++){
                list.add(String.valueOf(j));
            }
            student.setEducations(list);


            FastjsonSerialization.FASTJSON_SERIALIZATION.deserialize(
                    FastjsonSerialization.FASTJSON_SERIALIZATION.serialize(student));
        }
    }

    @Benchmark
    public void javaSerialization() throws IOException, ClassNotFoundException {
        student.setEducations(new LinkedList<>());

        for (int i = 0; i < count; i++){
            student.setName(String.valueOf(i));
            student.setAge(22+i);
            student.setHeight(1.80+i);
            student.setPhone(15927175619L+i);
            student.setLikes(new String[]{"swimming", "running", "eating"});

            List<String> list = student.getEducations();
            for (int j = 0; j < strs; j ++){
                list.add(String.valueOf(j));
            }
            student.setEducations(list);

            JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION.deserialize(
                    JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION.serialize(student));
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SerializationBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(0)
                .measurementIterations(2)
                .build();

        new Runner(opt).run();
    }
}
