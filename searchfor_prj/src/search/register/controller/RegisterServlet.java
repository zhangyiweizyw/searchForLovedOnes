package search.register.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import search.entity.User;
import search.register.service.RegisterService;
import search.user.service.UserService;
import search.util.MessageDisgest;


/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		boolean num = false;
		
		//获取输入流
		InputStream is = request.getInputStream();
		byte[] buffer = new byte[255];
		int len = is.read(buffer);
		if(len != -1) {
			String userStream = new String(buffer,0,len);
			System.out.println(userStream);
			
			//将json格式改为user对象格式
			JSONObject jsonObject = new JSONObject(userStream);
			String userInfo = jsonObject.getString("userJson");
			System.out.println(userInfo);
			Gson gson = new Gson();
			 
			//使用GSON将从客户端传来的JSON数据直接转成User类
				User user = gson.fromJson(userInfo, User.class);
				
				String name = user.getUserName();
				String pwd = user.getUserPwd();
				
				//对type进行截取，只需要其类型，说明不用获取
				String realType = user.getUserType();
				String type = realType.substring(0, 3);
				
				String email = user.getUserEmail();
				String tel = user.getUserTel();
				
			//对密码进行加密
			MessageDisgest messageDisgest = new MessageDisgest();
			String secretPwd = messageDisgest.secretPassword(pwd);
			System.out.println(name+secretPwd+type+email+tel);
			
			
			//当用户电话未被提前注册时，可以进行注册
			//存放用户注册信息
			RegisterService registerService = new RegisterService();
			registerService.addUserInfo(name,secretPwd,type,email,tel);
			
		}
		
		//向客户端传一个已经添加成功的标志
		JSONObject object = new JSONObject();
		object.put("isAdd", "1");
		
		response.getWriter().append(object.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		
	}

}
