package dao;

import beans.Good;
import jdbc.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GoodDao {

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    //返回所有商品的集合
    public ArrayList<Good> getAllItems() {
        ArrayList<Good> list = new ArrayList<Good>(); // 商品集合
        try {
            DBHelper dbHelper = new DBHelper();
            conn = dbHelper.getConnection();
            String sql = "select * from Good;"; // SQL语句
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Good good = new Good();
                good.setId(rs.getInt("id"));
                good.setName(rs.getString("name"));
                good.setKind(rs.getString("kind"));
                good.setPrice(rs.getDouble("price"));
                good.setOrigin(rs.getString("origin"));
                good.setPicture(rs.getString("picture"));
                list.add(good);// 把一个商品加入集合
            }
            return list; // 返回集合。
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    //根据商品编号获得商品资料
    public Good getGoodsById(int id) {
        try {
            DBHelper dbHelper = new DBHelper();
            conn = dbHelper.getConnection();
            String sql = "select * from good where id=?;"; // SQL语句
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Good good = new Good();
                good.setId(rs.getInt("id"));
                good.setName(rs.getString("name"));
                good.setKind(rs.getString("kind"));
                good.setPrice(rs.getDouble("price"));
                good.setOrigin(rs.getString("origin"));
                good.setPicture(rs.getString("picture"));
                return good;
            } else {
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            // 释放数据集对象
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            // 释放语句对象
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        }
    }


    //获取浏览记录的前五条
    public ArrayList<Good> getViewList(String list) {
        ArrayList<Good> goodslist = new ArrayList<Good>();
        int iCount = 5; //每次返回前五条记录
        if (list != null && list.length() > 0) {
            String[] arr = list.split("#");
            //如果商品记录大于等于5条
            if (arr.length >= 5) {
                for (int i = arr.length - 1; i >= arr.length - iCount; i--) {
                    goodslist.add(getGoodsById(Integer.parseInt(arr[i])));
                }
            } else {
                for (int i = arr.length - 1; i >= 0; i--) {
                    goodslist.add(getGoodsById(Integer.parseInt(arr[i])));
                }
            }
            return goodslist;
        } else {
            return null;
        }

    }


    //删除商品
    public boolean delete_good(String name) throws SQLException {
        DBHelper dbHelper = new DBHelper();
        conn = dbHelper.getConnection();
        String sql = "DELETE FROM good WHERE name=?";

        //这个地方出了个错，下边两句话写反了，注意一定是先预编译sql
        //才能设置参数
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        int n = stmt.executeUpdate();
        if (n == 1) {
            dbHelper.free(null, stmt, conn);
            return true;
        } else {
            dbHelper.free(null, stmt, conn);
            return false;
        }
    }

    //根据商品名称查询商品
    public Good getGoodsByName(String name) throws SQLException {
        DBHelper dbHelper = new DBHelper();
        conn = dbHelper.getConnection();
        String sql = "select * from good where name=?;"; // SQL语句
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        rs = stmt.executeQuery();
        if (rs.next()) {
            Good good = new Good();
            good.setId(rs.getInt("id"));
            good.setName(rs.getString("name"));
            good.setKind(rs.getString("kind"));
            good.setPrice(rs.getDouble("price"));
            good.setOrigin(rs.getString("origin"));
            good.setPicture(rs.getString("picture"));
            dbHelper.free(rs, stmt, conn);
            return good;
        } else {
            dbHelper.free(rs, stmt, conn);
            return null;
        }
    }

    public boolean addGood(Good good) throws Exception{
        int id = good.getId();
        String name = good.getName();
        String kind = good.getKind();
        String origin = good.getOrigin();
        double price = good.getPrice();
        String pic = good.getPicture();
        DBHelper dbHelper = new DBHelper();
        conn = dbHelper.getConnection();
        String sql = "INSERT INTO good VALUES (?, ?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.setString(2, name);
        stmt.setString(3, kind);
        stmt.setDouble(4, price);
        stmt.setString(5, origin);
        stmt.setString(6, pic);

        int n = stmt.executeUpdate();
        if (n == 1){
            dbHelper.free(null, stmt, conn);
            return true;
        } else {
            dbHelper.free(null, stmt, conn);
            return false;
        }

    }
}
