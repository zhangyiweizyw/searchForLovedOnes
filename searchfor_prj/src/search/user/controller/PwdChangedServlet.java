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
import search.util.MessageDisgest;



/**
 * Servlet implementation class PwdChangedServlet
 */
@WebServlet("/PwdChangedServlet")
public class PwdChangedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdChangedServlet() {
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
			
			//接收用户传来的修改的密码和电话
			JSONObject json = new JSONObject(userStream);
			String tel = json.getString("tel");
			String newPwd = json.getString("newPwd");
			System.out.println(tel+newPwd);
			
			//对密码实现加密操作
			MessageDisgest messageDisgest = new MessageDisgest();
			String secretPwd = messageDisgest.secretPassword(newPwd);
			
			UserService userService = new UserService();
			int i = userService.changeUserService(tel, secretPwd);
			System.out.println(i);//打印i的值，若为1，则修改成功
			
			JSONObject type = new JSONObject();
			if(i == 1) {
				type.put("isUpdate", "1");
			}else {
				type.put("isUpdate", "0");
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
