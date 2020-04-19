$(function () {
    // $(".sso-login-remember input").onclick(checkboxClick());
});

function profitSearch() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.profit_search').removeClass("displayNone");
}

function profitAdd() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.profit_add').removeClass("displayNone");
}

function calRealPrice() {
    var price = $('#profit_add_price').val();
    var discount = $('#profit_add_discount').val();
    var realPrice = price * discount;
    realPrice = realPrice.toFixed(2);
    $('#profit_add_realPrice').html(realPrice);
}
function profitAddSubmit() {
    var username = $('#profit_add_username').val();
    var name = $('#profit_add_name').val();
    var phone = $('#profit_add_phone').val();
    var price = $('#profit_add_price').val();
    var discount = $('#profit_add_discount').val();
    var realPrice = $('#profit_add_realPrice').html();
    var commissionRate = $('#profit_add_commissionRate').val();
    if (username == "" || name == "" || phone== "" || price == "" || realPrice== "" ||  discount == "" || commissionRate=="") {
        alert("参数不能为空");
        return false;
    }

    var param = {
        username: username,
        name: name,
        phone: phone,
        price: price,
        discount: discount,
        realPrice: realPrice,
        commissionRate: commissionRate
    };
    $.ajax({
        type: "POST",
        url: "/profitMng/add",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                alert("保存成功");
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

function profitSearchSubmit() {
    var username = $('#search_username').val();
    var phone = $('#search_phone').val();
    var name = $('#search_name').val();
    var startTime = $('#search_startTime').val();
    var endTime = $('#search_endTime').val();

    var param = {
        record: {
            username: username,
            phone: phone,
            name: name
        },
        startTime: startTime,
        endTime: endTime
    };

    $.ajax({
        type: "POST",
        url: "/profitMng/search",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                var res = data.result;
                $('#search_res').html("<table id=\"search_res_table\" class=\"pure-table\">\n" +
                    "                <tr>\n" +
                    "                    <th>id</th>\n" +
                    "                    <th>用户姓名</th>\n" +
                    "                    <th>客户手机</th>\n" +
                    "                    <th>产品名称</th>\n" +
                    "                    <th>价格</th>\n" +
                    "                    <th>折扣</th>\n" +
                    "                    <th>实际价格</th>\n" +
                    "                    <th>提成比例</th>\n" +
                    "                    <th>时间</th>\n" +
                    "                </tr>\n" +
                    "            </table>");
                for(var i = 0;i<res.length;i++) { //循环LIST
                    var record = res[i];//获取LIST里面的对象
                    $('#search_res_table').append(getRecordInfo(record));
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

function getRecordInfo(record) {
    var str = "<tr>" +
    "  <th>"+record.id+"</th>" +
    "  <th>"+ record.username +"</th>" +
    "  <th>"+ record.phone+"</th>" +
    "  <th>"+record.name+"</th>" +
    "  <th>"+record.price+"</th>" +
    "  <th>"+record.discount+"</th>" +
    "  <th>"+record.realPrice+"</th>" +
    "  <th>"+record.commissionRate+"</th>" +
    "  <th>"+record.createTime+"</th>" +
    "</tr>";
    return str;
}