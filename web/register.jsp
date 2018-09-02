<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/5/28
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="shortcut icon" href="#" />
    <title>欢迎注册</title>
    <link type="text/css" rel="stylesheet" href="css/register.css">
    <script type="text/javascript" rel="script" src="js/jq.js"></script>
    <script type="text/javascript" rel="script" src="js/register.js"></script>
</head>
<body>
    <div id="bg-image"></div>
    <div class="reg_div">
        <p>Register</p>
        <form class="form1" action="/S_register" method="post">
            <div class="div_uname">
                <input class="input_1" value="Username" name="username">
                <span class="tip_1"></span>
            </div>
            <div class="div_pwd">
                <input type="password" class="input_2" value="Password" name="password">
                <span class="tip_2"></span>
            </div>
            <div class="div_conpwd">
                <input type="password" class="input_3" value="Password">
                <span class="tip_3"></span>
            </div>
            <div class="div_telphone">
                <input class="input_4" value="TelPhone" name="telphone">
                <span class="tip_4"></span>
            </div>
            <div class="div_addr">
                <input class="input_5" value="Address" name="address">
                <span class="tip_5"></span>
            </div>
            <div>
                <input type="button" class="input_6" value="Register">
            </div>
        </form>
    </div>
</body>
</html>
