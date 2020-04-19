<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>客户管理</title>
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js?version=2020012301"></script>
    <script type="text/javascript" src="/js/productMng.js?version=2020012301"></script>
</head>
<body>
<#include "default.ftl">
<#include "top.ftl">
<#include "sidebar.ftl">
<div class="container-fluid mycontent" >
    <div class="product_search myform <#if productModify>displayNone</#if>">
        <div>产品id：<input id="search_id" type="text"> 产品名称：<input id="search_name" type="text">
            <button onclick="productSearchSubmit()">查询</button></div>
        <div id="search_res" class="searchRes">
            <div class="myform <#if !productSearch>displayNone</#if>">
                <div><label class="width80">产品id: </label>${productSearch.id} </div>
                <div><label class="width80">产品名称: </label>${productSearch.name}</div>
                <div><label class="width80">成本: </label>${productSearch.cost}</div>
                <div><label class="width80">价格: </label>${productSearch.price}</div>
                <div><label class="width80">提成: </label>${productSearch.commissionRate}</div>
                <a class='mybutton' href='/productMng/modify/${productSearch.name}'>修改</a>
                <a class='mybutton' href='/productMng/delete/${productSearch.name}'>删除</a>
            </div>
        </div>
    </div>

    <div class="product_modify myform <#if !productModify>displayNone</#if>">
        <#if productinfo.isMng == 0>由于您不是管理员，暂无权限进行此操作！<#else>
        <div><label class="width80">产品id: </label> ${productModify.id}</div>
        <div><label class="width80">产品名称: </label><input id="name" type="text" value="${productModify.name}"></div>
        <div><label class="width80">成本: </label><input id="cost" type="number" value="${productModify.cost}"></div>
        <div><label class="width80">价格: </label><input id="price" type="number" value="${productModify.price}"></div>
        <div><label class="width80">提成: </label><input id="commissionRate" type="number" value="${productModify.commissionRate}"></div>
        <button onclick="productModifySubmit()">确认</button>
        <button onclick="productSearch()">返回</button>
        </#if>
    </div>
    <div class="product_add myform displayNone">
        <#if productinfo.isMng == 0>由于您不是管理员，暂无权限进行此操作！<#else>
            <div><label class="width80">产品名称: </label><input id="product_add_name" type="text"></div>
            <div><label class="width80">成本: </label><input id="product_add_cost" type="number"></div>
            <div><label class="width80">价格: </label><input id="product_add_price" type="number"></div>
            <div><label class="width80">提成: </label><input id="product_add_commissionRate" type="number"></div>
            <button onclick="productAddSubmit()">添加</button>
        </#if>
    </div>
</div>
</body>
</html>