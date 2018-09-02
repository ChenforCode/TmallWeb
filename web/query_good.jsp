<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/6/15
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询商品</title>
    <link rel="stylesheet" type="text/css" href="css/admin_main.css">
    <link rel="stylesheet" type="text/css" href="css/query_good.css">
    <script type="text/javascript" rel="script" src="js/jq.js"></script>
    <script type="text/javascript" rel="script" src="js/user_nav.js"></script>
    <script type="text/javascript" rel="script" src="js/query_good.js"></script>
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
        <div class="query_div">
            <div class="query_title margin_div">
                <p>请输入要查询的商品名称</p>
            </div>
            <div class="div_query_name margin_div">
                <span>名称：</span>
                <input type="text" class="query_name">
                <span class="tip1"></span>
            </div>
            <div class="div_query_bt margin_div">
                <input type="button" class="query_bt" value="查询">
            </div>
            <div class="good_item">
                <div class="pic">
                    <img class="good_img">
                </div>
                <div class="info">
                    <p class="good_name"></p>
                    <p class="good_id"></p>
                    <p class="good_kind"></p>
                    <p class="good_price"></p>
                    <p class="good_origin"></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
