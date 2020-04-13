$(function () {
    // $(".sso-login-remember input").onclick(checkboxClick());
});

function userinfo() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.user_info').removeClass("displayNone")
}

function userinfoModify() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.user_info_modify').removeClass("displayNone")
}

function userinfoSearch() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.user_info_search').removeClass("displayNone")
}

function userinfoSearchModify() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.user_info_search_modify').removeClass("displayNone")
}

function userAdd() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.user_add').removeClass("displayNone")
}

function userAddSubmit() {
    var name = $('#user_add_name').val();
    var pinyin = $('#user_add_pinyin').val();
    var password = $('#user_add_password').val();
    var password2 = $('#user_add_password2').val();
    var position = $('#user_add_position').val();
    var isMng = $('#user_add_isMng').val();
    var email = $('#user_add_email').val();
    var phone = $('#user_add_phone').val();
    if (pinyin.length < 6 || pinyin.length > 20) {
        alert("请输入6-25位姓名拼音");
        return false;
    }
    if (password.length < 6 || password.length > 16) {
        alert("请输入6-16位密码");
        return false;
    }
    if (password != password2) {
        alert("两次密码不相同，请确认");
        return false;
    }
    var param = {
        name: name,
        pinyin: pinyin,
        password: password,
        position: position,
        isMng: isMng,
        email: email,
        phone: phone
    };

    $.ajax({
        type: "POST",
        url: "/userMng/userAdd",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                alert("保存成功,用户账号为" + data.username);
                window.location.href = "/userMng/";
            } else {
                alert("保存失败," + data.message);
            }
        },
        error: function (error) {
            alert("保存失败,服务器异常");
            // $("#saveButton").removeAttr("disabled");
        }
    });
}

