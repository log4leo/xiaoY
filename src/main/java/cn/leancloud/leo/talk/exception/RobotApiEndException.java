package cn.leancloud.leo.talk.exception;

/**
 * @author Leo.yy   Created on 16/9/29.
 * @description
 * @see
 */
public class RobotApiEndException extends RuntimeException {

    public RobotApiEndException(String msg) {
        super(msg);
    }

    public RobotApiEndException(String msg, Throwable throwable) {
        super(msg, throwable);
    }


}
