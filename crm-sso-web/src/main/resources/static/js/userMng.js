$(function () {
    // $(".sso-login-remember input").onclick(checkboxClick());
});

function userinfo() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.user_info').removeClass("displayNone");
}

function userinfoModify() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.user_info_modify').removeClass("displayNone");
}

function passwordModify() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.user_info_password_modify').removeClass("displayNone");
}

function userinfoSearch() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.user_info_search').removeClass("displayNone");
}

function userinfoSearchModify() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.user_info_search_modify').removeClass("displayNone");
}

function userAdd() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.user_add').removeClass("displayNone");
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
                alert("保存成功,用户账号为" + data.result.username);
                window.location.href = "/userMng/username/" + data.result.username;
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

function userinfoSearchSubmit() {
    var id = $('#search_id').val();
    if (id == "") {
        id = -1;
    }
    var username = $('#search_username').val();
    // var name = $('#search_name').val();
    // var position = $('#search_position').val();
    // var isMng = $('#search_isMng').val();
    // var email = $('#search_email').val();
    var phone = $('#search_phone').val();

    var param = {
        id: id,
        username: username,
        // name: name,
        // position: position,
        // isMng: isMng,
        // email: email,
        phone: phone
    };

    $.ajax({
        type: "POST",
        url: "/userMng/search",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                var res = data.result;
                $('.userinfoSearchId').html(res.id);
                $('.userinfoSearchUsername').html(res.username);
                $('#userinfoSearchName').html(res.name);
                $('#userinfoSearchPosition').html(res.position);
                if (res.isMng == 0) {
                    $('#userinfoSearchIsMng').html("否");
                } else {
                    $('#userinfoSearchIsMng').html("是");
                }
                $('#userinfoSearchEmail').html(res.email);
                $('#userinfoSearchPhone').html(res.phone);

                $('#userinfoSearchModifyName').val(res.name);
                $('#userinfoSearchModifyPosition').val(res.position);
                $('#userinfoSearchModifyIsMng').val(res.isMng);
                $('#userinfoSearchModifyEmail').val(res.email);
                $('#userinfoSearchModifyPhone').val(res.phone);
            } else {
                alert("查询失败," + data.message);
            }
        },
        error: function (error) {
            alert("保存失败,服务器异常");
            // $("#saveButton").removeAttr("disabled");
        }
    });
}

function passwordModifySubmit() {
    var username = $('#username').html();
    var password0 = $('#password_modify_password0').val();
    var password1 = $('#password_modify_password1').val();
    var password2 = $('#password_modify_password2').val();

    if (password1 != password2) {
        alert("新密码不相同");
        return false;
    }
    var param = {
        username: username,
        password: password0,
        passwordNew: password1,
    };

    $.ajax({
        type: "POST",
        url: "/userMng/passwordModify",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                alert("修改成功" );
            } else {
                alert("修改失败",data.message);
            }
        },
        error: function (error) {
            alert("修改失败,服务器异常");
            // $("#saveButton").removeAttr("disabled");
        }
    });
}

function userinfoModifySubmit() {
    var username = $('#username').html();
    var name = $('#userinfoModifyName').val();
    var position = $('#userinfoModifyPosition').val();
    var isMng = $('#userinfoModifyIsMng').val();
    var email = $('#userinfoModifyEmail').val();
    var phone = $('#userinfoModifyPhone').val();

    var param = {
        username: username,
        name: name,
        position: position,
        isMng: isMng,
        email: email,
        phone: phone
    };


    $.ajax({
        type: "POST",
        url: "/userMng/userInfoModify",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                $('#username').html();
                $('#name').html(name);
                $('#position').html(position);
                if (isMng==0) {
                    $('#isMng').html("否");
                } else {
                    $('#isMng').html("是");
                }
                $('#email').html(email);
                $('#phone').html(phone);
                alert("修改成功" );
            } else {
                alert("修改失败",data.message);
            }
        },
        error: function (error) {
            alert("修改失败,服务器异常");
        }
    });
}

function userinfoSearchDelete() {
    var username = $('.userinfoSearchUsername').html();


    $.ajax({
        type: "POST",
        url: "/userMng/userInfoDelete/" + username,
        data: "",
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                alert("删除成功" );
            } else {
                alert("删除失败",data.message);
            }
        },
        error: function (error) {
            alert("修改失败,服务器异常");
        }
    });
}

function userinfoSearchModifySubmit() {
    var username = $('.userinfoSearchUsername').html();
    var name = $('#userinfoSearchModifyName').val();
    var position = $('#userinfoSearchModifyPosition').val();
    var isMng = $('#userinfoSearchModifyIsMng').val();
    var email = $('#userinfoSearchModifyEmail').val();
    var phone = $('#userinfoSearchModifyPhone').val();

    var param = {
        username: username,
        name: name,
        position: position,
        isMng: isMng,
        email: email,
        phone: phone
    };


    $.ajax({
        type: "POST",
        url: "/userMng/userInfoModify",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                window.location.href = "/userMng/username/" + username;
                alert("修改成功" );
            } else {
                alert("修改失败",data.message);
            }
        },
        error: function (error) {
            alert("修改失败,服务器异常");
            // $("#saveButton").removeAttr("disabled");
        }
    });
}