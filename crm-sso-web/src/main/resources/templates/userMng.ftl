<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js?version=2020012301"></script>
    <script type="text/javascript" src="/js/userMng.js?version=2020012301"></script>
</head>
<body>
<#include "default.ftl">
<#include "top.ftl">
<#include "sidebar.ftl">
<div class="container-fluid mycontent" >
    <button onclick="userinfo()">个人信息</button>
    <button onclick="userinfoSearch()">用户查询</button>
    <button onclick="userAdd()">添加用户</button>
    <div class="user_info myform <#if !userinfo>displayNone</#if>">
        <div>用户id: ${userinfo.id}</div>
        <div>用户账号: ${userinfo.username}</div>
        <div>姓名: ${userinfo.name}</div>
        <div>职位: ${userinfo.position}</div>
        <div>管理员身份: <#if userinfo.isMng == 0>否<#else>是</#if></div>
        <div>邮箱: ${userinfo.email}</div>
        <div>手机: ${userinfo.phone}</div>
        <button onclick="userinfoModify()">修改个人信息</button>
        <button onclick="passwordModify()">修改密码</button>
    </div>
    <div class="user_info_modify myform displayNone">
        <div>用户id: ${userinfo.id}</div>
        <div>用户账号: ${userinfo.username}</div>
        <div>姓名: <input type="text" value="${userinfo.name}"></div>
        <div>职位: <#if userinfo.isMng == 0>${userinfo.position}<#else><input type="text" value="${userinfo.position}"></#if></div>
        <div>管理员身份: <#if userinfo.isMng == 0>否<#else>
                <select class="user_info_modify_isMng">
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
                　　   </#if></div>
        <div>邮箱: <input type="text" value="${userinfo.email}"></div>
        <div>手机: <input type="text" value="${userinfo.phone}"></div>
        <button>确认</button>
        <button onclick="userinfo()">返回</button>
    </div>
    <div class="user_info_password_modify myform displayNone">
        <div>原密码:<input id="password_modify_password0" type="password"></div>
        <div>新密码:<input id="password_modify_password1" type="password"></div>
        <div>重复密码:<input id="password_modify_password2" type="password"></div>
        <button onclick="passwordModifySubmit()">确认</button>
        <button onclick="userinfo()">返回</button>
    </div>
    <div class="user_info_search myform <#if !userinfoSearch>displayNone</#if>">
        <div>用户id：<input id="search_id" type="text"> 用户账号：<input id="search_username" type="text"> 姓名：<input id="search_name" type="text">
            职位：<input id="search_position" type="text"> 管理员身份：<select id="search_isMng" class="user_add_isMng">
                <option value="-1">不限</option>
                <option value="0">否</option>
                <option value="1">是</option>
            </select>
            邮箱：<input id="search_email" type="text">
            手机：<input id="search_phone" type="text">
            <button onclick="userinfoSearchSubmit()">查询</button></div>
        <div>用户id: ${userinfoSearch.id}</div>
        <div>用户账号: ${userinfoSearch.username}</div>
        <div>姓名: ${userinfoSearch.name}</div>
        <div>职位: ${userinfoSearch.position}</div>
        <div>管理员身份: <#if !userinfoSearch><#elseif userinfoSearch.isMng == 0>否<#else>是</#if></div>
        <div>邮箱: ${userinfoSearch.email}</div>
        <div>手机: ${userinfoSearch.phone}</div>
        <button onclick="userinfoSearchModify()">修改</button>
    </div>
    <div class="user_info_search_modify myform displayNone">
        <#if userinfo.isMng == 0>由于您不是管理员，暂无权限进行此操作！<#else>
        <div>用户id:</div>
        <div>账户:</div>
        <div>姓名:</div>
        <div>姓名拼音:</div>
        <div>职位:</div>
        <div>管理员身份:</div>
        <div>邮箱:</div>
        <div>手机:</div>
        <button>确认</button>
        <button onclick="userinfoSearch()">返回</button>
        </#if>
    </div>
    <div class="user_add myform displayNone">
        <#if userinfo.isMng == 0>由于您不是管理员，暂无权限进行此操作！<#else>
        <div>姓名:<input id="user_add_name" type="text"></div>
        <div>姓名拼音: <input id="user_add_pinyin" type="text"></div>
        <div>初始密码:<input id="user_add_password" type="password"></div>
        <div>重复密码:<input id="user_add_password2" type="password"></div>
        <div>职位:<input id="user_add_position" type="text"></div>
        <div>管理员身份:<select id="user_add_isMng" class="user_add_isMng">
                <option value="0">否</option>
                <option value="1">是</option>
            </select></div>
        <div>邮箱:<input id="user_add_email" type="text"></div>
        <div>手机:<input id="user_add_phone" type="text"></div>
        <button onclick="userAddSubmit()">添加</button>
        </#if>
    </div>
</div>
</body>
</html>