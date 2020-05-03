package com.wjc.jcdemolist.demo.jsonDemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.wjc.jcdemolist.R;
import com.wjc.jcdemolist.Utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonActivity extends AppCompatActivity {
    private static final String TAG = "JsonActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        try {
            createJson();
            parseJson();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createJson() throws JSONException, IOException {
        File file = new File(getFilesDir(), "jc.json");
        LogUtils.i(TAG, "createJson: dir=" + getFilesDir());
        JSONObject student = new JSONObject();
        student.put("name", "jc");
        student.put("sex", "男");
        student.put("age", 27);

        JSONObject course1 = new JSONObject();
        course1.put("name", "语文");
        course1.put("score", 99.9f);

        JSONObject course2 = new JSONObject();
        course2.put("name", "English");
        course2.put("score", 99.8f);
        JSONArray courses = new JSONArray();
        courses.put(0, course1);
        courses.put(1, course2);
        student.put("courses", courses);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(student.toString().getBytes());
        fos.close();
        LogUtils.i(TAG, "createJson: " + student.toString());
    }

    private void parseJson() throws IOException, JSONException {
        File file = new File(getFilesDir(), "jc.json");
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder sb = new StringBuilder();
        while (null != (line = br.readLine())) {
            sb.append(line);
        }
        fis.close();
        isr.close();
        br.close();
        Student student = new Student();

        //解析数组数据
        JSONObject stuJsonObj = new JSONObject(sb.toString());
        String name = stuJsonObj.optString("name", "");
        student.setName(name);
        student.setAge(stuJsonObj.optInt("age", 18));
        student.setSex(stuJsonObj.optString("sex", "男"));

        JSONArray coursesJson = stuJsonObj.optJSONArray("courses");
        for (int i = 0; i < coursesJson.length(); i++) {
            JSONObject courseJsonObject = coursesJson.getJSONObject(i);
            Course course = new Course();
            course.setName(courseJsonObject.optString("name"));
            course.setScore((float) courseJsonObject.optDouble("score", 0));
            student.setCourse(course);
        }

        LogUtils.i(TAG, "parseJson: " + student.getName());
    }
}

































