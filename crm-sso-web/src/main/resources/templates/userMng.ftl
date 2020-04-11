<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
<#--    <link rel="stylesheet" type="text/css" href="/css/view/">-->
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js?version=2020012301"></script>
<#--    <script type="text/javascript" src="/js/login.js?version=2020012301"></script>-->
</head>
<body>
<div class="sso-login">
    欢迎${username}
</div>
<#include "top.ftl">
</body>
<button>个人信息</button>
<button>用户查询</button>
<button>添加用户</button>
<div class="user_info">
    <div>用户id:</div>
    <div>账户:</div>
    <div>姓名:</div>
    <div>创建时间:</div>
    <div>职位:</div>
    <div>管理员身份:</div>
    <div>邮箱:</div>
    <div>手机:</div>
    <button>修改</button>
</div>
<div class="user_info_modify">
    <div>用户id:</div>
    <div>账户:</div>
    <div>姓名:</div>
    <div>创建时间:</div>
    <div>职位:</div>
    <div>管理员身份:</div>
    <div>邮箱:</div>
    <div>手机:</div>
    <button>修改</button>
</div>
<div class="user_info_search">
    <div>用户账号：<input type="text"> <button>查询</button></div>
    <div>用户id:</div>
    <div>账户:</div>
    <div>姓名:</div>
    <div>创建时间:</div>
    <div>职位:</div>
    <div>管理员身份:</div>
    <div>邮箱:</div>
    <div>手机:</div>
    <button>修改</button>
</div>
<div class="user_info_search_modify">
    <div>用户账号：<input type="text"> <button>查询</button></div>
    <div>用户id:</div>
    <div>账户:</div>
    <div>姓名:</div>
    <div>创建时间:</div>
    <div>职位:</div>
    <div>管理员身份:</div>
    <div>邮箱:</div>
    <div>手机:</div>
    <button>修改</button>
</div>
<div class="user_add">
    <div>姓名:<input type="text"></div>
    <div>拼音:<input type="text"></div>
    <div>密码:<input type="password"></div>
    <div>职位:<input type="text"></div>
    <div>管理员身份:<input type="text"></div>
    <div>邮箱:<input type="text"></div>
    <div>手机:<input type="text"></div>
    <button>添加</button>
</div>
</html>