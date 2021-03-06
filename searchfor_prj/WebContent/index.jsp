<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="404.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page errorPage="404.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<link href="css/bootstraps.css" rel='stylesheet' type='text/css' />
<link href="css/styles.css" rel='stylesheet' type='text/css' />
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

<link rel="stylesheet" type="text/css" href="css/style1.css" />
</head>
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Vegetables Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!--fonts-->
<link href='#css?family=Exo:100,200,300,400,500,600,700,800,900'
	rel='stylesheet' type='text/css'>

<!--//fonts-->
<script src="js/jquery.easydropdown.js"></script>

</head>
<body>
	<jsp:include page="layout/right.jsp"></jsp:include>


	<!--header-->
	<div class="header">
		<div class="container">
			<div class="header-top">
				<div class="logo">
					<a href="index.jsp"><img src="${ctx }/images/logo2.png" alt=" "></a>
				</div>
				<div class="search-in">
					<div class="header-grid">
						<ul>
							<li class="in-up"><a href="signin.jsp" class="scroll">
									登录 </a> <label>|</label></li>
							<li><a href="signup.jsp" class="scroll">注册</a> <label>|</label></li>
							<li class="in-up"><a href="center.jsp" class="scroll">个人中心</a>
								<label></label></li>
						</ul>
					</div>
					<div class="search-top">
						<div class="search">
							<form>
								<input type="text" value="Search" onfocus="this.value = '';"
									onblur="if (this.value == '') {this.value = '';}"> <input
									type="submit" value="">
							</form>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="header-bottom-bottom">
				<div class="top-nav">
					<span class="menu"> </span>
					<ul>
						<li><a href="index.jsp">首页</a></li>
						<li><a href="${ctx }/WebHall"> 寻人大厅</a></li>
						<li><a href="publish.jsp"> 发布寻人 </a></li>
						<li><a href="${ctx }/comjsp"> 真情留言 </a></li>
						<li><a href="center.jsp"> 个人中心 </a></li>
						<li><a href="${ctx }/jspavoid"> 相关措施 </a></li>
					</ul>
					<script>
						$("span.menu").click(function() {
							$(".top-nav ul").slideToggle(500, function() {
							});
						});
					</script>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<div class="banner">
		<!--slider-script-->
		<script src="js/responsiveslides.min.js"></script>
		<script>
			$(function() {
				$("#slider").responsiveSlides({
					auto : true,
					speed : 500,
					namespace : "callbacks",
					pager : true,
				});
			});
		</script>
		<!--//slider-script-->
		<!-- Slideshow 4 -->
		<div id="top" class="callbacks_container">
			<ul class="rslides" id="slider">
				<li><img src="${ctx }/images/a1.jpg" alt="" />
					<div class="banner-matter">
						<div class="price">
							<h2>我们在等你回家</h2>
						</div>
						<div class="banner-para">
							<p>落叶纷飞终归根 寻回心灵深处的感动</p>
						</div>
					</div></li>

				<li><img src="${ctx }/images/a91.png" alt="" />
					<div class="banner-matter">
						<div class="price">
							<h2>我们在等你回家</h2>
						</div>
						<div class="banner-para">
							<p>在站寻家 让爱回家</p>
						</div>
					</div></li>
				<li><img src="${ctx }/images/a5.jpg" alt="" />
					<div class="banner-matter">
						<div class="price">
							<h2>我们在等你回家</h2>
						</div>
						<div class="banner-para">
							<p>寻亲之路，你我相伴</p>
						</div>
					</div></li>
			</ul>
		</div>
	</div>
	<!-- //slider-->
	<!--content-->
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

								</div>
								<p>
									<span>苏星河</span> <span>男</span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span>亲逢ID：</span> <span>1001</span>
								</p>

								<a href="${ctx }/WebHall" class="button hvr-wobble-bottom">了解更多</a>
							</div>
						</div>
						<div class="col-md-4 banner-grid wow fadeInDownBig"
							data-wow-delay=".4s">

							<div class="ban1">
								<div class="ban-images  view fourth-effect">
									<img src="images/example2.jpg" class="img-responsive" alt="" />

								</div>
								<p>
									<span>包不同</span> <span>男</span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span>亲逢ID：</span> <span>1002</span>
								</p>
								<a href="${ctx }/WebHall" class="button hvr-wobble-bottom">了解更多</a>
							</div>
						</div>
						<div class="col-md-4 banner-grid wow fadeInRight animated"
							data-wow-delay=".5s">

							<div class="ban1">
								<div class="ban-images  view fourth-effect">
									<img src="images/example3.jpg" class="img-responsive" alt="" />

								</div>
								<p>
									<span>王成良</span> <span>男</span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<span>亲逢ID：</span> <span>1003</span>
								</p>
								<a href="${ctx }/WebHall" class="button hvr-wobble-bottom">了解更多</a>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>

				</div>

			</div>
		</div>
		<!--<div class="content">
				<div class="container">
				<div class="top-content">
					<div class="content-top">
						<div class="col-md-3 look">
							<a href="single.jsp"><img class="img-responsive" src="${ctx }/images/logo.png" alt=" " ></a>
							<p>姓名：*** </p>
							<a href="single.jsp" class="btn  btn-1c">了解更多</a>
						</div>
						<div class="col-md-3 look">
							<a href="single.jsp"><img class="img-responsive" src="${ctx }/images/logo.png" alt=" " ></a>
							<p>姓名：*** </p>
							<a href="single.jsp" class="btn  btn-1c">了解更多</a>
						</div>
						<div class="col-md-3 look">
							<a href="single.jsp"><img class="img-responsive" src="${ctx }/images/logo.png" alt=" " ></a>
							<p>姓名：*** </p>
							<a href="single.jsp" class="btn  btn-1c">了解更多</a>
						</div>
						</div>
						
					</div>
				</div>
			</div>  -->

		<!--<div class="col-md-3 look">
							<a href="single.jsp"><img class="img-responsive" src="${ctx }/images/logo.png" alt=" " ></a>
							<p>姓名：*** </p>
							<a href="single.jsp" class="btn  btn-1c">了解更多</a>
						</div>
						<div class="clearfix"> </div>
						</div>			
						<div class="content-top">
						<div class="col-md-3 look">
							<h4><a href="single.html">Internet tend to repeat </a></h4>
							<a href="single.html"><img class="img-responsive" src="${ctx }/images/logo.png" alt=" " ></a>
							<p>content here', making it look like readable English. Many desktop publishing packages </p>
							<a href="single.html" class="btn  btn-1c">Learn More</a>
						</div>
						<div class="col-md-3 look">
							<h4><a href="single.html">Internet tend to repeat </a></h4>
							<a href="single.html"><img class="img-responsive" src="${ctx }/images/logo.png" alt=" " ></a>
							<p>content here', making it look like readable English. Many desktop publishing packages </p>
							<a href="single.html" class="btn  btn-1c">Learn More</a>
						</div>
						<div class="col-md-3 look">
							<h4><a href="single.html">Internet tend to repeat </a></h4>
							<a href="single.html"><img class="img-responsive" src="${ctx }/images/logo.png" alt=" " ></a>
							<p>content here', making it look like readable English. Many desktop publishing packages </p>
							<a href="single.html" class="btn  btn-1c">Learn More</a>
						</div>
						<div class="col-md-3 look">
							<h4><a href="single.html">Internet tend to repeat </a></h4>
							<a href="single.html"><img class="img-responsive" src="${ctx }/images/logo.png" alt=" " ></a>
							<p>content here', making it look like readable English. Many desktop publishing packages </p>
							<a href="single.html" class="btn  btn-1c">Learn More</a>
						</div>
						<div class="clearfix"> </div>
					-->
		<!---->
		<!---->
		<div class="content-bottom-top">
			<div class="wmuSlider example1">
				<div class="wmuSliderWrapper">
					<article style="position: absolute; width: 100%; opacity: 0;">
						<div class="content-bottom">
							<div class="container">
								<span class="corn"> </span>
								<h3>do you know ?</h3>
								<p>Dedication of love, call of heart</p>
							</div>
						</div>
					</article>
					<article style="position: absolute; width: 100%; opacity: 0;">
						<div class="content-bottom">
							<div class="container">
								<span class="corn corn-in"> </span>
								<h3>do you know ?</h3>
								<p>Reach out and open the door of love</p>
							</div>
						</div>
					</article>
					<article style="position: absolute; width: 100%; opacity: 0;">
						<div class="content-bottom">
							<div class="container">
								<span class="corn"> </span>
								<h3>do you know ?</h3>
								<p>Every effort you make will be rewarded with kindness</p>
							</div>
						</div>
					</article>
				</div>
				<script src="js/jquery.wmuSlider.js"></script>
				<script>
					$('.example1').wmuSlider();
				</script>
			</div>
		</div>
		<!---->
		<!---
				<div class="content-bottom">
					<div class="container">
						<span class="corn"> </span>
						<h3>Do you Know  ?</h3>
						<p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, </p>
					</div>
				</div>
				<!---->
		<div class="map-top">
			<div class="map">
				<iframe
					src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12947831.742778081!2d-95.665!3d37.599999999999994!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x54eab584e432360b%3A0x1c3bb99243deb742!2sUnited+States!5e0!3m2!1sen!2sin!4v1422444552833"></iframe>
			</div>
			<div class="address">
				<h5>亲逢</h5>
				<p>让您和您的亲人重逢</p>
				<a href="index.jsp" class="company">info@qinfeng.com</a>
			</div>
		</div>
	</div>
	<!--footer-->
	<div class="footer">
		<div class="container">
			<p class="footer-grid">
				Copyright &copy; 2019.light of java All rights reserved.<a
					target="_blank" href="index.jsp">亲逢</a>
			</p>
		</div>
	</div>

	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>
