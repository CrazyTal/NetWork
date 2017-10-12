package com.ltao.study;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * jsoup解析HTML
 * 三种方式:
 */
public class JsoupStudy {
    private static Logger logger = LoggerFactory.getLogger(JsoupStudy.class);

    /**
     * 从字符串获取
     */
    public static void getParagraphFromStr() {
        String html = "<html><head><title> 这里是字符串内容</title></head><body>" +
                "<p class='p1'> 这里是 jsoup 作用的相关演示</p></body></html>";
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("p[class]");
        for (Element link : links) {
            String linkClass = link.className();
            String linkText = link.text();
            logger.info("linkClass : {}", linkClass);//{}是slf4j的占位符
            logger.info("linkText : {}", linkText);
            logger.info("link.toString() : {}", link.toString());
            logger.info("link.html() : {}", link.html());
//            System.out.println(linkClass);
//            System.out.println(linkText);
        }
    }

    /**
     * 从本地文件获取
     */
    public static void getHrefFromLocalFile() {
        File file = new File("filePath");
        Document document = null;
        try {
            document = Jsoup.parse(file, "UTF-8", "http://www.oschina.net/");
            //第三个参数，用于解决文件中URLs是相对路径的问题，如果不需要可以传入一个空字符串。
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = document.select("a[href]");
        links.forEach((link) -> {
            logger.info("linkHref : {}", link.attr("href"));
            logger.info("linkText : {}", link.text());
        });
    }

    /**
     * 从网络上解析数据
     */
    public static HashMap getHrefFromNet(String url) {
        HashMap map = new HashMap();
        String href = null;
        try {
            logger.info("start");
            //使用get
            Document document = Jsoup.connect(url).get();
            String title = document.title();
            logger.info("title : {}", title);
            Elements links = document.select("a[href]");
            links.forEach(link -> {
                String linkHref = link.attr("abs:href");
                String linkText = link.text();
                logger.info("abs:href : {}", linkHref);
                logger.info("text : {}", linkText);
            });
            logger.info("end");
            //-------------华丽丽的分割线--------------//
            //使用post
            /*
            logger.info("start");
            Document document2 = Jsoup.connect(url)
                    .data("query", "Java")
                    .userAgent("I am Jsoup")
                    .cookie("auth", "token")
                    .timeout(10000)
                    .post();
            String title2 = document2.title();
            logger.info("title2 : {}", title2);
            Elements links2 = document2.select("a[href]");
            links2.forEach(link -> {
                String linkHref = link.attr("abs:href");
                String linkText = link.text();
                logger.info("abs:href : {}", linkHref);
                logger.info("linkText : {}", linkText);
            });
            */
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


    public static void main(String[] args) {
//        getParagraphFromStr();
//        getHrefFromLocalFile();
        getHrefFromNet("https://www.hao123.com/");
    }
}
