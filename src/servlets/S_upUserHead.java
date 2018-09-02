package servlets;

import beans.User;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import dao.UserDao;
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


//上传用户头像，包括名称和图片
@WebServlet(name = "S_upUserHead", urlPatterns = "/S_upUserHead")
public class S_upUserHead extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        //上传图片
        upload_img(request, response, username);

        UserDao userDao = new UserDao();
        try {
            //拿到这个用户
            User user = userDao.findUserbyName(username);

            //设置这个用户的头像
            user.setHead(username + ".jpg");
            //删除老用户
            userDao.delete_user(username);

            //增加修改后的用户
            userDao.addUser(user);

            PrintWriter out = response.getWriter();
            Map map = new HashMap();
            map.put("success", Boolean.TRUE);
            map.put("msg", "上传成功");
            JSONObject json = JSONObject.fromObject(map);
            out.print(json);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void upload_img(HttpServletRequest request, HttpServletResponse response, String uname) throws IOException {
        String imgname = uname;
        //图片的保存路径
        String saveDirectory = this.getServletContext().getRealPath("/images/userhead");
        File savedir = new File(saveDirectory);
        if (!savedir.exists()){
            savedir.mkdirs();
        }

        //设置最大上传大小为10M
        int maxPostSize = 10 * 1024 *1024;
        FileRenamePolicy policy = new DefaultFileRenamePolicy();
        MultipartRequest multi;
        multi = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8", policy);
        Enumeration<String> files = multi.getFileNames();
        String name = files.nextElement();
        File f = multi.getFile(name);
        if (f != null){
            String fileName = f.getName();
            File sServerFile = new File(saveDirectory + "/" + imgname + ".jpg");
            if (sServerFile.exists()){
                sServerFile.delete();
            }
            f.renameTo(sServerFile);
        }

    }
}
