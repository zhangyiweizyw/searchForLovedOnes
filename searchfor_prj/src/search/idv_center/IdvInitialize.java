package search.idv_center;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import search.entity.User;
import search.user.dao.UserDao;

/**
 * Servlet implementation class IdvInitialize
 */
@WebServlet("/IdvInitialize")
public class IdvInitialize extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdvInitialize() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		int user_id = 0;
		User user=null;
		if (session.getAttribute("user_id") != null) {
			user_id = (int) session.getAttribute("user_id");// 获得当前登录用户的id	
			System.out.println(user_id);
			user=new UserDao().serachUser(user_id);	
			user.setId(user_id);
	}
		 	Gson gson_2 = new Gson();
			String jsonStr_2= gson_2.toJson(user);
			System.out.println(jsonStr_2);
			response.getWriter().append(jsonStr_2);
	}
}
