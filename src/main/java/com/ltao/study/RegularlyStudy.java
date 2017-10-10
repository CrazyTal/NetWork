package com.ltao.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 学习正则
 */
public class RegularlyStudy {
    private static Logger logger = LoggerFactory.getLogger(RegularlyStudy.class);

    public static void getMailAddr() throws Exception {
        URL url = new URL("http://blog.sina.com.cn/s/blog_515617e60101e151.html");
        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        //写到D盘
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("D:\\mailaddress2.txt")));
        String str = null;
        String regex = "[a-zA-Z0-9_]{6,12}@[a-zA-Z0-9]+(.[a-zA-Z]+)";
        Pattern pattern = Pattern.compile(regex);
        logger.info("start");
        while ((str = bufferedReader.readLine()) != null) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                String strTarget = matcher.group();
                /**
                 * 经测试，group(0)与group()相同，都表示与正则完整匹配的字符串，
                 * group(1)表示正则中第一个()中包含的字符串；
                 * group(2)表示正则中第二个()中包含的字符串...
                 * int start()表示所find()到的字符串，所在源字符串的位置，
                 * int end()表示所find()到的字符串的最后一个字符，所在源字符串的位置。
                 */
                logger.info("matcher.group(0) : {}", matcher.group(0));
                logger.info("matcher.group(1) : {}", matcher.group(1));
                logger.info("matcher.group() : {}", matcher.group());
                bufferedWriter.write(strTarget);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
        logger.info("end");
    }

    public static void main(String[] args) {
        try {
            getMailAddr();
        } catch (Exception e) {
            logger.error("error : " + e);
        }
    }
}
