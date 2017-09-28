package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Html {

    /**
     * 根据url获取网页文本
     *
     * @param url
     * @return
     */
    public Document getHtmlTextByUrl(String url) {
        Document doc = null;
        try {
//            doc = Jsoup.connect(url).timeout(5000000).get();
            int i = (int) Math.random() * 1000;
            while (i != 0) {
                doc = Jsoup.connect(url).data("query", "java").userAgent(
                        "Chrome：Mozilla/5.0 (Windows NT 5.1; zh-CN) AppleWebKit/535.12 (KHTML, like Gecko) Chrome/22.0.1229.79 Safari/535.12 ")
                        .cookie("auth", "token").timeout(3000).post();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            doc = Jsoup.connect(url).timeout(5000000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

}
