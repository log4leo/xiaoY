package cn.leancloud.leo.talk;

import cn.leancloud.leo.talk.exception.RobotApiEndException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */
public class RobotCenter implements Robot {

    private Robot qingyunRobot = new QingyunRobot();

    private Robot turingRobot = new TuringRobot();


    private final static Logger logger = LogManager.getLogger(QingyunRobot.class);

    @Override
    public String talk(String msg) {

        try {
            return turingRobot.talk(msg);

        } catch (Throwable e) {
            logger.error("用图灵机器人异常", e);

            try {
                return qingyunRobot.talk(msg);
            } catch (Throwable throwable) {
                logger.error("用青云机器人异常", e);
            }
        }

        return "小Y今天休息,不见客";
    }

}
