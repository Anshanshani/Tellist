<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生通讯录页面顶部</title>
<link href="${pageContext.request.contextPath }/css/_header.css" rel="stylesheet"/>
</head>
<!-- 页面顶部-->
<header id="top" class="fixed_nav">
    <div class="rt">
    <font color="blue"><h1>学生通讯录</h1></font>
        <ul class="lf">
        <c:if test="${user.username==null }">
<li><a href="${pageContext.request.contextPath }/web/register.jsp">注册</a><b>|</b></li>
<li><a href="${pageContext.request.contextPath }/web/login.jsp">登录</a></li>
</c:if>
<c:if test="${user.username!=null }">
<c:if test="${user.job=='student'}">
<li><a href="${pageContext.request.contextPath }/web/studentpersonage.jsp">${user.username}</a><b>|</b></li>
<li><a href="http://localhost/StudentPhone/logoutServlet">登出</a></li>
</c:if>
<c:if test="${user.job=='admin'}">
<li><a href="${pageContext.request.contextPath }/web/adminpersonage.jsp">${user.username}</a><b>|</b></li>
<li><a href="http://localhost/StudentPhone/logoutServlet">登出</a></li>
</c:if>
</c:if>
        </ul>
    </div>
</header>
</html>