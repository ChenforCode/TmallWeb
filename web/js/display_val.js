$(document).ready(function () {
    $(".input_1").focus(function () {
        if ($(this).val() == "Username"){
            $(this).val("");
            $(".tip_1").text("");
        }
    });
    $(".input_1").blur(function () {
        if ($(this).val() == ""){
            $(this).val("Username");
        }
    });
    $(".input_2").focus(function () {
        if ($(this).val() == "Password"){
            $(this).val("");
            $(".tip_2").text("");
        }
    });
    $(".input_2").blur(function () {
        if ($(this).val() == ""){
            $(this).val("Password");
        }
    });
    $(".input_3").click(function () {
        if ($(".input_1").val() == "Username"){
            $(".tip_1").text("请输入用户名");
        }
        if ($(".input_2").val() == "Password"){
            $(".tip_2").text("请输入密码");
        }
        if ($(".input_5").val() == ""){
            $(".tip_3").text("请输入验证码");
        }
        if ($(".tip_1").text() == "" && $(".tip_2").text() == ""
            && $(".tip_3").text() == ""){
            var uname = $(".input_1").val();
            var upwd = $(".input_2").val();
            var chcode = $(".input_5").val();
            $.ajax({
                url:"/S_dologin",
                type:"post",
                async:true,//异步
                data:{
                    "username": uname,
                    "password": upwd,
                    "checkcode": chcode
                },//传出的数据
                dataType:"json",//返回的数据类型，常用：html/text/json
                success:function(data){
                    if (!data.success){
                        $("#login_info").text(data["msg"]);
                    } else {
                        if (data["msg"] == "管理员登陆"){
                            window.location.href='/admin_main.jsp';
                        } else {
                            window.location.href='/main.jsp';
                        }
                    }
                }
            });
        } else {
            alert("请完善你的信息");
        }
    });
    $(".input_5").focus(function () {
        $(".tip_3").text("");
    });

    //为验证码图片添加点击事件，更换图片
    $(".check_img").click(function () {
        window.location.href="/login.jsp";
    });
    $(".input_4").click(function () {
        window.location.href = "/register.jsp";
    })
});