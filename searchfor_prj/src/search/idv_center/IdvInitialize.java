package search.idv_center;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.google.gson.Gson;

import search.entity.User;
import search.user.dao.UserDao;
import search.user.service.UserService;
import search.util.MessageDisgest;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int user_id = 0;
		User user = new User();
		InputStream is = request.getInputStream();

		byte[] buffer = new byte[255];
		int len = is.read(buffer);

		if (len != -1) {
			String json = new String(buffer, 0, len);
			Gson gson = new Gson();
			user_id = gson.fromJson(json,Integer.class);
			user = new UserDao().serachUser(user_id);
			String u = gson.toJson(user);
			System.out.println(u);
			response.getWriter().append(u);
		}
	}
}
