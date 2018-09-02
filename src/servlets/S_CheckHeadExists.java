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


//用来判断用户信息中是否有头像
@WebServlet(name = "S_CheckHeadExists", urlPatterns = "/S_CheckHeadExists")
public class S_CheckHeadExists extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        UserDao userDao = new UserDao();
        try {
            User user = userDao.findUserbyName(username);
            String head = user.getHead();
            if (!"nopic".equals(head)){
                PrintWriter out = response.getWriter();
                Map map = new HashMap();
                map.put("success", Boolean.TRUE);
                map.put("msg", "有头像！！");
                JSONObject json = JSONObject.fromObject(map);
                out.print(json);
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
