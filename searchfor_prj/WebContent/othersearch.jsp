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
<link rel="stylesheet" type="text/css" href="css/othersearchstyle.css" />
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
</head>
<body>

	<div class="imghead">
		<div class="imgheadbody">
			<div class="imghead1">
				<p
					style="color: white; padding-left: 20px; padding-top: 50px; font-size: 15px;">有一个人在你的心口</p>
			</div>
			<div class="imghead2">
				<p
					style="color: white; padding-left: 230px; padding-top: 90px; font-size: 15px;">念念不忘</p>
				<p
					style="color: white; padding-left: 10px; padding-top: 60px; font-size: 15px;">令你魂牵梦萦</p>

			</div>
			<div class="imghead3">
				<p
					style="color: white; padding-left: 80px; padding-top: 120px; font-size: 15px;">你是否期待这样的一次相遇</p>
			</div>
			<div class="imghead4">
				<p
					style="color: white; padding-left: 60px; padding-top: 150px; font-size: 15px;">久别重逢，再现当年少年时</p>
			</div>
		</div>
		<div class="imgheadtext">
			<ul>
				<li
					style="margin-top: 10px; margin-left: 200px; list-style: inside url(images/heart.png)">岁月无情</li>
				<li
					style="margin-left: 600px; list-style: inside url(images/heart.png)">将人们之间的羁绊斩断</li>
				<li
					style="margin-left: 400px; list-style: inside url(images/heart.png)">人间有爱</li>
				<li
					style="margin-left: 800px; list-style: inside url(images/heart.png)">让我们共同期待重逢的喜悦</li>
			</ul>
		</div>
	</div>
	<div class="vregister">
		<div class="vrbody">
			<div class="vrbody1">
				<li style="list-style: inside url(images/suntip.png)">其他寻人登记</li>
			</div>
			<div class="vrbody2">
				<div class="vrbody2a">
					<li style="list-style: inside url(images/tip.png)">请在登录的情况下进行操作</li>
					<li style="margin-left: 36px;">红色信息标记的信息为必填字段</li>
					<li style="margin-left: 36px;">不知道的字段请填无</li>
				</div>
				<div class="vrbody2b">
					<form
						action="${ctx }/AddOtherSearchByJspServlet"
						method="post" enctype="multipart/form-data">
						<p>一.寻人信息</p>
						<span>*姓名：<input type="text" name="oname" id="oname"
							style="width: 200px; height: 30px; margin-left: 50px;" /></span> <br>
						<br> <span>*性别：<input type="radio" name="osex" checked
							value="female" style="margin-left: 50px;" />女 <input
							type="radio" name="osex" checked value="male" />男
						</span> <br> <br> <span> 与该人的社会联系：<input type="text"
							name="orelation" id="orelation"
							style="width: 200px; height: 30px; margin-left: 50px;"
							placeholder="如朋友，家人，爱人等" /></span><br> <br> <span>*寻人原因及线索资料：</span>
						<br>
						<textarea name="oreson" id="oreson" cols="40" rows="5"
							style="margin-left: 250px;"></textarea>
						<br> <br>
						<p>二.您的信息</p>
						<span>*姓名：<input type="text" name="oyname" id="oyname"
							style="width: 200px; height: 30px; margin-left: 50px;" /></span> <br>
						<br> <span>*性别：<input type="radio" name="oysex"
							checked value="female" style="margin-left: 50px;" />女 <input
							type="radio" name="oysex" checked value="male" />男
						</span><br> <br> <span>*年龄：<input type="text"
							name="oyage" id="oyage"
							style="width: 200px; height: 30px; margin-left: 50px;" /></span> <br>
						<br> <span>*联系方式：<input type="text" name="oyphone"
							id="oyphone" onblur="isPhone()"
							style="width: 200px; height: 30px; margin-left: 50px;" /></span> <br>
							<p style="margin-left:260px;color:red;" id="showphonetip"></p>
						<br> <span>*邮箱：<input type="text" name="oyemail"
							id="oyemail" onblur="isEmail()"
							style="width: 200px; height: 30px; margin-left: 50px;" /></span> <br>
							<p style="margin-left:260px;color:red;" id="showemailtip"></p>
						<br> <span>QQ号：<input type="text" name="oyqq"
							id="oyqq" style="width: 200px; height: 30px; margin-left: 50px;" /></span>
						<br> <br> <span>*住址：<input type="text"
							name="oyaddr" id="oyaddr"
							style="width: 200px; height: 30px; margin-left: 50px;" /></span> <br>
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
							class="btn_submit" id="submitdata" />
					</form>
				</div>
			</div>
		</div>
	</div>

	<script>
		//验证手机号正确性
		function isPhone() {
			var phone = document.getElementById("oyphone").value;
			var tag = /^[1][34578][0-9]{9}$/;
			var showphonetip = document.getElementById("showphonetip");
			if (!tag.test(phone)) {
				showphonetip.innerHTML = "!输入的手机号格式不正确";
			} else {
				showphonetip.innerHTML = "";
			}
		}
		//验证邮箱的合法性
		function isEmail() {
			var showemailtip = document.getElementById("showemailtip");
			var email = document.getElementById("oyemail").value;
			var tag = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
			if (!tag.test(email)) {
				showemailtip.innerHTML = "!输入的邮箱格式不合法";
			} else {
				showemailtip.innerHTML = "";
			}
		}
		function judgenull() {
			//1.判断是否为登录状态
			var user_id = "${user_id}";
			console.log("id" + user_id);
			if(user_id!=""){
				var sfname = document.getElementById("sfname").value;
				var sfheight = document.getElementById("sfheight").value;
				var sfaddr = document.getElementById("sfaddr").value;
				var sfloseaddr = document.getElementById("sfloseaddr").value;
				var sffeature = document.getElementById("sffeature").value;
				var sfloseprocess = document.getElementById("sfloseprocess").value;
				var sffamily = document.getElementById("sffamily").value;
				var spyphone = document.getElementById("spyphone").value;
				var spyemail = document.getElementById("spyemail").value;
				var sffamilyaddr = document.getElementById("sffamilyaddr").value;
				var sffamilydes = document.getElementById("sffamily").value;
				var sfrelation = document.getElementById("sfrelation").value;
				var file0=document.getElementById("file0").value;
				var file1=document.getElementById("file1").value;
				var file2=document.getElementById("file2").value;
				var file3=document.getElementById("file3").value;
				var file4=document.getElementById("file4").value;
				if (sfname != "" && sfheight != "" && sfaddr != ""
						&& sfloseaddr != "" && sffeature != ""
						&& sfloseprocess != "" && sffamily != "" && spyphone != ""
						&& spyemail != "" && sffamilyaddr != "" && ffamilydes != ""
						&& sfrelation != "") {
					if(file0==""&&file1==""&&file2==""&&file3==""&&file4==""){
						alert("请至少上传一张图片!");
					}
					else{
						if (!confirm("你确定提交吗？提交后信息将无法修改。")) {
							$("#submitdata").attr("type", "button");
						} else {
							$("#submitdata").attr("type", "submit");
						}
					}
				} else {
					alert("您所填写的信息内包含空字段，请重新填写，不知道的字段请填无");
				}
			}
			else{
				//未登录
				alert("请下登录再进行操作！");
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