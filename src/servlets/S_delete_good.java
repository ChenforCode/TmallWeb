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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


//用来删除商品
@WebServlet(name = "S_delete_good", urlPatterns = "/S_delete_good")
public class S_delete_good extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String name = request.getParameter("del_goodname");

        GoodDao goodDao = new GoodDao();
        try {
            if (goodDao.getGoodsByName(name) != null){
                //有这个商品,删除之前保存下来！
                Good del_good = goodDao.getGoodsByName(name);
                try {
                    if (goodDao.delete_good(name)){
                        //删除成功
                        PrintWriter out = response.getWriter();
                        Map map = new HashMap();
                        map.put("success", Boolean.TRUE);
                        map.put("msg", "删除成功！所删除商品信息如下：");
                        map.put("id", del_good.getId());
                        map.put("name", del_good.getName());
                        map.put("kind", del_good.getKind());
                        map.put("origin", del_good.getOrigin());
                        map.put("price", del_good.getPrice());

                        JSONObject json = JSONObject.fromObject(map);
                        out.print(json);
                        out.close();
                    } else {
                        //删除失败
                        PrintWriter out = response.getWriter();
                        Map map = new HashMap();
                        map.put("success", Boolean.FALSE);
                        map.put("msg", "删除失败！");
                        JSONObject json = JSONObject.fromObject(map);
                        out.print(json);
                        out.close();
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                }
            } else {
                //没有这个商品
                PrintWriter out = response.getWriter();
                Map map = new HashMap();
                map.put("success", Boolean.FALSE);
                map.put("msg", "该商品不存在，请检查商品名！");
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
