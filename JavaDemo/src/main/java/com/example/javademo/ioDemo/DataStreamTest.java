package com.example.javademo.ioDemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ClassName:com.example.javademo.ioDemo
 * Description:
 * JcChen on 2019/8/7 22:55
 */
public class DataStreamTest {
    private static final byte[] byteArray = {0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A};

    public static void main(String[] args) {
           dataOutputStream();
           dataInputStream();
    }

    private static void dataOutputStream() {
        File file = new File("G:/IT/wjc_proj/JcDemoList/JavaDemo/src/txt/DataStreamTest.txt");
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
//            dataOutputStream.write(byteArray, 0, byteArray.length - 1);
            dataOutputStream.writeBoolean(true);
            dataOutputStream.writeChars("j");
            dataOutputStream.writeUTF("jc");
            dataOutputStream.writeDouble(2012);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void dataInputStream() {
        File file = new File("G:/IT/wjc_proj/JcDemoList/JavaDemo/src/txt/DataStreamTest.txt");
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            System.out.println(dataInputStream.readBoolean());
            System.out.println(dataInputStream.readChar());
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readDouble());
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
