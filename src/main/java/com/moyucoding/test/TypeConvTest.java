package com.moyucoding.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

//@State()
@Fork(value = 1, jvmArgsAppend = "-Djmh.stack.lines=3")
@Warmup(iterations = 2)
@Measurement(iterations = 3)
public class TypeConvTest {
    public static final int x = 10001;
    public static final int y = 20429;
    public static final String x1 = "10001";
    public static final String y1 = "20429";


    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public void test1(Blackhole blackhole) {
        blackhole.consume(x==y);
    }

    @Benchmark
    public void test2(Blackhole blackhole) {
        blackhole.consume(String.valueOf(x).equals(String.valueOf(y)));
    }



    @Benchmark
    public void test3(Blackhole blackhole) {
        blackhole.consume(x1.equals(y1));
    }
}
