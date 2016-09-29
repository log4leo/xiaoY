package cn.leancloud.leo.handler;

import cn.leancloud.leo.model.Message;
import cn.leancloud.leo.model.TextMsg;
import cn.leancloud.leo.utils.MessageUtil;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */
public class NotSupportMsgHandler implements MsgHandler {

    @Override
    public Message handle(Message from) {
        TextMsg msg = new TextMsg();
        msg.setContent("暂时只支持文字消息哦");

        return msg;
    }

    @Override
    public boolean match(Message msg) {
        return !MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msg.getMsgType());
    }
}
