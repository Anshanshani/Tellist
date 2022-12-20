<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生个人信息页面</title>
</head>
<body>
<!-- 页面顶部-->
<%@include file="_header.jsp" %>
    <!--个人信息头部-->
    <br>
    <div class="rightsidebar_box rt">
        <div class="rs_header">
            <span class="rs_header_active"><a href="${pageContext.request.contextPath }/web/studentpersonage.jsp">我的信息</a></span>
            <span><a href="${pageContext.request.contextPath }/web/studentpersonal_password.jsp">安全管理</a></span>
            <span><a href="${pageContext.request.contextPath }/web/friend.jsp">好友</a></span>
        </div>

        <!--个人信息具体内容 -->
                    <span>
 <!-- 从request域中获取msg属性 -->
 <%-- <%=request.getAttribute("msg")==null?"": request.getAttribute("msg")%> --%>
 <font color="red">${message }</font>
 </span>
         <form action="http://localhost/StudentPhone/changeServlet" method="post">
       
        <div class="rs_content">
	            <!--用户ID-->
	            <input type="hidden" name="id" value="${user.id }">
	             <!--用户名-->
            <div class="rs_content_job">
                <span class="same">职业：</span>
                <!--已登录的用户职业-->
                <span class="same rs_job">${user.job }</span>
            </div>
            <!--用户名-->
            <div class="rs_content_username">
                <span class="same">用户名：</span>
                <!--已登录的用户名-->
               
                <input autocomplete required minlength="6" maxlength="9" name="rs_username" id="rs_username" value="${user.username }" />
                <span class="change_username same_click">更改用户名</span>
                <span class="msg-default hidden">用户名长度在6到9位之间</span>
            </div>
            <!--性别-->
            <div class="rs_content_sex">
                <span class="same">性别：</span>
               <input type="radio" name="rs_gender" value="男"
               ${user.gender=="男"?"checked='checkde'":"" }>男
               <input type="radio" name="rs_gender" value="女"
               ${user.gender=="女"?"checked='checkde'":"" }>女
            </div>
            <!--绑定手机号-->
            <div class="rs_content_tel">
                <span  class="same">绑定手机号：</span>
                <input pattern="(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$" required type="text" name="rs_phone" id="rs_phone" value="${user.phone }"/>
                <span class="msg-default hidden">请输入合法的手机号</span>
            </div>
             <!--地址-->
            <div class="rs_content_address">
                <span  class="same">地址：</span>
                <input type="text" name="rs_address" id="rs_address" value=""/>
                <span class="change_address same_click">更改地址</span>
            </div>
            <!--保存按钮-->
            <div>
            <input class="save" type="submit" value="保存更改"></input>
            </div>
        </div>
        </form>
    </div>
</div>
<!-- 页面底部-->
<%@include file="_footer.jsp" %>
<script>
  /*1.对用户名进行验证*/
  rs_username.onblur = function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '用户名不能为空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('用户名不能为空');
    }else if(this.validity.tooShort){
      this.nextElementSibling.innerHTML = '用户名不能少于6位';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('用户名不能少于6位');
    }else {
      this.nextElementSibling.innerHTML = '用户名格式正确';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');
      var data =document.getElementById("rs_username").value;
      if(!data){   //用户没有输入任何内容
        return;
      }
      /**发起异步GET请求，询问服务器用户名是否已经存在**/
      //1 创建xhr
      var xhr = new XMLHttpRequest();
      console.log(xhr);
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
      xhr.open('GET','/checkUsername.html?username='+data, true);
      //4 发送请求
      xhr.send(null);

      //处理响应消息
      function doResponse(xhr){
        console.log('开始处理响应数据');
        //console.log(xhr);
        if(xhr.responseText==='yes'){
          alert('该用户名已被占用');
        }else if(xhr.responseText==='no'){
          alert('该用户名可以使用');
        }else {
          alert(xhr.responseText);
        }
      }
      xhr.open('GET','/checkUsername?username='+data, true);
    }
  }
 
  rs_username.onfocus = function(){
    this.nextElementSibling.innerHTML = '用户名长度在6到9位之间';
    this.nextElementSibling.className = 'msg-default';
  }
  
  /*2.对邮箱地址进行验证*/
  rs_email.onblur = function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '邮箱不能为空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('邮箱不能为空');
    }else if(this.validity.typeMismatch){
      this.nextElementSibling.innerHTML = '邮箱格式不正确';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('邮箱格式不正确');
    }else {
      this.nextElementSibling.innerHTML = '邮箱格式正确';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');

      var data =document.getElementById("rs_email").value;
      if(!data){   //用户没有输入任何内容
        return;
      }
      /**发起异步GET请求，询问服务器用户邮箱是否已经存在**/
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
      xhr.open('GET','/checkEmail.html?email='+data, true);
      //4 发送请求
      xhr.send(null);

      //处理响应消息
      function doResponse(xhr){
        console.log('开始处理响应数据');
        //console.log(xhr);
        if(xhr.responseText==='yes'){
          alert('该邮箱已被占用');
        }else if(xhr.responseText==='no'){
          alert('该邮箱可以使用');
        }else {
          alert(xhr.responseText);
        }
      }
      xhr.open('GET','/checkEmail?email='+data, true);
    }
  }
  rs_email.onfocus = function(){
    this.nextElementSibling.innerHTML = '请输入合法的邮箱地址';
    this.nextElementSibling.className = 'msg-default';
  }
  /*3.对手机号进行验证*/
 rs_phone.onblur = function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '手机号不能为空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('手机号不能为空');
    }else if(this.validity.patternMismatch){
      this.nextElementSibling.innerHTML = '手机号格式不正确';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('手机号格式不正确');
    }else {
      this.nextElementSibling.innerHTML = '手机号格式正确';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');

      var data =document.getElementById("rs_phone").value;
      if(!data){   //用户没有输入任何内容
        return;
      }
      /**发起异步GET请求，询问服务器用户手机号是否已经存在**/
      //1 创建xhr
      var xhr = new XMLHttpRequest();
      //2 监听状态改变 01234，4最有价值
      xhr.onreadystatechange = function(){
        if(xhr.readyState===4){//响应完成
          if(xhr.status===200){
            console.log('响应完成且成功');
            doResponse(xhr);
          }
          else{
            console.log('响应完成但有问题');
          }
        }
      }
      //3 打开连接
      xhr.open('GET','/checkPhone.html?phone='+data, true);
      //4 发送请求
      xhr.send(null);

      //处理响应消息
      function doResponse(xhr){
        console.log('开始处理响应数据');
        //console.log(xhr);
        if(xhr.responseText==='yes'){
          alert('该号码已被占用');
        }else if(xhr.responseText==='no'){
          alert('该号码可以使用');
        }else {
          alert(xhr.responseText);
        }
      }
      xhr.open('GET','/checkPhone?phone='+data, true);
    }
  }
 rs_phone.onfocus = function(){
    this.nextElementSibling.innerHTML = '请输入合法的手机号';
    this.nextElementSibling.className = 'msg-default';
  }
</script>
</body>
</html>