//查询商品信息
$(document).ready(function () {
    var name = $(".query_name");
    var tip = $(".tip1");

    $(".good_item").hide();

    $(".query_name").focus(function () {
        $(".tip1").text("");
    });

    //检测商品名是否存在
    $(".query_name").blur(function () {
        if (name.val() == ""){
            $(".tip1").text("商品名称不能为空！");
        } else {
            $.ajax({
                url:"/S_CheckGoodName",
                type:"post",
                async:false,//异步
                data:{
                    "name": name.val()
                },//传出的数据
                dataType:"json",
                success: function (data) {
                    if(!data.success){
                        tip.text(data["msg"]);
                    }
                }
            });
        }
    });

    //查询商品信息
    $(".query_bt").click(function () {
        if (name.val() != "" && tip.text() == ""){
            $.ajax({
                url:"/S_query_good",
                type:"post",
                async:false,//异步
                data:{
                    "name": name.val()
                },//传出的数据
                dataType:"json",
                success: function (data) {
                    if(data.success){
                        $(".good_item").show();
                        $(".good_img").attr("src", "/images/goodimg/" + data["pic"]);
                        $(".good_id").text("编号：" + data["id"]);
                        $(".good_name").text(data["name"]);
                        $(".good_kind").text("种类：" + data["kind"]);
                        $(".good_price").text("价格：" + data["price"]);
                        $(".good_origin").text("产地：" + data["origin"]);
                    }
                }
            });
        } else {
            alert("请完善信息！");
        }

    })
});