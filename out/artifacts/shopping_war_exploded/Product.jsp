<%@ page import="com.wei.dao.ProductDao" %>
<%@ page import="com.wei.dao.UserDao" %>
<%@ page import="com.wei.entity.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.wei.dao.CategoryDao" %>
<%@ page import="com.wei.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Random" %>
<%@ page import="com.wei.services.UsersServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>商品页面</title>
    <link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/style1.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.simpleGal.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.imagezoom.min.js" type="text/javascript" ></script>
    <style>
        img {
            max-width: none;
        }
        .tb-pic a {
            display: table-cell;
            text-align: center;
            vertical-align: middle;
        }
        .tb-pic a img {
            vertical-align: middle;
        }
        .tb-pic a {
            *display:block;
            *font-family:Arial;
            *line-height:1;
        }
        .tb-thumb {
            margin: 10px 0 0;
            overflow: hidden;
        }
        .tb-thumb li {
            float: left;
            width: 86px;
            height: 86px;
            overflow: hidden;
            border: 1px solid #cdcdcd;
            margin-right: 5px;
        }
        .tb-thumb li:hover, .tb-thumb .tb-selected {
            width: 84px;
            height: 84px;
            border: 2px solid #799e0f;
        }
        .tb-s310, .tb-s310 a {
            height: 365px;
            width: 365px;
        }
        .tb-s310, .tb-s310 img {
            max-height: 365px;
            max-width: 365px;
        }
        .tb-booth {
            border: 1px solid #CDCDCD;
            position: relative;
            z-index: 1;
        }
        div.zoomDiv {
            z-index: 999;
            position: absolute;
            top: 0px;
            left: 0px;
            background: #ffffff;
            border: 1px solid #ccc;
            display: none;
            overflow: hidden;
        }
        div.zoomMask {
            position: absolute;
            background: url("images/html/mask.png") repeat;
            cursor: move;
            z-index: 1;
        }
        <!--弹出隐藏层-->
            .black_overlay {
                display: none;
                position: absolute;
                top: 0%;
                left: 0%;
                width: 100%;
                height: 1200px;
                background-color: black;
                z-index: 9999;
                -moz-opacity: 0.4;
                opacity: 0.40;
                filter: alpha(opacity=40);
            }
        .white_content {
            display: none;
            position: absolute;
            top: 20%;
            left: 40%;
            width: 400px;
            height: 175px;
            border: none;
            background-color: white;
            z-index: 100200;
            overflow: auto;
        }
        .white_content_small {
            display: none;
            position: absolute;
            top: 20%;
            left: 30%;
            width: 40%;
            height: 50%;
            background-color: white;
            z-index: 1002;
            overflow: auto;
        }
        .dialogbox {
            padding: 20px;
            line-height: 40px;
        }
        .addbtn {
            width: 22px;
            height: 22px;
            background: url(images/html/close2.png) no-repeat;
            margin-right: 5px;
            margin-top: 3px;
            border: none;
            float: right;
        }
    </style>
    <script type="text/javascript">
        //弹出隐藏层
        function ShowDiv(show_div,bg_div){
            document.getElementById(show_div).style.display='block';
            document.getElementById(bg_div).style.display='block' ;
            var bgdiv = document.getElementById(bg_div);
            bgdiv.style.width = document.body.scrollWidth;
// bgdiv.style.height = $(document).height();
            $("#"+bg_div).height($(document).height());
        };
        //关闭弹出层
        function CloseDiv(show_div,bg_div)
        {
            document.getElementById(show_div).style.display='none';
            document.getElementById(bg_div).style.display='none';
        };
    </script>
</head>

<body>
<%
    HttpSession httpSession = request.getSession();
    ProductDao productDao = new ProductDao();
    UserDao userDao = new UserDao();
    CategoryDao categoryDao = new CategoryDao();
    List<Category> cat_list = categoryDao.select_all_cat();
    Product given_product = (Product) httpSession.getAttribute("given_product");
    Random random = new Random();
    int randint = random.nextInt(3) + 1;
