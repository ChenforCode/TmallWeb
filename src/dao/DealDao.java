package dao;

import beans.Deal;
import beans.Good;
import jdbc.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

public class DealDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public boolean createDeal(Deal deal) throws SQLException {
        String uname = deal.getUsername();
        String addr = deal.getAddress();
        double price = deal.getPrice();

        //构造货物信息字符串
        String goodinfoStr = "";
        HashMap<String, Integer> goods = deal.getGoods();
        Set<String> keys = goods.keySet();
        for (String key : keys) {
            goodinfoStr += key + "#";
            goodinfoStr += goods.get(key) + "#";
        }

        DBHelper dbHelper = new DBHelper();
        conn = dbHelper.getConnection();
        String sql = "INSERT INTO deal VALUES (NULL,'" + uname + "','" + addr + "','" + price + "','" + goodinfoStr + "')";
        pstmt = conn.prepareStatement(sql);

        int n = pstmt.executeUpdate();
        if (n == 1) {
            dbHelper.free(null, pstmt, conn);
            return true;
        } else {
            dbHelper.free(null, pstmt, conn);
            return true;
        }
    }

    //返回一个Deal的数组，因为一个一个User可能不止一个订单
    public Deal[] queryDealbyUname(String username) throws SQLException {
        DBHelper dbHelper = new DBHelper();
        conn = dbHelper.getConnection();
        String sql = "select * from deal where username='" + username + "'";
        pstmt = conn.prepareStatement(sql);

        rs = pstmt.executeQuery();
        int count = 0;
        while (rs.next()) {
            count++;
        }
        rs = pstmt.executeQuery();
        int index = 0;
        Deal[] deals = new Deal[count];
        while (rs.next()) {
            int id = rs.getInt(1);
            String addr = rs.getString(3);
            double price = rs.getDouble(4);
            String goodinfostr = rs.getString(5);

            //将货物信息的字符串分割
            String goodinfo[] = goodinfostr.split("#");
            HashMap<String, Integer> goods = new HashMap<>();
            //根据分割之后的数组重新构造出good的HashMap
            //每两组构造出一对(name, number)
            for (int i = 0; i < goodinfo.length - 1; i += 2) {
                goods.put(goodinfo[i], Integer.parseInt(goodinfo[i + 1]));
            }

            deals[index] = new Deal();
            deals[index].setId(id);
            deals[index].setUsername(username);
            deals[index].setAddress(addr);
            deals[index].setPrice(price);
            deals[index].setGoods(goods);
            index++;
        }

        return deals;
    }

    //返回一个Deal的数组，所有用户的订单信息
    public Deal[] queryAllDeal() throws SQLException {
        DBHelper dbHelper = new DBHelper();
        conn = dbHelper.getConnection();

        String sql = "SELECT * FROM deal";
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        int count = 0;
        while (rs.next()) {
            count++;
        }
        rs = pstmt.executeQuery();

        int index = 0;
        Deal[] deals = new Deal[count];
        while (rs.next()) {
            int id = rs.getInt(1);
            String uname = rs.getString(2);
            String addr = rs.getString(3);
            double price = rs.getDouble(4);
            String goodinfostr = rs.getString(5);

            //将货物信息的字符串分割
            String goodinfo[] = goodinfostr.split("#");
            HashMap<String, Integer> goods = new HashMap<>();
            //根据分割之后的数组重新构造出good的HashMap
            for (int i = 0; i < goodinfo.length - 1; i += 2) {
                goods.put(goodinfo[i], Integer.parseInt(goodinfo[i + 1]));
            }
            deals[index] = new Deal();
            deals[index].setId(id);
            deals[index].setUsername(uname);
            deals[index].setAddress(addr);
            deals[index].setPrice(price);
            deals[index].setGoods(goods);
            index++;
        }
        return deals;
    }
}
