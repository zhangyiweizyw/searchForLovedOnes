package search.register.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import search.register.service.RegisterService;
import search.util.MessageDisgest;

/**
 * Servlet implementation class RegisterWebServlet
 */
@WebServlet("/RegisterWebServlet")
public class RegisterWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterWebServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 转码
		
		String sess = request.getSession().getAttribute("session").toString();
		
		if(sess == "1") {
			//验证码获取正确
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String usertype = request.getParameter("usertype");
			String useremail = request.getParameter("useremail");
			String phonenum = request.getParameter("phonenum");
			
			//对密码进行加密
			MessageDisgest messageDisgest = new MessageDisgest();
			String secretPwd = messageDisgest.secretPassword(password);
			
			//进行用户信息注册，已经在网页进行空判断
			RegisterService registerService = new RegisterService();
			registerService.addUserInfo(username,secretPwd, usertype, useremail,phonenum);
			
			response.getWriter().print("<script language='javascript'>alert('注册成功！')</script>");
			response.setHeader("refresh","0.1,URL=signin.jsp");
		}else if(sess == "0") {
			response.getWriter().print("<script language='javascript'>alert('验证码错误，请重新获取！')</script>");
			response.setHeader("refresh","0.1,URL=signup.jsp");
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
