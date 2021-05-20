package zhang.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.sun.xml.internal.org.jvnet.fastinfoset.sax.FastInfosetReader;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Descripthon: map 转 json 字符串
 * @author: MrZhang
 * @date: 2021/3/23 15:31
 */
public class MapToJson extends BaseTest{
    public static void main(String[] args) throws ParseException {
        HashMap<String, String> map = new HashMap<>(8);
        // map.put("json","test");
        // String string = JSON.toJSONString(map);
        // Map<String, String> map1 = JSON.parseObject(string, Map.class);
        // System.out.println(string);
        // System.out.println(map1.get("json1"));

        System.out.println(BigDecimal.ZERO.compareTo(BigDecimal.valueOf(0)) ==0);
        System.out.println(BigDecimal.ZERO.compareTo(BigDecimal.valueOf(0.0))==0);

        map.put("cost","main");
        map.put("healthy","main");
        map.put("noHealthy","main");
        map.put("firstIssue","main");
        map.put("renew","main");
        String string = JSON.toJSONString(map);
        System.out.println(string);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = "2020-12-01 00:00:00.0";
        System.out.println(sdf.format(sdf.parse(s)));

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

class Title{
    private Tooltip tooltip;
    private Legend legend;
    private Axis xAxis;
    private Axis yAxis;
    private Grid grid;
    private Series series;

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public Axis getxAxis() {
        return xAxis;
    }

    public void setxAxis(Axis xAxis) {
        this.xAxis = xAxis;
    }

    public Axis getyAxis() {
        return yAxis;
    }

    public void setyAxis(Axis yAxis) {
        this.yAxis = yAxis;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}

class Tooltip{

}

class Legend{

}

class Grid{

}

class Axis{

}

class Series{

}