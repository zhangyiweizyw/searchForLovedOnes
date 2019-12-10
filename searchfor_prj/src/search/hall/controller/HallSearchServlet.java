package search.hall.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import search.entity.Basic_information;
import search.entity.User;
import search.hall.service.HallService;
import search.util.DBUtil;
/**
 * Servlet implementation class HallSearchServlet
 */
@WebServlet("/HallSearchServlet")
public class HallSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HallSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		InputStream input=request.getInputStream();
		byte[] buffer=new byte[255];
		int len=input.read(buffer);
		String type;
		if(len!=-1) {//已经到达流末尾而没有可用的字节，则返回-1,即为true
			String typeStream=new String(buffer,0,len);
			
			//将接收到的输入流转换成JSON形式
			JSONObject json = new JSONObject(typeStream);
			int typenumber = json.getInt("type");
			
//			int typenumber=name;
			System.out.println("已接受到客户端的数据是"+typenumber);//以此到数据库中搜寻数据
			HallService hallService=new HallService();
//			int a=Integer.parseInt(typeStream);
			List<Basic_information> basic=hallService.basicService(typenumber);//根据客户端传来的
			
			//把List传给客户端
			Gson gson=new Gson();
			String jsonString=gson.toJson(basic);
			System.out.println("查询到的数据是"+jsonString);
			
			response.getWriter().append(jsonString);
			
			
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
