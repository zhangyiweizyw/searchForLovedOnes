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
 * Servlet implementation class IdvCenterServlet
 */
@WebServlet("/IdvCenterServlet")
public class IdvCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdvCenterServlet() {
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
        String jsonStr=jsonStr1.substring(0, jsonStr1.length() -1);
        String type = jsonStr1.substring(jsonStr1.length()-1, jsonStr1.length()) ;
        
        User object=gson.fromJson(String.valueOf(jsonStr), new TypeToken<User>() {}.getType());
        if(type.equals("1")){
		new UserDao().changeUserName(object.getUserTel(),object.getUserName());
        }
        if(type.equals("2")){
        	new UserDao().changeUserTel(object.getUserEmail(), object.getUserTel());
        }
        if(type.equals("3")){
        	new UserDao().changeUserEmail(object.getUserTel(), object.getUserEmail());
        }
	}

}
