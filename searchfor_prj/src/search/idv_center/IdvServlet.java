package search.idv_center;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import search.entity.SearchPeopleBean;
import search.record.dao.SearchPeopleDao;

/**
 * Servlet implementation class IdvServlet
 */
@WebServlet("/IdvServlet")
public class IdvServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdvServlet() {
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
		Gson gson=new Gson();
		InputStreamReader is = new InputStreamReader(request.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(is);
		StringBuffer str = new StringBuffer();
        String line = null;
        while (null!=(line = bufferedReader.readLine()))  {
            str.append(line);
        }
        is.close();
        String jsonStr =new String(str.toString().getBytes("utf-8"),"UTF-8");

        int user_id= gson.fromJson(jsonStr, Integer.class);
        
        List<SearchPeopleBean> sps=new SearchPeopleDao().findSearchPeopleBeans(user_id);
  
        Gson gson_2 = new Gson();
		String jsonStr_2= gson_2.toJson(sps);
		
		response.getWriter().append(jsonStr_2);
	}

}
