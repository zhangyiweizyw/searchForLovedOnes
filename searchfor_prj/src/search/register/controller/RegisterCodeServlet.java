package search.register.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

import search.util.AliyunSmsUtils;

/**
 * Servlet implementation class RegisterCodeServlet
 */
@WebServlet("/RegisterCodeServlet")
public class RegisterCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//实现发送短信验证码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); // 转码
		
		//获取用户输入手机号、验证码
		String phonenum = request.getParameter("phonenum");
		String code = request.getParameter("code");//获取验证码
		System.out.println(code);
		System.out.println(phonenum);
		
		AliyunSmsUtils utils = new AliyunSmsUtils();
	    utils.setNewcode();
	    String sendCode = Integer.toString(utils.getNewcode());//生成要发送的验证码
	    System.out.println("要发送的验证码为："+sendCode);
	    HttpSession sess = request.getSession();
	    
	    try {
	    	SendSmsResponse res = utils.sendSms(phonenum, sendCode);//要发送的电话和验证码
			
			if(sendCode.equals(sendCode)) {
				//验证码提交正确，存入session
				sess.setAttribute("session", "1");
			}else {
				sess.setAttribute("session","0");
			}
	    }catch(Exception e) {
	    	e.printStackTrace();
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
