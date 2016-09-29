package cn.leancloud.leo.model;

import cn.leancloud.leo.utils.MessageUtil;
import com.thoughtworks.xstream.XStream;

import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */

@XmlRootElement(name = "xml")
public class TextMsg extends Message {

    private static XStream xStream = null;

    public TextMsg() {
        setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

    }
    @XmlElement(name = "Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toXml() {
        initXstream();
        return xStream.toXML(this);
    }

    public static TextMsg fromXml(String xml) {
        initXstream();
        return (TextMsg) xStream.fromXML(new StringReader(xml));
    }

    private static void initXstream() {
        if (xStream != null) {
            return;
        }

        try {
            xStream = new XStream();
            xStream.alias("xml", TextMsg.class);
            xStream.aliasField("ToUserName", TextMsg.class, "toUserName");
            xStream.aliasField("FromUserName", TextMsg.class, "fromUserName");
            xStream.aliasField("CreateTime", TextMsg.class, "createTime");
            xStream.aliasField("Content", TextMsg.class, "content");
            xStream.aliasField("MsgType", TextMsg.class, "msgType");
            xStream.aliasField("MsgId", TextMsg.class, "msgId");
        } catch (Throwable throwable) {
            xStream = null;
        }
    }

}
