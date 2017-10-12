package com.ltao;

import com.ltao.study.GetHtml;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BasicTest {

    @Test
    public void testIp() {
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            System.out.println(Inet4Address.getLocalHost().getHostAddress());
            System.out.println(Inet6Address.getLocalHost().getHostAddress());
            System.out.println(InetAddress.getLocalHost().getHostName());
            System.out.println(this.getClass().getResource("/").getPath());
            System.out.println(System.getProperty("catalina.home"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {

        try {
            Map<String, Integer> result = new TreeMap<String, Integer>();
            Document doc = Jsoup.connect("http://mvnrepository.com/artifact/junit/junit").get();
            Elements es = doc.getElementsByClass("vbtn release");
            for (Element e : es) {
                String version = e.attr("href").substring(6);
                String desUrl = "junit/" + version + "/usages";
                Element as = doc.select("a[href=" + desUrl + "]").first();
                String temp = as.text().replaceAll(",", "");
                result.put(version, Integer.valueOf(temp));
            }
            List<Map.Entry<String, Integer>> list = new ArrayList<>(result.entrySet());
            Collections.sort(list, Comparator.comparing(Map.Entry::getValue));
            FileWriter fw = new FileWriter(new File("test.txt"));
            for (Map.Entry<String, Integer> m : list) {
                System.out.printf("版本号【%s】使用量为 %d", m.getKey(), m.getValue());
                System.out.println();
                fw.write("版本号【" + m.getKey() + "】使用量为 " + m.getValue() + "\n");
                fw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    @Test
//    public void test2(){
//        WebDriver driver = SeleniumBase.getCurrentDriver();
//        SeleniumBase.openPage("https://www.baidu.com", new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver webDriver) {
//                return webDriver.findElement(By.id("su")) != null;
//            }
//        },15);
//        SeleniumBase.takeScreenShot();
//        SeleniumBase.quitDriver();
//    }

    @Test
    public void testGetWeather() {
        Document document = Jsoup.parse(GetHtml.getHtml());
        Elements contents = document.select("div.weatherYubaoBox");
        Elements trs = contents.get(0).getElementsByClass("yuBaoTable").get(0).getElementsByTag("tr");
        StringBuffer sb = new StringBuffer();
        sb.append(LocalDate.now());
        for (int i = 0; i < trs.size(); i++) {
            Element tr = trs.get(i);
            Elements tds = tr.getElementsByTag("td");
            for (int j = 0; j < tds.size(); j++) {
                Element td = tds.get(j);
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0 && j == 1) {
                    sb.append(td.html()).append(" ");
                    continue;
                }
                if (i == 1 && j == 0) {
                    sb.append(td.html()).append(" ");
                    continue;
                }
                sb.append(td.getElementsByTag("a").get(0).text()).append(" ");
            }
        }
        System.out.println(sb);
    }

    /*
         * // 直接从字符串中输入 HTML 文档 String html =
         * "<html><head><title> 开源中国社区 </title></head>" +
         * "<body><p> 这里是 jsoup 项目的相关文章 </p></body></html>"; Document doc =
         * Jsoup.parse(html);
         *
         * // 从 URL 直接加载 HTML 文档 Document doc =
         * Jsoup.connect("http://www.oschina.net/").get(); String title =
         * doc.title();
         *
         * Document doc = Jsoup.connect("http://www.oschina.net/")
         * .data("query", "Java") // 请求参数 .userAgent("I ’ m jsoup") // 设置
         * User-Agent .cookie("auth", "token") // 设置 cookie .timeout(3000) //
         * 设置连接超时时间 .post(); // 使用 POST 方法访问 URL
         */
    // 从文件中加载 HTML 文档
        /*
         * File input = new File("C:/test.html"); Document doc =
         * Jsoup.parse(input,"UTF-8","http://www.oschina.net/");
         */
        /*
         * Document doc = Jsoup.connect("http://athp.hp.com/portal/site/athp/")
         * .get(); System.out.println("title:" + doc.title()); Elements els =
         * doc.getElementsByTag("a"); System.out.println("\n\n\n" + els + "\n");
         * for (Element e : els) { System.out.println(e.nodeName() + ":\t" +
         * e.val()); }
         */

    @Test
    public void test2() {
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));
        System.out.println(LocalDate.now());
    }

}
