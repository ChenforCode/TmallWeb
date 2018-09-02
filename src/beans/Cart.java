package beans;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//购物车类
public class Cart {

	private HashMap<Good, Integer> goods;

	private double totalPrice;

	public Cart()
	{
		goods = new HashMap<Good,Integer>();
		totalPrice = 0.0;
	}
	
	
	public HashMap<Good, Integer> getGoods() {
		return goods;
	}

	public void setGoods(HashMap<Good, Integer> goods) {

	    this.goods = goods;
	}

	public double getTotalPrice() {

	    return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {

	    this.totalPrice = totalPrice;
	}
	
	//加入商品进购物车
	public boolean addGoodsInCart(Good good ,int number)
	{
        //判断购物车里是否已经有这个商品，如果有则直接修改数量
		if(goods.containsKey(good))
		{
			goods.put(good, goods.get(good)+number);
		}
		else
		{
			goods.put(good, number);
		}
		calTotalPrice(); //重新计算购物车的商品总价格
		return true;
	}
	
	//将商品从购物车中移除
	public boolean removeGoodsFromCart(Good good)
	{
		goods.remove(good);
		calTotalPrice(); //重新计算购物车的商品总价格
		return true;
	}
	
	//计算购物车内商品总价格
	public double calTotalPrice()
	{
		double sum = 0.0;
		Set<Good> keys = goods.keySet();
		Iterator<Good> it = keys.iterator();
	    while(it.hasNext())
	    {
	    	Good i = it.next();
	    	sum += i.getPrice()* goods.get(i);
	    }
	    this.setTotalPrice(sum); //设置商品总价格
	    return this.getTotalPrice();
	}
}
