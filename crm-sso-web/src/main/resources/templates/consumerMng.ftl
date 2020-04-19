<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>客户管理</title>
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js?version=2020012301"></script>
    <script type="text/javascript" src="/js/consumerMng.js?version=2020012301"></script>
</head>
<body>
<#include "default.ftl">
<#include "top.ftl">
<#include "sidebar.ftl">
<div class="container-fluid mycontent" >
    <div class="consumer_search myform <#if consumerModify>displayNone</#if>">
        <div>客户id：<input id="search_id" type="text"> 客户姓名：<input id="search_name" type="text">手机：<input id="search_phone" type="text">
            vip等级：<input id="search_vip_grade" type="number">
            <button onclick="consumerSearchSubmit()">查询</button></div>
        <div id="search_res" class="searchRes">
            <div class="myform <#if !consumerSearch>displayNone</#if>">
                <div><label class="width80">客户id: </label>${consumerSearch.id} </div>
                <div><label class="width80">客户姓名: </label>${consumerSearch.name}</div>
                <div><label class="width80">手机: </label>${consumerSearch.phone}</div>
                <div><label class="width80">vip等级: </label>${consumerSearch.vipGrade}</div>
                <div><label class="width80">余额: </label>${consumerSearch.balance}</div>
                <div><label class="width80">累计消费: </label>${consumerSearch.consume}</div>
                <div><label class="width80">邮箱: </label>${consumerSearch.email}</div>
                <a class='mybutton' href='/consumerMng/modify/${consumerSearch.phone}'>修改</a>
                <a class='mybutton' href='/consumerMng/delete/${consumerSearch.phone}'>删除</a>
            </div>
        </div>
    </div>

    <div class="consumer_modify myform <#if !consumerModify>displayNone</#if>">
        <#if consumerinfo.isMng == 0>由于您不是管理员，暂无权限进行此操作！<#else>
        <div><label class="width80">客户id: </label> ${consumerModify.id}</div>
        <div><label class="width80">客户姓名: </label><input id="name" type="text" value="${consumerModify.name}"></div>
        <div><label class="width80">手机: </label><input id="phone" type="text" value="${consumerModify.phone}"></div>
        <div><label class="width80">vip等级: </label><input id="vipGrade" type="number" value="${consumerModify.vipGrade}"></div>
        <div><label class="width80">余额: </label><span id="balance">${consumerModify.balance}</span></div>
        <div><label class="width80">累计消费: </label><span id="consume">${consumerModify.consume}</span></div>
        <div><label class="width80">邮箱: </label><input id="email" type="text" value="${consumerModify.email}"></div>
        <button onclick="consumerModifySubmit()">确认</button>
        <button onclick="consumerSearch()">返回</button>
        </#if>
    </div>
    <div class="consumer_add myform displayNone">
        <#if consumerinfo.isMng == 0>由于您不是管理员，暂无权限进行此操作！<#else>
            <div><label class="width80">客户姓名: </label><input id="consumer_add_name" type="text"></div>
            <div><label class="width80">手机: </label><input id="consumer_add_phone" type="text"></div>
            <div><label class="width80">vip等级: </label><input id="consumer_add_vip_grade" type="number"></div>
            <div><label class="width80">余额: </label><input id="consumer_add_balance" type="number"></div>
            <div><label class="width80">累计消费: </label><input id="consumer_add_consume" type="number"></div>
            <div><label class="width80">邮箱: </label><input id="consumer_add_email" type="text"></div>
            <button onclick="consumerAddSubmit()">添加</button>
        </#if>
    </div>
    <div class="consumer_balance myform displayNone">
        <#if consumerinfo.isMng == 0>由于您不是管理员，暂无权限进行此操作！<#else>
            <div><label class="width80">手机: </label><input id="consumer_balance_phone" type="text"></div>
            <div><label class="width80">充值金额: </label><input id="consumer_balance_balance" type="number"></div>
            <button onclick="consumerBalanceSubmit()">确认</button>
        </#if>
    </div>

</div>
</body>
</html>