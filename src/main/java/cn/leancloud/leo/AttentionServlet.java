package cn.leancloud.leo;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author Leo.yy   Created on 16/9/27.
 * @description
 * @see
 */
@WebServlet(name = "AttentionServlet", urlPatterns = {"/attention"})

public class AttentionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("currentTime", new Date());
        req.getRequestDispatcher("/time.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String content = req.getParameter("content");

        try {
            AVObject note = new Todo();
            note.put("content", content);
            note.save();
        } catch (AVException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/todos");
    }
}
