package cn.leancloud.leo;

import cn.leancloud.leo.utils.SignUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Leo.yy   Created on 16/9/27.
 * @description
 * @see
 */
@WebServlet(name = "SecurityServlet", urlPatterns = {"/security"})

public class SecurityServlet extends HttpServlet {

    private final static Logger logger = LogManager.getLogger(SecurityServlet.class);
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
}
