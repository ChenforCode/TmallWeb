//刚开始的时候将所有的导航栏都隐藏
function hide() {
    $(".phone").hide();
    $(".laptop").hide();
    $(".pad").hide();
    $(".airpod").hide();
    $(".camera").hide();
}
$(document).ready(function () {
    var a1 = $(".ul_phone");
    var a2 = $(".phone");
    var b1 = $(".ul_laptop");
    var b2 = $(".laptop");
    var c1 = $(".ul_pad");
    var c2 = $(".pad");
    var d1 = $(".ul_pod");
    var d2 = $(".airpod");
    var e1 = $(".ul_camera");
    var e2 = $(".camera");
    var img = $(".re_image");
    //鼠标经过a1, a2展示，其余全部隐藏
    a1.mouseover(function () {
        a2.show();
        b2.hide();
        c2.hide();
        d2.hide();
        e2.hide();
        img.hide();
    });
    //其余同理
    b1.mouseover(function () {
        a2.hide();
        b2.show();
        c2.hide();
        d2.hide();
        e2.hide();
        img.hide();
    });
    c1.mouseover(function () {
        a2.hide();
        b2.hide();
        c2.show();
        d2.hide();
        e2.hide();
        img.hide();
    });
    d1.mouseover(function () {
        a2.hide();
        b2.hide();
        c2.hide();
        d2.show();
        e2.hide();
        img.hide();
    });
    e1.mouseover(function () {
        a2.hide();
        b2.hide();
        c2.hide();
        d2.hide();
        e2.show();
        img.hide();
    });

    //鼠标全部离开的时候隐藏导航栏
    $(".navigator").mouseleave(function(){
        a2.hide();
        b2.hide();
        c2.hide();
        d2.hide();
        e2.hide();
        img.show();
    })


});