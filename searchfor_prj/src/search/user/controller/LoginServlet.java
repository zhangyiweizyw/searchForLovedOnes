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
			
			//查找数据库获得所有用户信息
			UserService userService = new UserService();
			List<User> users = userService.loginUser();
			for(int j =0; j<users.size();j++) {
				System.out.println(users.get(j).getUserEmail()+users.get(j).getUserPwd());//查询现有所有用户信息
			}
			
			
			//设置响应成功标志
			JSONObject type = new JSONObject();		
			
			for(int k = 0;k<users.size();k++) {
				if(!(users.get(k).getUserPwd().equals(secretPwd)) || !(users.get(k).getUserTel().equals(name))) {
					type.put("isSuccess","0");
				}else if(!(users.get(k).getUserPwd().equals(secretPwd)) || !(users.get(k).getUserEmail().equals(name))) {
					type.put("isSuccess", "0");
				}else if(secretPwd == null || secretPwd.equals("")) {
					type.put("isSuccess", "0");
				}else if(name == null || name.equals("")) {
					type.put("isSuccess", "0");
				}
				
			}
			for(int i = 0;i<users.size();i++) {
				if(users.get(i).getUserEmail().equals(name) && users.get(i).getUserPwd().equals(secretPwd)) {
					type.put("isSuccess", "1");
					System.out.println(users.get(i).getUserPwd()+users.get(i).getUserEmail());
				}else if(users.get(i).getUserPwd().equals(secretPwd) && users.get(i).getUserTel().equals(name)){
					type.put("isSuccess", "1");
					System.out.println(users.get(i).getUserPwd()+users.get(i).getUserTel());
				}
			}
			response.getWriter().append(type.toString());
			System.out.println("已经发送响应数据"+type.toString());
		}
	}
}
