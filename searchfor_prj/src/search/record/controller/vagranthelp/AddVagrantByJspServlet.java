package search.record.controller.vagranthelp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import search.entity.Vagrant;
import search.record.dao.VagrantDao;

/**
 * Servlet implementation class AddVagrantByJspServlet
 */
@WebServlet("/AddVagrantByJspServlet")
public class AddVagrantByJspServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddVagrantByJspServlet() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vname = request.getParameter("vname");
		System.out.println(vname);
		String vage = request.getParameter("vage");
		String vbegintime = request.getParameter("vbegintime");
		String vtfamily = request.getParameter("vtfamily");
		String vdfeature = request.getParameter("vdfeature");
		String vfadress = request.getParameter("vfadress");
		String vphone = request.getParameter("vphone");
		String sex = request.getParameter("vsex");
		System.out.println(sex);
		// 获取上传文件
		Part part = request.getPart("file0");
		// 获取文件信息
		String name = request.getParameter("file0");
		System.out.println(name);
		// 获取文件后缀
		/*
		 * String str=name.substring(name.lastIndexOf("."), name.length()-1);
		 * System.out.println("文件后缀名"+str); Long
		 * time=System.currentTimeMillis(); String
		 * path=this.getServletContext().getRealPath("/upload/") + time+ str;
		 * System.out.println("path"+path);
		 */

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		List<Map<String, String>> datas = new ArrayList<>();// 用来存储普通格式的信息
		Map<String, String> maps = new HashMap<>();
		List<String> imgpaths = new ArrayList<>();// 用来存储页面上传的图片在服务器端的存储路径
		FileItemFactory factory = new DiskFileItemFactory();

		// 创建文件上传处理器
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 开始解析请求信息
		List items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		// 对所有请求信息进行判断
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			// 信息为普通的格式
			if (item.isFormField()) {
				String fieldName = item.getFieldName();
				String value = item.getString("UTF-8");
				System.out.println("filedname" + fieldName);
				System.out.println("value" + value);
				maps.put(fieldName, value);
				// datas.add(map);
			}
			// 信息为文件格式
			else {
				String fileName = item.getName();
				System.out.println("文件名" + fileName);
				int index = fileName.lastIndexOf(".");
				String fileformat = fileName.substring(index + 1);
				System.out.println("realFileName" + fileformat);
				if (!fileName.equals("")) {
					System.out.println("文件不为空");
					Long time = System.currentTimeMillis();
					// 上传到upload文件夹下
					String basePath = this.getServletContext().getRealPath("/upload");
					String imgname = time + "." + fileformat;
					String imgpath = "/upload/" + imgname;
					imgpaths.add(imgpath);
					File file = new File(basePath, imgname);
					try {
						item.write(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
		// 上传普通信息和图片路径至数据库
		String vname = maps.get("vname");
		System.out.println(vname);
		String vage = maps.get("vage");
		String vbegintime = maps.get("vbegintime");
		String vtfamily = maps.get("vtfamily");
		String vdfeature = maps.get("vdfeature");
		String vfadress = maps.get("vfadress");
		String vphone = maps.get("vphone");
		String sex = maps.get("vsex");
		Vagrant v = new Vagrant(vname, sex, vage, vfadress, vbegintime, vtfamily, vdfeature, vphone);
		String[]imagepaths=new String[imgpaths.size()];
		for(int i=0;i<imgpaths.size();i++){
			imagepaths[i]=imgpaths.get(i);
		}
		// 辨别寻亲登记是哪一个用户写的
		ServletContext application = this.getServletContext();// 获取application
		int user_id=0;
		if(application.getAttribute("user_id")!=null){
			user_id = (int) application.getAttribute("user_id");// 获得当前登录用户的id
		}
		System.out.println("user_id"+user_id);
		VagrantDao vd = new VagrantDao();
		vd.judgeImage(v, imagepaths, 1);
		//返回寻人大厅界面
		response.sendRedirect("hall.jsp");
	}
}