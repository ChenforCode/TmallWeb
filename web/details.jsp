<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="beans.Good" %>
<%@ page import="dao.GoodDao" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>商品详情</title>

    <link href="css/detail.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jq.js"></script>
    <script type="text/javascript" src="js/detail.js"></script>
    <script type="text/javascript" src="js/quit_viewdeal.js"></script>
    <script type="text/javascript" src="js/personal_info.js"></script>
    <script type="text/javascript" src="js/head_display.js"></script>
</head>

<body>
<div id="bg-image"></div>
<div id="header">
    <a href="/main.jsp" class="goback_index">返回首页</a>
    <span class="nihao">你好 </span>
    <p class="username"><%=session.getAttribute("username")%></p>
    <div class="head_div"></div>
    <a class="quit_login" href="/login.jsp">退出登录</a>
    <input type="button" class="view_deal" value="查看订单">
</div>
<hr>
<div class="main_con">
    <div class="main_con_left">
        <!-- 商品详情 -->
        <%
            GoodDao goodDao = new GoodDao();
            int id = Integer.parseInt(request.getParameter("id"));
            Good good = goodDao.getGoodsById(id);
            if (good != null) {
        %>
        <div class="good_details">
            <div class="detail_title">
                <p>商品详情</p>
            </div>
            <div class="pic">
                <img src="images/goodimg/<%=good.getPicture()%>"/>
            </div>
            <div class="info">
                <div class="div_goodname">
                    <p class="_goodname"><%=good.getName() %></p>
                </div>
                <div>
                    <p>商品编号：</p>
                    <p class="good_id"><%=good.getId()%></p>
                </div>
                <div>
                    <p>种类：</p>
                    <p class="good_kind"><%=good.getKind()%></p>
                </div>
                <div>
                    <p>产地：</p>
                    <p class="good_origin"><%=good.getOrigin()%></p>
                </div>
                <div>
                    <p>价格：</p>
                    <p class="good_price"><%=good.getPrice()%>￥</p>
                </div>
                <div>
                    <p>购买数量：</p>
                    <span id="sub" onclick="sub();">-</span>
                    <input type="text" class="number" id="number" name="number" size="3" value="1"/>
                    <span id="add" onclick="add();">+</span>
                </div>
                <div id="cart">
                    <input type="button" class="AddtoCart_bt" value="添加到购物车">
                    <input type="button" class="Buynow_bt" value="立即购买">
                    <input type="button" class="ViewCart_bt" value="查看购物车">
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <div class="main_con_right">
        <%
            String list = "";
            //从客户端获得Cookies集合
            Cookie[] cookies = request.getCookies();
            //遍历这个Cookies集合
            if (cookies != null && cookies.length > 0) {
                for (Cookie c : cookies) {
                    if ("ListViewCookie".equals(c.getName())) {
                        list = c.getValue();
                    }
                }
            }

            //将本次浏览记录天交到cookie
            list += request.getParameter("id") + "#";
            //如果浏览记录超过1000条，清零.
            String[] arr = list.split("#");
            if (arr != null && arr.length > 0) {
                if (arr.length >= 1000) {
                    list = "";
                }
            }

            //更新Cookie
            Cookie cookie = new Cookie("ListViewCookie", list);
            response.addCookie(cookie);

        %>
        <!-- 浏览过的商品 -->
        <p class="view_info">您浏览过的商品</p>
        <!-- 循环开始 -->
        <%
            ArrayList<Good> itemlist = goodDao.getViewList(list);
            if (itemlist != null && itemlist.size() > 0) {
                for (Good i : itemlist) {

        %>
        <div class="view_justnow">
            <a href="details.jsp?id=<%=i.getId()%>">
                <img src="images/goodimg/<%=i.getPicture()%>"/>
            </a>
            <p class="dd_name"><%=i.getName()%></p>
            <p class="dd_kind">种类:<%=i.getKind()%></p>
            <p class="dd_city">产地:<%=i.getOrigin()%></p>
            <p class="dd_price">价格:<%=i.getPrice()%>￥</p>
        </div>
        <%
                }
            }
        %>
        <!-- 循环结束 -->
    </div>

</div>
</body>
</html>
