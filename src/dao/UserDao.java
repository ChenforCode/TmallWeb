package dao;

import beans.User;
import jdbc.DBHelper;
import org.apache.commons.lang.ObjectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public UserDao() {
    }

    //删除用户
    public boolean delete_user(String username) throws Exception{
        DBHelper db = new DBHelper();
        conn = db.getConnection();
        String sql = "DELETE FROM user WHERE username=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        int n = pstmt.executeUpdate();
        if (n == 1) {
            db.free(null, pstmt, conn);
            return true;
        } else {
            db.free(null, pstmt, conn);
            return false;
        }
    }

    //用户登陆
    public boolean check_login(String username, String password) throws Exception {
        DBHelper db = new DBHelper();
        conn = db.getConnection();
        String sql = "SELECT *FROM user WHERE username=? AND password=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            db.free(rs, pstmt, conn);
            return true;
        } else {
            db.free(rs, pstmt, conn);
            return false;
        }
    }


    //检查用户名是否重复
    public boolean register(String username) throws Exception {
        DBHelper db = new DBHelper();
        conn = db.getConnection();
        String sql = "SELECT * FROM user WHERE username=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        //如果能够查到这个信息，说明
        if (rs.next()) {
            db.free(rs, pstmt, conn);
            return false;
        }
        return true;
    }

    public boolean checkTelphone(String telphone) throws Exception {
        DBHelper db = new DBHelper();
        conn = db.getConnection();
        String sql = "SELECT * FROM user WHERE telphone=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, telphone);
        rs = pstmt.executeQuery();
        //如果能够查到这个信息，说明有人注册过这个电话号码
        if (rs.next()) {
            db.free(rs, pstmt, conn);
            return false;
        }
        return true;
    }


    //将注册好的用户添加到数据库
    public void addUser(User adduser) throws Exception{
        DBHelper dbHelper = new DBHelper();
        conn = dbHelper.getConnection();
        String uname = adduser.getUsername();
        String upwd = adduser.getPassword();
        String tel = adduser.getTelphone();
        String addr = adduser.getAddress();
        String head = adduser.getHead();

        String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, uname);
        pstmt.setString(2, upwd);
        pstmt.setString(3,tel);
        pstmt.setString(4, addr);
        pstmt.setString(5, head);
        pstmt.executeUpdate();
        dbHelper.free(null, pstmt, conn);
    }

    //通过用户名找到用户
    public User findUserbyName(String username) throws Exception{
        DBHelper dbHelper = new DBHelper();
        conn = dbHelper.getConnection();
        String sql = "SELECT * FROM user WHERE username='"+username+"'";
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        User user = new User();
        while(rs.next()){
            user.setUsername(rs.getString(1));
            user.setPassword(rs.getString(2));
            user.setTelphone(rs.getString(3));
            user.setAddress(rs.getString(4));
            user.setHead(rs.getString(5));
        }
        dbHelper.free(rs, pstmt, conn);
        return user;
    }

    //返回所有的用户集合
    public User[] queryAllUser() throws SQLException {
        DBHelper dbHelper = new DBHelper();
        conn = dbHelper.getConnection();
        String sql = "SELECT * FROM user";
        pstmt = conn.prepareStatement(sql);

        //查询有多少个记录
        rs = pstmt.executeQuery();
        int count = 0;
        while (rs.next()){
            count++;
        }
        User [] users = new User[count];
        rs = pstmt.executeQuery();
        int index = 0;
        while (rs.next()){
            //构造user
            users[index] = new User();
            users[index].setUsername(rs.getString(1));
            users[index].setPassword(rs.getString(2));
            users[index].setTelphone(rs.getString(3));
            users[index].setAddress(rs.getString(4));
            users[index].setHead(rs.getString(5));
            index++;
        }
        return users;
    }

}
