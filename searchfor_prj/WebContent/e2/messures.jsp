<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--header-->
	<div class="header">
		<div class="container">
			<div class="header-top">			
				<div class="logo">
					<a href="index.jsp"><img src="${ctx }/images/logo1.png" alt=" " ></a>
				</div>
				<div class="search-in">
					<div class="header-grid">
						<ul>
							<li class="in-up" ><a href="signin.jsp" class="scroll"> 登录 </a> <label>|</label></li>
							<li ><a href="signup.jsp" class="scroll">注册</a> <label>|</label></li>
							<li class="in-up" ><a href="center.jsp" class="scroll">个人中心</a> <label></label></li>						
						</ul>
				</div>
				<div class="search-top">
					<div class="search">
					<form>
						<input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}" >
						<input type="submit"  value="">
					</form>
				</div>
				<div class="clearfix"> </div>
				</div>
			</div>	
			<div class="clearfix"> </div>
			</div>
			<div class="header-bottom-bottom">
				<div class="top-nav">
					<span class="menu"> </span>
					<ul>
						<li><a href="index.jsp" >首页</a></li>
						<li><a href="precaution.jsp" >防拐防骗</a></li>
						<li><a href="approach.jsp" >寻人方法</a></li>
						<li><a href="policy.jsp" > 政策法规 </a></li>
					</ul>	
				<script>
					$("span.menu").click(function(){
						$(".top-nav ul").slideToggle(500, function(){
						});
					});
				</script>			
				</div>	
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>

<%@include file="/e2/layout/footer.jsp"  %>
</body>
</html>