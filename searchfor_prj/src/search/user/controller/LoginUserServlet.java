package search.user.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import search.user.service.UserService;
import search.util.MessageDisgest;

/**
 * Servlet implementation class LoginUserServlet
 */
@WebServlet("/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//获取输入流
		InputStream is = request.getInputStream();
		
		byte[] buffer = new byte[255];
		int len = is.read(buffer);
		
		if(len != -1) {
			String userStream = new String(buffer,0,len);
			System.out.println("123456");
			//接收用户登录电话密码
			JSONObject json = new JSONObject(userStream);
			String name = json.getString("name");
			String password = json.getString("password");
			System.out.println("已经接收到客户端数据:"+userStream);
			//对密码实现加密操作
			MessageDisgest messageDisgest = new MessageDisgest();
			String secretPwd = messageDisgest.secretPassword(password);
			
			//调用接口实现登陆
			UserService userService = new UserService();
			boolean num = userService.loginUser(name, secretPwd);
			
			//登录成功将user_id存入application中
			ServletContext application=this.getServletContext();
			if(num){
				System.out.println("获得用户id");
				int user_id=userService.getUserId(name);
				System.out.println("theuserid"+user_id);
				application.setAttribute("user_id",user_id);
			}
			
			JSONObject type = new JSONObject();
			if(num) {
				type.put("isSuccess", true);
			}else {
				type.put("isSuccess", false);
			}
			
			response.getWriter().append(type.toString());
		}
	}

}
