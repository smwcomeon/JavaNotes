## 读取文件 一行行读取

```java
package com.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IOReadFile {

    private static final Logger LOGGER = LoggerFactory.getLogger(IOReadFile.class);

    /**
     * 读取文件，
     * @param path  文件路径
     * @param spiltStr 行内分隔符
     * @param controlParseNum  读取行数限制
     * @return
     */
    public static Map<String,Object> readFileLimtRow(String path, String spiltStr, int controlParseNum){
        //用于表识中断解析
        boolean parseFlag= false;
        //表头 第一行数据
        List<String> title = new ArrayList<>();
        //数据源
        List<Map<String,Object>> result = new ArrayList<>();
        //源文件中行内字段信息不全的无效数据
        List<String> errorList = new ArrayList<>();
        //封装返回结果
        Map<String,Object> mapResult = new HashMap<>();

        //数据文件源
        File file = new File(path);
        //一行数据
        String lineString = "";
        InputStream is =null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        String[] split ;
        String chartSet ="GBK";
        try{
            chartSet = getFileCharset(file);
        }catch (Exception e){
            LOGGER.info("get charSet error ",e);
        }
        //文件解析开始----start
        try{
            is = new FileInputStream(file);
            isr = new InputStreamReader(is,chartSet);
            reader = new BufferedReader(isr);
            //读取行数
            int rowCount = 1;
            while (reader.readLine()!=null){
                //增加解析行数控制
                if (!parseFlag && controlParseNum !=-1){
                    //第一行数据 表头
                    if (rowCount == 1){
                        split=lineString.split(spiltStr);
                        for (String s : split) {
                            if (title.contains(s)){
                                title.add(s+"&nbsp");
                            }else {
                                title.add(s);
                            }
                        }
                    }
                    else{
                        //第二行起数据
                        split = (lineString + spiltStr+"a").split(spiltStr);//避免除第一行数据外都是空的数据而造成无效数据
                        if (title.size() !=split.length-1){
                            errorList.add("文件第"+rowCount+"行数据错误，字段长度与第一行数据标题不对应");
                        }
                        else {
                            Map<String,Object> rowData = new HashMap<>();
                            for (int i = 0;i<title.size();i++){
                                if (rowData.containsKey(title.get(i))){
                                    //数据字段重复业务处理
                                    rowData.put(title.get(i)+"&nbsp;",split[i]);
                                }else {
                                    rowData.put(title.get(i),split[i]);
                                }
                            }
                            result.add(rowData);//添加每行数据解析之后的map
                        }
                    }
                }
                //添加解析行数限制
                if (rowCount -1 == controlParseNum){
                    parseFlag = true;
                    break;
                }
                rowCount++;
            }
            mapResult.put("result",result);
            mapResult.put("title",title);
            mapResult.put("total",rowCount-2);//除第一行标题之外的有效数据总数

        }catch (Exception e){
            LOGGER.error("readFileLimtRow error",e);
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error("BufferReader close error",e);
                }
            }
            if (isr!=null){
                try {
                    isr.close();
                } catch (IOException e) {
                    LOGGER.error("InputStreamReader close error",e);
                }
            }
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("InputStram close error",e);
                }
            }
        }
        return  mapResult;
    }

    //判断编码格式方法
    private static String getFileCharset(File file) {
        String charset ="GBK";
        byte[] first3Bytes = new byte[3];
        FileInputStream fileInputStream = null;
        BufferedInputStream bis = null;
        try{
            boolean check = false;
            fileInputStream = new FileInputStream(file);
            bis = new BufferedInputStream(fileInputStream);
            bis.mark(0);
            int read = bis.read(first3Bytes,0,3);
            if (read != -1){
                return  charset; //文件编码为ANSI
            }else if (first3Bytes[0] == (byte) 0xFF
                    && first3Bytes[1] == (byte) 0xFE){
                charset = "UTF-16LE";
                check =true;
            }else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF){
                charset = "UTF-16BE"; //文件编码为 Unicode big endian
                check =true;
            }else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte)0xBF){
                charset = "UTF-8";
                check = true;
            }
            bis.reset();
            if (!check){
                int loc = 0;
                while ((read = bis.read()) !=-1){
                  loc++;
                  if (read >= 0xF0){
                      break;
                  }
                  if (0x80<=read && read <= 0xBF){//单独出现BF以下的 也算GBK
                      break;
                  }
                  if(0xc0 <= read && read<=0xDF){
                      read = bis.read();
                      if (0x80<=read && read<=0xBF){//双字节（0xc0 - 0xDF）
                          continue;
                      }else {
                          break;
                      }
                  }else if (0xE0 <= read && read <=0xEF){//也有可能出错
                        read = bis.read();
                        if(0x80<=read && read<=0xBF){
                            read =bis.read();
                            if (0x80<=read && read<=0xBF){
                                charset = "UTF-8";
                                break;
                            }else {
                                break;
                            }
                        }else {
                            break;
                        }
                  }
                }
            }
            bis.close();

        }catch (Exception e){
            LOGGER.error("getFileCharset",e);
        }finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    LOGGER.error("fileInputStream close() error",e);
                }
            }
            if (bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    LOGGER.error("InputStream close() error",e);
                }
            }
        }
        return charset;
    }

}

```
