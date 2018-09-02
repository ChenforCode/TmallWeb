var ary = ["images/recycle1.jpg",
    "images/recycle2.jpg",
    "images/recycle3.jpg",
    "images/recycle4.jpg"
];
var count = 0;

$(document).ready(function () {
    var img = $('.re_image img');

    //处理图片循环的事件
    function imgcycle() {
        count++;
        if (count == ary.length)
            count = 0;
        img.attr("src", ary[count]);
    }

    function run() {
        var timer = setInterval(imgcycle, 2000);
        img.mouseover(function () {
            clearInterval(timer);
        });
        img.mouseout(function () {
            timer = setInterval(imgcycle, 3000);
        });
    }

    run();

    $(".lbt").click(function () {
        count--;
        if (count >= 0) {
            img.attr("src", ary[count]);
        } else {
            count = 4;
            img.attr("src", ary[count]);
        }
    });

    $(".rbt").click(function () {
        count++;
        if (count <= 4) {
            img.attr("src", ary[count]);
        } else {
            count = 0;
            img.attr("src", ary[count]);
        }
    });
});


/*function run(argument) {
    var timer = setInterval(imgcycle, 2000);
    document.getElementById('re_image').onmouseover = function() {
        clearInterval(timer)
    }
    document.getElementById('re_image').onmouseout = function() {
        timer = setInterval(imgcycle, 3000);
    }
}
function imgcycle(argument) {
    count++;
    if(count == ary.length)
        count = 0;
    document.getElementById("re_image").src = ary[count];
}
function left_control(argument) {
    count--;
    if (count >= 0) {
        document.getElementById("re_image").src = ary[count];
    } else {
        count = 4;
        document.getElementById("re_image").src = ary[count];
    }
}
function right_control(argument) {
    count++;
    if (count <= 4) {
        document.getElementById("re_image").src = ary[count];
    } else {
        count = 0;
        document.getElementById("re_image").src = ary[count];
    }
}*/
