<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="beans.Cart" %>
<%@ page import="beans.Good" %>
<html>
<head>
    <meta content="text/html" charset="utf-8">
    <title>购物车</title>
    <link type="text/css" rel="stylesheet" href="css/cart.css"/>
    <script type="text/javascript" src="js/jq.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>
    <script type="text/javascript" src="js/quit_viewdeal.js"></script>
    <script type="text/javascript" src="js/personal_info.js"></script>
    <script type="text/javascript" src="js/head_display.js"></script>
</head>

<body>
<div id="bg-image"></div>
<div id="header">
    <a href="/main.jsp" class="goback_index">返回首页</a>
    <span>你好 </span>
    <p class="username"><%=session.getAttribute("username")%></p>
    <div class="head_div"></div>
    <a class="quit_login">退出登录</a>
    <input type="button" class="view_deal" value="查看订单">
</div>
<hr>
<div class="shopping">
    <div class="cart_head">
        <span class="good_id">商品编号</span>
        <span class="good_name">商品名称</span>
        <span class="good_kind">商品种类</span>
        <span class="good_origin">商品产地</span>
        <span class="good_price">商品单价</span>
        <span class="good_num">购买数量</span>
        <span class="good_op">操作</span>
    </div>
    <%
        //首先判断session中是否有购物车对象
        if (request.getSession().getAttribute("cart") != null) {
    %>
    <!-- 循环将cart中的商品添加到界面上 -->
    <%
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        HashMap<Good, Integer> goods = cart.getGoods();
        Set<Good> items = goods.keySet();
        Iterator<Good> it = items.iterator();

        while (it.hasNext()) {
            Good i = it.next();
    %>
    <div class="good_item">
        <img src="images/goodimg/<%=i.getPicture()%>">
        <span class="good_id_con"><%=i.getId()%></span>
        <span class="good_name_con"><%=i.getName()%></span>
        <span class="good_kind_con"><%=i.getKind()%></span>
        <span class="good_origin_con"><%=i.getOrigin()%></span>
        <span class="good_price_con"><%=i.getPrice()%></span>
        <span class="good_num_con"><%=goods.get(i)%></span>
        <a class="good_op_con" href="/CartServlet?action=delete&id=<%=i.getId()%>" onclick="delcfm();">删除</a>
    </div>
    <%
        }
    %>
    <div class="total">
        <span>总计：</span>
        <span id="total"><%=cart.getTotalPrice() %>
        </span>￥</span>
    </div>
    <%
        }
    %>
    <div class="submit_bt">
        <input type="button" class="creatDealbyCart" value="提交订单"/>
    </div>
</div>
</body>
</html>
