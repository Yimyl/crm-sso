$(function () {
    // $(".sso-login-remember input").onclick(checkboxClick());
});

function statementSearch() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.statement_search').removeClass("displayNone");
}

function statementAdd() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.statement_add').removeClass("displayNone");
}

function calRealPrice() {
    var price = $('#statement_add_price').val();
    var discount = $('#statement_add_discount').val();
    var realPrice = price * discount;
    realPrice = realPrice.toFixed(2);
    $('#statement_add_realPrice').html(realPrice);
}


function statementSearchSubmit() {
    var username = $('#search_username').val();
    var phone = $('#search_phone').val();
    var name = $('#search_name').val();
    var startTime = $('#search_startTime').val();
    var endTime = $('#search_endTime').val();
    var timeKind = $('#timeKind').val();
    var queryKind = $('#queryKind').val();

    var param = {
        record: {
            username: username,
            phone: phone,
            name: name
        },
        startTime: startTime,
        endTime: endTime
    };
    $('#search_res').html("<table id=\"search_res_table\" class=\"pure-table\">\n" +
        "                <tr>\n" +
        "                    <th>id</th>\n" +
        "                    <th>用户姓名</th>\n" +
        "                    <th>客户手机</th>\n" +
        "                    <th>产品名称</th>\n" +
        "                    <th>价格</th>\n" +
        "                    <th>收入</th>\n" +
        "                    <th>时间</th>\n" +
        "                </tr>\n" +
        "            </table>");
    if (queryKind != "0") {
        search(param, timeKind, queryKind);
    } else {
        search(param, timeKind, "user");
        search(param, timeKind, "consumer");
        search(param, timeKind, "product");
    }

}

function search(param, timeKind, queryKind) {
    $.ajax({
        type: "POST",
        url: "/statementMng/search/"+timeKind+"/"+queryKind,
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                var res = data.result;
                for(var i = 0;i<res.length;i++) { //循环LIST
                    var record = res[i];//获取LIST里面的对象
                    $('#search_res_table').append(getRecord(record, timeKind, queryKind));
                }
            } else {
                alert("搜索失败",data.message);
            }
        },
        error: function (error) {
            alert("搜索失败,服务器异常");
        }
    });
}
function getRecord(record, timeKind, querykind) {
    var time = record.year + "年";
    if (record.month != null && record.month != "") {
        time += record.month + "月";
    }
    if (record.day != null && record.day != "") {
        time += record.day + "日";
    }
    var str = "<tr>" +
    "  <th>"+record.id+"</th>" +
    "  <th>"+ record.username +"</th>" +
    "  <th>"+ record.phone+"</th>" +
    "  <th>"+record.name+"</th>" +
    "  <th>"+record.price+"</th>" +
    "  <th>"+record.income+"</th>" +
    "  <th>"+time+"</th>" +
    "</tr>";
    return str;
}


