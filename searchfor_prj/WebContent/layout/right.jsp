<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="css/jbstyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/style1.css" />
<script type="text/javascript" src="js/jbjquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	$(window).scroll(function() {
		if ($(window).scrollTop() > 300) {
			$('#jump li:eq(0)').fadeIn(800);
		} else {
			$('#jump li:eq(0)').fadeOut(800);
		}
	});
	
	$("#jbtop").click(function() {
		$('body,html').animate({
			scrollTop: 0
		},
		1000);
		return false;
	});
	
});
</script>
<script type="text/javascript" src="js/jbjquery.min.js"></script>
<ul id="jump">
	<li style="display:none;"><a id="jbtop" href="#jbtop"></a></li>
	<li><a id="sina" href="http://service.weibo.com/share/share.php?title=http://localhost:8080/searchfor_prj/%20%20&url=http%3A%2F%2Flocalhost%3A8080%2Fsearchfor_prj%2Findex.jsp%23jbtop&pic=#_loginLayer_1575892440712" target="_blank"></a></li>
	<li>
		<a id="weixin" href="javascript:void(0)" onmouseover="showEWM()" onmouseout="hideEWM()">
			<div id="EWM"><img src="images/weixin_code.png" /></div>
		</a>
	</li>
	<li><a class="-mob-share-open share" onclick=""></a></li>
	<li><a id="reply" href="" target="_blank"></a></li>
</ul>

<script type="text/javascript">
function showEWM(){
	document.getElementById("EWM").style.display = 'block';
}
function hideEWM(){
	document.getElementById("EWM").style.display = 'none';
}
</script>
<!--MOB SHARE BEGIN-->
<div class="-mob-share-ui-button -mob-share-open">分享</div>
<div class="-mob-share-ui" style="display: none">
    <ul class="-mob-share-list">
        <li class="-mob-share-qzone"><p>QQ空间</p></li>
        <li class="-mob-share-qq"><p>QQ好友</p></li>
        <li class="-mob-share-facebook"><p>Facebook</p></li>
        <li class="-mob-share-twitter"><p>Twitter</p></li>
    </ul>
    <div class="-mob-share-close">取消</div>
</div>
<div class="-mob-share-ui-bg"></div>
<script id="-mob-share" src="http://f1.webshare.mob.com/code/mob-share.js?appkey=2d591e11e2052"></script>
<!--MOB SHARE END-->
