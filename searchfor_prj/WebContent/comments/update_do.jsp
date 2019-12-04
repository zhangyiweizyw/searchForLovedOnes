<%@ page contentType="text/html;charset=gb2312"%>
<%@ page import="java.sql.*"%>
<html>
<head>
	<title>JSP+JDBC 留言管理程序——登陆</title>
</head>
<body>
<center>
	<h1>留言管理范例 —— JSP + JDBC实现</h1>
	<hr>
	<br>
	<%
		// 进行乱码处理
		request.setCharacterEncoding("GB2312") ;
	%>
	<%
		if(session.getAttribute("uname")!=null)
		{
			// 用户已登陆
	%>
	<%!
		String DBDRIVER			= "oracle.jdbc.driver.OracleDriver" ;
		String DBURL			= "jdbc:oracle:thin:@localhost:1521:MLDN" ;
		String DBUSER			= "scott" ;
		String DBPASSWORD		= "tiger" ;
		Connection conn			= null ;
		PreparedStatement pstmt	= null ;
	%>
	<%
		// 声明一个boolean变量
		boolean flag = false ;

		// 接收参数
		String title = request.getParameter("title") ;
		String author = request.getParameter("author") ;
		String content = request.getParameter("content") ;
		int id = 0 ;
		try
		{
			id = Integer.parseInt(request.getParameter("id")) ;
		}
		catch(Exception e)
		{}
	%>
	<%
		// 更新note表中的数据
		String sql = "UPDATE note set title=?,author=?,content=? WHERE id=?" ;
		try
		{
			Class.forName(DBDRIVER) ;
			conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD) ;
			pstmt = conn.prepareStatement(sql) ;
			pstmt.setString(1,title) ;
			pstmt.setString(2,author) ;
			pstmt.setString(3,content) ;
			pstmt.setInt(4,id);
			pstmt.executeUpdate() ;
			pstmt.close() ;
			conn.close() ;
			// 如果修改成功，则肯定能执行到此段代码
			flag = true ;
		}
		catch(Exception e)
		{}
	%>
	<%
			response.setHeader("refresh","2;URL=list_notes.jsp") ;
			if(flag)
			{
	%>
				留言修改成功，两秒后跳转到留言列表页！！！<br>
				如果没有跳转，请按<a href="list_notes.jsp">这里</a>！！！
	<%
			}
			else
			{
	%>
				留言修改失败，两秒后跳转到留言列表页！！！<br>
				如果没有跳转，请按<a href="list_notes.jsp">这里</a>！！！
	<%
			}
	%>
	<%
		}
		else
		{
			// 用户未登陆，提示用户登陆，并跳转
			response.setHeader("refresh","2;URL=login.jsp") ;
	%>
			您还未登陆，请先登陆！！！<br>
			两秒后自动跳转到登陆窗口！！！<br>
			如果没有跳转，请按<a href="login.jsp">这里</a>！！！<br>
	<%
		}
	%>
</center>
</body>
</html>