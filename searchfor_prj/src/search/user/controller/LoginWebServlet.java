package search.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import search.entity.User;
import search.user.service.UserService;
import search.util.MessageDisgest;

/**
 * Servlet implementation class LoginWebServlet
 */
@WebServlet("/LoginWebServlet")
public class LoginWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginWebServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 转码

		String phonenum = request.getParameter("phonenum");
		String password = request.getParameter("password");

		// 对用户输入密码进行加密
		MessageDisgest messageDisgest = new MessageDisgest();
		String secretPwd = messageDisgest.secretPassword(password);

		// 查找数据库中所有用户信息
		UserService userService = new UserService();
		boolean type = userService.loginUser(phonenum, secretPwd);

		// 网页端登录成功将user_id存入session中
		HttpSession session = request.getSession();
		if (type) {
			System.out.println("获得用户id");
			int user_id = userService.getUserId(phonenum);
			System.out.println("theuserid" + user_id);
			session.setAttribute("user_id", user_id);
		}

		// 建立变量存放从数据库中取出的数据
		if (type) {
			response.sendRedirect("index.jsp");
			return;
		} else {
			// 向session域中存放了目标页面提示错误的弹窗内容
			response.getWriter().print("<script language='javascript'>alert('手机号或密码错误，请确认是否注册！')</script>");
			response.setHeader("refresh", "1,URL=signin.jsp");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
