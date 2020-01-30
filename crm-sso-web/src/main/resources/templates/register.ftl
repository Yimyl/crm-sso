<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="/css/view/register.css">
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js?version=2020012301"></script>
    <script type="text/javascript" src="/js/register.js?version=2020012301"></script>
<#--    <script type="text/javascript" src="/js/crypto/sha256.js?version=2020012301"></script>-->
</head>
<body>
<div class="sso-register">
    <div class="sso-register-panel">
        <form class="myform" method="post">
            <div class="sso-register-title">
                <h2>用户注册</h2>
            </div>
            <div class="sso-tip displayNone"></div>
            <div class="sso-register-container">
                用户名:<input type="text" name="username" placeholder="请输入用户名">
            </div>
            <div class="sso-register-container">
                邮箱:<input type="email" name="email" placeholder="请输入邮箱">
            </div>
            <div class="sso-register-container">
                密码: <input type="password" name="password" maxlength="16" placeholder="请输入6~16位字符组成的密码">
            </div>
            <div class="sso-register-container">
                确认密码: <input type="password" name="password2" placeholder="请再次输入相同密码">
            </div>
            <div class="sso-register-container">
                验证码:<input type="text" name="validcode" placeholder="请输入验证码" minlength="6" maxlength="6">
                <img class="validation-img" src="../img/captcha.png" title="看不清楚？点击换一张">
            </div>
        </form>
        <div>
            <input class="sso-register-button" onclick="submitRegisterForm()" type="button" value="注 册">
        </div>
        <div class="sso-login-tip">
            <span>已有账号?</span><a href="/login">立即登录</a>
        </div>
    </div>
</div>
</body>
</html>
