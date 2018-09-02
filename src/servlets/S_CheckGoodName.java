package servlets;

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


//根据传过来的商品名称来判断该商品是否存在
@WebServlet(name = "S_CheckGoodName", urlPatterns = "/S_CheckGoodName")
public class S_CheckGoodName extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String name = request.getParameter("name");
        GoodDao goodDao = new GoodDao();
        try {
            //如果查找不到这个商品，说明商品不存在
            if (goodDao.getGoodsByName(name) == null){
                PrintWriter out = response.getWriter();
                Map map = new HashMap();
                map.put("success", Boolean.FALSE);
                map.put("msg", "该商品不存在");
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
