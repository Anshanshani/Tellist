<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>通讯录好友</title>
</head>
<body>
<!-- 页面顶部-->
<header id="top" >
  <div class="top" >
    <span><font color="red">通讯录好友</font></span>
  </div>
</header>   
           <div class="parent">
  <div class="container">
    <div class="panel rt">
      <form id="form-register" method="post" action="http://www.xuezimall.com/sp/friendServlet">
        <div class="txt"> 
  <span>
           <input autocomplete required minlength="6" maxlength="9" type="text" placeholder="请输入用户名" autofocus name="username1" id="username1"/>
              <button>添加通讯录好友</button>
           </span>
          
     <div>
      </form>
      <form id="form-register" method="post" action="http://www.xuezimall.com/sp/searchServlet">
        <div class="txt"> 
  <span>
           <input autocomplete required minlength="6" maxlength="9" type="text" placeholder="请输入用户名" autofocus name="username2" id="username2"/>
              <button>查询通讯录好友</button>
           </span>
          
     <div>
      </form>
   
     
    
      
           <span>
 <!-- 从request域中获取msg属性 -->
 <%-- <%=request.getAttribute("msg")==null?"": request.getAttribute("msg")%> --%>
 <font color="red">${message }</font>
 </span>
      </div>
  </div>
</div>
<!-- 页面底部 -->
</body>
</html>