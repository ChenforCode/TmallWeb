package servlets;

import beans.Good;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import dao.GoodDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


//将新增商品的信息上传，重点是商品图片的名称
@WebServlet(name = "S_doUpLoad", urlPatterns = "/S_doUpLoad")
public class S_doUpLoad extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        HttpSession session = request.getSession();
        //把name保存在session中，以便上传图片的时候进行命名
        session.setAttribute("goodname", name);
        String kind = request.getParameter("kind");
        String price = request.getParameter("price");
        String origin = request.getParameter("origin");

        //存储图片的名字
        String picture = name + ".jpg";

        Good good = new Good();
        good.setId(Integer.parseInt(id));
        good.setName(name);
        good.setKind(kind);
        good.setOrigin(origin);
        good.setPrice(Double.parseDouble(price));
        good.setPicture(picture);

        GoodDao goodDao = new GoodDao();
        try {
            goodDao.addGood(good);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
