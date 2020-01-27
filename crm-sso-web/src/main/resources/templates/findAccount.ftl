<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>找回密码</title>
    <#--    <link rel="stylesheet" type="text/css" href="/css/view/login.css">-->
    <link rel="stylesheet" type="text/script" href="/js/jquery-3.3.1.min.js?version=2020012301">
</head>
<body>
    <div>
        <form method="post" action="/login" >
            <input type="text" name="username" value=""${username}">
            <input type="email" name="email">
            <input type="password" name="password">
            <input type="password" name="password2">
            <input type="text" name="validcode">
            <input type="text" value="验证码">
            <input type="button" value="登录">
        </form>
    </div>
</body>
<script src="../static/js/jquery-3.3.1.min.js?version=2020012301" ></script>
