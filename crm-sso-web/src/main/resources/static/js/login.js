$(function () {
    // $("#sso-login-remember input").onclick(checkboxClick());
});

function checkboxClick() {
    if ($(".sso-login-remember input").attr("checked")) {

    }
}

function submitForm() {
    if (false) {
        return;
    }
    var data = new FormData($(".myform")[0]);
    $.ajax({
        url: "/doLogin",
        data: data,
        type: "POST",
        dataType: "json",
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
