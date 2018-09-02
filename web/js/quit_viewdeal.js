//处理用户查看订单的请求
$(document).ready(function () {
    $(".view_deal").click(function () {
        var username = $(".username").text();
        //查看订单
        $.ajax({
            url:"/S_ViewDeal",
            type:"post",
            async:false,
            data:{
                "username": username
            },//传出的数据
            dataType:"json",
            success: function (data) {
                if (data.success == true){
                    window.location.href = "/user_deal.jsp"
                } else {
                    alert(data["msg"]);
                }
            }
        });
    })
});