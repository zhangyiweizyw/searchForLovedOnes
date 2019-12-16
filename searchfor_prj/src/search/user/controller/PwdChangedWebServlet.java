package search.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import search.user.service.UserService;

/**
 * Servlet implementation class PwdChangedWebServlet
 */
@WebServlet("/PwdChangedWebServlet")
public class PwdChangedWebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdChangedWebServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//获取用户输入手机号、验证码和密码
		String phonenum = request.getParameter("phonenum");
		String code = request.getParameter("code");
		String newPwd = request.getParameter("password");
		
		//先判断密码是否存在于数据库中
		UserService userService = new UserService();
		boolean type = userService.judgeUserTelService(phonenum);
		
		if(type == true) {//当返回值为true,代表数据库中存在该号码，可以发送验证码
			
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
