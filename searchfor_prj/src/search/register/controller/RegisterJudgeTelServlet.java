package search.register.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import search.user.service.UserService;

/**
 * Servlet implementation class RegisterJudgeTelServlet
 */
@WebServlet("/RegisterJudgeTelServlet")
public class RegisterJudgeTelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterJudgeTelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//获取输入流：用户电话
		InputStream is = request.getInputStream();
		byte[] buffer = new byte[255];
		int len = is.read(buffer);
		if(len != -1) {
			String userStream = new String(buffer,0,len);
			
			JSONObject json = new JSONObject(userStream);
			String userTel = json.getString("judgeTel");
			
			//判断用户电话是否可注册
			UserService userService = new UserService();
			boolean num = userService.judgeUserTelService(userTel);
			
			JSONObject type = new JSONObject();
			if(num == true) {
				//手机号已存在，不可注册
				type.put("isTel",0);
			}else {
				//手机号不存在，可以注册
				type.put("isTel", 1);
			}
			
			response.getWriter().append(type.toString());
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
