<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="edu.jit.beans.User" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员个人信息页面</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script>
	function submit1(){
		document.form1.action ="http://localhost/StudentPhone/changestudent"
			document.form1.submit(); 
		
	}
	function submit2(){
		document.form1.action ="http://localhost/StudentPhone/addstudent"
			document.form1.submit(); 
	}
	function submit3(){
		document.form2.action ="http://localhost/StudentPhone/querystudent"
			document.form2.submit(); 
	}
	function submit4(){
		document.form2.action ="http://localhost/StudentPhone/delstudent"
			document.form2.submit(); 
	}
</script>
</head>
<body>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3 class="text-center">
				管理员功能页面
			</h3>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<form class="form-horizontal" role="form"  name="form1" >
				<div class="form-group">
					 <label for="inputPassword3" class="col-sm-2 control-label">职业</label>
					<div class="col-sm-10">
					<div class="radio">
							<label> <input type="radio" name="job" value="student"
                       >student</label>
						<label>	 <input type="radio" name="job" value="admin"
                       }>admin</label>
						</div>
	
               
					</div>
				</div>
				<div class="form-group">
					 <label for="inputPassword3" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="rsUsername" name="rs_username" />
					</div>
				</div>
				<div class="form-group">
					 <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="rsPassword" name="rs_password" />
					</div>
				</div>
				<div class="form-group">
					 <label for="inputPassword3" class="col-sm-2 control-label">性别</label>
					<div class="col-sm-10">
					
					
					<div class="radio">
							<label> <input type="radio" name="rs_gender" value="男"
                       ${user.gender=="男"?"checked='checkde'":"" }>男</label>
						<label>	 <input type="radio" name="rs_gender" value="女"
                       ${user.gender=="女"?"checked='checkde'":"" }>女</label>
						</div>
               
					</div>
				</div>
				<div class="form-group">
					 <label for="inputPassword3" class="col-sm-2 control-label">手机号</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="rsPhone" name="rs_phone" />
					</div>
				</div>
				<div class="form-group">
					 <label for="inputPassword3" class="col-sm-2 control-label">地址</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="rsAddress" name="rs_address" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 
						 <button type="submit" class="btn btn-default" onClick=submit2();>增加联系人信息</button>
						 <button type="submit" class="btn btn-default" onClick=submit1();>修改联系人信息</button>
						             <span>
 <!-- 从request域中获取msg属性 -->
 <%-- <%=request.getAttribute("msg")==null?"": request.getAttribute("msg")%> --%>
 <font color="red">${a1 }</font>
 </span>
					</div>
				</div>
			</form>
			<form class="form-horizontal" role="form" name="form2" METHOD="POST">
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="rsusername" name="rsusername" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 <button type="submit" class="btn btn-default" onClick=submit3();>查询联系人信息</button>
						 <button type="submit" class="btn btn-default" onClick=submit4();>删除联系人信息</button>
						             <span>
 <!-- 从request域中获取msg属性 -->
 <%-- <%=request.getAttribute("msg")==null?"": request.getAttribute("msg")%> --%>
 <font color="red">${a2 }</font>
 </span>
					</div>
				</div></form>
				<div class="row clearfix">
		<div class="col-md-1 column">
		</div>
		<div class="col-md-11 column">
			<table class="table">
				<thead>
					<tr>
						<th>
							职业
						</th>
						<th>
							用户名
						</th>
						<th>
							密码
						</th>
						<th>
							性别
						</th>
						<th>
							手机号
						</th>
						<th>
							地址
						</th>
					</tr>
				</thead>
				<tbody>
				<% 
				ArrayList stumess = (ArrayList) session.getAttribute("stumessage");
				 session.removeAttribute("stumessage");
				if(stumess!=null){
					//下面执行成功的地方 有数据
					for(int i=0;i<stumess.size();i++){
						User a=(User)stumess.get(i);
						
						%>
						
						
						<tr class="info">
						<td>
							<%=a.getJob() %>
						</td>
						<td>
							<%=a.getUsername() %>
						</td>
						<td>
							<%=a.getPassword() %>
						</td>
						<td>
							<%=a.getGender() %>
						</td>
						<td>
							<%=a.getPhone() %>
						</td>
						<td>
							<%=a.getAddress() %>
						</td>
					</tr>
						<%} }%>
					
			
						
			
				</tbody>
			</table>
		</div>
	</div>
			
		</div>
	</div>
	
</div>
</body>
</html>