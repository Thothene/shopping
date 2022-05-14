<%@ page import="com.wei.entity.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wei.dao.ProductDao" %>
<%@ page import="com.wei.dao.UserDao" %>
<%@ page import="com.wei.dao.CategoryDao" %>
<%@ page import="com.wei.entity.Category" %>
<%@ page import="com.wei.services.UsersServlet" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2022/3/17
  Time: 10:11
  <a href="Userlogin.jsp">登录</a>
<a href="Salespersonlogin.jsp">销售员入口</a>
<a href="Administratorlogin.jsp">管理员入口</a>
<a href="Register.jsp">注册</a>
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.SuperSlide.2.1.1.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common_js.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/footer.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/accordion.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/lrtk.js" type="text/javascript"></script>
    <title>产品列表页</title>
</head>
<script type="text/javascript" charset="UTF-8">
    //点击效果start
    function onclick(event) {
        info_more_down();
        return false;
    }
    //点击效果end
    //-->
    <!--实现手风琴下拉效果-->
    $(function(){
        $(".nav").accordion({
            //accordion: true,
            speed: 500,
            closedSign: '+',
            openedSign: '-'
        });
    });

    $(function() {
        $("#scrollsidebar").fix({
            float : 'left',
            //minStatue : true,
            skin : 'green',
            durationTime : 600
        });
    });
</script>
<body>
<%
    HttpSession httpSession = request.getSession();
    UsersServlet.count_total_time(request, response);
    ProductDao productDao = new ProductDao();
    UserDao userDao = new UserDao();
    CategoryDao categoryDao = new CategoryDao();
    List<Product> pro_list = (List<Product>) httpSession.getAttribute("recommend_product");
    if (pro_list.isEmpty()){
        response.sendRedirect("/shopping_war_exploded/Error.jsp");
    }
    List<Category> cat_list = categoryDao.select_all_cat();

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
<!--产品列表样式-->
<div class="Inside_pages">
    <!--位置-->
    <!--产品列表样式-->
    <div  class="scrollsidebar side_green clearfix" id="scrollsidebar">
        <div class="show_btn" id="rightArrow"><span></span></div>
        <!--左侧样式-->
        <div class="page_left_style side_content"  >
            <div class="side_title"><a title="隐藏" class="close_btn"><span></span></a></div>
            <div class="side_list">
                <div class="demo">
                    <ul class="nav">
                        <li class="Menu_Bar"><a href="#" target="_blank">首页</a></li>
                        <li class="Menu_Bar"><a href="#">酒馆战旗.鲍勃</a>
                            <ul>
                                <li><a href="#">我这有好吃的香辣饼干</a></li>
                                <li><a href="#">不贪怎么赢</a></li>
                                <li><a href="#">我看好你，你可别告诉别人</a></li>
                                <li><a href="#">哦吼吼</a></li>
                            </ul>
                        </li>
                        <li class="Menu_Bar"><a href="#">酒馆战旗.英雄.四大小偷</a>
                            <ul>
                                <li><a href="#">刀油</a></li>
                                <li><a href="#">猫猫</a></li>
                                <li><a href="#">苔丝</a></li>
                                <li><a href="#">拉法姆</a></li>
                            </ul>
                        </li>
                        <li class="Menu_Bar"><a href="#">酒馆战旗.随从</a></a>
                            <ul>
                                <li><a href="#">亲爹</a>
                                    <ul>
                                        <li>铜须</li>
                                        <li>糯米</li>
                                        <li>瑞文</li>
                                        <li>猎妈</li>
                                    </ul>
                                </li>
                                <li><a href="#">单《..》🐕都不玩</a>
                                    <ul>
                                        <li>糯米</li>
                                        <li>卡雷</li>
                                        <li>拉格</li>
                                        <li>灯神</li>
                                    </ul>
                                </li>
                                <li><a href="#">奇迹</a>
                                    <ul>
                                        <li>老司机</li>
                                        <li>大蛇</li>
                                        <li>海盗船</li>
                                        <li>蜂哥</li>
                                    </ul>
                                </li>
                                <li><a href="#">烂分</a>
                                    <ul>
                                        <li>泥巴人</li>
                                        <li>红绿灯</li>
                                        <li>剑宗</li>
                                        <li>气宗</li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li class="Menu_Bar"><a href="#">酒馆战旗.流派</a>
                            <ul>
                                <li><a href="#">刀油</a></li>
                                <li><a href="#">猫猫</a></li>
                                <li><a href="#">苔丝</a></li>
                                <li><a href="#">拉法姆</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="page_right_style">
            <div class="p_list  clearfix">
                <ul>
                    <%
                        for (Product product:pro_list){
                            String pro_image = product.getPro_image();
                            String pro_id = Integer.toString(product.getPro_id());
                    %>
                    <li class="gl-item">
                        <em class="icon_special tejia"></em>
                        <div class="Borders" style="box-shadow:7px 7px 7px #909090;border-radius: 0 15px 0 15px;overflow: hidden;">
                            <div class="img">
                                <img src="images/products/<%=pro_image%>" style="width:220px; height: 220px; display: inline-block;" onclick="showproduct(<%=pro_id%>)"/>
                            </div>
                            <div class="Price"><b>¥<%=product.getPro_price()%></b></div>
                            <div class="name"><a href="Product_Detailed.html"><%=product.getPro_name()%></a></div>
                            <div class="Shop_name"><a href="#">韦韦旗舰店</a></div>
                        </div>
                    </li>
                    <%}%>

                </ul>
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
            <li class="fixeBoxLi Home"> <a href="UsersServlet?action=search_all_purchased_records"> <span class="fixeBoxSpan"></span> <strong>购物记录</strong></a></li>
            <li class="fixeBoxLi BackToTop"> <span class="fixeBoxSpan"></span> <strong onclick="pageScroll()">返回顶部</strong> </li>
        </ul>
    </div>
</body>
</html>

<script>
    function showproduct(pro_id){
        location.href="/shopping_war_exploded/UsersServlet?action=search_given_product&pro_id="+pro_id;
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
