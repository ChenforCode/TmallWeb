<%@ page import="beans.Deal" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/6/16
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑商品</title>
    <link rel="stylesheet" type="text/css" href="css/admin_main.css">
    <link rel="stylesheet" type="text/css" href="css/admin_deal.css">
    <script type="text/javascript" rel="script" src="js/jq.js"></script>
    <script type="text/javascript" rel="script" src="js/user_nav.js"></script>
</head>
<body>
<div class="navigator">
    <div class="title">
        <p>Manage</p>
    </div>
    <div class="view_user">
        <p>查看用户信息</p>
    </div>
    <div class="view_goods">
        <p>查看商品信息</p>
    </div>
    <div class="add_good">
        <p>增加商品</p>
    </div>
    <div class="delete_good">
        <p>删除商品</p>
    </div>
    <div class="edit_good">
        <p>修改商品</p>
    </div>
    <div class="query_good">
        <p>查询商品</p>
    </div>
    <div class="view_deal">
        <p>查看订单信息</p>
    </div>
</div>
<div class="main_con">
    <div class="header">
        <p>欢迎来到商城管理系统</p>
        <a href="/login.jsp">退出登陆</a>
    </div>
    <div class="view_info">
        <div class="deal_head">
            <span class="deal_id">订单编号</span>
            <span class="deal_addr">目的地址</span>
            <span class="deal_goodinfo">商品信息</span>
            <span class="deal_price">订单总价</span>
        </div>
        <%
            //首先判断session中是否有Deal数组
            if (request.getSession().getAttribute("admin_deals") != null) {
        %>
        <%
            Deal[] deals = (Deal[]) request.getSession().getAttribute("admin_deals");
            //循环将deal信息渲染到界面上
            for (Deal deal : deals) {
        %>
        <div class="deal_item">
            <span class="deal_id_con"><%=deal.getId()%></span>
            <span class="deal_addr_con"><%=deal.getAddress()%></span>
            <div class="good_info">
                <%
                    HashMap<String, Integer> goodinfo = deal.getGoods();
                    Set<String> names = goodinfo.keySet();
                    Iterator<String> it = names.iterator();
                    while (it.hasNext()) {
                        String name = it.next();
                %>
                <%--每一个交易中的货物信息--%>
                <div class="good_info_item">
                    <span>名称：</span>
                    <span class="good_name"><%=name%></span>
                    <span>数量：</span>
                    <span class="good_num"><%=goodinfo.get(name)%></span>
                    <br>
                </div>
                <%
                    }
                %>
            </div>
            <span class="deal_price_con"><%=deal.getPrice()%>￥</span>
        </div>
        <%
            }
        %>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
