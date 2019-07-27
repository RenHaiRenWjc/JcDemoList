package com.wjc.jcdemolist.demo.protoBuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

/**
 * ClassName:com.wjc.jcdemolist.demo.protoBuf
 * Description:
 * JcChen on 2019/7/25 8:24
 */
public class TestProtoBuf {
    public static void main(String[] args) {
        byte[] bytes = serialize();
        System.out.println(Arrays.toString(bytes));
        System.out.println("------------");
        _StudentSerializable._Student student = deserialize(bytes);
        System.out.println(student);
    }

    public static byte[] serialize() {
        _StudentSerializable._Course.Builder courseBuilder = _StudentSerializable._Course.newBuilder()
                .setName("中文").setScore(149);
        _StudentSerializable._Student.Builder studentBuilder = _StudentSerializable._Student.newBuilder();
        studentBuilder.setName("Jc").setAge(16).setSax("man").addCourse(courseBuilder);
        _StudentSerializable._Student student = studentBuilder.build();
        return student.toByteArray();
    }

    public static _StudentSerializable._Student deserialize(byte[] bs) {
        try {
            return _StudentSerializable._Student.parseFrom(bs);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return null;
    }
}
