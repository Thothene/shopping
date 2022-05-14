<%@ page import="com.wei.dao.SalespersonDao" %>
<%@ page import="com.wei.dao.CategoryDao" %>
<%@ page import="com.wei.entity.Salesperson" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.wei.entity.Category" %>
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
    <title>可视化页面</title>
    <style>
        .box{
            width: 1000px;
            height: 600px;
            background-color: rgb(255, 255, 255);
        }
    </style>
</head>

<body>
<%
    HttpSession httpSession = request.getSession();
    SalespersonDao salespersonDao = new SalespersonDao();
    CategoryDao categoryDao = new CategoryDao();
    Salesperson salesperson = (Salesperson) httpSession.getAttribute("salesperson");
    httpSession.removeAttribute("salesperson");
    List<Map<String, Float>> month_list = (List<Map<String, Float>>) httpSession.getAttribute("month_list");
    List<Float> m_list = new ArrayList<>();
    for (int i = month_list.size(); i >= 1; --i){
        m_list.add(month_list.get(i-1).get(String.valueOf(i)));
    }
%>
<head>
    <div id="header_top">
        <div id="top">
            <div class="Inside_pages">
                <div class="Collection"><a href="AdministratorServlet?action=logout" class="green">管理员登出</a></div>
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
        <!--内容详细-->
        <div class="title_style">统计销售员数据</div>
        <div class="content_style">
            <!--报表管理-->
            <div class="Reportform_style">
                <div class="box" style="margin: auto"></div>
                <script src="js/echarts.js"></script>
                <script>
                    //3.初始化实例对象 echarts.init(dom容器)
                    var myChart = echarts.init(document.querySelector(".box"));
                    //4.指定配置项和数据
                    var option = {
                        color: ['#56A3F1', '#FFE434', '#67F9D8', '#FF917C'],
                        title: {
                            text: '销售员ID/名称：<%=salesperson.getSal_id()%>/<%=salesperson.getSal_name()%>'
                        },
                        legend: {},
                        //工具箱组件，可以另存为图片等功能
                        toolbox: {
                            feature: {
                                saveAsImage: {}
                            }
                        },
                        radar: [
                            {
                                indicator:
                                    [
                                        { text: '十二月'},
                                        { text: '十一月'},
                                        { text: '十月'},
                                        { text: '九月'},
                                        { text: '八月'},
                                        { text: '七月'},
                                        { text: '六月'},
                                        { text: '五月'},
                                        { text: '四月'},
                                        { text: '三月'},
                                        { text: '二月'},
                                        { text: '一月'}
                                    ],
                                center: ['25%', '50%'],
                                radius: 190,
                                startAngle: 90,
                                // 控制同心圆个数
                                splitNumber: 6,
                                shape: 'circle',
                                axisName: {
                                    formatter: '【{value}】',
                                    color: '#428BD4'
                                },
                                splitArea: {
                                    areaStyle: {
                                        color: ['#77EADF', '#26C3BE', '#64AFE9', '#428BD4'],
                                        shadowColor: 'rgba(0, 0, 0, 0.2)',
                                        shadowBlur: 10
                                    }
                                },
                                axisLine: {
                                    lineStyle: {
                                        color: 'rgba(211, 253, 250, 0.8)'
                                    }
                                },
                                splitLine: {
                                    lineStyle: {
                                        color: 'rgba(211, 253, 250, 0.8)'
                                    }
                                }
                            },
                            {

                                indicator:
                                    [
                                        { text: '十二月'},
                                        { text: '十一月'},
                                        { text: '十月'},
                                        { text: '九月'},
                                        { text: '八月'},
                                        { text: '七月'},
                                        { text: '六月'},
                                        { text: '五月'},
                                        { text: '四月'},
                                        { text: '三月'},
                                        { text: '二月'},
                                        { text: '一月'}
                                    ],
                                center: ['75%', '50%'],
                                radius: 190,
                                axisName: {
                                    color: '#fff',
                                    backgroundColor: '#666',
                                    borderRadius: 3,
                                    padding: [3, 3]
                                }
                            }
                        ],
                        series: [
                            {
                                type: 'radar',
                                emphasis: {
                                    lineStyle: {
                                        width: 4
                                    }
                                },
                                data: [
                                    <%
                                    List<Category> cat_list = categoryDao.select_by_cat_sal_id(salesperson.getSal_id());
                                    for (Category category : cat_list){
                                        List<Map<String, Float>> temp_list1 = salespersonDao.count_every_month_category_money(salesperson.getSal_id(), category.getCat_id());
                                        List<Float> temp_list2 = new ArrayList<>();
                                        for (int i = temp_list1.size(); i >= 1; --i){
                                            temp_list2.add(temp_list1.get(i-1).get(String.valueOf(i)));
                                        }
                                    %>
                                    {
                                        value: <%=temp_list2%>,
                                        name: "种类ID：" + <%=category.getCat_id()%>
                                    },
                                    <%
                                    }
                                    %>
                                ]
                            },
                            {
                                type: 'radar',
                                radarIndex: 1,
                                data: [
                                    {
                                        // 红线
                                        value: <%=m_list%>,
                                        name: '销售员每月总销售额',
                                        areaStyle: {
                                            color: new echarts.graphic.RadialGradient(0.1, 0.6, 1, [
                                                {
                                                    color: 'rgba(255, 145, 124, 0.1)',
                                                    offset: 0
                                                },
                                                {
                                                    color: 'rgba(255, 145, 124, 0.9)',
                                                    offset: 1
                                                }
                                            ])
                                        }
                                    }
                                ]
                            }
                        ]
                    };
                    //5.将配置项设置给echarts实例对象，使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                    // 当我们浏览器缩放的时候，图表也等比例缩放
                    window.addEventListener("resize", function() {
                        // 让我们的图表调用 resize这个方法
                        myChart.resize();
                    });

                </script>
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
