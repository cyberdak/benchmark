package test2;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashMap;
import java.util.Map;

@Fork(value = 1, jvmArgsAppend = "-Djmh.stack.lines=3")
@Warmup(iterations = 2)
@Measurement(iterations = 3)
public class MapTest {

    public static final Map<String,String> map = new HashMap<>();
    public static final Map<String,String> map3 = new HashMap<>();
    public static final Map<Key,String> map1 = new HashMap<>();
    public static final Map<Key,String> map4 = new HashMap<>();

    public static final String[] data = new String[]{"kwn-smy:100","zhy-sdt:200","zhy1-sdt:200"
            ,"zhy2-sdt:200","zhy3-sdt:200","zhy4-sdt:200"};

    static{
        for(String s : data ){
            String[] ss = s.split(":");
            map.put(ss[0],ss[1]);

            String[] qq = ss[0].split("-");

            map1.put(new Key(qq[0],qq[1]),ss[1]);
        }
    }


    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public void test1(Blackhole blackhole) {
        for(String s : data ){
            String[] ss = s.split(":");

            String[] qq = ss[0].split("-");

            blackhole.consume(map.containsKey(qq[0]+"-"+qq[1]));
        }
    }

    @Benchmark
    public void test2(Blackhole blackhole) {

        for(String s : data ){
            String[] ss = s.split(":");

            String[] qq = ss[0].split("-");

            blackhole.consume(map3.containsKey(qq[0]+"-"+qq[1]));

        }
    }



    @Benchmark
    public void test3(Blackhole blackhole) {

        for(String s : data ){
            String[] ss = s.split(":");

            String[] qq = ss[0].split("-");

            blackhole.consume(map4.put(new Key(qq[0],qq[1]),ss[1]));

        }
    }



    @Benchmark
    public void test4(Blackhole blackhole) {

        for(String s : data ){
            String[] ss = s.split(":");

            String[] qq = ss[0].split("-");

            blackhole.consume(map1.put(new Key(qq[0],qq[1]),ss[1]));

        }
    }



}
