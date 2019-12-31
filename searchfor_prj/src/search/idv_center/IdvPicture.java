package search.idv_center;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import search.entity.User;
import search.user.dao.UserDao;

/**
 * Servlet implementation class IdvPicture
 */
@WebServlet("/IdvPicture")
public class IdvPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdvPicture() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		InputStreamReader is = new InputStreamReader(request.getInputStream(),"UTF-8");
		BufferedReader bufferedReader = new BufferedReader(is);
		StringBuffer str = new StringBuffer();
        String line = null;
        while (null!=(line = bufferedReader.readLine()))  {
            str.append(line);
        }
        is.close();
        String jsonStr1 =new String(str.toString().getBytes("utf-8"),"UTF-8");
        System.out.println(jsonStr1);
       String[] jsonstr=jsonStr1.split("\\|");
        String uri=jsonstr[0];
        String tel=jsonstr[1];
        new UserDao().InsertUrl(tel,uri);
	}

}
