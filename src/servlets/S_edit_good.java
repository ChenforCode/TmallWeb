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


//用来编辑修改商品
@WebServlet(name = "S_edit_good", urlPatterns = "/S_edit_good")
public class S_edit_good extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String name = request.getParameter("name");
        String origin = request.getParameter("origin");
        String price = request.getParameter("price");

        GoodDao goodDao = new GoodDao();
        try {
            //根据名字拿到旧的对象
            Good good = goodDao.getGoodsByName(name);
            if (origin != ""){
                good.setOrigin(origin);
            }
            if (price != ""){
                good.setPrice(Double.parseDouble(price));
            }
            HttpSession session = request.getSession();
            session.setAttribute("edit_good", good);
            //删除老商品
            goodDao.delete_good(name);
            //添加进新商品
            if (goodDao.addGood(good)){
                PrintWriter out = response.getWriter();
                Map map = new HashMap();
                map.put("success", Boolean.TRUE);
                map.put("msg", "修改成功，修改结果如下：");
                map.put("id", good.getId());
                map.put("name", good.getName());
                map.put("kind", good.getKind());
                map.put("price", good.getPrice());
                map.put("origin", good.getOrigin());
                JSONObject json = JSONObject.fromObject(map);
                out.print(json);
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                Map map = new HashMap();
                map.put("success", Boolean.FALSE);
                map.put("msg", "修改失败！");
                JSONObject json = JSONObject.fromObject(map);
                out.print(json);
                out.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
