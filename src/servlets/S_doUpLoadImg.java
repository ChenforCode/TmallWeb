package servlets;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
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

//将商品图片上传到工程的指定目录下
@WebServlet(name = "S_doUpLoadImg", urlPatterns = "/S_doUpLoadImg")
public class S_doUpLoadImg extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //将图片上传到项目的imgages/goodimg文件夹下
        upload_img(request, response);
        PrintWriter out = response.getWriter();
        Map map = new HashMap();
        map.put("success", Boolean.TRUE);
        map.put("msg", "上传成功");
        JSONObject json = JSONObject.fromObject(map);
        out.print(json);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void upload_img(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String imgname = null;
        //在session中拿到imgname
        HttpSession session = request.getSession();
        if (session.getAttribute("goodname") != null){
            imgname = (String) session.getAttribute("goodname")+".jpg";
        }
        //图片的保存路径
        String saveDirectory = this.getServletContext().getRealPath("/images/goodimg");
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
            File sServerFile = new File(saveDirectory + "/" + imgname);
            if (sServerFile.exists()){
                sServerFile.delete();
            }
            f.renameTo(sServerFile);
        }
    }
}
