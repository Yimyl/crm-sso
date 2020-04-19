<script type="text/javascript" src="/js/top.js?version=2020012301"></script>
<div class="navbar navbar-fixed-top">
    <div class="sidebar-toggler">
        <a href="#">≡</a>
    </div>
    <div class="container-fluid">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
            <a class="navbar-brand" href="/home"></a> </div>
        <div class="navbar-collapse collapse" role="navigation">
            <ul class="nav navbar-nav navbar-right user-state">
                <li><span class="padding-lr-2x"><i class="icon-user"></i>${userinfo.username}</span></li>
                <li><a href="/logout"><i class="icon-exit"></i>退出</a></li>
            </ul>
            <ul class="site-nav nav navbar-nav">
                <li ($nav_head==0) class="active" ><a href="/home">首页</a></li>

                <li ($nav_head==9) class="active" ><a href="/userMng">用户管理</a></li>

                <li ($nav_head==1) class="active" ><a href="/consumerMng">客户管理</a></li>

                <li ($nav_head==2) class="active" ><a href="/productMng">产品管理</a></li>

                <li ($nav_head==3) class="active" ><a href="/profitMng">业绩管理</a></li>
                
                <li ($nav_head==4) class="active" ><a href="/statementMng">效果报表</a></li>
                
                <li ($nav_head==5) class="active" ><a href="$baseurl/mng/commission/index">结算管理</a></li>
                
                <li ($nav_head==6) class="active" ><a href="$baseurl/mng/commset/index">分成管理</a></li>
                
                <li ($nav_head==7) class="active" ><a href="$baseurl/mng/message/index">公告消息</a></li>
                
                <li ($nav_head==8) class="active" ><a href="$baseurl/mng/privilege/index">权限管理</a></li>
            </ul>
        </div>
    </div>
</div>

