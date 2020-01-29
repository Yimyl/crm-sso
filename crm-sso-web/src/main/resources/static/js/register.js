$(function () {
    // $(".sso-login-remember input").onclick(checkboxClick());
});

function submitRegisterForm() {
    if (false) {
        return;
    }
    var data = new FormData($(".myform")[0]);
    $.ajax({
        url: "/doRegister",
        type: "post",
        data: data,
        dataType: "text",
        processData: false, // 告诉jquery不要处理数据
        contentType: false, // 告诉jquery不要设置contentType
        success: function (data) {
            alert("ok");
        },
        error: function (data) {
            alert("no");
        }
    })
}
