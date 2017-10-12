package com.ltao.study;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GetHtml {

    private static Logger logger = LoggerFactory.getLogger(GetHtml.class);

    public static String getHtml() {
        String html = "";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //访问的目标站点，端口和协议
        HttpHost targetProxy = new HttpHost("www.weather.com.cn", 80, "http");
        //代理的地址
        HttpHost proxy = new HttpHost("web-proxy.***.com", 8080);
        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        //目标地址
        HttpGet httpGet = new HttpGet("/weather/101020100.shtml");
        try {
            HttpResponse httpResponse = httpClient.execute(targetProxy, httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                html = EntityUtils.toString(httpEntity);
                logger.info(html);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return html;
    }

}
