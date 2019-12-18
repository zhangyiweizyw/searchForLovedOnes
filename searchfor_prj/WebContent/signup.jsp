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
			height:550px;
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
						<input type="text" id="usertype" name="username" placeholder="&nbsp;&nbsp;&nbsp;请输入用户名" class="form1"  required="required"  maxlength=8>
					</div>
					
					<div class="form">
						<label style="width:120px"><span class="mark">&nbsp;&nbsp;&nbsp;&nbsp;*</span><span class="user">设置密码</span></label>
						<input type="password" id="password" name="password" placeholder="&nbsp;&nbsp;&nbsp;请输入密码" class="form1" required="required">
					</div>
					
					<div class="form">
						<label style="width:120px"><span class="mark">&nbsp;&nbsp;&nbsp;&nbsp;*</span><span class="user">用户类型</span></label>
						<input type="radio" name="usertype" value="寻亲者" class="findtype" style="margin-left:18px" id="usertype" checked>寻亲者
						<input type="radio" name="usertype" value="志愿者"  style="margin-left:28px" id="usertype">志愿者
					</div>
					
					<div class="form">
						<label style="width:120px"><span class="mark">&nbsp;&nbsp;&nbsp;&nbsp;*</span><span class="user">电子邮箱</span></label>
						<input type="text" id="useremail" name="useremail" placeholder="&nbsp;&nbsp;&nbsp;请输入电子邮箱" class="form1" required="required">
					</div>
					
					<div class="form">
						<label style="width:120px"><span class="mark">&nbsp;&nbsp;&nbsp;&nbsp;*</span><span class="user">联系方式</span></label>
						<input type="text" id="phonenum" name="phonenum" class="form1" placeholder="&nbsp;&nbsp;&nbsp;请输入注册手机号" style="width:300px" required="required" pattren="(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$">
					</div>
					
					<div class="form">
						<label style="width:120px"><span class="mark">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*</span><span class="user">验证码</span></label>
						<input type="text" name="code" id="code" placeholder="&nbsp;&nbsp;&nbsp;请输入验证码" class="form1" >
						<input type="button" class="code" id="btnSendCode" onClick="sendMessage()" value="&nbsp;获取短信验证码" style="width:122px;height:35px;background-color:#FF8C00;border-radius:8px;color:white;border:none">
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
						<input type="submit" id="sub" onClick="login()" value="确认">
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
	<script language="javaScript">
			
			function login(){
				var username = document.getElementById("username");
				var password = document.getElementById("password");
				//var usertype = document.getElementById("usertype");
				var useremail = document.getElementById("useremail");
				var phonenum = document.getElementById("phonenum");
				
					if(username.value == ""){
						alert("请输入手机号！");
					}else if(password.value == ""){
						alert("请输入密码！");
					}else if(useremail.value == ""){
						alert("请输入电子邮箱！");
					}else if(usertel.value == ""){
						alert("请输入用户手机号！");
					}
					
			}
			
	</script>
	
	<script type="text/javascript">
		var InterValObj;//timer变量，控制时间
		var count = 60; //间隔函数，1秒执行
		var curCount;//当前剩余秒数
			
		function sendMessage() {
			curCount = count;
			//设置button效果，开始计时
			$("#btnSendCode").attr("disabled", "true");
			$("#btnSendCode").val(curCount + "秒后可重新发送");
			InterValObj = window.setInterval(SetRemainTime, 1000);//启动计时器，1秒执行一次
		}
		
		//timer处理函数
		function SetRemainTime() {
			if (curCount == 0) {
				window.clearInterval(InterValObj);//停止计时器
				$("#btnSendCode").removeAttr("disabled");//启用按钮
				$("#btnSendCode").val("重新发送验证码");
			}
			else {
				curCount--;
				$("#btnSendCode").val(curCount + "秒后可重新发送");
			}
		}
	</script>
	
	<script type="text/javascript">
		var sms = "";
		//获取session中存放的值，用以判断用户是否存在于数据库中
		//var tel = "${sessionScrop.sess}";

		$("#btnSendCode").click(function(){//当点击发送验证码按钮时触发
			var phonenum = $("#phonenum").val();
		    
			if(phonenum != null){
				$.ajax({
					url : "RegisterCodeServlet",//发送请求
					type : "post",
					data : {
						
						"phonenum" : phonenum,//传递用户电话
						
					},
					success : function(result){
						sms = result;
					}
				});
				
			}else if(phonenum == ""){
				alert("请输入手机号！");
				return false;
			}
		});
	</script>
<%@include file="/layout/footer.jsp"  %>
</body>
</html>