//用来跳转到个人信息的界面
$(document).ready(function () {

    //用户名的点击事件
    $(".username").click(function () {
        $.ajax({
            url:"/S_personal_info",
            type:"post",
            async:false,
            data:{
            },//传出的数据
            dataType:"json",
            success: function (data) {
                if (data.success == true) {
                    window.location.href = "/personal_info.jsp";
                } else {
                    alert("请求失败，请重试！");
                }
            }
        });
   });
});