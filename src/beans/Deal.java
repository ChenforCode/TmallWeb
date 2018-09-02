package beans;

import java.util.HashMap;

public class Deal {

    //订单的基本信息
    private int id;
    private String username;
    private String address;
    private double price;
    private HashMap<String, Integer> goods;

    public Deal(int id, String username, String address, double price, HashMap<String, Integer> goods) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.price = price;
        this.goods = goods;
    }

    public Deal() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }


    public HashMap<String, Integer> getGoods() {

        return goods;
    }

    public void setGoods(HashMap<String, Integer> goods) {

        this.goods = goods;
    }
}
