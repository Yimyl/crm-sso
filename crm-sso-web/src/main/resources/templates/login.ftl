<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <div>
        <form>
            <input type="text" name="username" value="${username}">
            <input type="password" name="password">
            <input type="checkbox" name="remember" <#if isRemember>checked</#if> >
            <input type="text" name="validcode">
            <a href="/findAccount">忘记密码</a>
            <input type="submit" value="登录">
            <input type="button" value="注册">
        </form>
    </div>
</body>
<script src="../static/js/jquery-3.3.1.min.js?version=2020012301" ></script>
