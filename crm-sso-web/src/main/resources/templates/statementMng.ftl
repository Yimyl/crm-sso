<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>业绩管理</title>
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js?version=2020012301"></script>
    <script type="text/javascript" src="/js/statementMng.js?version=2020012301"></script>
</head>
<body>
<#include "default.ftl">
<#include "top.ftl">
<#include "sidebar.ftl">
<div class="container-fluid mycontent" >
    <div class="statement_search myform">
        <div><select id="timeKind">
                <option value="year">年度</option>
                <option value="month">月度</option>
                <option value="day">日度</option>
            </select>
            <select id="queryKind">
                <option value="0">不限</option>
                <option value="user">用户</option>
                <option value="consumer">客户</option>
                <option value="product">产品</option>
            </select>
            用户姓名：<input id="search_username" type="text">
            客户手机：<input id="search_phone" type="text">
            产品名称：<input id="search_name" type="text">
            时间: <input id="search_startTime" type="text" class="dateSet"> ~ <input id="search_endTime" type="text" class="dateSet">
            <button onclick="statementSearchSubmit()">查询</button></div>
        <div id="search_res" class="searchRes">
            <table id="search_res_table" class="pure-table">
                <tr>
                    <th>id</th>
                    <th>用户姓名</th>
                    <th>客户手机</th>
                    <th>产品名称</th>
                    <th>价格</th>
                    <th>收入</th>
                    <th>时间</th>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>