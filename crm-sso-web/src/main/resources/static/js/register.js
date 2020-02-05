$(function () {
    // $(".sso-login-remember input").onclick(checkboxClick());
});

function submitRegisterForm() {

    var data = new FormData($(".myform")[0]);
    alert(CryptoJS.HmacSHA256(data.get("password")));
    if (data.get("username").length < 6 || data.get("username").length > 20) {
        alert("请输入6-20位用户名");
        return false;
    }
    if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(data.get("email"))){
        alert("邮箱格式不正确!");
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
    //密码加密
    // CryptoJS.HmacSHA256
    $.ajax({
        url: "/doRegister",
        type: "post",
        data: data,
        dataType: "text",
        processData: false, // 告诉jquery不要处理数据
        contentType: false, // 告诉jquery不要设置contentType
        success: function (data) {
            if (data == "success") {
                window.location.href='/login';
                return;
            }
            $(".validcode").parent().removeClass("displayNone");
            $(".sso-tip").removeClass("displayNone");
            if (data == "validcodeError") {
                $(".sso-tip").html("验证码错误");
            } else if (data == "paramError") {
                $(".sso-tip").html("参数错误");
            }
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
