package cn.leancloud.leo.handler;

import cn.leancloud.leo.model.Message;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */
public interface MsgHandler {

    /**
     * 处理消息
     * @param from
     * @return
     */
    Message handle(Message from);

    boolean match(Message msg);
}
