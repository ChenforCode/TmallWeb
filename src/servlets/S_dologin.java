package servlets;

import dao.UserDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


//完成登陆工作
@WebServlet(name = "S_dologin", urlPatterns = "/S_dologin")
public class S_dologin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkcode = request.getParameter("checkcode");
        HttpSession session = request.getSession();
        String RCheckcode = (String) session.getAttribute("checkCode");
        UserDao userDao = new UserDao();
        PrintWriter out = response.getWriter();
        try {
            //有这个用户
            if (userDao.check_login(username, password)){
                //验证码正确
                if (checkcode.equalsIgnoreCase(RCheckcode)) {
                    Map map = new HashMap();
                    map.put("success", Boolean.TRUE);
                    map.put("msg", "普通用户登陆");
                    JSONObject json = JSONObject.fromObject(map);
                    out.print(json);
                    out.close();
                    //向下一个界面传入用户信息，转发至下一个对象
                    session.setAttribute("username", username);
                    //request.getRequestDispatcher("/main.jsp").forward(request,response);
                } else {
                    //验证码不正确
                    Map map = new HashMap();
                    map.put("success", Boolean.FALSE);
                    map.put("msg", "信息或验证码有误");
                    JSONObject json = JSONObject.fromObject(map);
                    out.print(json);
                    out.close();
                }
            //没有这个用户
            } else {
                //如果是管理员登陆并且验证码正确
                if (username.equals("admin") && password.equals("admin")){
                    if (checkcode.equalsIgnoreCase(RCheckcode)){
                        Map map = new HashMap();
                        map.put("success", Boolean.TRUE);
                        map.put("msg", "管理员登陆");
                        JSONObject json = JSONObject.fromObject(map);
                        out.print(json);
                        out.close();
                    } else {
                        Map map = new HashMap();
                        map.put("success", Boolean.FALSE);
                        map.put("msg", "信息或验证码有误");
                        JSONObject json = JSONObject.fromObject(map);
                        out.print(json);
                        out.close();
                    }
                } else {
                    Map map = new HashMap();
                    map.put("success", Boolean.FALSE);
                    map.put("msg", "用户名或者密码错误");
                    JSONObject json = JSONObject.fromObject(map);
                    out.print(json);
                    out.close();
                }
                //request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
