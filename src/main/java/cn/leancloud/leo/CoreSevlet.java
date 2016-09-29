package cn.leancloud.leo;

import cn.leancloud.leo.handler.MsgHandler;
import cn.leancloud.leo.handler.MsgHandlerRouter;
import cn.leancloud.leo.leando.LeanMessage;
import cn.leancloud.leo.model.Message;
import cn.leancloud.leo.model.TextMsg;
import cn.leancloud.leo.utils.MessageUtil;
import cn.leancloud.leo.utils.SignUtil;
import com.thoughtworks.xstream.XStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.Map;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */
@WebServlet(name = "CoreServlet", urlPatterns = {"/core"})
public class CoreSevlet extends HttpServlet {

    private static MsgHandlerRouter msgHandlerRouter = new MsgHandlerRouter();

    private final static Logger logger = LogManager.getLogger(CoreSevlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = resp.getWriter();
                out.print(echostr);
                out.close();
            } else {
                logger.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            logger.error(e, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("这是 post 方法！");
        OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream());

        try {
            String xml = MessageUtil.parseXml(req);
            Map<String, String> map = MessageUtil.convertXml2Map(xml);
            String msgType = map.get("MsgType");

            Message responseMsg = null;
            if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {
                TextMsg msg = TextMsg.fromXml(xml);
                responseMsg = msgHandlerRouter.handle(msg);
            } else if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
                String event = map.get("Event");
                if (MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(event)) {
                    responseMsg = new TextMsg();
                    ((TextMsg) responseMsg).setContent("欢迎关注我,你可以通过聊天问我任何问题,来不及详细解释了,赶紧撩起来!");
                    responseMsg.setFromUserName(map.get("ToUserName"));
                    responseMsg.setToUserName(map.get("FromUserName"));
                    responseMsg.setCreateTime((new Date()).getTime());
                }
            } else {
                responseMsg = new TextMsg();
                ((TextMsg) responseMsg).setContent("暂时只支持文字消息哦");
                responseMsg.setFromUserName(map.get("ToUserName"));
                responseMsg.setToUserName(map.get("FromUserName"));
                responseMsg.setCreateTime((new Date()).getTime());

            }

            try {

                LeanMessage fromLeanMsg = new LeanMessage();
                fromLeanMsg.put("msgId", map.get("MsgId"));
                fromLeanMsg.put("msgType", map.get("MsgType"));
                fromLeanMsg.put("fromUserName", map.get("FromUserName"));
                fromLeanMsg.put("toUserName", map.get("ToUserName"));
                fromLeanMsg.put("content", map.get("Content"));
                fromLeanMsg.save();

                LeanMessage toLeanMsg = new LeanMessage();
                toLeanMsg.put("msgId", responseMsg.getMsgId());
                toLeanMsg.put("msgType", responseMsg.getMsgType());
                toLeanMsg.put("fromUserName", responseMsg.getFromUserName());
                toLeanMsg.put("toUserName", responseMsg.getToUserName());
                if (responseMsg instanceof TextMsg) {
                    toLeanMsg.put("content", ((TextMsg) responseMsg).getContent());
                }
                toLeanMsg.save();


            } catch (Throwable throwable) {
                logger.error("save msg error", throwable);
            }


            String response = responseMsg.toXml();

            writer.write(response);

            writer.flush();
            writer.close();

            System.out.println("=============================" + map.get("Content"));

            logger.info(map.get("Content"));

        } catch (Exception e) {
            logger.error(e, e);
        }
    }


}
