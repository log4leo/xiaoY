package cn.leancloud.leo.talk;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */
public class QingyunRobot implements Robot {

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    private final static Logger logger = LogManager.getLogger(QingyunRobot.class);

    @Override
    public String talk(String msg) {
        try {
            // 创建httpget.
            String url = String.format("http://api.qingyunke.com/api.php?key=free&appid=0&msg=%s", msg);
            HttpGet httpget = new HttpGet(url);
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            System.out.println("--------------------------------------");
            // 打印响应状态
            System.out.println(response.getStatusLine());
            if (entity != null) {
                // 打印响应内容长度
//                        System.out.println("Response content length: " + entity.getContentLength());
                // 打印响应内容
                String jsonRet = EntityUtils.toString(entity);
                JSONObject jsonObject = JSON.parseObject(jsonRet);
                return jsonObject.getString("content");
            }
            // 关闭连接,释放资源

        } catch (Exception e) {
            logger.error("qingyun robot talk exception", e);
        }

        return "风太大,听不清你说啥";
    }

    public static void main(String[] args) {
        QingyunRobot robot = new QingyunRobot();
        System.out.println(robot.talk("你好"));
    }
}

