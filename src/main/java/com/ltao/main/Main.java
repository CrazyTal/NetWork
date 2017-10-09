package com.ltao.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        //链家
//        System.out.println(ConnectionUtil.Connect("http://bj.lianjia.com/ershoufang/pg1/"));
        //百度
        String url = "http://www.baidu.com";
        String result;
//        result = ConnectionUtil.connect(url);
        result = sendGet(url);
        String imgStr = regexString(result, "src=\"(.+?)\"");

        System.out.println(result);
        System.out.println("---------华丽丽的分割线---------");
        System.out.println(imgStr);
    }

    public static String sendGet(String urlStr) {
        StringBuffer sb = new StringBuffer("");
        BufferedReader bufferedReader = null;

        try {
            URL url = new URL(urlStr);
            URLConnection urlConnection = url.openConnection();
            /**
             * URLConnection可以处理多方式访问
             * HttpURLConnection只针对Http请求
             */
            urlConnection.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static String regexString(String targetStr, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(targetStr);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return "nothing";
    }
}
