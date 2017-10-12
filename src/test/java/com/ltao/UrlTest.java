package com.ltao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlTest {

    private Logger logger = LoggerFactory.getLogger(UrlTest.class);

    @Test
    public void test1() throws Exception {
        String html = "<html>" +
                "<head>" +
                "<title> 这里是字符串内容</title>" +
                "</head>" +
                "<body>" +
                "<p class='p1'> 这里是 jsoup 作用的相关演示</p>" +
                "<a href=\"http://www.oschina.net/p/jsoup\">jsoup</a>\n" +
                "<a href=\"/p/jsoup\">jsoup</a>\n" +
                "<a href=\"../jsoup\">jsoup</a>" +
                "</body>" +
                "</html>";
        // TODO: 2017/10/11 测试href相对路径绝对路径以及链接
//        URL url = new URL("http://www.oschina.net/");
//        Document doc = Jsoup.parse(url, 3 * 1000);
        Document doc = Jsoup.parse(html);
        Element link1 = doc.select("a").first();
        Element link2 = doc.select("a").get(1);
        Element link3 = doc.select("a").get(2);
        String relHref1 = link1.attr("href"); // == "/"
        String relHref2 = link2.attr("href");
        String relHref3 = link3.attr("href");
        logger.info("href1 : {}", relHref1);
        logger.info("href2 : {}", relHref2);
        logger.info("href3 : {}", relHref3);
        //前面加abs，Jsoup就会获取绝对路径
        String absHref1 = link1.attr("abs:href"); // "http://www.oschina.net/"
        String absHref2 = link2.attr("abs:href");
        String absHref3 = link3.attr("abs:href");
        logger.info("abs:href1 : {}", absHref1);
        logger.info("abs:href2 : {}", absHref2);
        logger.info("abs:href3 : {}", absHref3);


    }
}
