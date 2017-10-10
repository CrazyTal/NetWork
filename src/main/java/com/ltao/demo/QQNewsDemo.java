package com.ltao.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 从腾讯新闻网获取邮箱
 */
public class QQNewsDemo {

    //MalformedURLException 继承于 IOException
    public static List<String> getEmailFromWeb() throws IOException {
        List<String> stringList = new ArrayList<>();
        String line = null;
        //邮箱正则
        String regexEmail = "\\w+@\\w+(\\.[a-zA-Z]{2,3}){1,2}";
        Pattern pattern = Pattern.compile(regexEmail);
        URL url = new URL("http://news.qq.com/zt2015/wxghz/index.htm");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
//        URL url = new URL("http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl");
//        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//        httpURLConnection.setConnectTimeout(10000);
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

        /**
         * url.openStream() 源码
         * public final InputStream openStream() throws java.io.IOException {
         *    return openConnection().getInputStream();
         * }
         * 可得出结论，url.openStream()相当于，url.openConnection().getInputStream();
         * 只是在试用时一般先
         * HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection()
         * 将得到的URLConnection强转为HttpURLConnection，然后从中获取关于资源的其他的信息，比如资源的大小长度等等
         * HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
         * httpURLConnection.setRequestMethod("POST");
         * httpURLConnection.getHeaderField(0);
         */
        while ((line = bufferedReader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                stringList.add(matcher.group());
            }
        }
        return stringList;
    }

    public static void main(String[] args) {
        try {
            getEmailFromWeb().forEach((s) -> System.out.println(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
