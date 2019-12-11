package search.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html; charset=UTF-8"); //转码
		 
		 String phonenum = request.getParameter("phonenum");
		 String password = request.getParameter("password");
		 
		//对密码进行加密
		MessageDisgest messageDisgest = new MessageDisgest();
		String secretPwd = messageDisgest.secretPassword(password);
		
		System.out.println(phonenum+secretPwd);
		 //查找数据库中所有用户信息
		 UserService userService = new UserService();
		 List<User> users = new ArrayList<>();
		 users = userService.loginUser();
		 for(int i = 0;i<users.size();i++) {
			 if(users.get(i).getUserTel().equals(phonenum) && users.get(i).getUserPwd().equals(secretPwd)) {//用户电话和密码都正确
				 System.out.println("123456");
				 //request.getRequestDispatcher("index.jsp").forward(request, response);
				 System.out.println(phonenum+secretPwd);
				
			 }else if(!users.get(i).getUserTel().equals(phonenum) || !users.get(i).getUserPwd().equals(secretPwd)) {//用户电话或密码输入错误
				 //request.getRequestDispatcher("signin.jsp").forward(request, response);
				 PrintWriter out;
				 try {
					 out = response.getWriter();
					 out.print("<script>alert('用户电话或密码错误！')</script>");
					 out.close();
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
				 
			 }
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
