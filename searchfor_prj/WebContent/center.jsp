<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="search.entity.User"%>
<%@page import="search.user.dao.UserDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@include file="/layout/header.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#all{
    border:5px solid #CCCCCC;
    width:1100px;
    height:350px;
    font-family:"微软雅黑";
   margin:0 auto;
}
#all .content{
    width:180px;
    height:340px;
    padding:10px;
    font-size:15px;
}
#all .text{
    font-family:"微软雅黑";
    font-size:12px;
}

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
			
			margin:0 auto;
			border:none;
	}
	
	.form{
			width:550px;
			height:60px;
			border:none;
			margin-top:5px
			text-align: center;
			 line-height: 80px;

		}
		.form2{
				font-size:35px;
			text-align:center;
			font-family:黑体;
			color:#668B8B;
			margin-left:50px;
		}
		.mark{
			font-size:15px;
			text-align:center;
			height:30px;
			line-height:30px;
			margin-left:20px;
			color:;
		}
		.mark1{
			color:red;
			font-size:20px;
			display:inline-block;
			text-align:center;
			height:30px;
			line-height:30px;
			margin-left:20px
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
		
			height:50px;
			line-height:50px;
			background-color:white;
			font-size:25px;
			border:0;
			margin:0 auto;
			display:block;
			margin-left:220px;


		}
		
		.code{
			margin-left:10px
		}

</style>
<link rel="stylesheet" href="https://www.hopexr.com/sass/swiper.min.css"/>
    <link rel="stylesheet" href="https://www.hopexr.com/sass/style.css"/>

    
  <img style="width:100%;position:absolute;top:0;left:0;z-index:-1" src="https://www.hopexr.com/img/back.jpg" >

<title>Insert title here</title>
</head>
<body>

<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/style1.css" rel='stylesheet' type='text/css' />
<!---css--->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!---js--->
<script src="js/jquery-1.12.0.min.js"></script>
<script src="js/bootstrap.js"></script>
<!---js--->
<!--JS for animate-->
	<link href="css/animate.css" rel="stylesheet" type="text/css" media="all">
	<script src="js/wow.min.js"></script>
		<script>
			new WOW().init();
		</script>
	<!--//end-animate-->

<!---webfont--->
<link href='#css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
<link href='#css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<!---webfont--->
</head>
<body>
<%! User user=new User();%>
	<%
	session =request .getSession();// 获取session
	int user_id = 0;
	if (session.getAttribute("user_id") != null) {
		user_id = (int) session.getAttribute("user_id");
		System.out.println(user_id);
		user=new UserDao().serachUser(user_id);	// 获得当前登录用户的id
		System.out.println(user.getUserEmail());
	}else{
		user.setUserName("请先进行登录");
	}
	pageContext.setAttribute("user",user);
	%>

	<div class="content">
	<div class="mail">
		<div class="container">
 <div style="margin-top:10px;margin-bottom:10px;font-size:15px;"><a href="index.jsp">首页</a><span>></span><span class="red">个人中心</span></div>
			<div class="mail-grids">
				<div class="col-md-6 mail-left wow fadeInRight animated" data-wow-delay=".5s">
					<h4>用户名：</h4>
					<p style="
			font-size:35px;
		
			font-family:黑体;
			font-weight:bold;
		">${pageScope.user.userName}</p>
					<ul>
						<li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>联系方式<span style="font-size:15px;		font-family:黑体;font-weight:bold;
		">${user.userTel}</span></li>
						<li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i>电子邮件<span style="
			font-size:15px;
		
			font-family:黑体;
			font-weight:bold;
		">${pageScope.user.userEmail}</span></li>
					</ul>
					<ul>
						<li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>用户类型<span style="
			font-size:15px;
		
			font-family:黑体;
			font-weight:bold;
		">${pageScope.user.userType}</span></li>
					</ul>
				</div>
				<div class="col-md-6 mail-left wow fadeInLeft animated" data-wow-delay=".5s">
					<h4>意见反馈</h4>
					<p>亲逢官网欢迎您提出宝贵意见！</p>
					<form>
						<input type="text" placeholder="Name" required=" ">
						<input type="email" placeholder="Email" required=" ">
						<div class="clearfix"> </div>
						<input type="text" placeholder="Subject" required=" ">
						<textarea placeholder="Type Your Text Here...." required=" "></textarea>
						<input type="submit" value="提交" onclick="sumbit_sure()" >
					</form>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	
	</div>
	
	<!--copy-->
	<div class="copy-section wow fadeInRight animated" data-wow-delay=".5s"">
		<div class="container">
				<div class="social-icons">
					<a href="#"><i class="icon"></i></a>
					<a href="#"><i class="icon1"></i></a>
					<a href="#"><i class="icon2"></i></a>
					<a href="#"><i class="icon3"></i></a>
				</div>
			<p style="
			font-size:15px;
		
			font-family:黑体;
			font-weight:bold;
		">Copyright &copy; 2016.Company name All rights reserved.<a target="_blank" href="http://guantaow.taobao.com/"></a><a target="_blank" href="http://www.moobnn.com">亲逢</a></p>
		</div>
	</div>
<script language="javascript">
function sumbit_sure(){
var gnl=confirm("确定要提交?");
if (gnl==true){

return true;
}else{
return false;
}
}
</script>

</body>

</html>

