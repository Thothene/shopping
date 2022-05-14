<%@ page import="com.wei.dao.SalespersonDao" %>
<%@ page import="com.wei.dao.CategoryDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wei.entity.Category" %>
<%@ page import="com.wei.entity.Administrator" %>
<%@ page import="com.wei.utils.IpUtils" %>
<%@ page import="com.wei.dao.A_operationDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--管理员查看所有类别，表格形式--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="css/common.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="css/user_style.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="js/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
    <script src="js/common_js.js" type="text/javascript"></script>
    <script src="js/footer.js" type="text/javascript"></script>
    <script src="js/common_js.js" type="text/javascript"></script>
    <script src="js/footer.js" type="text/javascript"></script>
    <script src="js/iCheck.js" type="text/javascript"></script>
    <script src="js/custom.js" type="text/javascript"></script>
    <script src="Plugins/laydate/laydate.js" type="text/javascript"></script>
    <title>管理员——类别页面</title>
</head>

<body>
<%
    HttpSession httpSession = request.getSession();
    SalespersonDao salespersonDao = new SalespersonDao();
    CategoryDao categoryDao = new CategoryDao();
    List<Category> cat_list = categoryDao.select_all_cat();
    if(cat_list == null){
        String msg = "当前类别为空！";
        response.getWriter().write("<script language=javascript>alert('" +msg+ "');window.location='Administrator_main.jsp'</script>");
    }
    Administrator administrator = (Administrator) httpSession.getAttribute("administrator");
    A_operationDao a_operationDao = new A_operationDao();
    a_operationDao.insert_search(administrator.getA_id(), IpUtils.getIp(request));
%>
<head>
    <div id="header_top">
        <div id="top">
            <div class="Inside_pages">
                <div class="Collection"><a href="AdministratorServlet?action=logout" class="green">管理员登出</a></div>
            </div>
        </div>
        <div id="header"  class="header page_style">
            <div class="logo"><a href="index.html"><img src="images/html/logo.png" /></a></div>
            <!--结束图层-->

            <!--购物车样式-->

        </div>
        <!--菜单栏-->
        <div class="Navigation" id="Navigation">
            <ul class="Navigation_name">
                <li><a href="Administrator_main.jsp">首页</a></li>
                <li><a href="#">大悲无泪</a></li>
                <li><a href="#">大悟无言</a></li>
                <li><a href="#">大笑无声</a></li>
            </ul>
        </div>
        <script>$("#Navigation").slide({titCell:".Navigation_name li",trigger:"click"});</script>
    </div>
