<%@ page import="beans.User" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/6/10
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <link rel="stylesheet" type="text/css" href="css/admin_main.css">
    <link rel="stylesheet" type="text/css" href="css/view_user.css">
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
    <div class="content">
        <%
            if (session.getAttribute("users") != null){
                User [] users = (User[]) session.getAttribute("users");
                for (User user: users){

        %>
        <div class="user_item">
            <span>用户名：<%=user.getUsername()%></span>
            <span>密码：<%=user.getPassword()%></span>
            <span>电话：<%=user.getTelphone()%></span>
            <span>收货地址：<%=user.getAddress()%></span>
        </div>
        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>
