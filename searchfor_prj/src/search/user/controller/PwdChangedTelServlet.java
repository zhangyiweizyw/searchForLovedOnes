package search.user.controller;

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
 * Servlet implementation class PwdChangedTelServlet
 */
@WebServlet("/PwdChangedTelServlet")
public class PwdChangedTelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdChangedTelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//获取输入流
		InputStream is = request.getInputStream();
		byte[] buffer = new byte[255];
		int len = is.read(buffer);
		if(len != -1) {
			String userStream = new String(buffer,0,len);
			
			//接收用户传来的电话,并判断是否存在于数据库中
			JSONObject json = new JSONObject(userStream);
			String tel = json.getString("searchTel");
			
			System.out.println(tel);
			UserService userService = new UserService();
			boolean type = userService.judgeUserTelService(tel);
			
			System.out.println(type);
			JSONObject object = new JSONObject();
			if(type) {
				object.put("isExist", "1");
			}else {
				object.put("isExist", "0");
			}
			
			response.getWriter().append(object.toString());
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
