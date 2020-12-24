package com.JDKStream;

import com.JDKStream.salary.Employer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 一、Stream
 */
public class TestStreamApi {

    List<Employer> emps =Arrays.asList(
            new Employer("张三",15,77.44),
            new Employer("李四",58,9999.33),
            new Employer("王五",43,8888.88),
            new Employer("赵六",10,6666.66),
            new Employer("田七",36,123456.77),
            new Employer("田七",36,123456.77)

    );
    List<Employer> employers =Arrays.asList(
            new Employer("张三",15,88886.44, Employer.Status.FREE),
            new Employer("李四",58,9999.33, Employer.Status.BUSY),
            new Employer("王五",43,8888.88, Employer.Status.VOCATION),
            new Employer("赵六",10,6666.66, Employer.Status.BUSY),
            new Employer("田七",36,123456.77, Employer.Status.FREE)
    );

    /**
     * 终止操作
     * 查找与匹配
     *  allMatch ： 检查是否匹配所有元素，全部匹配
     *  anyMatch ：检车是否至少匹配一个元素
     *  nonoMatch : 检查是否没有匹配所有元素，全部匹配
     *  findFirst ： 返回第一个元素
     *  findAny : 返回当前流中的任意元素
     *  count ： 返回流中元素个数
     *  max 返回流中最大值
     *  min 返回流中最小值
     *
     */
    @Test
    public void testStop(){
        //检验是否全部匹配
        boolean allMatch = employers.stream()
                .allMatch((e) -> e.getStatus().equals(Employer.Status.FREE));
        System.out.println("allMatch=="+allMatch);

        //检验是否全部匹配
        boolean anyMatch = employers.stream()
                .anyMatch((e) -> e.getStatus().equals(Employer.Status.FREE));
        System.out.println("anyMatch=="+anyMatch);

        //检验是否全不匹配
        boolean nonoMatch = employers.stream()
                .noneMatch((e) -> e.getStatus().equals(Employer.Status.FREE));
        System.out.println("nonoMatch=="+nonoMatch);

        //匹配工资最低的
        Optional<Employer> findFirst = employers.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalry(), e2.getSalry()))
                .findFirst();
        System.out.println("findFirst==="+findFirst.get());

        //随机返回满足条件的一个
        Optional<Employer> findAny = employers.parallelStream() //并行流
                .filter((e1) ->e1.getStatus().equals(Employer.Status.BUSY))
                .findAny();
        System.out.println("findAny==="+findAny.get());

        //随机返回满足条件的个数
        Long count = employers.parallelStream() //并行流
                .filter((e1) ->e1.getStatus().equals(Employer.Status.BUSY))
                .count();
        System.out.println("count==="+count);

        //返回满足条件最大的
        Optional<Employer> max = employers.stream()
                .max((e1, e2) -> Double.compare(e1.getSalry(), e2.getSalry()));
        System.out.println("max==="+max);

        //返回满足条件最小的工资 三种写法
        Optional<Employer> min1 = employers.stream()
                .min((e1, e2) -> Double.compare(e1.getSalry(), e2.getSalry()));
        System.out.println("min1==="+min1.get().getSalry());

        Optional<Double> min2 = employers.stream()
                .map(Employer::getSalry)
                .min((e1, e2) -> Double.compare(e1, e2));
        System.out.println("min2==="+min2.get());

        Optional<Double> min3= employers.stream()
                .map(Employer::getSalry)
                .min(Double::compareTo);
        System.out.println("min3=="+min3.get());


    }

    /**
     * 中间操作
     *  排序
     *     sorted()  自然排序
     *     sorted(Comparator com) 定制排序(Comparator)
     */
    @Test
    public void testSort(){
        List<String> list  = Arrays.asList("ccc","bbb","aaa","ddd","eee");

        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("==============================");

        emps.stream()
                .sorted((e1,e2)->{
                    if (e1.getAge().equals(e2.getAge())){
                        return  e1.getName().compareTo(e2.getName());
                    }else {
                        return -e1.getAge().compareTo(e2.getAge());
                    }
                }).forEach(System.out::println);
    }

    /**
     * 映射
     * map 接收Lamdba，将元素转换成器他形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * faltMap 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流连接成一个流
     */
    @Test
    public void testMap(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");

        list.stream()
                .map((x)->x.toUpperCase()) //把字符串转换成大写
                .forEach(System.out::println);

        System.out.println("=============================");

        this.emps.stream()
                .map(Employer::getName)  //类名::调用
                .forEach(System.out::println);

        System.out.println("=============================");

        //编写一个函数作为参数传入
        Stream<Stream<Character>> streamStream = list.stream()
                .map(TestStreamApi::filterCharcter);
        //双重遍历
        streamStream.forEach((emp)->{
            emp.forEach(System.out::println);
        });

        System.out.println("==================flatMap=========================");

        //faltMap
        list.stream()
                .flatMap(TestStreamApi::filterCharcter)
                .forEach(System.out::println);


    }

    public static Stream<Character> filterCharcter(String str){
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }
    @Test
    public void  test3(){
        //limit
        emps.stream()
                .filter((e)->e.getSalry()>5000)
                .limit(2)
                .forEach(System.out::println);
        System.out.println("=================================");

        //skip
        emps.stream()
                .filter((e)->e.getSalry()>5000)
                .skip(2)
                .forEach(System.out::println);

        System.out.println("=================================");

        //distinct
        emps.stream()
                .filter((e)->e.getSalry()>5000)
                .skip(2)
                .distinct() //注意 需要重写hashCode 和equals方法
                .forEach(System.out::println);
    }

    /**
     *  筛选与切片
     *  filter--接受Lamdba，从流中排除某些元素
     *  limit -- 截断流，使其元素不超过给定数量
     *  skip（n） 跳过元素 返回一个去处前n个元素的流，若流中不足nge，则返回一个空流。与limit互补
     *  distinct 筛选通过流所生成的元素的hashCode（）和equals（）去除重复的元素
     *
     */
    @Test
    public void test2(){
        //中间操作不会做出任何处理，除非碰见终止操作
        Stream<Employer> emp =emps.stream()
                .filter(
                        (x)->{
//                            System.out.println("Stream中间操作。。。。。");
                            return x.getAge()>30;
                        }
                );

        //终止操作
        emp.forEach(System.out::println);



    }

    //创建Stream的方法
    @Test
    public void test1(){
        //1、可以通过Collection系列集合提供的stream()或paralleStream（）
        List<String> list = new ArrayList<>();

        Stream<String> stream = list.stream();

        //2、通过Arrays中的静态方法stream()获取数组流
        Employer[] emps= new Employer[10];
        Stream<Employer> stream1 = Arrays.stream(emps);

        //3、通过Stream类中的静态发方法of（）
        Stream<String> stream2 = Stream.of("a","b","c");

        //4、创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0,(x)->2+x);
        stream3.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(()->Math.random()).limit(10).forEach(System.out::println);

    }
}
