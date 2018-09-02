<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/6/11
  Time: 8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加商品</title>
    <link rel="stylesheet" type="text/css" href="css/admin_main.css">
    <link rel="stylesheet" type="text/css" href="css/add_good.css">
    <script type="text/javascript" rel="script" src="js/jq.js"></script>
    <script type="text/javascript" rel="script" src="js/user_nav.js"></script>
    <script type="text/javascript" rel="script" src="js/add_good.js"></script>
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
                <p>请输入商品信息</p>
            </div>
            <form action="/S_doUpLoad" method="post" id="uploadinfo">
                <div>
                    <span>编号：</span>
                    <input type="text" class="id" name="id">
                    <span class="tip1"></span>
                </div>
                <div>
                    <span>名称：</span>
                    <input type="text" class="name" name="name">
                    <span class="tip2"></span>
                </div>
                <div>
                    <span>种类：</span>
                    <select class="kind" name="kind">
                        <option value ="phone" selected="selected">phone</option>
                        <option value ="laptop">laptop</option>
                        <option value="pad">pad</option>
                        <option value="airpod">airpod</option>
                        <option value="camera">camera</option>
                    </select>
                </div>
                <div>
                    <span>价格：</span>
                    <input type="text" class="price" name="price">
                    <span class="tip3"></span>
                </div>
                <div>
                    <span>产地：</span>
                    <input type="text" class="origin" name="origin">
                    <span class="tip4"></span>
                </div>
            </form>
            <form action="/S_doUpLoadImg" method="post" id="uploadimg" enctype="multipart/form-data">
                <div class="div_file">
                    <span>图片：</span>
                    <input type="file" id="pic" name="pic"/>
                    <div class="view_img">
                        <img src="/images/defualt.jpg" class="view_imgtag">
                    </div>
                    <span class="tip5"></span>
                </div>
            </form>
            <div class="div_upbt">
                <input type="button" value="上传" class="upload_bt">
            </div>
            <div class="info"></div>
        </div>
    </div>
</div>
</body>
</html>
