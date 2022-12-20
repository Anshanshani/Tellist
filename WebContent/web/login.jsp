<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生通讯录登入界面</title>
</head>
<body>
<!-- 页面顶部-->
<header id="top">
    <div class="top">
        <span><font color="red">欢迎登入</font></span>
    </div>
</header>
 <form id="login-form" method="post" name="form1" action="${pageContext.request.contextPath }/loginServlet">
职业：
<select name="job">
      <option  selected="selected">==请选择==</option>
      <option value="student">学生</option>
      <option value="admin">管理员</option>
 </select>
  <div class="txt">
                <p>
					登录学生通讯录<span><a href="${pageContext.request.contextPath }/web/register.jsp">新用户注册</a></span>
					<span>
					<font color="red">${message }</font>
					</span>
                </p>
                <div class="text">
                    <input type="text" placeholder="请输入您的用户名" name="username" id="username" required>
                </div>
                
                <div class="text">
                    <input type="password" id="password" placeholder="请输入您的密码" name="password" required minlength="6" maxlength="15">
                </div>
                <input class="button_login" type="submit" value="登录" id="bt-login" onclick="Save()"/>
            </div>
 </form>
 <!--错误提示-->
<div id="showResult"></div>
 <!-- 页面底部-->
 <script>
    $("#username").blur(function(){
        var data = $("#username").val();
        if (data == null || data == "") {
            $("#showResult").text("用户名不能为空！");
            $("#showResult").css("color","red");
            return false;
        }
        $.ajax({
            type:"POST",
            url:"/checkUsername.html",
            data:"username="+data,
            beforeSend:function(XMLHttpRequest)
            {
                $("#showResult").text("正在查询");

            },
            success:function(message)
            {
                if(message ==="yes"){
                    $("#showResult").text("该用户名可以被使用");
                }else if(message === 'no'){
                    $("#showResult").text("该用户名不存在");
                    $("#showResult").css("color","red");
                }else {
                    $("#showResult").text("系统异常！");
                    $("#showResult").css("color","red");
                }
            },
            error:function()
            {
                //错误处理
            }
        });
    });
</script>
</body>
</html>