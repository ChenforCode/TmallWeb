<%@ page import="beans.Good" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/6/11
  Time: 0:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除商品</title>
    <link rel="stylesheet" type="text/css" href="css/admin_main.css">
    <link rel="stylesheet" type="text/css" href="css/delete_good.css">
    <script type="text/javascript" rel="script" src="js/jq.js"></script>
    <script type="text/javascript" rel="script" src="js/user_nav.js"></script>
    <script type="text/javascript" rel="script" src="js/delete_good.js"></script>
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
        <div class="info">
            <p class="del_title">请输入要删除的商品的名称</p>
            <input type="text" name="delete_good_name" class="del_name" placeholder="goodName">
            <input type="button" class="del_but" value="删除商品">
            <p class="del_info"></p>
            <div class="del_good_info">
                <div>
                    <span class="hide_title">商品编号：</span>
                    <span class="del_id"></span>
                </div>
                <div>
                    <span class="hide_title">商品名称：</span>
                    <span class="del_name"></span>
                </div>
                <div>
                    <span class="hide_title">商品种类：</span>
                    <span class="del_kind"></span>
                </div>
                <div>
                    <span class="hide_title">商品产地：</span>
                    <span class="del_origin"></span>
                </div>
                <div>
                    <span class="hide_title">商品价格：</span>
                    <span class="del_price"></span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
