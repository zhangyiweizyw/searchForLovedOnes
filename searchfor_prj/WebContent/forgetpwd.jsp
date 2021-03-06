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
	}
	
	.bian{
			width:550px;
			height:380px;
			border:1px solid #A9A9A9;
			margin:0 auto;
			border:none;
		}
		
	.form{
			width:550px;
			height:60px;
			border:none;
			margin-top:15px;
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
		
		#sub{
			width:250px;
			height:40px;
			background-color:#FF8C00;
			border-radius:8px;
			border:0;
			margin:0 auto;
			display:block;
			color:#fff;
			margin-top:20px;
			font-size:15px;
			font-family:黑体;
			font-weight:bold;
		}
	
</style>
</head>
<body>
	<jsp:include page="layout/right.jsp"></jsp:include>
	<div class="container">
	
		<!-- 上方链接 -->
			<div class="topmess">
				<a href="index.jsp">首页</a>
				<span>></span>
				<a href="signin.jsp">登录</a>
				<span>></span>
				<a style="color:red">忘记密码</a>
			</div>
			
			<div class="log1">重设密码</div>
			
			<!-- 边框 -->
			<div class="bian" style="position:relative;margin-bottom:20px;position:relative">
				<!-- 正文：重设密码部分 -->
				<div class="forget">
					<form action="PwdChangedWebServlet" method="post" style="margin-top:50px">
						
						<div class="form">
							<label><span class="mark">&nbsp;&nbsp;&nbsp;*</span><span class="user">手机号</span></label>
							<input type="text" name="phonenum" id="phonenum" placeholder="&nbsp;&nbsp;&nbsp;请输入手机号" class="form1" required="required">
						</div>
						
						<div class="form">
							<label><span class="mark">&nbsp;&nbsp;&nbsp;*</span><span class="user">验证码</span></label>
							<input type="text" name="code" id="code" placeholder="&nbsp;&nbsp;&nbsp;&nbsp;请输入验证码" class="form1" >
							<input type="button" class="code" name="btnSendCode" id="btnSendCode" onClick="sendMessage()"  class="feachBtn" value="&nbsp;获取短信验证码" style="width:122px;height:35px;background-color:#FF8C00;border-radius:8px;color:white;border:none;margin-left:10px">
						</div>
						
						<div class="form" style="margin-left:3px">
							<label><span class="mark">&nbsp;&nbsp;&nbsp;*</span><span  class="user">新密码</span></label>
							<input type="password" name="password" placeholder="&nbsp;&nbsp;&nbsp;请输入密码" class="form1" requirxed="required">
						</div>
						
						<div class="form2">
							<input type="submit" id="sub" value="确认更改"/>
						</div>
						
					</form>
				</div>
			</div>
	</div>
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
					url : "PwdChangedCodeServlet",//发送请求
					type : "post",
					data : {
						"phonenum" : phonenum//传递用户电话
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
<%@include file="/layout/footer.jsp" %>
</body>
</html>