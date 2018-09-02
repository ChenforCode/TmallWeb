function add() {
    var num = parseInt($("#number").val());
    if (num < 100) {
        $("#number").val(++num);
    }
}

function sub() {
    var num = parseInt($("#number").val());
    if (num > 1) {
        $("#number").val(--num);
    }
}

$().ready(function () {
    //查看购物车
    $(".ViewCart_bt").click(function () {
        window.location.href = "/cart.jsp";
    });
    //添加进购物车
    $(".AddtoCart_bt").click(function () {
        var id = $(".good_id").text();
        var num = $(".number").val();
        $.ajax({
            url:"/CartServlet",
            type:"post",
            async:false,//异步
            data:{
                "id": id,
                "num": num,
                "action": "add"
            },//传出的数据
            dataType:"json",
            success: function (data) {
                if (data.success == true){
                    alert(data["msg"]);
                } else {
                    alert(data["msg"]);
                }
            }
        });
    });

    $(".Buynow_bt").click(function () {
        var username = $(".username").text();
        var goodid = $(".good_id").text();
        var price = $(".good_price").text();
        var num = $(".number").val();
        //获得总价格
        var totalPrice = parseFloat(price) * parseInt(num);
        $.ajax({
            url:"/S_createDeal",
            type:"post",
            async:false,
            data:{
                "username": username,
                "goodid": goodid,
                "number": num,
                "totalPrice" : totalPrice
            },//传出的数据
            dataType:"json",
            success: function (data) {
                if (data.success == true){
                    alert(data["msg"]);
                } else {
                    alert(data["msg"]);
                }
            }
        });
    })
});