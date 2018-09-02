$(document).ready(function () {
    $(".input_1").focus(function () {
        if ($(this).val() == "Username"){
            $(this).val("");
            $(".tip_1").text("");
        }
    });
    $(".input_1").blur(function () {
        var name = $(this);
        //失去焦点的时候，如果什么都没输入，仍然变成默认值
        if (name.val() == ""){
            name.val("Username");
        }
        //如果不是空值，也不是默认值，那么判断是否合法
        var check = false;
        if(name.val() != "" && name.val() != "Username"){
            var reg = /^[a-zA-Z0-9_-]{4,16}$/;
            if (reg.test(name.val())){
                check = true;
                $(".tip_1").text("");
            }
            //如果非法
            if (!check){
                $(".tip_1").text("用户名非法")
                //如果合法，发送ajax请求
            } else {
                var username = name.val();
                $.ajax({
                    url:"/S_checkUsername",
                    type:"post",
                    async:true,//异步
                    data:{"username":username},//传出的数据
                    dataType:"json",//返回的数据类型，常用：html/text/json
                    success:function(data){
                        if (!data.success){
                            $(".tip_1").text(data["msg"]);
                        } else {
                            $(".tip_1").text("");
                        }
                    }
                });
            }
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
    $(".input_3").focus(function () {
        if ($(this).val() == "Password"){
            $(this).val("");
            $(".tip_3").text("");
        }
    });
    $(".input_3").blur(function () {
        if ($(this).val() != $(".input_2").val()){
            $(this).val("Password");
            $(".tip_3").text("两次密码不一致！");
        }
    });
    $(".input_4").focus(function () {
        if ($(this).val() == "TelPhone"){
            $(this).val("");
            $(".tip_4").text("");
        }
    });
    $(".input_4").blur(function () {
        if ($(this).val() == ""){
            $(this).val("TelPhone");
        }

        var phone = $(this);

        var check = false;
        //检验电话号码是否合法
        if (phone.val() != "" && phone.val() != "TelPhone"){
            var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
            if (reg.test(phone.val())){
                check = true;
            }

            //如果不合法则输出信息
            if (!check){
                $(".tip_4").text("电话号码非法");
            } else {
                //如果合法，则发送ajax请求判断是否重复
                var telphone = phone.val();
                $.ajax({
                    url:"/S_checkTelphone",
                    type:"post",
                    async:true,//异步
                    data:{"telphone":telphone},//传出的数据
                    dataType:"json",//返回的数据类型，常用：html/text/json
                    success:function(data){
                        if (!data.success){
                            $(".tip_4").text(data["msg"]);
                        } else {
                            $(".tip_4").text("");
                        }
                    }
                });
            }
        }




    });
    $(".input_5").focus(function () {
        if ($(this).val() == "Address"){
            $(this).val("");
            $(".tip_5").text("");
        }
    });
    $(".input_5").blur(function () {
        if ($(this).val() == ""){
            $(this).val("Address");
        }
    });
    $(".input_6").click(function () {
        if ($(".input_1").val() == "Username"){
            $(".tip_1").text("请输入用户名");
        }
        if ($(".input_2").val() == "Password"){
            $(".tip_2").text("请输入密码");
        }
        if ($(".input_4").val() == "TelPhone"){
            $(".tip_4").text("请输入电话号码");
        }
        if ($(".input_5").val() == "Address"){
            $(".tip_5").text("请输入收货地址");
        }

        //总的提交,所有错误信息均消失，才能提交表单
        if ($(".tip_1").text() == "" && $(".tip_2").text() == "" &&
            $(".tip_3").text() == "" && $(".tip_4").text() == "" &&
            $(".tip_5").text() == ""){


            $(".form1").submit();
            //window.location.href="/login.jsp";
        } else {
            alert("请完善您的信息！");
        }
    });
});