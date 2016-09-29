package cn.leancloud.leo.handler;

import cn.leancloud.leo.model.Message;
import cn.leancloud.leo.model.TextMsg;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */
public class MsgHandlerRouter implements MsgHandler {


    private List<MsgHandler> handlers = Lists.newArrayList();

    public MsgHandlerRouter() {
        handlers.add(new NotSupportMsgHandler());
        handlers.add(new TextMsgHandler());
    }
    @Override
    public Message handle(Message from) {

        for (MsgHandler msgHandler : handlers) {
            if (msgHandler.match(from)) {
                Message msg = msgHandler.handle(from);
                exchangeMsgName(from, msg);
                return msg;
            }
        }

        TextMsg msg = new TextMsg();
        exchangeMsgName(from, msg);
        msg.setContent("你太流弊了,小Y也词穷了..");

        return msg;
    }

    private void exchangeMsgName(Message from, Message to) {
        to.setFromUserName(from.getToUserName());
        to.setToUserName(from.getFromUserName());
        to.setCreateTime((new Date()).getTime());
    }

    @Override
    public boolean match(Message msg) {
        return false;
    }
}
