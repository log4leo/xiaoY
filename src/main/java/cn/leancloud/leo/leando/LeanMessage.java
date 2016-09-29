package cn.leancloud.leo.leando;

import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */
@AVClassName("LeanMessage")
public class LeanMessage extends AVObject {

    public String getContent() {
        return getString("content");
    }

    public String getFromUserName() {
        return getString("fromUserName");
    }

    public String getToUserName() {
        return getString("toUserName");
    }

    public String getMsgType() {
        return getString("msgType");
    }

    public String getMsgId() {
        return getString("msgId");
    }


    @Override
    public String toString() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("content", this.getString("content"));
        result.put("fromUserName", this.getString("fromUserName"));
        result.put("toUserName", this.getString("toUserName"));
        result.put("msgType", this.getString("msgType"));
        result.put("msgId", this.getString("msgId"));
        result.put("objectId", this.getObjectId());
        result.put("createdAt", this.getCreatedAt());
        return JSON.toJSONString(result);
    }


}
