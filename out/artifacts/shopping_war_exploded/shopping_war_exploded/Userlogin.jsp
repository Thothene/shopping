<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>登陆</title>
    <link href="/shopping_war_exploded/css/base.css" rel="stylesheet" type="text/css">
    <link href="/shopping_war_exploded/css/css.css" rel="stylesheet" type="text/css">
    <script src="/shopping_war_exploded/js/jquery-2.1.1.min.js"></script>
    <style>
        .tab {
            overflow: hidden;
            margin-top: 20px; margin-bottom:-1px;
        }
        .tab li {
            display: block;
            float: left;
            width: 100px;padding:10px 20px; cursor:pointer; border:1px solid #ccc;
        }
        .tab li.on {
            background: #90B831; color:#FFF;padding:10px 20px;
        }
        .conlist {padding:30px; border:1px solid #ccc; width:300px;}
        .conbox {
            display: none;
        }
    </style>
    <script>
        $(function show(){
            $(".tab li").each(function(i){
                $(this).click(function(){
                    $(this).addClass("on").siblings().removeClass("on");
                    $(".conlist .conbox").eq(i).show().siblings().hide();
                })
            })
        })
    </script>
</head>

<body>
<div class="login-top">
    <div class="wrapper">
        <div class="fl font30">LOGO</div>
        <div class="fr">您好，欢迎为生活充电在线！</div>
    </div>
</div>
<div class="l_main">
    <div class="l_bttitle2">
        <!-- <h2>登录</h2>-->
        <h2><a href="index.jsp">< 返回首页</a></h2>
    </div>
    <div class="loginbox fl">
        <div class="tab">
            <ul>
                <li class="on" onclick="sh">我是用户</li>
            </ul>
        </div>
        <div class="conlist">
            <div class="conbox" style="display:block;">
                <form action="UsersServlet?action=login" method="post">
                    <p>
                        <input name="user_name" type="text" class="loginusername" placeholder="请输入用户名称">
                    </p>
                    <p>
                        <input name="user_password" type="password" class="loginuserpassword" placeholder="请输入用户密码">
                    </p>
                    <p><span class="fl fntz14 margin-t10"><a href="Register.jsp" style="color:#ff6000">立即注册</a></span></p>
                    <p>
                        <input type="submit" class="loginbtn" value="登  录">
                    </p>
                </form>
            </div>
        </div>
    </div>


    <div class="fr margin-r100 margin-t45"><img src="images/html/login-pic.png" width="507" height="325"></div>
</div>
</body>
</html>