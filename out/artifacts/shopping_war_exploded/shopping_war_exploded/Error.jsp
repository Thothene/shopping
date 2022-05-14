<%@ page import="com.wei.entity.User" %>
<%@ page import="com.wei.entity.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2022/3/25
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>弹窗界面</title>
</head>
<body>
<%
    HttpSession httpSession = request.getSession();
    User user = (User) httpSession.getAttribute("user");
    List<Product> pro_list = (List<Product>) httpSession.getAttribute("recommend_product");
    if (user == null){
        String msg = "当前用户未登陆，请登陆后查看！";
        response.getWriter().write("<script language=javascript>alert('" +msg+ "');window.location='index.jsp'</script>");
    }
    else if(pro_list.isEmpty()){
        String msg = "当前用户未有浏览/购买记录！";
        response.getWriter().write("<script language=javascript>alert('" +msg+ "');window.location='Products.jsp'</script>");
    }
%>

</body>
</html>
