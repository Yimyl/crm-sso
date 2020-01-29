<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/css/view/common.css">
    <link rel="stylesheet" type="text/css" href="/css/view/login.css">
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js?version=2020012301"></script>
    <script type="text/javascript" src="/js/login.js?version=2020012301"></script>
<#--    <script type="text/javascript" src="/js/crypto/sha256.js?version=2020012301"></script>-->
</head>
<body>
<div class="sso-login">
    <div class="sso-login-panel">
        <form class="myform" method="post">
            <div class="sso-login-title">
                <h2>用户登陆</h2>
            </div>
            <div class="sso-login-container">
                用户名:<input type="text" class="username" name="username" value="${username}" placeholder="请输入用户名">
            </div>
            <div class="sso-login-container">
                密码: <input type="password" class="password" name="password" maxlength="16" placeholder="请输入密码">
            </div>
            <div class="sso-login-container <#if firstLogin == 'true'>displayNone</#if>">
                验证码:<input type="text" class="validcode" name="validcode" placeholder="请输入验证码">
                <img class="validation-img" src="../img/captcha.png" title="看不清楚？点击换一张">
            </div>
            <div class="sso-login-remember">
                <label><input type="checkbox" class="a" name="remember" <#if isRemember>checked</#if>><span>记住我</span></label>
            <a href="/findAccount">忘记密码</a>
            </div>
        </form>
        <div>
            <input class="sso-login-button" type="button" onclick="submitForm()" value="登 录">
        </div>
        <div class="sso-register-tip">
            <span>还没有账号?</span><a href="/register">立即注册</a>
        </div>
    </div>
</div>
</body>
</html>