</head>
<!--添加店铺样式-->
<div class="Inside_pages clearfix">
    <div class="left_style">
        <!--列表-->
        <div class="menu_style">
            <ul class="menu_list">
                <li ><em></em><a href="Administrator_statistical_chart.jsp">统计图表</a></li>
                <li><em></em><a href="Administrator_all_category.jsp">查看类别</a></li>
                <li><em></em><a href="Administrator_all_salesperson.jsp">查看销售员</a></li>
                <li><em></em><a href="Administrator_category_management.jsp">类别管理</a></li>
                <li><em></em><a href="Administrator_salesperson_management.jsp">销售员管理</a></li>
            </ul>
        </div>
    </div>
    <!--右侧内容样式-->
    <div class="right_style">
        <!--消费记录样式-->
        <div class="user_address_style">
            <div class="title_style"><em></em>查看类别</div>
            <div class="Exp_record_style">
                <table cellpadding="0"  cellspacing="0"  width="100%" class="record_list">
                    <thead><tr class="label_name"><td width="15%">类别id</td><td width="15%">类别名称</td><td width="20%">所属销售员编号</td><td width="20%">所属销售员名称</td><td width="30%">所属销售员邮箱</td></tr></thead>
                    <tbody>
                        <% for (Category category : cat_list){%>
                        <tr class="content">
                            <td><%=category.getCat_id()%></td>
                            <td><%=category.getCat_name()%></td>
                            <td><%=salespersonDao.select(category.getCat_sal_id()).getSal_id()%></td>
                            <td><%=salespersonDao.select(category.getCat_sal_id()).getSal_name()%></td>
                            <td><%=salespersonDao.select(category.getCat_sal_id()).getSal_email()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<!--网站地图-->
<div class="fri-link-bg clearfix">
    <div class="fri-link">
        <div class="logo left margin-r20"><img src="images/html/fo-logo.jpg" width="152" height="81" /></div>
        <div class="left"><img src="images/html/qd.png" width="90"  height="90" />
            <p>扫描加个微信</p>
        </div>
        <div class="">
            <dl>
                <dt>新手上路</dt>
                <dd><a href="#">多看多问</a></dd>
                <dd><a href="#">不会百度吗</a></dd>
                <dd><a href="#">不带了不带了</a> </dd>
                <dd><a href="#">带不动</a></dd>
                <dd><a href="#">新人我看好你哦</a></dd>
            </dl>
            <dl>
                <dt>周杰伦曲目</dt>
                <dd><a href="https://www.bilibili.com/video/BV1sS4y1h7g7?spm_id_from=333.999.0.0" target="_blank">听妈妈的话</a></dd>
                <dd><a href="https://www.bilibili.com/video/BV1fx411N7bU?p=109" target="_blank">魔术先生</a></dd>
                <dd><a href="https://www.bilibili.com/video/BV1fx411N7bU?p=114" target="_blank">乔克叔叔</a> </dd>
                <dd><a href="https://www.bilibili.com/video/BV1fx411N7bU?p=120" target="_blank">免费教学录影带</a></dd>
            </dl>
            <dl>
                <dt>售后保障</dt>
                <dd><a href="http://scjgj.sc.gov.cn/scjgj/flfg/2020/7/13/2ceed2bf7a214f88862da68d6f2ab159.shtml" target="_blank">《质量法》</a></dd>
                <dd><a href="https://gkml.samr.gov.cn/nsjg/fgs/201906/t20190625_302783.html" target="_blank">《权益保护法》</a></dd>
                <dd><a href="https://wenku.baidu.com/view/a0ef1d3fd838376baf1ffc4ffe4733687f21fcd2.html" target="_blank">《更退规定》</a> </dd>
                <dd><a href="http://s.yingle.com/y/fg/100452.html" target="_blank">《暂行办法》</a></dd>
            </dl>
            <dl>
                <dt>支付方式</dt>
                <dd><a href="https://umoney.duxiaoman.com/" target="_blank">借贷支付</a></dd>
                <dd><a href="https://weixin.qq.com/" target="_blank">微信支付</a></dd>
                <dd><a href="https://www.alipay.com/" target="_blank">支付宝支付</a></dd>
                <dd><a href="https://baike.baidu.com/item/现金支付/4006929" target="_blank">现金支付</a></dd>
            </dl>
            <dl>
                <dt>神奇的网站</dt>
                <dd><a href="https://www.cbaigui.com/" target="_blank">未知礼盒</a></dd>
                <dd><a href="https://www.allhistory.com/book/home-list" target="_blank">未知大礼盒</a></dd>
                <dd><a href="https://theuselessweb.com/" target="_blank">未知超级礼盒</a> </dd>
                <dd><a href="http://www.dbbqb.com" target="_blank">未知超级大礼盒</a></dd>
                <dd><a href="https://thispersondoesnotexist.com/" target="_blank">未知超级大礼盒</a></dd>
            </dl>
            <dl>
                <dt>酒馆战旗</dt>
                <dd><a href="">绿龙第一！！</a></dd>
                <dd><a href="">红龙第一！！</a></dd>
                <dd><a href="">黄龙第一！！</a> </dd>
                <dd><a href="">黑龙第一！！</a></dd>
            </dl>
        </div>
    </div>
</div>
<!--网站地图END-->
<!--网站页脚-->
<div class="copyright">
    <div class="copyright-bg">
        <div class="hotline">为生活充电在线，我与赌毒不共戴天 <span>招商热线参加富婆电话薄</span> 客服热线：110</div>
        <div class="hotline co-ph">
            <p>版权所有Copyright ©可可爱爱的乐乐和韦韦</p>
            <p>ICP备塞尔号 不良信息举报</p>
        </div>
    </div>
</div>
</body>
</html>
