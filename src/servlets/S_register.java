package servlets;

import beans.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//完成注册功能
@WebServlet(name = "S_register", urlPatterns = "/S_register")
public class S_register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pwd = request.getParameter("password");
        String telp = request.getParameter("telphone");
        String addr = request.getParameter("address");
        String head = "nopic";
        User user = new User(uname, pwd, telp, addr,head);

        UserDao userDao = new UserDao();
        try {
            userDao.addUser(user);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
