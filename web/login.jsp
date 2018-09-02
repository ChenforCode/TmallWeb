<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/5/27
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="#" />
    <title>欢迎登陆</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script type="text/javascript" rel="script" src="js/jq.js"></script>
    <script type="text/javascript" rel="script" src="js/display_val.js"></script>
</head>
<body>
    <div id="bg-image"></div>
    <div class="login_div">
        <form class="form1" method="post" action="/S_dologin">
            <div class="part0">
                <p id="welcome_info">Welcome</p>
            </div>
            <div class="part1">
                <input type="text" name="username" class="input_1" value="Username">
                <span class="tip_1"></span>
            </div>
            <div class="part2">
                <input type="password" name="password" class="input_2" value="Password">
                <span class="tip_2"></span>
            </div>
            <div class="part3">
                <input type="text" name="checkcode" class="input_5">
                <img src="/S_checkcode" class="check_img">
                <span class="tip_3"></span>
            </div>
            <div class="part4">
                <input type="button" value="Login" class="input_3">
                <input type="button" value="Register" class="input_4">
            </div>
            <div class="part5">
                <p id="login_info"></p>
            </div>
        </form>
    </div>
</body>
</html>
