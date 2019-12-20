package search.record.controller.searchpeople;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import search.entity.SearchPeopleBean;
import search.record.dao.SearchPeopleDao;
import search.util.ImageUtil;

/**
 * Servlet implementation class AddSearchPeopleServlet
 */
@WebServlet("/AddSearchPeopleServlet")
public class AddSearchPeopleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSearchPeopleServlet() {
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
		System.out.println("上传家寻亲人信息的图片文件");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String jsonStr = request.getParameter("image");
		String jsontextstr = request.getParameter("infor");
		List<byte[]> bytes = new ArrayList();
		Gson gson = new Gson();
		bytes = gson.fromJson(jsonStr, new TypeToken<List<byte[]>>() {
		}.getType());
		SearchPeopleBean spb = gson.fromJson(jsontextstr, SearchPeopleBean.class);
		System.out.println(gson.toJson(spb));
		// 将字节数组转换成图片并保存在upload文件夹下
		ImageUtil iu = new ImageUtil();
		String[] imgpaths = new String[bytes.size()];// 存放图片路径
		for (int i = 0; i < bytes.size(); i++) {
			Long time = System.currentTimeMillis();
			String path = this.getServletContext().getRealPath("/upload/") + time + ".png";
			imgpaths[i] = "/upload/" + time + ".png";
			iu.byteToImage(bytes.get(i), path);
		}
		// 辨别寻亲登记是哪一个用户写的
		HttpSession session = request.getSession();// 获取session
		int user_id = 0;
		if (session.getAttribute("user_id") != null) {
			user_id = (int) session.getAttribute("user_id");// 获得当前登录用户的id
		}
		// 上传信息至数据库
		SearchPeopleDao spd = new SearchPeopleDao();
		spd.judgeImage(spb, imgpaths, user_id);//user_id先设为3
		// 上传成功，返回给客户端信息
		response.getWriter().append("上传成功");

	}

}
