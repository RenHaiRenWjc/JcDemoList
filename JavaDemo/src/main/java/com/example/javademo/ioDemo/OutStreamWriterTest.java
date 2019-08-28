package com.example.javademo.ioDemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * ClassName:com.example.javademo.ioDemo
 * Description: 字符流
 * JcChen on 2019/8/7 23:36
 */
public class OutStreamWriterTest {
    private static final String string = "Jc";

    public static void main(String[] args) throws IOException {
        testOutStreamWriter();
        testInputStreamRead();

    }

    public static void testOutStreamWriter() throws IOException {
        File file = new File("G:/IT/wjc_proj/JcDemoList/JavaDemo/src/txt/OutStreamWriter.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file, true);
        writer(fileOutputStream, null);
        writer(fileOutputStream, "GBK");
        writer(fileOutputStream, "UTF-8");

    }

    private static void writer(FileOutputStream fileOutputStream, String encoding) throws IOException {
        OutputStreamWriter outputStreamWriter;
        if (encoding == null) {
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        } else {
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, encoding);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(string);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        System.out.println(outputStreamWriter.getEncoding());
    }

    private static void testInputStreamRead() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(
                new File("G:/IT/wjc_proj/JcDemoList/JavaDemo/src/txt/OutStreamWriter.txt"));
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"GBK");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            System.out.println("code:" + inputStreamReader.getEncoding());
            System.out.println(s);
        }
        bufferedReader.close();
    }

}
