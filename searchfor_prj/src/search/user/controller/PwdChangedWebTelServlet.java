package search.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

import search.user.service.UserService;
import search.util.AliyunSmsUtils;

/**
 * Servlet implementation class PwdChangedWebTelServlet
 */
@WebServlet("/PwdChangedWebTelServlet")
public class PwdChangedWebTelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdChangedWebTelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tel = request.getParameter("phone");
		
		//判断用户电话是否已注册
		UserService userService = new UserService();
		boolean type = userService.judgeUserTelService(tel);
		
		if(type) {
			//号码已经存在，可以发送短信
			//获取短信验证码工具类
			AliyunSmsUtils utils = new AliyunSmsUtils();
		    utils.setNewcode();
		    String sendCode = Integer.toString(utils.getNewcode());//得到要发送的验证码
		    System.out.println("要发送的验证码为："+sendCode);
		    
		    //发送短信
			try {
				SendSmsResponse res = utils.sendSms(tel, sendCode);//要发送的电话和验证码
			} catch (ClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			//号码不存在，无法发送验证码
			response.getWriter().print("<script language='javascript'>alert('该手机号还未注册，请先注册！')</script>");
			response.setHeader("refresh", "1,URL=signup.jsp");
			
		}<a href="${ctx }/PwdChangedWebTelServlet?phone=${phonenum}"
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
