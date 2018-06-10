package servlets;

import beans.Cart;
import beans.Deal;
import beans.Good;
import beans.User;
import dao.DealDao;
import dao.UserDao;
import net.sf.json.JSONObject;

import javax.servlet.GenericServlet;
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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "S_createDealbyCart", urlPatterns = "/S_createDealbyCart")
public class S_createDealbyCart extends HttpServlet {
    //写购物车提交订单的写法
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        //获取Session对象
        HttpSession session = request.getSession();
        //获取购物车对象
        Cart cart = (Cart) session.getAttribute("cart");
        //获取用户名
        String username = (String) session.getAttribute("username");

        UserDao userDao = new UserDao();
        User user = null;
        try {
            user = userDao.findUserbyName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取地址信息
        String address = user.getAddress();

        //获取购物车内的商品总价
        Double totalPrice = cart.getTotalPrice();

        //购物车中的goods对象，对他进行遍历得到goodname和number
        HashMap<Good, Integer> goods = cart.getGoods();
        //deal的goods对象
        HashMap<String, Integer> deal_goods = new HashMap<>();

        //得到cart中goodsHashMap的键值， 也就是good对象
        Set<Good> items = goods.keySet();
        Iterator<Good> it = items.iterator();

        //遍历构建HashMap(String, Intenger)
        while (it.hasNext()) {
            Good i = it.next();
            //构造deal中的商品信息
            deal_goods.put(i.getName(), goods.get(i));
        }

        //构建deal
        Deal deal = new Deal();
        deal.setUsername(username);
        deal.setAddress(address);
        deal.setPrice(totalPrice);
        deal.setGoods(deal_goods);

        //将deal添加到数据库中
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

    }
}
