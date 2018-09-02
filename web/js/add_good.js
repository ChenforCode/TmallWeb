$(document).ready(function () {
    var bt = $(".upload_bt");
    var id = $(".id");
    var name = $(".name");
    var price = $(".price");
    var origin = $(".origin");
    var t1 = $(".tip1");
    var t2 = $(".tip2");
    var t3 = $(".tip3");
    var t4 = $(".tip4");
    var t5 = $(".tip5");

    
    id.focus(function () {
        t1.text("");
    });

    //id的合法性验证
    id.blur(function () {
        var reg=/^[0-9]*$/;
        var id_val = id.val();

        //判空
        if (id_val === ""){
            t1.text("ID不能为空！");
        } else {
            if (reg.test(id_val)){
                //向服务器发送ajax请求判断是否存在该ID
                $.ajax({
                    url:"/S_checkGood",
                    type:"post",
                    async:true,
                    data:{
                        "type": "id",
                        "id": id_val
                    },//传出的数据
                    dataType:"json",//返回的数据类型，常用：html/text/json
                    success:function(data){
                        if (!data.success){
                            t1.text(data["msg"]);
                        }
                    }
                });
            } else {
                t1.text("商品ID非法！");
            }
        }
    });
    name.focus(function () {
        t2.text("");
    });

    //判断name是否存在
    name.blur(function () {
        var name_val = name.val();
        if (name_val === ""){
            t2.text("名称不能为空！");
        } else {
            //向服务器发送ajax请求判断是否存在该name
            $.ajax({
                url:"/S_checkGood",
                type:"post",
                async:true,
                data:{
                    "type": "name",
                    "name": name_val
                },//传出的数据
                dataType:"json",//返回的数据类型，常用：html/text/json
                success:function(data){
                    if (!data.success){
                        t2.text(data["msg"]);
                    }
                }
            });
        }
    });
    price.focus(function () {
        t3.text("");
    });
    price.blur(function () {
        var reg=/^-?\d*\.?\d*$/;
        var price_val = price.val();
        if(price_val === ""){
            t3.text("价格不能为空！");
        } else {
            if (!reg.test(price_val)) {
                t3.text("商品价格非法！");
            }
        }
    });
    origin.focus(function () {
        t4.text("");
    });

    origin.blur(function () {
        var origin_val = $(".origin").val();
        if (origin_val === ""){
            t4.text("产地不能为空！");
        }
    });


    //第一次预览图片
    $("#pic").change(function () {
        //拿到文件对象
        var fileObj = document.getElementById("pic").files[0];
        //拿到文件路径
        var filepath = $(this).val();

        //判断格式
        var start = filepath.indexOf(".") + 1;
        var name = filepath.substring(start, filepath.length).toLowerCase();
        if (name != "jpg") {
            alert("图片格式必须为jpg格式");
            $("#pic").val("");
            $(".view_imgtag").attr("src", "");
        } else {
            //判断大小
            if (fileObj.size / 1024 / 1024 > 10) {
                alert("图片不能超过10M");
                $("#pic").val("");
                $(".view_imgtag").attr("src", "");
            } else {
                //格式和大小都符合则预览图片！
                var windowURL = window.URL;
                //createObjectURL创建一个指向该参数对象(图片)的URL
                var dataURL = windowURL.createObjectURL(fileObj);
                $(".view_imgtag").attr("src", dataURL);
            }
        }
    });

    //信息提交
    bt.click(function () {
        if (t1.text() === "" && t2.text() === "" && t3.text() === "" &&
            t4.text() === "" && t1.text() === "" && name.val() !== "" &&
            id.val() !== "" && price.val() !== "" && origin.val() !== "" &&
            $("#pic").val() !== ""){

            //商品信息的提交
            $.ajax({
                url:"/S_doUpLoad",
                type:"post",
                async:false,
                data:{
                    "id": id.val(),
                    "name": name.val(),
                    "kind": $(".kind option:checked").text(),
                    "price": price.val(),
                    "origin": origin.val()
                },//传出的数据
                dataType:"json",//返回的数据类型，常用：html/text/json
                success:function(data){

                }
            });

            //$("#uploadimg").submit();

            //商品图片的上传
            var formData = new FormData();
            var img_file = document.getElementById("pic");
            var fileObj = img_file.files[0];
            formData.append("img", fileObj);
            $.ajax({
                url:"/S_doUpLoadImg",
                type:"post",
                async:false,
                processData : false,
                contentType : false,
                data: formData,
                dataType:"json",//返回的数据类型，常用：html/text/json
                success:function(data){
                    $(".info").text(data["msg"]);
                }
            });

        } else {
            alert("请完善商品信息！");
        }
    })

});


