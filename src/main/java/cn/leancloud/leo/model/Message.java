package cn.leancloud.leo.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */
@XmlRootElement(name = "xml")
// 控制JAXB 绑定类中属性和字段的排序
public class Message {

    @XmlElement(name = "ToUserName")

    private String toUserName;
    @XmlElement(name = "FromUserName")
    private String fromUserName;

    @XmlElement(name = "CreateTime")
    private Long createTime;

    @XmlElement(name = "MsgType")
    private String msgType;


    private String msgId;


    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String toXml() {
        throw new UnsupportedOperationException("base class not allowed to use this method");
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
