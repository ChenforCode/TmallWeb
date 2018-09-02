<%@ page import="beans.Good" %>
<%@ page import="dao.GoodDao" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/5/30
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to Tmall</title>
    <link href="css/main.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/jq.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="js/imgcycleimg.js"></script>
    <script type="text/javascript" src="js/quit_viewdeal.js"></script>
    <script type="text/javascript" src="js/personal_info.js"></script>
    <script type="text/javascript" src="js/head_display.js"></script>
    <meta content="text/html" charset=UTF-8>
</head>
<body onload="hide()">
<div id="bg-image"></div>
<div id="header">
    <span class="nihao">你好 </span>
    <p class="username"><%=session.getAttribute("username")%></p>
    <div class="head_div"></div>
    <a class="quit_login" href="/login.jsp">退出登录</a>
    <input type="button" class="view_deal" value="查看订单">
</div>
<div id="main_con">
    <div class="navigator">
        <div class="nav_left">
            <div class="ul_phone"><p>智能手机</p></div>
            <div class="ul_laptop"><p>笔记本电脑</p></div>
            <div class="ul_pad"><p>平板电脑</p></div>
            <div class="ul_pod"><p>耳机</p></div>
            <div class="ul_camera"><p>数码相机</p></div>
        </div>
        <div class="pic_display">
            <div class="re_image" id="re_image">
                <input type="submit" class="lbt" value="<">
                <input type="submit" class="rbt" value=">">
                <img src="images/recycle2.jpg" alt="">
            </div>
            <div class="phone">
                <div class="good_1">
                    <span>iPhone&nbsp;&nbsp;</span>
                    <a href="/details.jsp?id=1">iPhone X | </a>
                    <a href="/details.jsp?id=2">iPhone 8 | </a>
                    <a href="/details.jsp?id=3">iPhone 7 | </a>
                    <a href="/details.jsp?id=4">iPhone 6</a>
                </div>
                <div class="good_1">
                    <span>三星&nbsp;&nbsp;</span>
                    <a href="/details.jsp?id=5">Galaxy S9 | </a>
                    <a href="/details.jsp?id=6">Galaxy S8 | </a>
                    <a href="/details.jsp?id=7">Note 8 | </a>
                    <a href="/details.jsp?id=8">Note 7</a>
                </div>
                <div class="good_1">
                    <span>华为&nbsp;&nbsp;</span>
                    <a href="/details.jsp?id=9">P20 | </a>
                    <a href="/details.jsp?id=10">P10 | </a>
                    <a href="/details.jsp?id=11">mate 10 | </a>
                    <a href="/details.jsp?id=12">mate 9</a>
                </div>
                <div class="good_1">
                    <span>小米&nbsp;&nbsp;</span>
                    <a href="/details.jsp?id=13">小米 8 | </a>
                    <a href="/details.jsp?id=14">Mix2 | </a>
                    <a href="/details.jsp?id=15">note 3 | </a>
                    <a href="/details.jsp?id=16">红米 5</a>
                </div>
            </div>
            <div class="laptop">
                <div class="good_1">
                    <span>联想&nbsp;&nbsp;</span>
                    <a href="/details.jsp?id=17">小新V4000 | </a>
                    <a href="/details.jsp?id=18">拯救者 | </a>
                    <a href="/details.jsp?id=19">ThinkPad | </a>
                    <a href="/details.jsp?id=20">YOGA</a>
                </div>
                <div class="good_1">
                    <span>惠普&nbsp;&nbsp;</span>
                    <a href="/details.jsp?id=21">暗影精灵 | </a>
                    <a href="/details.jsp?id=22">银河舰队 | </a>
                    <a href="/details.jsp?id=23">畅游人 | </a>
                    <a href="/details.jsp?id=24">光影精灵</a>
                </div>
                <div class="good_1">
                    <span>戴尔&nbsp;&nbsp;</span>
                    <a href="/details.jsp?id=25">游匣 | </a>
                    <a href="/details.jsp?id=26">灵越 | </a>
                    <a href="/details.jsp?id=27">G3 | </a>
                    <a href="/details.jsp?id=28">G7</a>
                </div>
            </div>
            <div class="pad">
                <div class="good_1">
                    <span>iPad&nbsp;&nbsp;</span>
                    <a href="/details.jsp?id=29">iPad mini | </a>
                    <a href="/details.jsp?id=30">iPad air | </a>
                    <a href="/details.jsp?id=31">iPad pro</a>
                </div>
                <div class="good_1">
                    <span>三星&nbsp;&nbsp;</span>
                    <a>Galaxy Tab S3 | </a>
                    <a>Galaxy TabPro S | </a>
                    <a>Galaxy Tab S 10.5</a>
                </div>
            </div>
            <div class="airpod">
                <div class="good_1">
                    <span>苹果耳机&nbsp;&nbsp;</span>
                    <a>AirPods | </a>
                    <a>EarPods | </a>
                    <a>EarPods iPhone 7</a>
                </div>
                <div class="good_1">
                    <span>Edifier&nbsp;&nbsp;</span>
                    <a>H297 | </a>
                    <a>H841P | </a>
                    <a>H840</a>
                </div>
                <div class="good_1">
                    <span>Philips&nbsp;&nbsp;</span>
                    <a>SHP9500 | </a>
                    <a>SHB4205 | </a>
                    <a>SHB9850NC</a>
                </div>
                <div class="good_1">
                    <span>Beats&nbsp;&nbsp;</span>
                    <a>SHP9500 | </a>
                    <a>SHB4205 | </a>
                    <a>SHB9850NC</a>
                </div>
            </div>
            <div class="camera">
                <div class="good_1">
                    <span>佳能&nbsp;&nbsp;</span>
                    <a>EOS 80D | </a>
                    <a>EOS 77D | </a>
                    <a>EOS 6D | </a>
                    <a>EOS 200D</a>
                </div>
                <div class="good_1">
                    <span>尼康&nbsp;&nbsp;</span>
                    <a>D3400 | </a>
                    <a>D5300 | </a>
                    <a>D750 | </a>
                    <a>D7200</a>
                </div>
                <div class="good_1">
                    <span>索尼&nbsp;&nbsp;</span>
                    <a>ILCE-6000L | </a>
                    <a>ILCE-7M2K | </a>
                    <a>ILCE-7RM3 | </a>
                    <a>ILCE-7M2</a>
                </div>
            </div>
        </div>
    </div>

    <div class="goods_list">
        <!-- 商品循环开始 -->
        <%
            GoodDao goodDao = new GoodDao();
            ArrayList<Good> list = goodDao.getAllItems();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Good good = list.get(i);
        %>
        <div class="good_item">
            <div class="pic">
                <a href="details.jsp?id=<%=good.getId()%>">
                    <img class="good_img" src="images/goodimg/<%=good.getPicture()%>">
                </a>
            </div>
            <div class="info">
                <p class="good_name"><%=good.getName() %></p>
                <p class="good_kind">种类:<%=good.getKind()%></p>
                <p class="good_price">价格:<%=good.getPrice()%></p>
                <p class="good_origin">产地:<%=good.getOrigin()%></p>
            </div>
        </div>
        <!-- 商品循环结束 -->

        <%
                }
            }
        %>
    </div>
</div>
</body>
</html>