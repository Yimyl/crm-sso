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
        return false;
    }
    if (data.get("password").length < 6 || data.get("password").length > 16) {
        alert("请输入6-16位密码");
        return false;
    }
    if (!$(".validcode").parent().hasClass("displayNone") && data.get("validcode").length != 4) {
        alert("请输入4位验证码");
        return false;
    }
    if ($(".remember").prop("checked")) {
        $(".remember").attr("value", "remember");
    }
    $.ajax({
        url: "/doLogin",
        data: data,
        // data: JSON.stringify(data),
        type: "POST",
        dataType: "text",
        processData: false, // 告诉jquery不要处理数据
        contentType: false, // 告诉jquery不要设置contentType
        success: function (data) {
            if (data == "success") {
                window.location.href='/home';
                return;
            }
            $(".validcode").parent().removeClass("displayNone");
            $(".sso-tip").removeClass("displayNone");
            if (data == "validcodeError") {
                $(".sso-tip").html("验证码错误");
            } else if (data == "usernamepasswordError") {
                $(".sso-tip").html("用户名或密码错误");
            }
            $(".password").prop("value", "");
            $(".validcode").prop("value", "");
            getCode();
        },
        error: function (data) {
            alert("系统错误，请联系管理员");
        }
    })
}

//获取验证码
function getCode(){
    $(".sso-validcode-img").attr("src", "/valid-code?"+Math.random());
}

//刷新验证码
function getRefreshCode(){
    $(".sso-validcode-img").attr("src", "/refresh-valid-code?"+Math.random());
}
