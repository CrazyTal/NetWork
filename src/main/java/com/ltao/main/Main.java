package main;

import util.ConnectionUtil;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //链家
//        System.out.println(ConnectionUtil.Connect("http://bj.lianjia.com/ershoufang/pg1/"));
        //百度
        String url = "http://www.baidu.com";
        String result = ConnectionUtil.connect(url);
        System.out.println(result);
    }
}
