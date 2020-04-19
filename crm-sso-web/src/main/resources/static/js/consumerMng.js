$(function () {
    // $(".sso-login-remember input").onclick(checkboxClick());
});

function consumerSearch() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.consumer_search').removeClass("displayNone");
}

function consumerAdd() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.consumer_add').removeClass("displayNone");
}

function consumerBalance() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.consumer_balance').removeClass("displayNone");
}

function consumerAddSubmit() {
    var name = $('#consumer_add_name').val();
    var phone = $('#consumer_add_phone').val();
    var vipGrade = $('#consumer_add_vip_grade').val();
    var balance = $('#consumer_add_balance').val();
    var consume = $('#consumer_add_consume').val();
    var email = $('#consumer_add_email').val();

    var param = {
        name: name,
        phone: phone,
        vipGrade: vipGrade,
        balance: balance,
        consume: consume,
        email: email
    };
    $.ajax({
        type: "POST",
        url: "/consumerMng/add",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                alert("保存成功");
                window.location.href = "/consumerMng/search/" + phone;
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

function consumerSearchSubmit() {
    var id = $('#search_id').val();
    if (id == "") {
        id = -1;
    }
    var name = $('#search_name').val();
    var phone = $('#search_phone').val();
    var vipGrade = $('#search_vip_grade').val();
    if (vipGrade == "") {
        vipGrade = -1;
    }
    var param = {
        id: id,
        name: name,
        phone: phone,
        vipGrade: vipGrade
    };

    $.ajax({
        type: "POST",
        url: "/consumerMng/search",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                var res = data.result;
                $('#search_res').html("");
                for(var i = 0;i<res.length;i++) { //循环LIST
                    var consumer = res[i];//获取LIST里面的对象
                    $('#search_res').append(getConsumerInfo(consumer));
                }
            } else {
                alert("搜索失败",data.message);
            }
        },
        error: function (error) {
            alert("搜索失败,服务器异常");
            // $("#saveButton").removeAttr("disabled");
        }
    });
}

function consumerModifySubmit() {
    var name = $('#name').val();
    var phone = $('#phone').val();
    var vipGrade = $('#vipGrade').val();
    var balane = $('#balane').val();
    var consume = $('#consume').val();
    var email = $('#email').val();

    var param = {
        name: name,
        phone: phone,
        vipGrade: vipGrade,
        balane: balane,
        consume: consume,
        email: email
    };


    $.ajax({
        type: "POST",
        url: "/consumerMng/modify",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                window.location.href = "/consumerMng/search/" + phone;
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

function consumerBalanceSubmit() {
    var phone = $('#consumer_balance_phone').val();
    var balance = $('#consumer_balance_balance').val();

    if (phone == "" || balance == "") {
        alert("手机号和金额不能为空");
        return false;
    }
    var param = {
        phone: phone,
        balance: balance
    };


    $.ajax({
        type: "POST",
        url: "/consumerMng/balance",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                window.location.href = "/consumerMng/search/" + phone;
                alert("充值成功，余额：" + data.result);
            } else {
                alert("充值失败",data.message);
            }
        },
        error: function (error) {
            alert("充值失败,服务器异常");
            // $("#saveButton").removeAttr("disabled");
        }
    });
}

function getConsumerInfo(consumer) {
    var str = "<div><label class=\"width80\">客户id: </label>"+consumer.id + "</div>" +
        "<div><label class=\"width80\">客户姓名: </label> " + consumer.name + "</div>" +
        "<div><label class=\"width80\">手机: </label>" + consumer.phone + "</div>" +
        "<div><label class=\"width80\">vip等级: </label>" + consumer.vipGrade + "</div>" +
        "<div><label class=\"width80\">余额: </label>" + consumer.balance + "</div>" +
        "<div><label class=\"width80\">累计消费: </label>" + consumer.consume + "</div>" +
        "<div><label class=\"width80\">邮箱: </label>" + consumer.email + "</div>" +
        "<a class='mybutton' href='/consumerMng/modify/" + consumer.phone + "'>修改</a>" +
        "<a class='mybutton' href='/consumerMng/delete/" + consumer.phone + "'>删除</a>";
    return str;
}