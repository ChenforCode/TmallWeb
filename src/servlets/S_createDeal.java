package servlets;

import beans.Deal;
import beans.Good;
import beans.User;
import dao.DealDao;
import dao.GoodDao;
import dao.UserDao;
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


//立即购买，直接对这一个商品创建订单
@WebServlet(name = "S_createDeal", urlPatterns = "/S_createDeal")
public class S_createDeal extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String goodid = request.getParameter("goodid");
        String number = request.getParameter("number");
        String totalPrice = request.getParameter("totalPrice");

        UserDao userDao = new UserDao();
        GoodDao goodDao = new GoodDao();
        User user = null;
        Good good = null;
        try {
            user = userDao.findUserbyName(username);
            good = goodDao.getGoodsById(Integer.parseInt(goodid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String address = user.getAddress();

        //构建货物和数量的HashMap
        HashMap<String, Integer> goodinfo = new HashMap<>();
        goodinfo.put(good.getName(), Integer.parseInt(number));

        //构建deal
        Deal deal = new Deal();
        deal.setGoods(goodinfo);
        deal.setUsername(username);
        deal.setAddress(address);
        deal.setPrice(Double.parseDouble(totalPrice));

        //将创建好的deal加入到数据库中
        DealDao dealDao = new DealDao();
        PrintWriter out = response.getWriter();
        try {
            //如果加入成功，返回Ajax请求的json字符串
            if (dealDao.createDeal(deal)){
                Map map = new HashMap();
                map.put("success", Boolean.TRUE);
                map.put("msg", "下单成功！");
                JSONObject json = JSONObject.fromObject(map);
                out.print(json);
            } else {
                Map map = new HashMap();
                map.put("success", Boolean.FALSE);
                map.put("msg", "下单失败，请重新下单！");
                JSONObject json = JSONObject.fromObject(map);
                out.print(json);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
