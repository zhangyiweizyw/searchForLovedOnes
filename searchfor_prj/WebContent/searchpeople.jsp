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
<link rel="stylesheet" type="text/css" href="css/searchpeoplestyle.css" />
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>

	<div class="imghead">
		<div class="imgheadbody">
			<div class="imghead1">
				<p
					style="color: #000; padding-left: 20px; padding-top: 50px; font-size: 15px;">宝贝</p>
				<p
					style="color: #000; padding-left: 10px; padding-top: 20px; font-size: 15px;">别哭</p>
			</div>
			<div class="imghead2">
				<p
					style="color: #fff; padding-left: 220px; padding-top: 120px; font-size: 15px;">我们在找你</p>

			</div>
			<div class="imghead3">
				<p
					style="color: #000; padding-left: 40px; padding-top: 150px; font-size: 15px;">困苦艰难阻挡不了我们相聚的步伐</p>
			</div>
			<div class="imghead4">
				<p
					style="color: #fff; padding-left: 80px; padding-top: 130px; font-size: 15px;">宝贝，我们期待和你的团圆</p>
			</div>
		</div>
		<div class="imgheadtext">
			<ul>
				<li
					style="margin-top: 10px; margin-left: 200px; list-style: inside url(images/heart.png)">团团圆圆</li>
				<li
					style="margin-left: 600px; list-style: inside url(images/heart.png)">是心中最渴望的明灯</li>
				<li
					style="margin-left: 400px; list-style: inside url(images/heart.png)">愿漫长寻亲之路</li>
				<li
					style="margin-left: 800px; list-style: inside url(images/heart.png)">天堑变通途</li>
			</ul>
		</div>
	</div>
	<div class="vregister">
		<div class="vrbody">
			<div class="vrbody1">
				<li style="list-style: inside url(images/suntip.png)">家寻亲人登记</li>
			</div>
			<div class="vrbody2">
				<div class="vrbody2a">
					<li style="list-style: inside url(images/tip.png)">请在登录的情况下进行操作</li>
					<li style="margin-left: 36px;">红色信息标记的信息为必填字段</li>
					<li style="margin-left: 36px;">不知道的字段请填无</li>
				</div>
				<div class="vrbody2b">
					<form
						action="http://localhost:8080/searchfor_prj/AddSearchPeopleJspServlet"
						method="post" enctype="multipart/form-data">
						<p>一.失踪者信息</p>
						<span>*姓名：<input type="text" name="spname" id="spname"
							style="width: 200px; height: 30px; margin-left: 50px;" /></span> <br>
						<br> <span>*性别：<input type="radio" name="spsex"
							checked value="female" style="margin-left: 50px;" />女 <input
							type="radio" name="spsex" checked value="male" />男
						</span> <br> <br> <span>*出生日期：<select id="year"
							name="year" style="margin-left: 65px;"></select>年 <select
							id="month" name="month"></select>月 <select id="day" name="day"></select>日
						</span> <br> <br> <span>*失踪时身高：<input type="text"
							name="spheight" id="spheight"
							style="width: 200px; height: 30px; margin-left: 50px;" /></span> <br>
						<br> <span>失踪时年龄：<input type="text"
							style="width: 200px; height: 30px; margin-left: 55px;" /></span> <br>
						<br> <span>*失踪日期：<select id="loseyear" name="loseyear"
							style="margin-left: 65px;"></select>年 <select id="losemonth"
							name="losemonth"></select>月 <select id="loseday" name="loseday"></select>日
						</span> <br> <br> <span>*是否采血：<input type="radio"
							name="isBlood" checked value="yes" style="margin-left: 50px;" />是
							<input type="radio" name="isBlood" checked value="no" />否
						</span> <br> <br> <span>*是否报案：<input type="radio"
							name="isRecord" checked value="yes" style="margin-left: 50px;" />是
							<input type="radio" name="isRecord" checked value="no" />否
						</span> <br> <br> <span>*失踪人籍贯<input type="text"
							name="spaddr" id="spaddr"
							style="width: 200px; height: 30px; margin-left: 50px;" /></span> <br>
						<br> <span>*失踪地点<input type="text" name="sploseaddr"
							id="sploseaddr"
							style="width: 200px; height: 30px; margin-left: 50px;" /></span> <br>
						<br> <span>*失踪者特征描述：</span> <br>
						<textarea name="spfeature" id="spfeature" cols="40" rows="5"
							style="margin-left: 240px;"></textarea>
						<br> <br> <span>*失踪经过：</span> <br>
						<textarea name="sploseprocess" id="sploseprocess" cols="40"
							rows="5" style="margin-left: 250px;"></textarea>
						<br> <br> <span>*家庭背景及其线索资料：</span> <br>
						<textarea name="spfamily" id="spfamily" cols="40" rows="5"
							style="margin-left: 250px;"></textarea>
						<br>
						<p>二.联系人信息</p>
						<br> <span>*姓名：<input type="text" name="spyname"
							id="spyname"
							style="width: 200px; height: 30px; margin-left: 100px;" /></span> <br>
						<br> <span>*联系方式：<input type="text" name="spyphone"
							id="spyphone" onblur="isPhone()"
							style="width: 200px; height: 30px; margin-left: 73px;" /></span> <br>
						<p Style="margin-left: 250px; color: red;" id="showphonetip"></p>
						<br> <span>*邮箱：<input type="text" name="spyemail"
							id="spyemail" onblur="isEmail()"
							style="width: 200px; height: 30px; margin-left: 100px;" /></span> <br>
							<p style="margin-left:250px;color:red;" id="showemailtip"></p>
						<br> <span>与失踪人联系：<input type="text"
							name="spyrelation" id="spyrelation"
							style="width: 200px; height: 30px; margin-left: 50px;"
							placeholder="如孩子，父母，叔侄等" /></span> <br> <br> <span>*现住址：<input
							type="text" name="spyaddr" id="spyaddr"
							style="width: 200px; height: 30px; margin-left: 85px;" /></span> <br>
						<p>三.上传图片(至少上传一张)</p>
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
						<input type="button" value="确认登记" onclick="judgenull()"
							class="btn_submit" />
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
		//验证手机号正确性
		function isPhone() {
			var phone = document.getElementById("spyphone").value;
			var tag = /^[1][34578][0-9]{9}$/;
			var showphonetip = document.getElementById("showphonetip");
			if (!tag.test(phone)) {
				showphonetip.innerHTML = "!输入的手机号格式不正确";
			} else {
				showphonetip.innerHTML = "";
			}
		}
		//验证邮箱的合法性
		function isEmail(){
			var showemailtip=document.getElementById("showemailtip");
			var email=document.getElementById("spyemail").value;
			var tag=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
			if(!tag.test(email)){
				showemailtip.innerHTML="!输入的邮箱格式不合法";
			}
			else{
				showemailtip.innerHTML="";
			}
		}
		function judgenull() {
			//1.判断是否为登录状态
			var user_id = "${user_id}";
			console.log("id" + user_id);
			if (user_id != "") {
				var spname = document.getElementById("spname").value;
				var spheight = document.getElementById("spheight").value;
				var spaddr = document.getElementById("spaddr").value;
				var sploseaddr = document.getElementById("sploseaddr").value;
				var spfeature = document.getElementById("spfeature").value;
				var sploseprocess = document.getElementById("sploseprocess").value;
				var spfamily = document.getElementById("spfamily").value;
				var spyname = document.getElementById("spyname").value;
				var spyphone = document.getElementById("spyphone").value;
				var spyemail = document.getElementById("spyemail").value;
				var spyrelation = document.getElementById("spyrelation").value;
				var spyaddr = document.getElementById("spyaddr").value;
				var file0 = document.getElementById("file0").value;
				var file1 = document.getElementById("file1").value;
				var file2 = document.getElementById("file2").value;
				var file3 = document.getElementById("file3").value;
				var file4 = document.getElementById("file4").value;
				if (spname != "" && spheight != "" && spaddr != ""
						&& sploseaddr != "" && spfeature != ""
						&& sploseprocess != "" && spfamily != ""
						&& spyname != "" && spyphone != "" && spyemail != ""
						&& spyrelation != "" && spyaddr != "") {
					if (file0 == "" && file1 == "" && file2 == ""
							&& file3 == "" && file4 == "") {
						alert("请至少上传一张图片!");
					} else {
						if (!confirm("你确定提交吗？提交后信息将无法修改。")) {
							$("#submitdata").attr("type", "button");
						} else {
							$("#submitdata").attr("type", "submit");
						}
					}
				} else {
					alert("您所填写的信息内包含空字段，请重新填写，不知道的字段请填无");

				}
			} else {
				alert("请先登录再进行操作!");
			}

		}

		window.onload = function() {
			var now = new Date();
			var nowyear = now.getFullYear();//获取当前年份
			var i = 0;
			for (i = 1940; i <= nowyear; i++) {
				document.getElementById("year").options.add(new Option(i, i));
				document.getElementById("loseyear").options
						.add(new Option(i, i));
			}
			for (i = 1; i <= 12; i++) {
				document.getElementById("month").options.add(new Option(i, i));
				document.getElementById("losemonth").options.add(new Option(i,
						i));
			}
			for (i = 1; i <= 31; i++) {
				document.getElementById("day").options.add(new Option(i, i));
				document.getElementById("loseday").options
						.add(new Option(i, i));
			}

		}
		var themonth = document.getElementById("month");
		var thelosemonth = document.getElementById("losemonth");
		themonth.onclick = function() {
			//提前删除所有节点
			document.getElementById("day").options.length = 0;
			var years = document.getElementById("year").children;
			var months = document.getElementById("month").children;
			var year = 0;
			var month = 0;
			var i = 0;
			for (i = 0; i < years.length; i++) {
				if (years[i].selected) {
					year = years[i].value;
				}
			}
			for (i = 0; i < months.length; i++) {
				if (months[i].selected) {
					month = months[i].value;
				}
			}
			if (month == 1 || month == 3 || month == 5 || month == 7
					|| month == 8 || month == 10 || month == 12) {
				for (i = 1; i <= 31; i++) {
					document.getElementById("day").options
							.add(new Option(i, i));
				}
			} else if (month == 2) {
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
					//今年是闰年
					for (i = 1; i <= 29; i++) {
						document.getElementById("day").options.add(new Option(
								i, i));
					}
				} else {
					//平年
					for (i = 1; i <= 28; i++) {
						document.getElementById("day").options.add(new Option(
								i, i));
					}
				}
			} else {
				for (i = 1; i <= 30; i++) {
					document.getElementById("day").options
							.add(new Option(i, i));
				}
			}
		}

		thelosemonth.onclick = function() {
			//提前删除所有节点
			document.getElementById("loseday").options.length = 0;
			var years = document.getElementById("loseyear").children;
			var months = document.getElementById("losemonth").children;
			var year = 0;
			var month = 0;
			var i = 0;
			for (i = 0; i < years.length; i++) {
				if (years[i].selected) {
					year = years[i].value;
				}
			}
			for (i = 0; i < months.length; i++) {
				if (months[i].selected) {
					month = months[i].value;
				}
			}
			if (month == 1 || month == 3 || month == 5 || month == 7
					|| month == 8 || month == 10 || month == 12) {
				for (i = 1; i <= 31; i++) {
					document.getElementById("loseday").options.add(new Option(
							i, i));
				}
			} else if (month == 2) {
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
					//今年是闰年
					for (i = 1; i <= 29; i++) {
						document.getElementById("loseday").options
								.add(new Option(i, i));
					}
				} else {
					//平年
					for (i = 1; i <= 28; i++) {
						document.getElementById("loseday").options
								.add(new Option(i, i));
					}
				}
			} else {
				for (i = 1; i <= 30; i++) {
					document.getElementById("loseday").options.add(new Option(
							i, i));
				}
			}
		}

		function deleteimg(id) {
			$("#img" + id).attr("src", "images/upimgtip.jpg");
			if(document.getElementById("file"+id).value!=""){
				console.log("删除");
				document.getElementById("file"+id).value="";
			}
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