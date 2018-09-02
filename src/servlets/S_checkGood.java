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


//用来判断增加的商品的ID和名称是否合格
@WebServlet(name = "S_checkGood", urlPatterns = "/S_checkGood")
public class S_checkGood extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String type = request.getParameter("type");

        //如果发送过来的是验证id的请求
        if ("id".equals(type)){
            String id = request.getParameter("id");
            GoodDao goodDao = new GoodDao();
            //如果没有找到这个id，说明这个id可以使用
            if (goodDao.getGoodsById(Integer.parseInt(id)) == null){
                PrintWriter out = response.getWriter();
                Map map = new HashMap();
                map.put("success", Boolean.TRUE);
                map.put("msg", "可以使用");
                JSONObject json = JSONObject.fromObject(map);
                out.print(json);
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                Map map = new HashMap();
                map.put("success", Boolean.FALSE);
                map.put("msg", "该ID已经存在！");
                JSONObject json = JSONObject.fromObject(map);
                out.print(json);
                out.close();
            }
        } else {
            //发送过来的是验证name的请求
            String name = request.getParameter("name");
            GoodDao goodDao = new GoodDao();
            try {
                //该名称没有被使用
                if (goodDao.getGoodsByName(name) == null){
                    PrintWriter out = response.getWriter();
                    Map map = new HashMap();
                    map.put("success", Boolean.TRUE);
                    map.put("msg", "可以使用");
                    JSONObject json = JSONObject.fromObject(map);
                    out.print(json);
                    out.close();
                } else {
                    PrintWriter out = response.getWriter();
                    Map map = new HashMap();
                    map.put("success", Boolean.FALSE);
                    map.put("msg", "该商品名已存在！");
                    JSONObject json = JSONObject.fromObject(map);
                    out.print(json);
                    out.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