%>
<!--头部导航-->
<head>
    <div id="header_top">
        <div id="top">
            <div class="Inside_pages">
                <div class="Collection"><a href="UsersServlet?action=logout" class="green">登出</a></div>
                <div class="hd_top_manu clearfix">
                    <ul class="clearfix">
                        <li class="hd_menu_tit" data-addclass="hd_menu_hover"><a href="UsersServlet?action=search_cart">购物车</a></li>
                        <li class="hd_menu_tit" data-addclass="hd_menu_hover"><a href="UsersServlet?action=search_all_browse_records">浏览记录</a> </li>
                        <li class="hd_menu_tit" data-addclass="hd_menu_hover"><a href="UsersServlet?action=search_all_purchased_records">购买记录</a></li>
                        <li class="hd_menu_tit" data-addclass="hd_menu_hover"><a href="UsersServlet?action=delete_user">用户注销</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="header"  class="header page_style">
            <div class="logo"><img src="images/html/logo.png" /></div>
        </div>
        <!--菜单栏-->
        <div class="Navigation" id="Navigation">
            <ul class="Navigation_name">
                <li><a href="Products.jsp">首页</a></li>
                <li class="hour"><a href="UsersServlet?action=get_recommend_product">推荐商品</a></li>
                <%
                    for (Category category : cat_list){
                %>
                <li class="hour">
                    <a href="UsersServlet?action=search_given_category&given_category=<%=category.getCat_name()%>" style="display: inline-block;"><%=category.getCat_name()%></a>
                </li>
                <%
                    }
                %>
            </ul>
        </div>
        <script>$("#Navigation").slide({titCell:".Navigation_name li",trigger:"click"});</script>
    </div>
</head>
<!--头部导航END-->

<div class="clear">&nbsp;</div>

<!--网站头部-->
<div class="sup-wid">
    <div class="pro_detail" >
        <div class="pro_detail_img float-lt">
            <div class="gallery">
                <div class="tb-booth tb-pic tb-s310"><img width="367px" height="464px" src="images/products/<%=given_product.getPro_image()%>"  rel="images/products/meinv<%=randint%>.jpg" class="jqzoom" /> </div>
            </div>
        </div>
        <div class="float-lt pro_detail_con">
            <div class="pro_detail_tit"><%=given_product.getPro_name()%></div>
            <div class="pro_detail_ad"><%=given_product.getPro_info()%></div>
            <div class="clear"></div>
            <div class="pro_detail_price  margin-t20"><span class="margin-r20">市场价</span><span style=" font-size:16px"><s>￥<%=given_product.getPro_price()+199%></s></span></div>
            <div class="clear"></div>
            <div class="pro_detail_act margin-t20 fl"><span class="margin-r20">售价</span><span style="font-size:26px; font-weight:bold; color:#dd514c;">￥<%=given_product.getPro_price()%></span></div>
            <div class="clear"></div>
            <div class="act_block margin-t5"><span>本商品可使用9999个千纸鹤免费换取</span></div>
            <form action="UsersServlet?action=add_product_to_cart&pro_id=<%=given_product.getPro_id()%>" method="post">
                <div class="pro_detail_number margin-t30">
                    <div class="margin-r20 float-lt">数量</div>

                    <div class="">
                        <input type="number" name="pro_number"/>
                        <span>（库存<%=given_product.getPro_number()-given_product.getPro_sold()%>）</span> </div>
                    <div class="clear"></div>
                </div>
                <div class="pro_detail_number margin-t20">
                    <div class="margin-r20 float-lt">月成交量：<span class="colorred"><strong>999!</strong></span></div>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
                <div class="pro_detail_btn margin-t30">
                    <input type="submit" class="loginbtn" value="加入购物车">
                </div>
            </form>
        </div>
    </div>
    <div class="clear"></div>
    <script>
        $(function(){
            $(".pro_tab li").each(function(i){
                $(this).click(function(){
                    $(this).addClass("cur").siblings().removeClass("cur");
                    $(".conlist .conbox").eq(i).show().siblings().hide();
                })
            })
        })
    </script>
</div>

<div class="clear">&nbsp;</div>


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