$(function () {
    // $("#sso-login-remember input").onclick(checkboxClick());
    //如果需要验证码，发送图片请求
});

function checkboxClick() {
    if ($(".sso-login-remember input").attr("checked")) {

    }
}

function submitForm() {
    var data = new FormData($(".myform")[0]);
    if (data.get("username").length < 6 || data.get("username").length > 20) {
        alert("请输入6-20位用户名");
    }
    if (data.get("password").length < 6 || data.get("password").length > 16) {
        alert("请输入6-16位密码");
    }
    if (!$(".validcode").parent().hasClass("displayNone") && data.get("validcode").length != 4) {
        alert("请输入4位验证码");
    }

    $.ajax({
        url: "/doLogin",
        data: data,
        // data: JSON.stringify(data),
        type: "POST",
        dataType: "text",
        processData: false, // 告诉jquery不要处理数据
        contentType: "text/html;charset=utf-8", // 告诉jquery不要设置contentType
        success: function (data) {
            if (data == "home") {
                window.location.href='/home';
            } else {
                $(".validcode").parent().removeClass("displayNone");
            }
            alert("ok");
            alert(data);
        },
        error: function (data) {
            if (data == "home") {
                window.location.href='/home';
            } else {
                $(".validcode").parent().removeClass("displayNone");
            }
            alert("no");
            alert(data == "login");
        }
    })
}
