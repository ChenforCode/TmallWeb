package servlets;

import beans.Good;
import dao.GoodDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//查看商品
@WebServlet(name = "S_view_goods", urlPatterns = "/S_view_goods")
public class S_view_goods extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //拿到goods
        GoodDao goodDao = new GoodDao();
        ArrayList<Good> goods = goodDao.getAllItems();
        HttpSession session = request.getSession();
        session.setAttribute("goods", goods);

        //响应ajax请求
        PrintWriter out = response.getWriter();
        Map map = new HashMap();
        map.put("success", Boolean.TRUE);
        map.put("msg", "查询成功");
        JSONObject json = JSONObject.fromObject(map);
        out.print(json);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
