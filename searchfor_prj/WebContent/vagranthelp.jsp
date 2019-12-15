<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@include file="layout/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/vagranthelpstyle.css" />
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>

	<div class="imghead">
		<div class="imgheadbody">
			<div class="imghead1">
				<p
					style="color: white; padding-left: 20px; padding-top: 50px; font-size: 15px;">你可曾看见他们的悲伤</p>
			</div>
			<div class="imghead2">
				<p
					style="color: white; padding-left: 50px; padding-top: 120px; font-size: 15px;">你可曾了解他们背后的故事</p>
			</div>
			<div class="imghead3">
				<p
					style="color: white; padding-left: 150px; padding-top: 100px; font-size: 15px;">请给予他们一些帮助</p>
			</div>
			<div class="imghead4">
				<p
					style="color: white; padding-left: 140px; padding-top: 120px; font-size: 15px;">让他们早日脱离苦难</p>
			</div>
		</div>
		<div class="imgheadtext">
			<ul>
				<li style="margin-top: 10px; margin-left: 200px; list-style: inside url(images/heart.png)">寻寻觅觅</li>
				<li style="margin-left: 600px; list-style: inside url(images/heart.png)">回家之路就在前方</li>
				<li style="margin-left: 400px; list-style: inside url(images/heart.png)">不灭的希望</li>
				<li style="margin-left: 800px; list-style: inside url(images/heart.png)">是你我共同的坚守</li>
			</ul>
		</div>
	</div>
	<div class="vregister">
		<div class="vrbody">
			<div class="vrbody1">
				<li style="list-style: inside url(images/suntip.png)">流浪救助登记</li>
			</div>
			<div class="vrbody2">
				<div class="vrbody2a">
					<li style="list-style: inside url(images/tip.png)">请在登录的情况下进行操作</li>
					<li style="margin-left: 36px;">红色信息标记的信息为必填字段</li>
					<li style="margin-left: 36px;">不知道的字段请填无</li>
				</div>
				<div class="vrbody2b">
					<form>
						<p>一.流浪者信息</p>
						<span>*姓名：<input type="text" name="vname"
							style="width: 200px; height: 30px; margin-left: 50px;" /></span> <br>
						<br> <span>*性别：<input type="radio" name="vsex" checked
							value="female" style="margin-left: 50px;" />女 <input
							type="radio" name="vsex" checked value="male" />男
						</span> <br>
						<br>
						<span> 年龄：<input type="text" name="vage"
							style="width: 200px; height: 30px; margin-left: 50px;"
							placeholder="填大致年龄区间也可以" /></span> <br>
						<br> <span> *开始流浪时间：<input type="text"
							name="vbegintime"
							style="width: 200px; height: 30px; margin-left: 50px;"
							placeholder="填大致时间即可" /></span><br> <br> <span>*目标家庭信息：</span>
						<br>
						<textarea name="vtfamily" cols="40" rows="5"
							style="margin-left: 200px;"></textarea>
						<br>
						<span> *流浪者特征描述：</span> <br>
						<textarea name="vdfeature" cols="40" rows="5"
							style="margin-left: 200px;"></textarea>
						<br> <span>*发现地址：</span> <br>
						<textarea name="vfadress" cols="40" rows="3"
							style="margin-left: 200px;"></textarea>
						<br>
						<span> *您的联系方式：<input type="text" name="vphone"
							style="width: 200px; height: 30px; margin-left: 50px;" /></span> <br>
						<p>二.上传图片</p>
						<br>
						<div class="imgbody">
							<input type="file" name="file0" id="file0" class="imgfile" /><br>
							<img src="images/upimgtip.jpg" id="img0"> <input
								type="button" value="刪除" onclick="deleteimg(0)" class="mydelimg" />
						</div>
						<div class="imgbody">
							<input type="file" name="file1" id="file1" class="imgfile" /><br>
							<img src="images/upimgtip.jpg" id="img1"> <input
								type="button" value="刪除" onclick="deleteimg(1)" class="mydelimg" />
						</div>
						<div class="imgbody">
							<input type="file" name="file2" id="file2" class="imgfile" /><br>
							<img src="images/upimgtip.jpg" id="img2"> <input
								type="button" value="刪除" onclick="deleteimg(2)" class="mydelimg" />

						</div>
						<div class="imgbody">
							<input type="file" name="file3" id="file3" class="imgfile" /><br>
							<img src="images/upimgtip.jpg" id="img3"> <input
								type="button" value="刪除" onclick="deleteimg(3)" class="mydelimg" />

						</div>
						<div class="imgbody">
							<input type="file" name="file4" id="file4" class="imgfile" /><br>
							<img src="images/upimgtip.jpg" id="img4"> <input
								type="button" value="刪除" onclick="deleteimg(4)" class="mydelimg" />
						</div>
						<input type="submit" value="确认登记" onclick="" class="btn_submit" />
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
		function deleteimg(id) {
			$("#img" + id).attr("src", "images/upimgtip.jpg");
		}

		$("#file0").change(function() {
			var objUrl = getObjectURL(this.files[0]);//获取文件信息  
			console.log("objUrl = " + objUrl);
			if (objUrl) {
				$("#img0").attr("src", objUrl);
			}
		});
		$("#file1").change(function() {
			var objUrl = getObjectURL(this.files[0]);//获取文件信息  
			console.log("objUrl = " + objUrl);
			if (objUrl) {
				$("#img1").attr("src", objUrl);
			}
		});
		$("#file2").change(function() {
			var objUrl = getObjectURL(this.files[0]);//获取文件信息  
			console.log("objUrl = " + objUrl);
			if (objUrl) {
				$("#img2").attr("src", objUrl);
			}
		});
		$("#file3").change(function() {
			var objUrl = getObjectURL(this.files[0]);//获取文件信息  
			console.log("objUrl = " + objUrl);
			if (objUrl) {
				$("#img3").attr("src", objUrl);
			}
		});
		$("#file4").change(function() {
			var objUrl = getObjectURL(this.files[0]);//获取文件信息  
			console.log("objUrl = " + objUrl);
			if (objUrl) {
				$("#img4").attr("src", objUrl);
			}
		});
		function getObjectURL(file) {
			var url = null;
			if (window.createObjectURL != undefined) {
				url = window.createObjectURL(file);
			} else if (window.URL != undefined) { // mozilla(firefox)  
				url = window.URL.createObjectURL(file);
			} else if (window.webkitURL != undefined) { // webkit or chrome  
				url = window.webkitURL.createObjectURL(file);
			}
			return url;
		}
	</script>

	<%@include file="layout/footer.jsp"%>



</body>

</html>