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
<link rel="stylesheet" type="text/css" href="css/publishstyle.css" />
</head>
<body>
<jsp:include page="layout/right.jsp"></jsp:include>
	<div class="bodyall">
		<div class="headerback">
		<div class="hearderbackimg"></div>
		</div>

		<div class="registertype">
			<div class="registertype1">
				<div class="registertype1a">请您选择登记类型:</div>
				<ul>
					<a href="searchpeople.jsp"><li style="list-style: inside url(images/suntip.png)">家寻亲人</li></a>
					<a href="searchfamily.jsp"><li style="list-style: inside url(images/suntip.png)">亲人寻家</li></a>
					<a href="vagranthelp.jsp"><li style="list-style: inside url(images/suntip.png)">流浪救助</li></a>
					<a href="othersearch.jsp"><li style="list-style: inside url(images/suntip.png)">其他寻人</li></a>
				</ul>
			</div>
		</div>
		<div class="textbody">
			<div class="textbody1">
				<div class="textbody1a">登记须知</div>
				<div class="textbodymid">
					<div class="textbody1b">
						<li>一.登记类型说明</li>
						<li>1.家寻亲人是指家庭寻找自己丢失的家庭成员，包括儿童，老人等，可在该模块下进行登记。</li>
						<li>2.亲人寻家是指因各种原因与家庭失去联系的人，可在该模块下进行登记。</li>
						<li>3.流浪救助是指在街头流浪乞讨的疑似被拐人员，可在该模块下登记。</li>
						<li>4.其他寻人是指可以寻找除家庭关系以外有社会联系的人，可在此模块下登记。</li>
					</div>
					<div class="textbody1c">
						<li>二.登记说明</li>
						<li>1.亲逢app是秉承关爱走失人士的理念的非营利性社会公益app。</li>
						<li>2.进行寻亲登记之前，请认真阅读以下内容及法律声明：当您的家人失踪或被拐后，请马上拨打110报警。</li>
						<li>3.本应用为寻亲者提供免费的寻人帮助，请如实填写您在网站登记的资料。</li>
						<li>4.请不要利用本应用发布虚假寻人信息或恶作剧信息，本应用将保留一切追究法律责任的权利。</li>
						<li>5、在您登记的信息审核通过以后， 我们会将您的信息下发给工作组的志愿者进行跟进，
							跟进过程中，志愿者会与您联系确认登记内容；为了防止被骗，当有其它人与您联系询问情况时，
							请将通话内容及短信内容记录下来，与跟进志愿者或网站办公室进行沟确认通后，再看下一步如何进行；如果有人给您以彩信或电子邮件形式发送您亲人的照片，
							请将该照片转给跟进志愿者或者网站的邮箱，以辩认是否为合成图像。</li>
						<li>6、本应用所提供的寻人服务不会收取您任何费用，如果有任何人以亲逢app名义向您索取财物，请直接报警或与我们联系。</li>
						<li style="color: red;">我们的办公电话：15227808278</li>
						<li style="color: red;">地址：河北师范大学</li>
						<li style="color: red;">电子邮箱：1154453784@qq.com</li>
					</div>
					<div class="textbody1d">
						<li>三.敬告用户</li>
						<li>1.本应用享有以任何形式无偿使用、转载和引用用户向本协会或网站提交的启事和有关信息的权利，同时保留对用户发布信息管理、修改、删除的权利。</li>
						<li>2.本应用善意提醒信息发布者审慎管理个人信息，且勿轻信陌生人的电话，谨防上当受骗。用户在寻人启事中如若承诺了酬金，则由悬赏者自己承担，相关事宜由双方自行协商，用户通过使用本网站免费寻人服务平台所引起的一切纠纷与本应用无关。</li>
						<li>3.本应用保留在任何时间自行修改、增删本法律声明中任何内容的权利。用户每次登陆或使用本网站时均视为同意受当时有效的条款的制约。该法律声明的解释权归本协会及网站所有。</li>
						<li>4、本法律声明的制定、执行、解释及争议的解决适用中华人民共和国法律。</li>
					</div>
					<div class="textbody1e">
						<li>四.法律声明</li>
						<li>亲逢应用是由各界爱心人士自愿组成的民间志愿者组织，
							是具有独立法人资格的非营利性社会公益团体。《亲逢寻亲网》，即http://www.xunqin.com是隶属于亲逢app的公益性寻人网站，为失踪群体提供免费的寻人帮助，帮助走失、被拐、被遗弃儿童寻找亲人，帮助因各种原因流浪、乞讨、卖艺的儿童回归家庭。本协会及网站已按照相关规定在信息产业部和公安部门完成了备案。
							本协会及网站的宗旨是：防范走失，共筑和谐。</li>
						<li>(一.立场原则)</li>
						<li style="margin-left: 60px;">1.本应用及网站坚决拥护中国共产党的领导，维护祖国统一，自觉遵守《全国人大常委会关于维护互联网安全的决定》及中华人民共和国的各项法律法规，
							坚持爱国、守法、自律、真实、文明的原则，尊重网络道德。</li>
						<li style="margin-left: 60px;">2.本应用及网站坚决反对任何危害国家安全、民族团结，破坏国家宗教政策、社会稳定的活动和行为，禁止任何含有侮辱、诽谤、教唆、淫秽及攻击性内容的信息和作品。
							不组织、不提倡、不参与任何示威、游行、上访等活动。 本协会及网站谢绝一切境外媒体的采访与合作。</li>
						<li>(二.免责条款)</li>
						<li style="margin-left: 60px;">1.本应用及网站所提供的信息，只供参考之用，不应用于任何商业用途。不保证该等信息的准确性、有效性、及时性或完整性。本应用及网站一概毋须以任何方式就任何信息传递或传送的不准确或错误对用户或第三人负任何直接或间接的责任。用户应为使用本网站承担全部责任和风险。
							本应用及网站保留自行决定修改本网站中任何部分内容的权利。</li>
						<li style="margin-left: 60px;">2.本应用可能会提供由第三方发起并维护的互联网站的链接。网站提供该等链接的目的仅在于向用户提供方便。本应用及网站对链接内容不作任何暗示和保证。第三方网站对本网站的链接，并不表示本应用及网站对第三方作出了某种认可、授权，或表明其属于本协会及网站的关联组织、合作组织或业务伙伴。在多数情况下，
							本网站并未意识到第三方已提供对本网站的链接，也不承担由此引起的任何责任。</li>
						<li style="color: red;">请认真阅读以上信息并确认是否进行登记，如果确认登记请在页面上方选择登记类型并进行登记，如果不登记您可以直接返回</li>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="layout/footer.jsp"%>


</body>
</html>