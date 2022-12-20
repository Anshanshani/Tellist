<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生通讯录注册页面</title>
</head>
<body>
<!-- 页面顶部-->
<header id="top" >
  <div class="top" >
    <span><font color="red">欢迎注册</font></span>
  </div>
</header>   
           <div class="parent">
  <div class="container">
    <div class="panel rt">
      <form id="form-register" method="post" action="${pageContext.request.contextPath }/registerServlet">
        <div class="txt"> 
  <span>            
   注册
  </span>
            <span>
              <a href="${pageContext.request.contextPath }/web/login.jsp">直接登录</a>
           </span>
           <span>
 <!-- 从request域中获取msg属性 -->
 <%-- <%=request.getAttribute("msg")==null?"": request.getAttribute("msg")%> --%>
 ${message }
 </span>
        </div>
        <div class="form-group">
职业：
<select name="job">
      <option  selected="selected">==请选择==</option>
      <option value="student">学生</option>
      <option value="admin">管理员</option>
 </select>
<br/>
          <label for="username">用户名：</label>
          <input autocomplete required minlength="6" maxlength="9" type="text" placeholder="请输入用户名" autofocus name="username" id="username"/>
          <span class="msg-default">用户名长度在6到9位之间</span>
        </div>
        <div class="form-group">
          <label for="password">登录密码：</label>
          <input required type="password" minlength="6" maxlength="12" placeholder="请输入密码" name="password" autofocus id="password"/>
          <span class="msg-default hidden">密码长度在6到12位之间</span>
        </div>
		 <div class="form-group">
          <label for="passwordconfirm">确认密码：</label>
          <input required type="password" minlength="6" maxlength="12" placeholder="请确认密码" name="passwordconfirm" autofocus id="passwordconfirm"/>
          <span class="msg-default hidden">密码长度在6到12位之间</span>
        </div>
        <div class="form-group">
          <label for="phone">手机号：</label>
          <input id="phone" name="phone" placeholder="请输入您的手机号" pattern="(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$" required type="text" />
          <span class="msg-default hidden">请输入合法的手机号</span>
        </div>
        <div class="form-group">
          <label></label>
          <input type="submit" value="提交注册信息" id="bt-register"/>
        </div>
      </form>
      </div>
  </div>
</div>
<!-- 页面底部 -->
<script>
  /*1.对用户名进行验证*/
  username.onblur = function(){
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
      var data =document.getElementById("username").value;
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
 
  username.onfocus = function(){
    this.nextElementSibling.innerHTML = '用户名长度在6到9位之间';
    this.nextElementSibling.className = 'msg-default';
  }
  password.onfocus = function(){
    this.nextElementSibling.innerHTML = '密码长度在6到12位之间';
    this.nextElementSibling.className = 'msg-default';
  }
  password.onblur = function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '密码不能为空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('密码不能为空');
    }else if(this.validity.tooShort){
      this.nextElementSibling.innerHTML = '密码长度在尽量别少于6位';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('密码长度在尽量别少于6位');
    }else {
      this.nextElementSibling.innerHTML = '密码格式正确';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');
    }
  }
  

  passwordconfirm.onfocus = function(){
    this.nextElementSibling.innerHTML = '密码长度在6到12位之间';
    this.nextElementSibling.className = 'msg-default';
  }
  passwordconfirm.onblur = function(){
    if(this.validity.valueMissing){
      this.nextElementSibling.innerHTML = '密码不能为空';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('密码不能为空');
    }else if(this.validity.tooShort){
      this.nextElementSibling.innerHTML = '密码长度在尽量别少于6位';
      this.nextElementSibling.className = 'msg-error';
      this.setCustomValidity('密码长度在尽量别少于6位');
    }else {
      this.nextElementSibling.innerHTML = '密码格式正确';
      this.nextElementSibling.className = 'msg-success';
      this.setCustomValidity('');
    }
  }
  /*3.对手机号进行验证*/
  phone.onblur = function(){
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

      var data =document.getElementById("phone").value;
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
  phone.onfocus = function(){
    this.nextElementSibling.innerHTML = '请输入合法的手机号';
    this.nextElementSibling.className = 'msg-default';
  }
</script>
</body>
</html>