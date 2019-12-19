package search.record.controller.othersearch;

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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import search.entity.OtherSearchBean;
import search.entity.Vagrant;
import search.record.dao.OtherSearchDao;
import search.record.dao.VagrantDao;

/**
 * Servlet implementation class AddOtherSearchByJspServlet
 */
@WebServlet("/AddOtherSearchByJspServlet")
public class AddOtherSearchByJspServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOtherSearchByJspServlet() {
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

		System.out.println("post");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 用来存储普通格式的信息
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
		String oname = maps.get("oname");
		String osex = maps.get("osex");
		String orelation = maps.get("orelation");
		String oreson = maps.get("oreson");
		String oyname = maps.get("oyname");
		String oysex = maps.get("oysex");
		int oyage = Integer.parseInt(maps.get("oyage"));
		String oyphone = maps.get("oyphone");
		String oyemail = maps.get("oyeamil");
		String oyqq = maps.get("oyqq");
		String oyaddr = maps.get("oyaddr");
		OtherSearchBean o = new OtherSearchBean(oname, osex, oreson, orelation, oyname, oysex, oyage, oyemail, oyphone,
				oyaddr);
		String[] imagepaths = new String[imgpaths.size()];
		for (int i = 0; i < imgpaths.size(); i++) {
			imagepaths[i] = imgpaths.get(i);
		}
		// 辨别寻亲登记是哪一个用户写的
		ServletContext application = this.getServletContext();// 获取application
		int user_id = (int) application.getAttribute("user_id");//
		// 获得当前登录用户的id
		System.out.println("user_id"+user_id);
		OtherSearchDao osd = new OtherSearchDao();
		osd.judgeImage(o, imagepaths, user_id);
		//返回寻人大厅界面
	}

}
