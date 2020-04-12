## 文件合并实例--- 目前读取文件个数写死 待优化
``` java

package com.luban.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * 拆分文件前个数目前写死 待优化
 */
public class MergeFile {
    public static void main(String[] args) throws Exception {
//        mergeFile1();
        mergeFile2();
    }

    /**
     * 合并文件的基础方法 待优化
     * @throws IOException
     */
    public static void mergeFile1() throws IOException {
        //读取多个拆分后的文件
        ArrayList<FileInputStream> inPut = new ArrayList<>();
        //合并刚拆分的文件 数量先写死 待优化
        for (int i=1;i<6;i++){
            inPut.add(new FileInputStream("D:\\split\\"+i+".part"));
        }
        //指定合并后的文件输出流
        OutputStream out = new FileOutputStream("d:\\bb.zip");

        //将多个输入流依次读入内存，最后依一次性输出
        byte[] bytes = new byte[1024 * 1024];
        for (FileInputStream fileInputStream : inPut) {
            int len = fileInputStream.read(bytes);
            out.write(bytes,0,len);
        }
        out.close();
        for (FileInputStream fileInputStream : inPut) {
            //逐个关闭输入流
            fileInputStream.close();
        }
        System.out.println("合并完成");
    }

    public static void mergeFile2() throws  IOException{
        ArrayList<FileInputStream> inputs = new ArrayList<>();
        for (int i = 1; i <=5 ; i++) {
            inputs.add(new FileInputStream("D:\\split\\"+i+".part"));
        }
        //指定合并后的文件输出流
        OutputStream out = new FileOutputStream("d:\\cc.zip");

        //jdk1.0版本的api
        Enumeration<FileInputStream> enumeration = Collections.enumeration(inputs);
        //多个文件流合并为一个流
        SequenceInputStream sis = new SequenceInputStream(enumeration);

        //输出
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len=sis.read(buf))!=-1){
            out.write(buf,0,len);
        }
        out.close();
        sis.close();
        System.out.println("合并完成");

    }

}

```
