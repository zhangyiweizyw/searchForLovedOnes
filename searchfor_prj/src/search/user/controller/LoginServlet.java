package search.user.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import search.entity.User;
import search.user.service.UserService;
import search.util.MessageDisgest;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//获取输入流
		InputStream is = request.getInputStream();
		byte[] buffer = new byte[255];
		int len = is.read(buffer);
		if(len != -1) {
			String userStream = new String(buffer,0,len);
			System.out.println("已经接收到客户端数据:"+userStream);
			
			//将接收到的输入流转换成JSON形式
			JSONObject json = new JSONObject(userStream);
			String name = json.getString("name");
			String password = json.getString("password");//得到用户的原始密码
			
			//对用户登录所填写密码进行加密操作
			MessageDisgest messageDisgest = new MessageDisgest();
			String secretPwd = messageDisgest.secretPassword(password);
			
			System.out.println(name+secretPwd);
			
			//传入用户输入电话/邮箱和密码查找数据库获得所有用户信息,得到一个返回值为布尔类型
			UserService userService = new UserService();
			boolean num = userService.loginUser(name,password);
			
			//设置响应成功标志
			JSONObject type = new JSONObject();		
				if(num== false) {
					type.put("isSuccess","0");
				}else if(secretPwd == null || secretPwd.equals("")) {
					type.put("isSuccess", "0");
				}else if(name == null || name.equals("")) {
					type.put("isSuccess", "0");
				}else if(num == true) {
					type.put("isSuccess", "1");
				}
			
			response.getWriter().append(type.toString());
			System.out.println("已经发送响应数据"+type.toString());
		}
	}
}