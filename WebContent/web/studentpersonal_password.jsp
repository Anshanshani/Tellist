<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 页面顶部-->
<%@include file="_header.jsp" %>
<head lang="en">
    <meta charset="UTF-8">
    <title>安全管理</title>
</head>
<body>
<br>
    <!--个人信息头部-->
    <div class="rightsidebar_box rt">
        <div class="rs_header">
            <span ><a href="${pageContext.request.contextPath }/web/studentpersonage.jsp">我的信息</a></span>
            <span class="rs_header_active"><a href="${pageContext.request.contextPath }/web/studentpersonal_password.jsp">安全管理</a></span>
        </div>
<!-- 从request域中获取msg属性 -->
 <%-- <%=request.getAttribute("msg")==null?"": request.getAttribute("msg")%> --%>
 <font color="red">${message }</font>
 </span>
        <!--安全管理 -->
         <form action="${pageContext.request.contextPath }/safeServlet" method="post">
        <div class="rs_content">
            <p class="change_password_title">更改密码</p>
                      <span>
 
            <!--用户ID-->
	            <input type="hidden" name="id" value="${user.id }">
            <div class="new_password">
                <span class="word">输入旧密码：</span><input required type="password" minlength="6" maxlength="12" type="password" name="oldPassword" id="oldPassword"/><span class="change_hint"></span>
                <span class="msg-default hidden">请输入正确的旧密码</span>
            </div>
            <div class="new_password">
                <span class="word">输入新密码：</span><input required type="password" minlength="6" maxlength="12" type="password" name="newPassword" id="newPassword"/><span class="change_hint"></span>
            </div>
            <div class="confirm_password">
                <span class="word">确认新密码：</span><input required type="password" minlength="6" maxlength="12" type="password" name="conNewPassword" id="conNewPassword"/><span class="confirm_hint"></span>
            </div>
            <button class="save_password">
                保存更改
            </button>
        </div>
        </form>
    </div>
</div>
<!-- 页面底部-->
<%@include file="_footer.jsp" %>
<script>
oldPassword.onblur = function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '旧密码不能为空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('旧密码不能为空');
    }else if(this.validity.tooShort){
      this.nextElementSibling.innerHTML = '旧密码不能少于6位';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('旧密码不能少于6位');
    }else {
      this.nextElementSibling.innerHTML = '旧密码格式正确';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');

      var data =document.getElementById("oldPassword").value;
      if(!data){   //用户没有输入任何内容
        return;
      }
      /**发起异步GET请求，询问服务器用户名是否已经存在**/
      //1 创建xhr
      var xhr = new XMLHttpRequest();
      //2 监听状态改变 01234，4最有价值
      xhr.onreadystatechange = function(){
        if(xhr.readyState===4){//响应完成
          if(xhr.status===200){
            console.log('响应完成且成功');
            doResponse(xhr);
          }
          else {
            console.log('响应完成但有问题');
          }
        }
      }
      //3 打开连接
      xhr.open('GET','/checkoldPassword.html?oldPassword='+data, true);
      //4 发送请求
      xhr.send(null);

      //处理响应消息
      function doResponse(xhr){
        console.log('开始处理响应数据');
        //console.log(xhr);
        if(xhr.responseText==='yes'){
          alert('旧密码正确');
        }else if(xhr.responseText==='no'){
          alert('旧密码错误');
        }else {
          alert(xhr.responseText);
        }
      }
      xhr.open('GET','/checkoldPassword?oldPassword='+data, true);
    }
  }
oldPassword.onfocus = function(){
    this.nextElementSibling.innerHTML = '请输入正确的旧密码';
    this.nextElementSibling.className = 'msg-default';
  }
  newPassword.onfocus = function(){
	    this.nextElementSibling.innerHTML = '新密码长度在6到12位之间';
	    this.nextElementSibling.className = 'msg-default';
	  }
  newPassword.onblur = function(){
	    if(this.validity.valueMissing){
	      this.nextElementSibling.innerHTML = '新密码不能为空';
	      this.nextElementSibling.className = 'msg-error';
	      this.setCustomValidity('新密码不能为空');
	    }else if(this.validity.tooShort){
	      this.nextElementSibling.innerHTML = '新密码长度在尽量别少于6位';
	      this.nextElementSibling.className = 'msg-error';
	      this.setCustomValidity('新密码长度在尽量别少于6位');
	    }else {
	      this.nextElementSibling.innerHTML = '新密码格式正确';
	      this.nextElementSibling.className = 'msg-success';
	      this.setCustomValidity('');
	    }
	  }
	  

  conNewPassword.onfocus = function(){
	    this.nextElementSibling.innerHTML = '确认密码长度在6到12位之间';
	    this.nextElementSibling.className = 'msg-default';
	  }
  conNewPassword.onblur = function(){
	    if(this.validity.valueMissing){
	      this.nextElementSibling.innerHTML = '确认密码不能为空';
	      this.nextElementSibling.className = 'msg-error';
	      this.setCustomValidity('确认密码不能为空');
	    }else if(this.validity.tooShort){
	      this.nextElementSibling.innerHTML = '确认密码长度在尽量别少于6位';
	      this.nextElementSibling.className = 'msg-error';
	      this.setCustomValidity('确认密码长度在尽量别少于6位');
	    }else {
	      this.nextElementSibling.innerHTML = '确认密码格式正确';
	      this.nextElementSibling.className = 'msg-success';
	      this.setCustomValidity('');
	    }
	  }
</script>
</html>