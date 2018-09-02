package servlets;

import beans.Deal;
import dao.DealDao;
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


//查看订单
@WebServlet(name = "S_ViewDeal", urlPatterns = "/S_ViewDeal")
public class S_ViewDeal extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        DealDao dealDao = new DealDao();
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        //拿到deals数组
        try {
            Deal [] deals = dealDao.queryDealbyUname(username);
            if (deals != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user_deals", deals);
                Map map = new HashMap();
                map.put("success", Boolean.TRUE);
                map.put("msg", "读取订单信息成功！");
                JSONObject json = JSONObject.fromObject(map);
                out.print(json);
                out.close();
            } else {
                Map map = new HashMap();
                map.put("success", Boolean.FALSE);
                map.put("msg", "读取订单信息失败！");
                JSONObject json = JSONObject.fromObject(map);
                out.print(json);
                out.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
