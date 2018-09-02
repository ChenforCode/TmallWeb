function delcfm() {
    if (!confirm("确认要删除？")) {
        window.event.returnValue = false;
    }
}

$(document).ready(function () {
    $(".creatDealbyCart").click(function () {
        $.ajax({
            url:"/S_createDealbyCart",
            type:"post",
            async:false,
            data:{

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