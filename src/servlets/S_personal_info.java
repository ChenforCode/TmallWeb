package servlets;

import beans.User;
import dao.UserDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


//用来显示用户的个人信息
@WebServlet(name = "S_personal_info", urlPatterns = "/S_personal_info")
public class S_personal_info extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        //获取到用户对象
        UserDao userDao = new UserDao();
        try {
            User user = userDao.findUserbyName(username);
            session.setAttribute("personal_user", user);
            PrintWriter out = response.getWriter();
            Map map = new HashMap();
            map.put("success", Boolean.TRUE);
            map.put("msg", "请求成功！");
            JSONObject json = JSONObject.fromObject(map);
            out.print(json);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
