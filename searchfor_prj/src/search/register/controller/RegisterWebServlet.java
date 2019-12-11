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
		response.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		String useremail = request.getParameter("useremail");
		String usertel = request.getParameter("usertel");
		
		//对密码进行加密
		MessageDisgest messageDisgest = new MessageDisgest();
		String secretPwd = messageDisgest.secretPassword(password);
		
		RegisterService registerService = new RegisterService();
		registerService.addUserInfo(username,secretPwd, usertype, useremail, usertel);
		
		response.sendRedirect("signin.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
