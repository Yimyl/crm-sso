<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/css/view/login.css">
    <link rel="stylesheet" type="text/script" href="/js/jquery-3.3.1.min.js?version=2020012301">
</head>
<body>
<div class="sso-login">
    <div class="sso-login-panel">
        <form class="myform" method="post" action="/login">
            <div class="sso-login-title">
                <h2>用户登陆</h2>
            </div>
            <div class="sso-login-container">
                用户名:<input type="text" name="username" value="${username}" placeholder="请输入用户名">
            </div>
            <div class="sso-login-container">
                密码: <input type="password" name="password" placeholder="请输入密码">
            </div>
            <div class="sso-login-container">
                验证码:<input type="text" name="validcode" placeholder="请输入验证码">
                <img class="validation-img" src="../img/captcha.png" title="看不清楚？点击换一张">
            </div>
            <div class="sso-login-remember">
            <input type="checkbox" name="remember" <#if isRemember>checked</#if>><span>记住我</span>
            <a href="/findAccount">忘记密码</a>
            </div>
            <div>
                <input class="sso-login-button sso-login-button-left" type="submit" value="登 录">
                <input class="sso-login-button sso-login-button-right" type="button" value="注 册" onclick="window.location.href = '/register'">
            </div>
        </form>
    </div>
</div>
</body>
</html>
