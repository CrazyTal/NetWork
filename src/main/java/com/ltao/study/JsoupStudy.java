package com.ltao.study;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * jsoup解析HTML
 * 三种方式:
 */
public class JsoupStudy {
    private static Logger logger = LoggerFactory.getLogger(JsoupStudy.class);

    public static void getParagraphFromStr() {
        String html = "<html><head><title> 这里是字符串内容</title></head\"+ \">\"+\"<body>" +
                "<p class='p1'> 这里是 jsoup 作用的相关演示</p></body></html>";
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("p[class]");
        for (Element link : links) {
            String linkClass = link.className();
            String linkText = link.text();
            logger.info("linkClass : {}", linkClass);//{}是slf4j的占位符
            logger.info("linkText : {}", linkText);
//            System.out.println(linkClass);
//            System.out.println(linkText);
        }
    }

    public static void getHrefFromLocalFile() {
        File file = new File("filePath");
        Document document = null;
        try {
            document = Jsoup.parse(file, "UTF-8", "http://www.oschina.net/");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        getParagraphFromStr();
    }
}
