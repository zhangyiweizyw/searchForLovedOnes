<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@include file="/layout/header.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<style>
	.log1{
			color:#FF8C00;
			font-size:35px;
			text-align:center;
			font-family:黑体;
			font-weight:bold;
			margin-top:20px;
	}
	
	.bian{
			width:550px;
			height:450px;
			border:1px solid #A9A9A9;
			margin:0 auto;
			border:none;
	}
	
	.form{
			width:550px;
			height:60px;
			border:none;
			margin-top:5px
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
		
		.user{
			font-size:15px;
			color:grey;
		}
		
		.form1{
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
			margin-left:200px ;
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
		
		.code{
			margin-left:10px
		}
		
</style>
</head>
<body>
	<div class="container">
		
		<!-- 上方链接 -->
		<div class="topmess">
			<a href="index.jsp">首页</a>
			<span>></span>
			<a  style="color:red">注册</a>
		</div>
	
		<div class="log1">用户注册</div>
		
		<!-- 边框 -->
		<div class="bian" style="position:relative;margin-bottom:40px;position:relative">
			<!-- 正文：注册部分 -->
			<div class="register">
				<form action="RegisterWebServlet" method="post" style="margin-top:50px">
					<div class="form">
						<label style="width:120px"><span class="mark">*</span><span class="user">用户名/昵称</span></label>
						<input type="text" name="username" placeholder="&nbsp;&nbsp;&nbsp;请输入用户名" class="form1"  required="required"  maxlength=8>
					</div>
					
					<div class="form">
						<label style="width:120px"><span class="mark">&nbsp;&nbsp;&nbsp;&nbsp;*</span><span class="user">设置密码</span></label>
						<input type="password" name="password" placeholder="&nbsp;&nbsp;&nbsp;请输入密码" class="form1" required="required">
					</div>
					
					<div class="form">
						<label style="width:120px"><span class="mark">&nbsp;&nbsp;&nbsp;&nbsp;*</span><span class="user">用户类型</span></label>
						<input type="radio" name="usertype" value="寻亲者" class="findtype" style="margin-left:18px">寻亲者
						<input type="radio" name="usertype" value="志愿者"  style="margin-left:28px">志愿者
					</div>
					<div class="form">
						<label style="width:120px"><span class="mark">&nbsp;&nbsp;&nbsp;&nbsp;*</span><span class="user">联系方式</span></label>
						<input type="text" name="usertel" id="user_phone" placeholder="&nbsp;&nbsp;&nbsp;请输入注册手机号" class="form1" required="required" pattren="(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$" style="300px">
						<input type="button" class="code" onClick="yanzheng()" value="获取短信验证码" style="width:110px;height:35px;background-color:#FF8C00;border-radius:8px;color:white;border:none">
					</div>
					
					<div class="form">
						<label style="width:120px"><span class="mark">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*</span><span class="user">验证码</span></label>
						<input type="text" name="code" class="form1" placeholder="&nbsp;&nbsp;&nbsp;请输入手机验证码" style="width:300px">
					</div>
					
					<div class="form" style="">
						<div style="float:left;width:50px;height:50px;margin-left:25%">
							<input name="save" id="save" type="checkbox" onClick="save_ck(this);" checked required style="margin-left:30%;width:20px;height:20px;margin-top:10px">
						</div>
						
						<div style="float:left;width:300px;height:50px;margin-top:10px">
							<span style="color:grey;font-size:13px;">我同意</span><span style="color:#3478f6;font-size:13px;"><a href="" style="text-decoration:none;color:#3478f6">《亲逢服务条款》</a></span>
						</div>
					</div>
					
					<div class="form">
						<input type="submit" id="sub" value="确认">
					</div>
					
					<div class="form">
						<ul id="ul">
							<li class="ul-li" style="color:grey">已有账号？</li>
							<li class="ul-li"><a href="signin.jsp" style="color:#3478f6">登录</a></li>
						</ul>
					</div>
				</form>
			</div>
		</div>
	</div>
<%@include file="/layout/footer.jsp"  %>
</body>
</html>