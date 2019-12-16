package search.record.controller.vagranthelp;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import search.entity.Vagrant;
import search.record.dao.VagrantDao;
import search.util.ImageUtil;


/**
 * Servlet implementation class AddVagrantServlet
 */
@WebServlet("/AddVagrantServlet")
public class AddVagrantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddVagrantServlet() {
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
		System.out.println(this.getServletContext().getRealPath("/"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("上传流浪者信息的图片文件");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String jsonStr=request.getParameter("image");
		String jsontextstr=request.getParameter("infor");
		List<byte[]> bytes = new ArrayList();
		Gson gson = new Gson();
		bytes = gson.fromJson(jsonStr, new TypeToken<List<byte[]>>() {
		}.getType());
		Vagrant v=gson.fromJson(jsontextstr, Vagrant.class);
		System.out.println("流浪者姓名"+v.getName());
		// 将字节数组转换成图片并保存在upload文件夹下
		ImageUtil iu=new ImageUtil();
		String[]imgpaths=new String[bytes.size()];//存放图片路径
		for(int i=0;i<bytes.size();i++){
			Long time=System.currentTimeMillis();
			String path = this.getServletContext().getRealPath("/upload/") + time+ ".png";
			imgpaths[i]="/upload/"+time+".png";
			//String path = "/upimg/img/"+ time+ ".png";
			//imgpaths[i]="/upimg/img/"+time+".png";
//			imgpaths[i]="D:/xunqinimg/"+time+".png";
			iu.byteToImage(bytes.get(i), path);
		}
		//辨别寻亲登记是哪一个用户写的
		ServletContext application = this.getServletContext();// 获取application
		int user_id=(int) application.getAttribute("user_id");//获得当前登录用户的id
		//上传信息至数据库
		VagrantDao vd=new VagrantDao();
		vd.judgeImage(v, imgpaths,user_id);
		//上传成功，返回给客户端信息
		response.getWriter().append("上传成功");

	}
}

