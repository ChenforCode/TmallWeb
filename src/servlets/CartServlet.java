package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Cart;
import beans.Good;
import dao.GoodDao;
import net.sf.json.JSONObject;


//用来完成购物车的各种操作如：增删改查
@WebServlet(name = "CartServlet", urlPatterns = "/CartServlet")
public class CartServlet extends HttpServlet {

	private String action ;
	private GoodDao goodDao = new GoodDao();

	//servlet的doPost方法，判断用户传来的操作，如增加，删除，展示等等。
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		if(request.getParameter("action") != null)
		{
			this.action = request.getParameter("action");
			if(action.equals("add"))
			{
				if(addToCart(request,response))
				{
                    PrintWriter out = response.getWriter();
                    Map map = new HashMap();
                    map.put("success", Boolean.TRUE);
                    map.put("msg", "添加成功");
                    JSONObject json = JSONObject.fromObject(map);
                    out.print(json);
                    out.close();
				}
				else
				{
                    PrintWriter out = response.getWriter();
                    Map map = new HashMap();
                    map.put("success", Boolean.FALSE);
                    map.put("msg", "添加失败");
                    JSONObject json = JSONObject.fromObject(map);
                    out.print(json);
                    out.close();
				}
			}
			if(action.equals("show"))
			{
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
			if(action.equals("delete"))
			{
                //删除之后重新刷新界面
				if(deleteFromCart(request,response))
				{
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}
				else
				{
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}
			}
		}
		
	}

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

	private boolean addToCart(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		String number = request.getParameter("num");
		Good good = goodDao.getGoodsById(Integer.parseInt(id));

		//购物车不存在那么新建一个购物车
		if(request.getSession().getAttribute("cart")==null)
		{
			Cart cart = new Cart();
			request.getSession().setAttribute("cart",cart);
		}
		Cart cart = (Cart)request.getSession().getAttribute("cart");

		//将good信息和数量传到Cart类的方法中
		if(cart.addGoodsInCart(good, Integer.parseInt(number)))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	private boolean deleteFromCart(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
	    Good good = goodDao.getGoodsById(Integer.parseInt(id));
	    if(cart.removeGoodsFromCart(good))
	    {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}

}
