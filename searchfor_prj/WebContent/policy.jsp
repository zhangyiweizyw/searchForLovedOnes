<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@include file="/layout/header_messure.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="width: 100%;">
		<c:forEach items="${page.list }" var="li">
			<div
				style="border: 3px solid #CAE1FF; margin: 0 5% 3% 5%;; width: 90%; height: 255px;">

				<img style="float: left; margin-right: 60px" alt=""
					src="${ctx }/images/b1.jpg" width="250" height="250">

				<div style="align: center;">
					<font size="6px" color="#FF8C00"> <c:out
							value="${li.title }"></c:out></font>
				</div>

				<div
					style="display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 5; overflow: hidden; white-space: normal; text-overflow: ellipsis">
					<font size="5px" color="#708090"><c:out
							value="${li.content }" /></font>
				</div>


			</div>
		</c:forEach>
	</div>


	<div style="float: none; width: 100%; font-size: 20px" align="center">
		共有${page.totalCount } 条，共${page.totalPageNum } 页， <a
			href="${ctx }/jsplaw?num=1">首页</a> <a
			href="${ctx }/jsplaw?num=${page.prePageNum }">上一页</a> 第
		${page.currentPageNum } 页 <a
			href="${ctx }/jsplaw?num=${page.nextPageNum }">下一页</a> <a
			href="${ctx }/jsplaw?num=${page.totalPageNum }">末页</a>
	</div>

	<%@include file="/layout/footer.jsp"%>
</body>
</html>