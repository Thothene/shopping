<%@ page import="com.wei.entity.User" %>
<%@ page import="com.wei.entity.P_record" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wei.entity.Product" %>
<%@ page import="com.wei.dao.ProductDao" %>
<%@ page import="com.wei.dao.UserDao" %>
<%@ page import="com.wei.dao.CategoryDao" %>
<%@ page import="com.wei.entity.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/user_style.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common_js.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/footer.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/layer/layer.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/iCheck.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/custom.js" type="text/javascript"></script>
    <title>用户购物记录</title>
</head>

<body>
<%
    HttpSession httpSession = request.getSession();
    ProductDao productDao = new ProductDao();
    CategoryDao categoryDao = new CategoryDao();
    List<Category> cat_list = categoryDao.select_all_cat();
    User user = (User) httpSession.getAttribute("user");
    List<P_record> p_recordList = (List<P_record>) httpSession.getAttribute("p_recordList");
    if (p_recordList == null) {
        String msg = "当前购物记录为空！";
        response.getWriter().write("<script language=javascript>alert('" + msg + "');window.location='Products.jsp'</script>");
    }
%>
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
            <div class="hd_Shopping_list" id="Shopping_list">
                <div class="s_cart"><a href="UsersServlet?action=search_cart">我的购物车</a> <i class="ci-right">&gt;</i><i class="ci-count" id="shopping-amount">99+</i></div>
            </div>
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
<div class="user_style clearfix">
    <div class="user_center clearfix">
        <div class="left_style">
            <div class="menu_style">
                <div class="user_title"><a href="用户中心.html">用户中心</a></div>
                <div class="user_Head">
                    <div class="user_portrait">
                        <img src="images/html/people.png">
                        <div class="background_img"></div>
                    </div>
                    <div class="user_name">
                        <p><span class="name"><%=user.getUser_name()%></span></p>
                        <p>访问时间：未来的某一天</p>
                    </div>
                </div>
                <div class="sideMen">
                    <!--菜单列表图层-->
                    <dl class="accountSideOption1">
                        <dt class="transaction_manage"><em class="icon_1"></em>订单管理</dt>
                        <dd>
                            <ul>
                                <li> <a href="用户中心-我的订单.html">我的订单</a></li>
                                <li> <a href="用户中心-收货地址.html">收货地址</a></li>
                                <li> <a href="用户中心-产品预订.html">产品预订</a></li>
                            </ul>
                        </dd>
                    </dl>
                    <dl class="accountSideOption1">
                        <dt class="transaction_manage"><em class="icon_2"></em>会员管理</dt>
                        <dd>
                            <ul>
                                <li> <a href="#"> 用户信息</a></li>
                                <li> <a href="#"> 我的收藏</a></li>
                                <li> <a href="#"> 我的留言</a></li>
                                <li> <a href="#"> 我的标签</a></li>
                                <li> <a href="#"> 我的推荐</a></li>
                                <li> <a href="#"> 我的评论</a></li>
                            </ul>
                        </dd>
                    </dl>
                    <dl class="accountSideOption1">
                        <dt class="transaction_manage"><em class="icon_3"></em>账户管理</dt>
                        <dd>
                            <ul>
                                <li><a href="用户中心-账户管理.html">账户余额</a></li>
                                <li><a href="用户中心-消费记录.html">消费记录</a></li>
                                <li><a href="#">跟踪包裹</a></li>
                                <li><a href="#">资金管理</a></li>
                            </ul>
                        </dd>
                    </dl>
                    <dl class="accountSideOption1">
                        <dt class="transaction_manage"><em class="icon_4"></em>分销管理</dt>
                        <dd>
                            <ul>
                                <li> <a href="#">店铺管理</a></li>
                                <li> <a href="#">我的盟友</a></li>
                                <li> <a href="#">我的佣金</a></li>
                                <li> <a href="#">申请提现</a></li>
                            </ul>
                        </dd>
                    </dl>
                </div>
                <script>jQuery(".sideMen").slide({titCell:"dt", targetCell:"dd",trigger:"click",defaultIndex:0,effect:"slideDown",delayTime:300,returnDefault:true});</script>
            </div>
        </div>
        <!--右侧样式-->
        <div class="right_style">
            <div class="title_style"><em></em>购物记录</div>
            <div class="Order_form_style">
                <div class="Order_form_list">
                    <table>
                        <thead>
                            <tr>
                                <td class="list_name_title0">商品名称</td>
                                <td class="list_name_title1">商品图像</td>
                                <td class="list_name_title2">商品单价（￥）</td>
                                <td class="list_name_title4">购买数量</td>
                                <td class="list_name_title5">购买总价（￥）</td>
                                <td class="list_name_title6">操作IP</td>
                            </tr>
                        </thead>
                        <%
                            for (P_record p_record : p_recordList){
                                Product product = productDao.select_by_pro_id(p_record.getPrec_pro_id());
                                String pro_image = product.getPro_image();
                                String pro_id = Integer.toString(product.getPro_id());
                        %>
                        <tbody>
                        <tr class="Order_info"><td colspan="6" class="Order_form_time">购买时间：<%=p_record.getPrec_time()%> | 订单号：<%=p_record.getPrec_id()%> <em></em></td></tr>
                        <tr class="Order_Details">
                            <td width="12.5%"><%=product.getPro_name()%></td>
                            <td class="split_line" width="25%">
                                    <img src="images/products/<%=pro_image%>" style="width:80px; height: 80px; display: inline-block;" onclick="showproduct(<%=pro_id%>)"/>
                            </td>
                            <td width="12.5%"><%=product.getPro_price()%></td>
                            <td width="12.5%"><%=p_record.getPrec_number()%></td>
                            <td width="12.5%"><%=p_record.getPrec_total_money()%></td>
                            <td width="25%" class="split_line"><%=p_record.getPrec_ip()%></td>
                        </tr>
                        </tbody>
                        <%
                            }
                        %>
                    </table>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function(){
                $('.Order_form_style input').iCheck({
                    checkboxClass: 'icheckbox_flat-green',
                    radioClass: 'iradio_flat-green'
                });
            });
        </script>
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
<!--右侧菜单栏购物车样式-->
    <div class="fixedBox">
        <ul class="fixedBoxList">
            <li class="fixeBoxLi user"><a href="UsersServlet?action=search_all_browse_records"> <span class="fixeBoxSpan"></span> <strong>浏览记录</strong></a></li>
            <li class="fixeBoxLi BackToTop"> <span class="fixeBoxSpan"></span> <strong onclick="pageScroll()">返回顶部</strong> </li>
        </ul>
    </div>
</body>
</html>
<script>
    function showproduct(pro_id){
        location.href="/shopping_war_exploded/UsersServlet?action=search_given_product&amp;pro_id="+pro_id;
    }
    function pageScroll(){
        //把内容滚动指定的像素数（第一个参数是向右滚动的像素数，第二个参数是向下滚动的像素数）
        window.scrollBy(0,-100);
        //延时递归调用，模拟滚动向上效果
        scrolldelay = setTimeout('pageScroll()',5);
        //获取scrollTop值，声明了DTD的标准网页取document.documentElement.scrollTop，否则取document.body.scrollTop；因为二者只有一个会生效，另一个就恒为0，所以取和值可以得到网页的真正的scrollTop值
        var sTop=document.documentElement.scrollTop+document.body.scrollTop;
        //判断当页面到达顶部，取消延时代码（否则页面滚动到顶部会无法再向下正常浏览页面）
        if(sTop==0) clearTimeout(scrolldelay);
    }
</script>

