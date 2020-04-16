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
    <div class="user_info myform <#if !userinfo>displayNone</#if>">
        <div><label class="width80">用户id: </label>${userinfo.id}</div>
        <div><label class="width80">用户账号: </label><span id="username">${userinfo.username}</span></div>
        <div><label class="width80">姓名: </label><span id="name">${userinfo.name}</span></div>
        <div><label class="width80">职位: </label><span id="position">${userinfo.position}</span></div>
        <div><label class="width80">管理员身份: </label><span id="isMng"><#if userinfo.isMng == 0>否<#else>是</#if></span></div>
        <div><label class="width80">邮箱: </label><span id="email">${userinfo.email}</span></div>
        <div><label class="width80">手机: </label><span id="phone">${userinfo.phone}</span></div>
        <button onclick="userinfoModify()">修改个人信息</button>
        <button onclick="passwordModify()">修改密码</button>
    </div>
    <div class="user_info_modify myform displayNone">
        <div><label class="width80">用户id: </label>${userinfo.id}</div>
        <div><label class="width80">用户账号: </label>${userinfo.username}</div>
        <div><label class="width80">姓名: </label><input id="userinfoModifyName" type="text" value="${userinfo.name}"></div>
        <div><label class="width80">职位: </label><#if userinfo.isMng == 0>${userinfo.position}<#else><input id="userinfoModifyPosition" type="text" value="${userinfo
            .position}"></#if></div>
        <div><label class="width80">管理员身份: </label><#if userinfo.isMng == 0>否<#else>
                <select id="userinfoModifyIsMng" >
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
                　　   </#if></div>
        <div><label class="width80">邮箱: </label><input id="userinfoModifyEmail" type="text" value="${userinfo.email}"></div>
        <div><label class="width80">手机: </label><input id="userinfoModifyPhone" type="text" value="${userinfo.phone}"></div>
        <button onclick="userinfoModifySubmit()" >确认</button>
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
        <div>用户id：<input id="search_id" type="text"> 用户账号：<input id="search_username" type="text">手机：<input id="search_phone" type="text">
            <button onclick="userinfoSearchSubmit()">查询</button></div>
        <div><label class="width80">用户id: </label><span class="userinfoSearchId">${userinfoSearch.id}</span></div>
        <div><label class="width80">用户账号: </label><span class="userinfoSearchUsername">${userinfoSearch.username}</span></div>
        <div><label class="width80">姓名: </label><span id="userinfoSearchName">${userinfoSearch.name}</span></div>
        <div><label class="width80">职位: </label><span id="userinfoSearchPosition">${userinfoSearch.position}</span></div>
        <div><label class="width80">管理员身份: </label><span id="userinfoSearchIsMng"><#if !userinfoSearch><#elseif userinfoSearch.isMng == 0>否<#else>是</#if></span></div>
        <div><label class="width80">邮箱: </label><span id="userinfoSearchEmail">${userinfoSearch.email}</span></div>
        <div><label class="width80">手机: </label><span id="userinfoSearchPhone">${userinfoSearch.phone}</span></div>
        <button onclick="userinfoSearchModify()">修改</button>
        <button onclick="userinfoSearchDelete()">删除</button>
    </div>
    <div class="user_info_search_modify myform displayNone">
        <#if userinfo.isMng == 0>由于您不是管理员，暂无权限进行此操作！<#else>
        <div><label class="width80">用户id: </label> <span class="userinfoSearchId">${userinfoSearch.id}</span></div>
        <div><label class="width80">用户账号: </label> <span class="userinfoSearchUsername">${userinfoSearch.username}</span></div>
        <div><label class="width80">姓名: </label> <input id="userinfoSearchModifyName" type="text" value="${userinfoSearch.name}"></div>
        <div><label class="width80">职位: </label> <input id="userinfoSearchModifyPosition" type="text" value="${userinfoSearch.position}"></div>
        <div><label class="width80">管理员身份: </label>
                <select id="userinfoSearchModifyIsMng" value="${userinfoSearch.isMng}">
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select></div>
        <div><label class="width80">邮箱: </label><input id="userinfoSearchModifyEmail" type="text" value="${userinfoSearch.email}"></div>
        <div><label class="width80">手机: </label><input id="userinfoSearchModifyPhone" value="${userinfoSearch.phone}"></div>
        <button onclick="userinfoSearchModifySubmit()">确认</button>
        <button onclick="userinfoSearch()">返回</button>
        </#if>
    </div>
    <div class="user_add myform displayNone">
        <#if userinfo.isMng == 0>由于您不是管理员，暂无权限进行此操作！<#else>
        <div><label class="width80">姓名: </label><input id="user_add_name" type="text"></div>
        <div><label class="width80">姓名拼音:</label><input id="user_add_pinyin" type="text"></div>
        <div><label class="width80">初始密码: </label><input id="user_add_password" type="password"></div>
        <div><label class="width80">重复密码: </label><input id="user_add_password2" type="password"></div>
        <div><label class="width80">职位: </label><input id="user_add_position" type="text"></div>
        <div><label class="width80">管理员身份: </label><select id="user_add_isMng" class="user_add_isMng">
                <option value="0">否</option>
                <option value="1">是</option>
            </select></div>
        <div><label class="width80">邮箱: </label><input id="user_add_email" type="text"></div>
        <div><label class="width80">手机: </label><input id="user_add_phone" type="text"></div>
        <button onclick="userAddSubmit()">添加</button>
        </#if>
    </div>
</div>
</body>
</html>