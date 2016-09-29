package cn.leancloud.leo.talk;

import cn.leancloud.leo.talk.exception.RobotApiEndException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */
public class TuringRobot implements Robot {

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    private final static Logger logger = LogManager.getLogger(QingyunRobot.class);


    private final static String apiKey = "47cc18410bf4423aaef992b56401835b";
    private final static String userId = "bearliejj";

    @Override
    public String talk(String msg) {
        try {
            // 创建httpget.
            String url = String.format("http://www.tuling123.com/openapi/api?key=%s&info=%s&userid=%s",apiKey, msg,userId);
            HttpGet httpget = new HttpGet(url);
            BasicHeader header = new BasicHeader("apikey", apiKey);
            httpget.addHeader(header);
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
                String text = jsonObject.getString("text");
                if("当前请求调用次数已用尽".equals(text)) {
                    throw new RobotApiEndException("图灵机器人次数用完");
                }
                if (text != null && text.length() > 0) {
                    return text;
                }
            }
            // 关闭连接,释放资源
            throw new RuntimeException("turing robot no answer");
        } catch (Exception e) {
            logger.error("qingyun robot talk exception", e);
            throw new RuntimeException("turing robot talk exception");
        }

    }

    public static void main(String[] args) {
        TuringRobot robot = new TuringRobot();
        String ans = robot.talk("你好");
        System.out.println(ans);

    }
}
