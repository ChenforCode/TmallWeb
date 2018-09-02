<%@ page import="beans.Cart" %>
<%@ page import="beans.Good" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="beans.Deal" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/6/8
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户订单</title>
    <link href="css/user_deal.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jq.js"></script>
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
    <a class="quit_login" href="/login.jsp">退出登录</a>
</div>
<hr>
<div class="main_con">
    <div class="deal_head">
        <span class="deal_id">订单编号</span>
        <span class="deal_addr">目的地址</span>
        <span class="deal_goodinfo">商品信息</span>
        <span class="deal_price">订单总价</span>
    </div>
    <%
        //首先判断session中是否有Deal数组
        if (request.getSession().getAttribute("user_deals") != null) {
    %>
    <%
        Deal[] deals = (Deal[]) request.getSession().getAttribute("user_deals");
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
</body>
</html>
