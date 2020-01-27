<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
        <link rel="stylesheet" type="text/css" href="/css/view/register.css">
    <link rel="stylesheet" type="text/script" href="/js/jquery-3.3.1.min.js?version=2020012301">
</head>
<body>
<div class="sso-register">
    <div class="sso-register-panel">
        <form class="myform" method="post" action="/register">
            <div class="sso-register-title">
                <h2>用户注册</h2>
            </div>
            <div class="sso-register-container">
                用户名:<input type="text" name="username" placeholder="请输入用户名">
            </div>
            <div class="sso-register-container">
                邮箱:<input type="email" name="email" placeholder="请输入邮箱">
            </div>
            <div class="sso-register-container">
                密码: <input type="password" name="password" placeholder="请输入密码">
            </div>
            <div class="sso-register-container">
                确认密码: <input type="password" name="password2" placeholder="请再次输入相同密码">
            </div>
            <div class="sso-register-container">
                验证码:<input type="text" name="validcode" placeholder="请输入验证码">
                <img class="validation-img" src="../img/captcha.png" title="看不清楚？点击换一张">
            </div>
            <div>
                <input class="sso-register-button sso-register-button-left" type="submit" value="注 册">
                <input class="sso-register-button sso-register-button-right" type="button" value="登 录" onclick="window.location.href = '/login'">
            </div>
        </form>
    </div>
</div>
</body>
</html>
