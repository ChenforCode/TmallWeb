$(document).ready(function () {
    $(".edit_title").hide();
    var bt = $(".edit_bt");
    var name = $(".name");
    var price = $(".price");
    var origin = $(".origin");

    //姓名提示
    var t1 = $(".tip1");

    //价格提示
    var t2 = $(".tip2");

    //提交提示
    var tip = $(".tip");



    name.focus(function () {
        t1.text("");
    });

    //判断name是否存在
    name.blur(function () {
        var name_val = name.val();
        if (name_val === ""){
            t1.text("名称不能为空！");
        } else {
            //向服务器发送ajax请求判断是否存在该name
            $.ajax({
                url:"/S_CheckGoodName",
                type:"post",
                async:true,
                data:{
                    "name": name_val
                },//传出的数据
                dataType:"json",//返回的数据类型，常用：html/text/json
                success:function(data){
                    if (!data.success){
                        t1.text(data["msg"]);
                    }
                }
            });
        }
    });

    price.focus(function () {
        t2.text("");
    });

    price.blur(function () {
        var reg=/^-?\d*\.?\d*$/;
        var price_val = price.val();
        if(price_val === ""){
            t2.text("价格不能为空！");
        } else {
            if (!reg.test(price_val)) {
                t2.text("商品价格非法！");
            }
        }
    });

    bt.click(function () {
        if (t1.text() == "" && t2.text() == ""){
            //产地和价格至少填写一个
            if (name.val() != null && (price.val() != "" || origin.val() != "")){
                $.ajax({
                    url:"/S_edit_good",
                    type:"post",
                    async:true,
                    data:{
                        "name": name.val(),
                        "price": price.val(),
                        "origin": origin.val()
                    },//传出的数据
                    dataType:"json",//返回的数据类型，常用：html/text/json
                    success:function(data){
                        if (!data.success){
                            tip.text(data["msg"]);
                        } else {
                            $(".edit_title").show();
                            tip.text(data["msg"]);
                            $(".edit_id").text(data["id"]);
                            $(".edit_name").text(data["name"]);
                            $(".edit_kind").text(data["kind"]);
                            $(".edit_price").text(data["price"]);
                            $(".edit_origin").text(data["origin"]);
                        }
                    }
                });
            } else {
                tip.text("请修改完善信息！");
            }
        } else {
            tip.text("请修改完善信息！");
        }
    })

});


