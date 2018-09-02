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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//查看用户信息
@WebServlet(name = "S_view_user", urlPatterns = "/S_view_user")
public class S_view_user extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        response.setCharacterEncoding("utf-8");
        try {
            //从UserDao中得到users数组
            User [] users = userDao.queryAllUser();
            HttpSession session = request.getSession();
            session.setAttribute("users", users);

            //响应ajax请求
            PrintWriter out = response.getWriter();
            Map map = new HashMap();
            map.put("success", Boolean.TRUE);
            map.put("msg", "查询成功");
            JSONObject json = JSONObject.fromObject(map);
            out.print(json);
            out.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
