<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="utf-8">
  <title>注册</title>
  <link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css">
  <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
  <style>
    .tab {
      overflow: hidden;
      margin-top: 5px; margin-bottom:-1px;
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
</head>

<body class="l-bg">
  <div class="login-top">
    <div class="wrapper">
      <div class="fl font30">清静无为</div>
      <div class="fr">您好，欢迎为生活充电在线！</div>
    </div>
  </div>
  <div class="l_main2">
    <div class="l_bttitle2">
      <h2><a href="index.jsp">< 返回首页</a></h2>
    </div>
    <div class="loginbox">
      <div class="conlist">
        <div class="conbox" style="display:block;">
          <form action="UsersServlet?action=register" method="post">
            <p>
            <div class="fl res-text">用户名：</div><div><input name="user_name" type="text" class="loginuser"></div>
            </p>
            <p>
            <div class="fl res-text">密码：</div><div><input name="user_password" type="password" class="loginuser"></div>
            </p>
            <p>
            <div class="fl res-text">确认密码：</div><div><input name="user_re_password" type="password" class="loginuser"></div>
            </p>
            <p>
            <div class="fl res-text">邮箱：</div><div><input name="user_email" type="email" class="loginuser"></div>
            </p>
            <p>
              <input type="submit" class="loginbtn" value="注 册">
            </p>
          </form>
        </div>
      </div>
    </div>
  </div>
</body>
</html>