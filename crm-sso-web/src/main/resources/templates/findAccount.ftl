<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>找回密码</title>
    <link rel="stylesheet" type="text/css" href="/css/view/findAccount.css">
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js?version=2020012301"></script>
</head>
<body>
<div class="sso-findAccount">
    <div class="sso-findAccount-panel">
        <form class="myform" method="post" action="/findAccount">
            <div class="sso-findAccount-title">
                <h2>找回密码</h2>
            </div>
            <div class="sso-findAccount-container">
                用户名:<input type="text" name="username" placeholder="请输入用户名">
            </div>
            <div class="sso-findAccount-container">
                验证码:<input type="text" name="validcode" placeholder="请输入验证码" minlength="6" maxlength="6">
                <button>发送验证码到邮箱</button>
            </div>
            <div>
                <input class="sso-findAccount-button" type="submit" value="确 认">
            </div>
            <div class="sso-login-tip">
                <a href="/login">立即登录</a>
            </div>
        </form>
    </div>
</div>
</body>
