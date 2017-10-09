package com.ltao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regularly {
    private static Logger logger = LoggerFactory.getLogger(Regularly.class);

    public static void getMailAddr() throws Exception {
        URL url = new URL("http://blog.sina.com.cn/s/blog_515617e60101e151.html");
        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("D:\\mailaddress2.txt")));
        String str = null;
        String regex = "[a-zA-Z0-9_]{6,12}@[a-zA-Z0-9]+(.[a-zA-Z]+)";
        Pattern pattern = Pattern.compile(regex);
        logger.info("start");
        while ((str = bufferedReader.readLine()) != null) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                String strTarget = matcher.group();
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
