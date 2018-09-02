package servlets;

import beans.Good;
import dao.GoodDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


//用来查询商品
@WebServlet(name = "S_query_good", urlPatterns = "/S_query_good")
public class S_query_good extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String name = request.getParameter("name");
        GoodDao goodDao = new GoodDao();
        try {
            Good good = goodDao.getGoodsByName(name);
            if (good != null){
                PrintWriter out = response.getWriter();
                Map map = new HashMap();
                map.put("success", Boolean.TRUE);
                map.put("msg", "添加失败");
                map.put("id", good.getId());
                map.put("name", good.getName());
                map.put("kind", good.getKind());
                map.put("price", good.getPrice());
                map.put("origin", good.getOrigin());
                map.put("pic", good.getPicture());
                JSONObject json = JSONObject.fromObject(map);
                out.print(json);
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                Map map = new HashMap();
                map.put("success", Boolean.FALSE);
                map.put("msg", "查询失败");
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
