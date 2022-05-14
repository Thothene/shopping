<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--管理员首页--%>
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
    <script src="${pageContext.request.contextPath}/js/common_js.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/footer.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/iCheck.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/custom.js" type="text/javascript"></script>
    <title>销售员首页</title>
</head>

<body>
<head>
    <div id="header_top">
        <div id="top">
            <div class="Inside_pages">
                <div class="Collection"><a href="SalespersonServlet?action=logout" class="green">销售员登出</a></div>
            </div>
        </div>
        <div id="header"  class="header page_style">
            <div class="logo"><a href=""><img src="images/html/logo.png" /></a></div>
            <!--结束图层-->

            <!--购物车样式-->

        </div>
        <!--菜单栏-->
        <div class="Navigation" id="Navigation">
            <ul class="Navigation_name">
                <li><a href="Salesperson_main.jsp">首页</a></li>
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
                <li ><em></em><a href="/shopping_war_exploded/SalespersonServlet?action=visual_performance">可视化业绩</a></li>
                <li><em></em><a href="Salesperson_all_product.jsp">查看商品</a></li>
                <li><em></em><a href="Salesperson_manage_product.jsp">管理商品</a></li>
            </ul>
        </div>
    </div>
    <!--右侧内容样式-->
    <div class="right_style">
        <!--内容详细-->
        <div class="title_style"><em></em>管理商品</div>
        <div class="content_style">
            <!--报表管理-->
            <div class="Reportform_style">
                    <div class="Reportform_table">
                        <div class="label_name">插入新商品</div>
                        <ul class="inline date_inline">
                            <form action="SalespersonServlet?action=add_product" method="post" style="display: inline" enctype="multipart/form-data">
                                商品名称：<input type="text" name="pro_name">
                                商品信息：<input type="text" name="pro_info">
                                商品数量：<input type="number" name="pro_number" min="0">
                                商品价格：<input type="number" name="pro_price" min="0" step="0.01">
                                商品类别：<input type="number" name="pro_cat_id" min="1">
                                商品已售：<input type="number" name="pro_sold" min="0">
                                商品图像：<input type="file" name="pro_image">
                                <input type="submit" value="提交"  class="Export_btn"/>
                            </form>
                        </ul>
                    </div>
                    <div class="Reportform_table">
                        <div class="label_name">删除商品</div>
                        <ul class="inline date_inline">
                            <form action="SalespersonServlet?action=delete_product" method="post" style="display: inline">
                                商品ID：<input type="number" name="pro_id" min="0">
                                <input type="submit" value="提交"  class="Export_btn"/>
                            </form>
                        </ul>
                    </div>
                    <div class="Reportform_table">
                        <div class="label_name">修改商品销售价格</div>
                        <ul class="inline date_inline">
                            <form action="SalespersonServlet?action=update_product_price" method="post" style="display: inline">
                                商品ID：<input type="number" name="pro_id" min="0">
                                新的商品价格：<input type="number" name="pro_price" min="0" step="0.01">
                                <input type="submit" value="提交"  class="Export_btn"/>
                            </form>
                        </ul>
                    </div>
                    <div class="Reportform_table">
                        <div class="label_name">修改商品总量</div>
                        <ul class="inline date_inline">
                            <form action="SalespersonServlet?action=update_product_number" method="post" style="display: inline">
                                商品ID：<input type="number" name="pro_id" min="0">
                                新的商品总量：<input type="number" name="pro_number" min="0">
                                <input type="submit" value="提交"  class="Export_btn"/>
                            </form>
                        </ul>
                    </div>
                    <div class="Reportform_table">
                        <div class="label_name">查看特定商品的浏览日志</div>
                        <ul class="inline date_inline">
                            <form action="SalespersonServlet?action=search_given_product_b_record" method="post" style="display: inline">
                                商品ID：<input type="number" name="pro_id" min="0">
                                <input type="submit" value="提交"  class="Export_btn"/>
                            </form>
                        </ul>
                    </div>
                    <div class="Reportform_table">
                        <div class="label_name">查看特定商品的购买日志</div>
                        <ul class="inline date_inline">
                            <form action="SalespersonServlet?action=search_given_product_p_record" method="post" style="display: inline">
                                商品ID：<input type="number" name="pro_id" min="0">
                                <input type="submit" value="提交"  class="Export_btn"/>
                            </form>
                        </ul>
                    </div>
                    <div class="Reportform_table">
                        <div class="label_name">查看特定类别的销售状况</div>
                        <ul class="inline date_inline">
                            <form action="SalespersonServlet?action=search_given_category" method="post" style="display: inline">
                                类别ID：<input type="number" name="cat_id" min="0">
                                <input type="submit" value="提交"  class="Export_btn"/>
                            </form>
                        </ul>
                    </div>
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
