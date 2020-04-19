$(function () {
    // $(".sso-login-remember input").onclick(checkboxClick());
});

function productSearch() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.product_search').removeClass("displayNone");
}

function productAdd() {
    $('.myform').each(function(){$(this).addClass("displayNone")});
    $('.product_add').removeClass("displayNone");
}

function productAddSubmit() {
    var name = $('#product_add_name').val();
    var cost = $('#product_add_cost').val();
    var price = $('#product_add_price').val();
    var commissionRate = $('#product_add_commissionRate').val();

    var param = {
        name: name,
        cost: cost,
        price: price,
        commissionRate: commissionRate
    };
    $.ajax({
        type: "POST",
        url: "/productMng/add",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                alert("保存成功");
                window.location.href = "/productMng/search/" + name;
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

function productSearchSubmit() {
    var id = $('#search_id').val();
    if (id == "") {
        id = -1;
    }
    var name = $('#search_name').val();
    var param = {
        id: id,
        name: name
    };

    $.ajax({
        type: "POST",
        url: "/productMng/search",
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
                    var product = res[i];//获取LIST里面的对象
                    $('#search_res').append(getProductInfo(product));
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

function productModifySubmit() {
    var name = $('#name').val();
    var cost = $('#cost').val();
    var price = $('#price').val();
    var commissionRate = $('#commissionRate').val();

    var param = {
        name: name,
        cost: cost,
        price: price,
        commissionRate: commissionRate
    };


    $.ajax({
        type: "POST",
        url: "/productMng/modify",
        data: JSON.stringify(param),
        dataType: "json",
        contentType: "json/application",
        async: false,
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                window.location.href = "/productMng/search/" + name;
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

function getProductInfo(product) {
    var str = "<div><label class=\"width80\">产品id: </label>"+product.id + "</div>" +
        "<div><label class=\"width80\">产品名称: </label> " + product.name + "</div>" +
        "<div><label class=\"width80\">成本: </label>" + product.cost + "</div>" +
        "<div><label class=\"width80\">价格: </label>" + product.price + "</div>" +
        "<div><label class=\"width80\">提成: </label>" + product.commissionRate + "</div>" +
        "<a class='mybutton' href='/productMng/modify/" + product.name + "'>修改</a>" +
        "<a class='mybutton' href='/productMng/delete/" + product.name + "'>删除</a>";
    return str;
}