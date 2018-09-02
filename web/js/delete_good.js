$(document).ready(function () {
    $(".hide_title").hide();
    var del_input = $(".del_name");
    var del_but = $(".del_but");
    var del_info = $(".del_info");


    //对删除按钮的点击事件进行监听
    del_but.click(function () {
        var del_goodname = del_input.val();
        //如果没有输入名字，那么提示输入名字
        if (del_goodname == ""){
            del_info.text("请输入商品名称！");
        } else {
            //发送ajax请求
            $.ajax({
                url:"/S_delete_good",
                type:"post",
                async:false,
                data:{
                    "del_goodname": del_goodname
                },//传出的数据
                dataType:"json",//返回的数据类型，常用：html/text/json
                success:function(data){
                    if (data.success == true){
                        del_info.text(data["msg"]);
                        $(".hide_title").show();
                        $(".del_id").text(data["id"]);
                        $(".del_name").text(data["name"]);
                        $(".del_kind").text(data["kind"]);
                        $(".del_origin").text(data["origin"]);
                        $(".del_price").text(data["price"]);
                    } else {
                        del_info.text(data["msg"]);
                    }
                }
            });
        }
    });

    //输入信息的时候将p_info这个元素清空
    del_input.focus(function () {
        del_info.text("");
        $(".hide_title").hide();
    })
});