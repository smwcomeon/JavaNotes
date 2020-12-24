package com.JDKStream;

import com.JDKStream.salary.Employer;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用：若Lamdba体中的内容有方法已经实现了，我们可以使用“方法引用”
 *          可以理解为方法引用是lamdba 表达式的另外一种变现形式
 * 主要有三种语法格式:
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 *
 * 二 构造器引用：
 *      格式：ClassName::new
 *      需要调用的构造器的参数列表要与函数式接口中的抽象方法的参数列表保持一致
 *
 *  三 数组引用
 *  Type[]::new
 *
 */
public class TestMethodRef {

    @Test
    public void test5(){
        //lamdba表达式方法
        Function<Integer,String[]> function = (x)->new String[x];
        String[] apply = function.apply(11);
        System.out.println(apply.length);

        //数组类型引用
        Function<Integer,String[]> fun2 = String[]::new;
        System.out.println(fun2.apply(12).length);

    }

    //类::静态方法名
    @Test
    public void test4(){
        Supplier<Employer> sup = Employer::new;
        System.out.println(sup.get());

        Function<Integer,Employer> fun = Employer::new;
        Employer apply = fun.apply(11);
        System.out.println(apply);

    }


    @Test
    public void test3(){
        Comparable<Integer> com = (x)-> Integer.compare(x,5);
        int i = com.compareTo(1);
        System.out.println(i);


        BiPredicate<String,String> biPre= (x,y)->x.equals(y);

        BiPredicate<String,String> biPre2 = String::equals;
        boolean test = biPre2.test("A", "B");
        System.out.println(test);


//        Comparable<Integer> com2 =Integer::compare;

    }

    //对象引用
    @Test
    public void test1(){
        PrintStream ps1= System.out;
        Consumer con = (x)->ps1.println(x);

        Consumer con2 = ps1::println;

        Consumer<String> consumer =System.out::println;
        consumer.accept("aaaaaaaa");
    }

    @Test
    public void test2(){
        Employer emp = new Employer();

        Supplier<String> supp1= () -> emp.getName();
        String s = supp1.get();
        System.out.println(s);

        Supplier<Integer> supp2 = emp::getAge;
        Integer integer = supp2.get();
        System.out.println("integer"+integer);


    }


}
