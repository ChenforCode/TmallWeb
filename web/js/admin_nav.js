$(document).ready(function () {
    //为各个导航栏创建导航栏并且发送ajax请求
    $(".title").click(function () {
       window.location.href = '/admin_main.jsp';
    });

    //查看用户信息
    $(".view_user").click(function () {
        $.ajax({
            url:"/S_view_user",
            type:"post",
            async:false,
            data:{

            },//传出的数据
            dataType:"json",//返回的数据类型，常用：html/text/json
            success:function(data){
                if (data.success == true){
                    window.location.href = '/view_user.jsp'
                }
            }
        });
    });


    //查看商品信息
    $(".view_goods").click(function () {
        $.ajax({
            url:"/S_view_goods",
            type:"post",
            async:false,
            data:{

            },//传出的数据
            dataType:"json",//返回的数据类型，常用：html/text/json
            success:function(data){
                if (data.success == true){
                    window.location.href = '/view_goods.jsp'
                }
            }
        });
    });

    //增加商品，跳转到增加商品的界面
    $(".add_good").click(function () {
        window.location.href = "/add_good.jsp";
    });


    //删除商品 跳转到删除商品的界面
    $(".delete_good").click(function () {
        window.location.href = '/delete_good.jsp';
    });

    //修改商品 跳转到修改商品的界面
    $(".edit_good").click(function () {
        window.location.href = '/edit_good.jsp';
    });

    //查询商品 跳转到查询商品的界面
    $(".query_good").click(function () {
        window.location.href = '/query_good.jsp';
    });

    //显示订单 跳转到显示订单的界面
    $(".view_deal").click(function () {
        $.ajax({
            url:"/S_adminDeal",
            type:"post",
            async:false,
            data:{
            },//传出的数据
            dataType:"json",
            success: function (data) {
                if (data.success == true){
                    window.location.href = "/admin_deal.jsp"
                } else {
                    alert(data["msg"]);
                }
            }
        });
    });

});