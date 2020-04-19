<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>业绩管理</title>
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js?version=2020012301"></script>
    <script type="text/javascript" src="/js/profitMng.js?version=2020012301"></script>
</head>
<body>
<#include "default.ftl">
<#include "top.ftl">
<#include "sidebar.ftl">
<div class="container-fluid mycontent" >
    <div class="profit_add myform">
        <#if profitinfo.isMng == 0>由于您不是管理员，暂无权限进行此操作！<#else>
            <div><label class="width80">用户姓名: </label><input id="profit_add_username" type="text"></div>
            <div><label class="width80">客户手机: </label><input id="profit_add_phone" type="text"></div>
            <div><label class="width80">产品名称: </label><input id="profit_add_name" type="text"></div>
            <div><label class="width80">价格: </label><input id="profit_add_price" type="number"></div>
            <div><label class="width80">折扣: </label><input id="profit_add_discount" type="number" value="1" onblur="calRealPrice()"></div>
            <div><label class="width80">实际价格: </label><span id="profit_add_realPrice" type="number" onblur="calRealPrice()"></span></div>
            <div><label class="width80">提成比例: </label><input id="profit_add_commissionRate" type="number"></div>
            <button onclick="profitAddSubmit()">添加</button>
        </#if>
    </div>
    <div class="profit_search myform displayNone">
        <div>用户姓名：<input id="search_username" type="text"> 客户手机：<input id="search_phone" type="text">
            产品名称：<input id="search_name" type="text">
            时间: <input id="search_startTime" type="text" class="dateSet"> ~ <input id="search_endTime" type="text" class="dateSet">
            <button onclick="profitSearchSubmit()">查询</button></div>
        <div id="search_res" class="searchRes">
            <table id="search_res_table" class="pure-table">
                <tr>
                    <th>id</th>
                    <th>用户姓名</th>
                    <th>客户手机</th>
                    <th>产品名称</th>
                    <th>价格</th>
                    <th>折扣</th>
                    <th>实际价格</th>
                    <th>提成比例</th>
                    <th>时间</th>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>