$(document).ready(function () {
    //input的change事件
    //第一次预览图片
    $("#head_pic").change(function () {
        //拿到文件对象
        var fileObj = document.getElementById("head_pic").files[0];
        //拿到文件路径
        var filepath = $(this).val();
        //判断格式
        var start = filepath.indexOf(".") + 1;
        var name = filepath.substring(start, filepath.length).toLowerCase();
        if (name != "jpg") {
            alert("图片格式必须为jpg格式");
            $("#head_pic").val("");
            $(".person_head").css("background-image", "url(/images/userhead/default_head.jpg)");
        } else {
            //判断大小
            if (fileObj.size / 1024 / 1024 > 10) {
                alert("图片不能超过10M");
                $("#head_pic").val("");
                $(".person_head").css("background-image", "url(/images/userhead/default_head.jpg)");
            } else {
                //格式和大小都符合则预览图片！
                var windowURL = window.URL;
                //createObjectURL创建一个指向该参数对象(图片)的URL
                var dataURL = windowURL.createObjectURL(fileObj);
                $(".person_head").css("background-image", "url(" + dataURL + ")");
            }
        }
    });

    $(".uphead_bt").click(function () {
        //头像上传
        var formData = new FormData();
        var img_file = document.getElementById("head_pic");
        var fileObj = img_file.files[0];
        formData.append("img", fileObj);
        $.ajax({
            url: "/S_upUserHead",
            type: "post",
            async: false,
            processData: false,
            contentType: false,
            data: formData,
            dataType: "json",//返回的数据类型，常用：html/text/json
            success: function (data) {
                alert("头像上传成功");
                window.location.href = "/personal_info.jsp";
            }
        });
    });
});