<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="search.entity.Basic_information"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@include file="/layout/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>寻人大厅</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/hall.css" />
<!---css--->
<link href="css/bootstraps.css" rel='stylesheet' type='text/css' />
<link href="css/styles.css" rel='stylesheet' type='text/css' />
<!---css--->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript">
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 


</script>
<!---js--->
<script src="js/jquery-1.12.0.min.js"></script>
<script src="js/bootstrap.js"></script>
<!--JS for animate-->
<link href="css/animate.css" rel="stylesheet" type="text/css"
	media="all">
<script src="js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
<!--//end-animate-->

<!---webfont--->
<link href='#css?family=Ubuntu+Condensed' rel='stylesheet'
	type='text/css'>
<link
	href='#css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<!---webfont--->
<link rel="stylesheet" type="text/css" href="css/style_common.css" />
<link rel="stylesheet" type="text/css" href="css/style1.css" />
</head>
<body>
	<jsp:include page="layout/right.jsp"></jsp:include>
	<div class="all">
		<!-- 开头图片,装逼于无形-->
		<div class="header-back"></div>
		<!---welcome-->
		<!-- 动态生成card -->
				<!---welcome-->
		<div class="content">
			<div class="welcome-section">
				<div class="container">
					<div class="banner-bottom">
						<div class="banner-grids">
						<c:forEach items="${page.list }" var="li" begin="0" end="0">
							<div class="col-md-4 banner-grid wow fadeInLeft animated"
								data-wow-delay=".5s">

								<div class="ban1">
									<div class="ban-images  view fourth-effect">
										<img src="${ctx }/${li.photo }" class="img-responsive" alt="" />
										<!-- <div class="mask"></div> -->
									</div>
									<p>
										<span><c:out
								value="${li.name }"></c:out></span> <span><c:out
								value="${li.sex }"></c:out></span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>亲逢ID：</span> <span><c:out value="${li.id }" /></span>
									</p>
									 
									<a href="hall.jsp" class="button hvr-wobble-bottom">了解更多</a>
								</div>
							</div>
							</c:forEach>
							<c:forEach  items="${page.list }" var="li" begin="1" end="1">
							<div class="col-md-4 banner-grid wow fadeInDownBig"
								data-wow-delay=".4s">

								<div class="ban1">
									<div class="ban-images  view fourth-effect">
										<img src="${ctx }/${li.photo }"  class="img-responsive" alt="" />
										<!-- <div class="mask"></div> -->
									</div>
									<p>
										<span><c:out
								value="${li.name }"></c:out></span> <span><c:out
								value="${li.sex }"></c:out></span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>亲逢ID：</span> <span><c:out value="${li.id }" /></span>
									</p>
									<a href="hall.jsp" class="button hvr-wobble-bottom">了解更多</a>
								</div>
							</div>
							</c:forEach>
							<c:forEach   items="${page.list }" var="li" begin="2" end="2">
							<div class="col-md-4 banner-grid wow fadeInRight animated"
								data-wow-delay=".5s">

								<div class="ban1">
									<div class="ban-images  view fourth-effect">
										<img src="${ctx }/${li.photo }" class="img-responsive" alt="" />
										<!-- <div class="mask"></div> -->
									</div>
									<p>
										<span><c:out
								value="${li.name }"></c:out></span> <span><c:out
								value="${li.sex }"></c:out></span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>亲逢ID：</span> <span><c:out value="${li.id }" /></span>
									</p>
									<a href="hall.jsp" class="button hvr-wobble-bottom">了解更多</a>
								</div>
							</div>
							</c:forEach>
							<div class="clearfix"></div>
						</div>

					</div>

				</div>
			</div>
			<!---welcome-->
			<div class="welcome-section">
				<div class="container">
					<div class="banner-bottom">
						<div class="banner-grids">
						<c:forEach   items="${page.list }" var="li" begin="3" end="3">
							<div class="col-md-4 banner-grid wow fadeInLeft animated"
								data-wow-delay=".5s">

								<div class="ban1">
									<div class="ban-images  view fourth-effect">
										<img src="${ctx }/${li.photo }" class="img-responsive" alt="" />
										<!-- <div class="mask"></div> -->
									</div>
									<p>
										<span><c:out
								value="${li.name }"></c:out></span> <span><c:out
								value="${li.sex }"></c:out></span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>亲逢ID：</span> <span><c:out value="${li.id }" /></span>
									</p>
									<a href="hall.jsp" class="button hvr-wobble-bottom">了解更多</a>
								</div>
							</div>
							</c:forEach>
							<c:forEach  items="${page.list }" var="li" begin="4" end="4">
							<div class="col-md-4 banner-grid wow fadeInDownBig"
								data-wow-delay=".4s">

								<div class="ban1">
									<div class="ban-images  view fourth-effect">
										<img src="${ctx }/${li.photo }" class="img-responsive" alt="" />
										<!-- <div class="mask"></div> -->
									</div>
									<p>
										<span><c:out
								value="${li.name }"></c:out></span> <span><c:out
								value="${li.sex }"></c:out></span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>亲逢ID：</span> <span><c:out value="${li.id}"/></span>
									</p>
									<a href="hall.jsp" class="button hvr-wobble-bottom">了解更多</a>
								</div>
							</div>
							</c:forEach>
							<c:forEach items="${page.list }" var="li" begin="5" end="5"> 
							<div class="col-md-4 banner-grid wow fadeInRight animated"
								data-wow-delay=".5s">

								<div class="ban1">
									<div class="ban-images  view fourth-effect">
										<img src="${ctx }/${li.photo }" class="img-responsive" alt="" />
										<!-- <div class="mask"></div> -->
									</div>
									<p>
										<span><c:out
								value="${li.name }"></c:out></span> <span><c:out
								value="${li.sex }"></c:out></span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>亲逢ID：</span> <span><c:out value="${li.id}"/></span>
									</p>
									<a href="hall.jsp" class="button hvr-wobble-bottom">了解更多</a>
								</div>
							</div>
							</c:forEach>
							<div class="clearfix"></div>
						</div>

					</div>

				</div>
			</div>



		</div>
		<div style="float: none; width: 100%; font-size: 20px" align="center">
			共有${page.totalCount } 条，共${page.totalPageNum } 页， <a
				href="${ctx }/WebHall?num=1">首页</a> <a
				href="${ctx }/WebHall?num=${page.prePageNum }">上一页</a> 第
			${page.currentPageNum } 页 <a
				href="${ctx }/WebHall?num=${page.nextPageNum }">下一页</a> <a
				href="${ctx }/WebHall?num=${page.totalPageNum }">末页</a>
		</div>

	</div>
	<%@include file="/layout/footer.jsp"%>
</body>
</html>