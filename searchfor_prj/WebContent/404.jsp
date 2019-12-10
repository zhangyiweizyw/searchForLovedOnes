<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isErrorPage="true"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!doctype html>
<head>
	<meta charset="utf-8">	
	<title>404错误提示页</title>
	<meta name="description" content="">
	<meta name="author" content="">

	<meta name="viewport" content="width=device-width,initial-scale=1">
	
	<!-- CSS: implied media=all -->
	<link rel="stylesheet" href="css/404style.css">
	<link rel="stylesheet" href="css/404blue.css">
	<script src="js/jquery-404.js"></script>
	<script src="js/404script.js"></script>
    <style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
    </style>
</head>
<body>
	<div id="error-container">
		<div id="error">
			<div id="pacman"></div>
		</div>		
		<div id="container">
			<div id="title">
				<h1>对不起, 你访问的页面不存在!</h1>
			</div>
			<div id="content">
				<div class="left">
					<p class="no-top">&nbsp;&nbsp;&nbsp;可能是如下原因引起了这个错误:</p>
					<ul>
						<li>&nbsp;&nbsp;&nbsp;URL输入错误</li>
						<li>&nbsp;&nbsp;&nbsp;链接已失效</li>
						<li>&nbsp;&nbsp;&nbsp;其他原因...</li>
					</ul>
                    <div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
				<div align="center"><span class="STYLE1">你找不到的只是网页，他们找不到的却是家和亲人！爱心公益，需要你我...                </span>									</br>
			  </div>
				<iframe scrolling='yes' frameborder='0' src='http://yibo.iyiyun.com/js/yibo404/key/3260' width='100%' height='230' style='display:block;'></iframe>
			</div>
		</div>
	</div>
</body>

</html>