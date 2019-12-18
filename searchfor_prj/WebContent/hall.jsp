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
<!---js--->
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
<link rel="stylesheet" type="text/css" href="css/style9.css" />
</head>
<body>
	<div class="all">
		<!-- 开头图片,装逼于无形-->
		<div class="header-back"></div>
		<!---welcome-->
		<div class="content">
			<div class="welcome-section">
				<div class="container">
					<div class="banner-bottom">
						<div class="banner-grids">
							<div class="col-md-4 banner-grid wow fadeInLeft animated"
								data-wow-delay=".5s">

								<div class="ban1">
									<div class="ban-images  view fourth-effect">
										<img src="images/example1.jpg" class="img-responsive" alt="" />
										<div class="mask"></div>
									</div>
									<p>
										<span>苏星河</span> <span>男</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>亲逢ID：</span> <span>1001</span>
									</p>
									 
									<a href="hall.jsp" class="button hvr-wobble-bottom">了解更多</a>
								</div>
							</div>
							<div class="col-md-4 banner-grid wow fadeInDownBig"
								data-wow-delay=".4s">

								<div class="ban1">
									<div class="ban-images  view fourth-effect">
										<img src="images/example2.jpg" class="img-responsive" alt="" />
										<div class="mask"></div>
									</div>
									<p>
										<span>包不同</span> <span>男</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>亲逢ID：</span> <span>1002</span>
									</p>
									<a href="hall.jsp" class="button hvr-wobble-bottom">了解更多</a>
								</div>
							</div>
							<div class="col-md-4 banner-grid wow fadeInRight animated"
								data-wow-delay=".5s">

								<div class="ban1">
									<div class="ban-images  view fourth-effect">
										<img src="images/example3.jpg" class="img-responsive" alt="" />
										<div class="mask"></div>
									</div>
									<p>
										<span>王成良</span> <span>男</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>亲逢ID：</span> <span>1003</span>
									</p>
									<a href="hall.jsp" class="button hvr-wobble-bottom">了解更多</a>
								</div>
							</div>
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
							<div class="col-md-4 banner-grid wow fadeInLeft animated"
								data-wow-delay=".5s">

								<div class="ban1">
									<div class="ban-images  view fourth-effect">
										<img src="images/example4.jpg" class="img-responsive" alt="" />
										<div class="mask"></div>
									</div>
									<p>
										<span>王小川</span> <span>男</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>亲逢ID：</span> <span>1004</span>
									</p>
									<a href="hall.jsp" class="button hvr-wobble-bottom">了解更多</a>
								</div>
							</div>
							<div class="col-md-4 banner-grid wow fadeInDownBig"
								data-wow-delay=".4s">

								<div class="ban1">
									<div class="ban-images  view fourth-effect">
										<img src="images/example5.jpg" class="img-responsive" alt="" />
										<div class="mask"></div>
									</div>
									<p>
										<span>木婉清</span> <span>女</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>亲逢ID：</span> <span>1005</span>
									</p>
									<a href="hall.jsp" class="button hvr-wobble-bottom">了解更多</a>
								</div>
							</div>
							<div class="col-md-4 banner-grid wow fadeInRight animated"
								data-wow-delay=".5s">

								<div class="ban1">
									<div class="ban-images  view fourth-effect">
										<img src="images/example6.jpg" class="img-responsive" alt="" />
										<div class="mask"></div>
									</div>
									<p>
										<span>邓天同</span> <span>男</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>亲逢ID：</span> <span>1006</span>
									</p>
									<a href="hall.jsp" class="button hvr-wobble-bottom">了解更多</a>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>

					</div>

				</div>
			</div>



		</div>
	</div>
	<%@include file="/layout/footer.jsp"%>
</body>
</html>