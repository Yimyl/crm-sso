$(function () {
    // $(".sso-login-remember input").onclick(checkboxClick());
});

function userMng() {
    // window.location.href='/userMng';
    addBar("个人信息", "userinfo()");
    addBar("用户查询", "userinfoSearch()");
    addBar("添加用户", "userAdd()");
}

function consumerMng() {
    window.location.href='/consumerMng';
}

function profitMng() {
    window.location.href='/profitMng';
}

function addBar(name, onclick) {
    alert($('.side-menu').html());
    $('.side-menu').html("<li id=\"__actCommit\" class=\"\"><i class=\"icon-ad-calendar\"></i> <span>left1</span></a>\n" +
        "            <div id=\"expire\"></div>\n" +
        "        </li>");
    // $('.side-menu').html("<li id=\"__actCommit\" class=\"\"><i class=\"icon-ad-calendar\"></i> <span onclick='"+ onclick + "'>" + name +"</span></a></li>");
}