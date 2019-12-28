<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@include file="/layout/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Insert title here</title>
</head>
<body>

	<jsp:include page="layout/right.jsp"></jsp:include>


	<link rel="stylesheet"
		href="https://www.hopexr.com/sass/swiper.min.css" />
	<link rel="stylesheet" href="https://www.hopexr.com/sass/style.css" />


	<img
		style="width: 100%; position: absolute; top: 0; left: 0; z-index: -1"
		src="https://www.hopexr.com/img/back.jpg">


	<div id="content">
		<div class="container">
			<br> <img style="width: 100%" src="images/qinfeng2.png" alt="">
			<div class="title-4">
				<p>真情留言</p>
			</div>
			<div style="margin-top: 10px; margin-bottom: 10px; font-size: 15px;">
				<a href="index.jsp">首页</a><span>></span><span class="red">真情留言</span>
			</div>
			<div class="clearfix board-container">
				<div class="t clearfix">
					<div class="fl">&nbsp;&nbsp;亲逢网感谢您的来访，为了让更多的寻亲家庭早日团聚，也为了我们能够帮助到更多的寻亲者，欢迎您给我们留下宝贵的留言。</div>
					<div class="fr">共有${page.totalCount }个留言&nbsp;&nbsp;</div>
				</div>
				<ul class="aa">
					<c:forEach items="${page.list }" var="li">

						<li class="clearfix">
							<h5>ID： ${li.name}</h5>
							<p>${li.content }</p> <span class="fr"> ${li.time}</span>
						</li>

					</c:forEach>

				</ul>
				<div class="page container">
					<div style="float: none; width: 100%; font-size: 20px"
						align="center">
						共有${page.totalCount } 条，共${page.totalPageNum } 页， <a
							href="${ctx }/comjsp?num=1">首页</a> <a
							href="${ctx }/comjsp?num=${page.prePageNum }">上一页</a> 第
						${page.currentPageNum } 页 <a
							href="${ctx }/comjsp?num=${page.nextPageNum }">下一页</a> <a
							href="${ctx }/comjsp?num=${page.totalPageNum }">末页</a>
					</div>
				</div>
				<br /> <br />
			</div>
			<div style="height: 50px;"></div>
			<div class="form container">
				<div class="form-t">发表留言</div>
				<form action="${ctx }/comsub" method="post">
					<dl class="clearfix">


						<dd class="clearfix" style="width: 25%">
							<div class="fr">
								姓名： <input name="name" class="fr" id="name" type="text"
									style="width: 150px" />
							</div>
						</dd>
						<dd class="clearfix" style="width: 25%">

							<div class="fr">
								联系方式： <input name="tel" class="fr" id="phone" type="text" />
							</div>
						</dd>
						<dd class="clearfix" style="width: 25%">
							<div class="fr">
								QQ： <input name="qq" class="fr" id="internet" type="text" />
							</div>
						</dd>
						<dd class="clearfix" style="width: 25%">

							<div class="fr">
								电子邮箱： <input name="email" class="fr" id="email" type="text" />
							</div>
						</dd>
					</dl>
					<div class="li">
						留言内容：
						<textarea name="content" cols="30" id="message" rows="10"
							style="width: 100%"></textarea>
					</div>
					<input class="submit" type="submit" id="leavemessage" value="我要发布" />
				</form>

				<div class="li">
					<p style="color: red">
						<b>温馨提示:</b>
					</p>

				</div>
			</div>

		</div>
	</div>

	</div>







	<%@include file="/layout/footer.jsp"%>



</body>
</html>