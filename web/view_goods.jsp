<%@ page import="beans.User" %>
<%@ page import="dao.GoodDao" %>
<%@ page import="beans.Good" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/6/11
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品信息</title>
    <link rel="stylesheet" type="text/css" href="css/admin_main.css">
    <link rel="stylesheet" type="text/css" href="css/view_goods.css">
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
        <div class="goods_list">
            <!-- 商品循环开始 -->
            <%
                if (session.getAttribute("goods") != null) {
                    ArrayList<Good>list = (ArrayList<Good>) session.getAttribute("goods");
                    if (list != null && list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            Good good = list.get(i);
            %>
            <div class="good_item">
                <div class="pic">
                    <img class="good_img" src="images/goodimg/<%=good.getPicture()%>">
                </div>
                <div class="info">
                    <p class="good_name"><%=good.getName() %>
                    </p>
                    <p class="good_kind">种类:<%=good.getKind()%>
                    </p>
                    <p class="good_price">价格:<%=good.getPrice()%>
                    </p>
                    <p class="good_price">产地:<%=good.getOrigin()%>
                    </p>
                </div>
            </div>
            <!-- 商品循环结束 -->

            <%
                        }
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
