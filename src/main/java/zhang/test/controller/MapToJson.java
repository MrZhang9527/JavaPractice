package zhang.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.HashMap;
import java.util.Map;

/**
 * @Descripthon: map 转 json 字符串
 * @author: MrZhang
 * @date: 2021/3/23 15:31
 */
public class MapToJson {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>(1);
        map.put("json","test");
        String string = JSON.toJSONString(map);
        Map<String, String> map1 = JSON.parseObject(string, Map.class);
        System.out.println(string);
        System.out.println(map1.get("json1"));

        User zs = new User("张三", 18);

    }
}


class User{
    @JSONField(name = "Name")
    private String name;
    @JSONField(name = "Age")
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}