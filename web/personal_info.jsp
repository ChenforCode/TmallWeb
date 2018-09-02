<%@ page import="beans.User" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/6/16
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
    <link type="text/css" rel="stylesheet" href="css/personal_info.css">
    <script type="text/javascript" src="js/jq.js"></script>
    <script type="text/javascript" src="js/control_head.js"></script>
    <script type="text/javascript" src="js/quit_viewdeal.js"></script>
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
    <div class="personal_info_con">
        <%
            if (session.getAttribute("personal_user") != null){
                User user = (User) session.getAttribute("personal_user");
        %>
            <div class="person_head"></div>
            <div class="div_uphead">
                <form action="/S_upLoadHeadImg" id="up_headimg" enctype="multipart/form-data">
                    <input type="file" id="head_pic" name="head_pic">
                    <input type="button" class="uphead_bt" value="上传头像">
                </form>
            </div>
            <div class="person_username">
                <p>用户名：<%=user.getUsername()%></p>
            </div>
            <div class="person_password">
                <p>密码：<%=user.getPassword()%></p>
            </div>
            <div class="person_telphone">
                <p>联系方式：<%=user.getTelphone()%></p>
            </div>
            <div class="person_addr">
                <p>收货地址：<%=user.getAddress()%></p>
            </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
