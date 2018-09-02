<%@ page import="beans.Good" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/6/14
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑商品</title>
    <link rel="stylesheet" type="text/css" href="css/admin_main.css">
    <link rel="stylesheet" type="text/css" href="css/edit_good.css">
    <script type="text/javascript" rel="script" src="js/jq.js"></script>
    <script type="text/javascript" rel="script" src="js/user_nav.js"></script>
    <script type="text/javascript" rel="script" src="js/edit_good.js"></script>
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
        <div class="upload_form">
            <div class="form_title">
                <p>请输入要修改的商品名称和要修改的信息</p>
            </div>
            <form action="/S_doUpLoad" method="post" id="uploadinfo">
                <div>
                    <span>名称：</span>
                    <input type="text" class="name" name="name">
                    <span class="tip1"></span>
                </div>
                <div>
                    <span>价格：</span>
                    <input type="text" class="price" name="price">
                    <span class="tip2"></span>
                </div>
                <div>
                    <span>产地：</span>
                    <input type="text" class="origin" name="origin">
                </div>
            </form>
            <div class="div_editbt">
                <input type="button" value="上传" class="edit_bt">
            </div>
            <div class="div_tip">
                <p class="tip"></p>
            </div>
            <div class="edit_good_info">
                <div>
                    <span class="edit_title">商品编号：</span>
                    <span class="edit_id"></span>
                </div>
                <div>
                    <span class="edit_title">商品名称：</span>
                    <span class="edit_name"></span>
                </div>
                <div>
                    <span class="edit_title">商品种类：</span>
                    <span class="edit_kind"></span>
                </div>
                <div>
                    <span class="edit_title">商品产地：</span>
                    <span class="edit_origin"></span>
                </div>
                <div>
                    <span class="edit_title">商品价格：</span>
                    <span class="edit_price"></span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
