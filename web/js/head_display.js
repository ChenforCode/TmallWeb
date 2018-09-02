//每次跳转界面的时候判断一下头像是否存在

$(document).ready(function () {
    var username = $(".username").text();
    $.ajax({
        url:"/S_CheckHeadExists",
        type:"post",
        async:true,
        data:{
        },//传出的数据
        dataType:"json",//返回的数据类型，常用：html/text/json
        success:function(data){
            if (data.success){
                $(".head_div").css("background-image", "url(/images/userhead/"+username+".jpg)");
            }
        }
    });
});