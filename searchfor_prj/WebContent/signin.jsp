<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@include file="/layout/header.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
	<style>
		.log1{
			color:#FF8C00;
			font-size:35px;
			text-align:center;
			font-family:黑体;
			font-weight:bold;
		}
		
		.bian{
			width:350px;
			height:380px;
			border:1px solid #A9A9A9;
			margin:0 auto;
		}
		
		.form{
			width:350px;
			height:60px;
			border:none;
			margin-top:25px
		}
		
		.form1{
			width:350px;
			height:40px;
			border:none;
			margin-top:10px;
		}
		.mark{
			color:red;
			font-size:20px;
			display:inline-block;
			text-align:center;
			height:30px;
			line-height:30px;
			margin-left:20px
		}
		
		.phone{
			font-size:15px;
			color:grey;
		}
		
		.phonenum{
			height:40px;
			width:180px;
			font-size:16px;
			margin-left:2%;
			border-radius:0 2px 2px 0;
		}
		
		#ul{
			list-style:none;
			height:20px;
			width:170px;
			margin-left:160px ;
			margin-top:10px;
			
		}
		
		.ul-li{
			float:left;
			width:80px;
			text-align:center;
		}
		
		#ul a{
			text-decoration:none;
			color:cornflowerblue;
		}
		
		#sub{
			width:250px;
			height:40px;
			background-color:#FF8C00;
			border-radius:8px;
			border:0;
			margin:0 auto;
			display:block;
			color:#fff;
			margin-bottom:30px;
			font-size:15px;
			font-weight:bold;
		}
	</style>
</head>
<body>
		<div class="container">
			
			<!-- 上方链接 -->
			<div class="topmess">
				<a href="index.jsp">首页</a>
				<span>></span>
				<a style="color:red">登录</a>
			</div>
			
			<div class="log1">用户登录</div>
			
			<!-- 边框 -->
			<div class="bian" style="position:relative;margin-bottom:40px;position:relative">
				<!-- 正文：登录部分 -->
				<div class="login">
					<form action="LoginWebServlet" method="post" style="margin-top:50px">
						
						<div class="form">
							<label><span class="mark">&nbsp;&nbsp;&nbsp;*</span><span  class="phone">手机号</span></label>
							<input type="text" id="username" name="phonenum" placeholder="&nbsp;&nbsp;&nbsp;请输入手机号" class="phonenum" required="required">
						</div>
						
						<div class="form" style="margin-left:3px">
							<label><span class="mark">*</span><span class="phone">登陆密码</span></label>
							<input type="password" id="password" name="password" placeholder="&nbsp;&nbsp;&nbsp;请输入密码" class="phonenum" required="required">
						</div>
						
						<div class="form" >
							<ul id="ul">
								<li class="ul-li">
									<a href="forgetpwd.jsp" id="forget">忘记密码</a>
								</li>
									<div style="border:1px solid;display:inline-block;float:left;height:12px;margin-top:5px"></div>
								<li class="ul-li">
									<a href="signup.jsp" id="signup">注册账号</a>
								</li>
							</ul>
						</div>
						<div class="form1">
							<input type="submit" id="sub" onClick="login()" value="确认登录"/>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script language="javaScript">
			
			function login(){
				var username = document.getElementById("username");
				var password = document.getElementById("password");
				
					if(username.value == ""){
						alert("请输入手机号！");
					}else if(password.value == ""){
						alert("请输入密码！");
					}
			}
		</script>
<%@include file="/layout/footer.jsp" %>
</body>
</html>