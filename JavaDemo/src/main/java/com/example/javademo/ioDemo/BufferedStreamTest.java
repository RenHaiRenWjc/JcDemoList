package com.example.javademo.ioDemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;

/**
 * ClassName:com.example.javademo.ioDemo
 * Description:  字节流
 * JcChen on 2019/8/7 8:31
 */
public class BufferedStreamTest {
    private static final byte[] byteArray = {0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A};


    public static void main(String[] args) {
        bufferedOutputStream();
        bufferedInputStream();
    }

    private static void bufferedOutputStream() {//输出流，从内存写入文件
        File file = new File("G:/IT/wjc_proj/JcDemoList/JavaDemo/src/txt/BufferedStreamTest.txt");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(byteArray[0]);
            bos.write(byteArray, 1, byteArray.length - 1);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bufferedInputStream() {// 输入流，从文件到内存
        File file = new File("G:/IT/wjc_proj/JcDemoList/JavaDemo/src/txt/BufferedStreamTest.txt");
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            for (int i = 0; i < 10; i++) {
                if (bis.available() >= 0) {
                    System.out.println(byteToString((byte) bis.read()));
                }
            }
            bis.mark(6);//标记当前流位置
            bis.skip(10);
            byte[] b = new byte[1024];
            int n1 = bis.read(b, 0, b.length );
            System.out.println("----remain byte:" + n1);
            printByteValue(b);
            bis.reset();//从标记位置重新读取
            int n2 = bis.read(b, 0, b.length);
            System.out.println("----remain byte:" + n2);
            printByteValue(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String byteToString(byte b) {
        byte[] barray = {b};
        return new String(barray);
    }

    private static void printByteValue(byte[] buf) {
        for (byte b : buf) {
            if (b != 0) {
                System.out.print(byteToString(b) + " ");
            }
        }
    }
}
