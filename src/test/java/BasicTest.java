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
}
