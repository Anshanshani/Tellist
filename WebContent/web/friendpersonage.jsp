<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>通讯录好友信息页</title>
</head>
<body>
 <span>通讯录好友用户名</span>
 <input autocomplete required minlength="6" maxlength="9" type="text"  autofocus name="username" id="username" value="${user1.username }"/>
 <br>
 <span>通讯录好友电话号码</span>
  <input type="text" autofocus name="phone" id="phone" value="${user1.phone }"/>
  <br>
   <span>通讯录好友电话地址</span>
  <input type="text"  autofocus name="address" id="address" value="${user1.address }"/>
  <br>
</body>
</html>