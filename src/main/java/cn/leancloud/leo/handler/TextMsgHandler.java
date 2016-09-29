package cn.leancloud.leo.handler;

import cn.leancloud.leo.model.Message;
import cn.leancloud.leo.model.TextMsg;
import cn.leancloud.leo.talk.Robot;
import cn.leancloud.leo.talk.RobotCenter;
import cn.leancloud.leo.utils.MessageUtil;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */
public class TextMsgHandler implements MsgHandler {

    private Robot robot = new RobotCenter();

    @Override
    public Message handle(Message from) {
        TextMsg msg = new TextMsg();
        String ans = robot.talk(((TextMsg) from).getContent());
        System.out.println(ans);
        if (ans != null) {
            ans = ans.replace("mzxing.com", "www.leoyang.net/support.html").replace("菲菲", "小Y").replace("何玲燕", "小Y")
            .replace("小宇", "小Y").replace("梅州行","小Y个人博客");
        }
        msg.setContent(ans);
        return msg;
    }

    @Override
    public boolean match(Message msg) {
        return MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msg.getMsgType());
    }


}
