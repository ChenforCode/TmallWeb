package servlets;

import com.sun.xml.internal.ws.api.pipe.ContentType;
import dao.UserDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


//判断用户名是否被使用
@WebServlet(name = "S_checkUsername", urlPatterns = "/S_checkUsername")
public class S_checkUsername extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        UserDao userDao = new UserDao();
        String username = request.getParameter("username");
        PrintWriter out = response.getWriter();
        try {
            //如果没有人注册过返回{"success":true, "msg":"用户名已经被注册"}
            if (userDao.register(username)) {
                Map map = new HashMap();
                map.put("success", Boolean.TRUE);
                map.put("msg", "可以使用");
                JSONObject json = JSONObject.fromObject(map);
                out.print(json);
            } else {
                Map map = new HashMap();
                map.put("success", Boolean.FALSE);
                map.put("msg", "用户名已被使用");
                JSONObject json = JSONObject.fromObject(map);
                out.print(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
