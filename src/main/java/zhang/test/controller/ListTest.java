package zhang.test.controller;
import java.io.IOException;
import java.util.ArrayList;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import zhang.test.entity.DicVo;

import java.util.*;

/**
 * @Descripthon: 列表练习
 * @author: MrZhang
 * @date: 2021/2/1 19:03
 */
public class ListTest extends BaseTest{
    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet(){{
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
        }};
        System.out.println(hashSet);
        HashSet<String> hashSet1 = new HashSet(){{
            add("11");
            add("12");
            add("13");
            add("14");
            add("14");
        }};
        System.out.println(hashSet1);
        hashSet.addAll(hashSet1);
        System.out.println(hashSet);
        for (String s : hashSet) {
            System.out.println(s);
        }

        try {
            throw new IOException("111");
        }catch (IOException e){
            System.out.println(e.toString());
        }



        // List<DicVo> list = new ArrayList<>();
        // DicVo template1 = new DicVo();
        // template1.setValue("123456781234");
        // template1.setText("123456781234-1234");
        // list.add(template1);
        // DicVo template2 = new DicVo();
        // template2.setValue("123456782234");
        // template2.setText("123456781234-2234");
        // list.add(template2);
        // DicVo template3 = new DicVo();
        // template3.setValue("1234567");
        // template3.setText("1234567-1234");
        // list.add(template3);
        // getDic(list);
        // System.out.println(list);
        // listTest("1");

        List<Object> list = create();
        list.size();
        new HashMap<>(5).size();
    }
    public static void getDic(List<DicVo> list){
        Map<String, Integer> map = new HashMap<>(1);
        String value;
        String sub;
        for (DicVo vo : list) {
            value = vo.getValue();
            if(value.length() > 8){
                sub = value.substring(0,8);
                if(!map.containsKey(sub)){
                    map.put(sub, 1);
                }else {
                    map.put(sub, map.get(sub)+1);
                }
            }
        }
        map.forEach((key, val) -> {
           if (val>1){
               DicVo template = new DicVo();
               template.setValue(key);
               template.setText(key);
               list.add(template);
           }
        });
        list.sort(Comparator.comparing(DicVo::getValue));
    }
    public static void listTest(String target){

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        if (list.contains(target)){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
    }

    public static <E> List<E> create(){
        return Lists.newArrayList();
    }
}
