package cn.shuaijunlan.trpc.microbench.serialization;

import cn.shuaijunlan.serialization.fastjson.FastjsonSerialization;
import cn.shuaijunlan.serizlization.java.JavaNativeSerialization;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:37 PM 3/1/19.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
public class SerializationBenchmark {
    private Student student = new Student();


    @Benchmark
    public int fastJsonSerialization() throws IOException, ClassNotFoundException {

        for (int i = 0; i < 100000000; i++){
            student.setName(String.valueOf(i));
            student.setAge(22+i);
            student.setHeight(1.80+i);
            student.setPhone(15927175619L+i);
            student.setLikes(new String[]{"swimming", "running", "eating"});
            assert student.toString().equals(
                    FastjsonSerialization.FASTJSON_SERIALIZATION.deserialize(
                            FastjsonSerialization.FASTJSON_SERIALIZATION.serialize(student)));
        }
        return 0;
    }

    @Benchmark
    public int javaSerialization() throws IOException, ClassNotFoundException {

        for (int i = 0; i < 100000000; i++){
            student.setName(String.valueOf(i));
            student.setAge(22+i);
            student.setHeight(1.80+i);
            student.setPhone(15927175619L+i);
            student.setLikes(new String[]{"swimming", "running", "eating"});
            assert student.toString().equals(
                    JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION.deserialize(
                            JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION.serialize(student)));
        }
        return 0;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SerializationBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(1)
                .measurementIterations(3)
                .build();

        new Runner(opt).run();
    }
}
