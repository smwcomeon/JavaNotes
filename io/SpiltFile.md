## 1.文件拆分
```java
package com.luban.io;

import java.io.*;
import java.util.Properties;

public class SpiltFile {
    public static void main(String[] args) throws Exception {
        //源文件地址
        File inFile = new File("d:\\aa.zip"); //5,171,978 字节
        File outFile = new File("d:\\split");
        if(!outFile.exists()){
            //创建单级目录
//            outFile.mkdir();
            outFile.mkdirs();
        }


        //文件拆分
        splitFile1(inFile,outFile);
    }


    /**
     * 拆分文件基础版 1
     * @param inFile
     * @param outFile
     * @throws IOException
     */
    private static void splitFile1(File inFile,File outFile) throws IOException {
        //拆分 1一个输入流
        FileInputStream in = new FileInputStream(inFile);
        //文件输出流
        OutputStream out =null;

        //创建缓存数组
        byte[] buf = new byte[1024 * 1024];
        int len = -1;
        int count =1;

        while ((len = in.read(buf))!=-1){
            //文件拆分文件个数先写死
           out = new FileOutputStream(new File(outFile,count++ +".part"));
           //读写一次 一次1M直接写入文件中
           out.write(buf,0,len);
           out.close();
        }
        //优化升级：把文件拆分前的文件名、分割数量等信息保存到一个配置文件中
        //方式一:

        /*//创建一个写配置文件的输出流
        out = new FileOutputStream(new File(outFile,count+".properties"));
        //获取不同操作系统的换行符
        String lineSeparator = System.getProperty("line.separator");
        out.write(("filename="+inFile.getName()).getBytes());
        out.write(lineSeparator.getBytes());
        out.write(("partcount="+ --count).getBytes());
        out.close();*/

        //方法二
        out = new FileOutputStream(new File(outFile,count+".properties"));
        Properties properties = new Properties();
        properties.setProperty("filename",inFile.getName());
        properties.setProperty("partcount",(count-1)+"");
        //添加中文备注文件乱码 未解决
        properties.store(new OutputStreamWriter(out,"utf-8"),"拆分文件属性");


        //关闭输入流
        in.close();
        System.out.println("文件拆分完成");
    }
}

```
## 2.文件合并

