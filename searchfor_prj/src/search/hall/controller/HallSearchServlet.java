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
//import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import search.entity.Basic_information;
import search.entity.User;
import search.hall.service.HallService;
import search.util.DBUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
			System.out.println("客户端一开始传给服务端的的数据是"+typeStream);
			JSONObject json = new JSONObject(typeStream);
			String wtf=json.getString("client");
			System.out.println("经解析过后的数据是"+wtf);
			HallService hallService=new HallService();
			
			//这里需要判断一下类型，再作操作
			//判断这个String类型的typeStream里是否全是数字，如果是的话即把它转换成int类型并进行id查询
			//如果不全是的话，就进行名字查询
			
			
			if(true==judge(wtf)){
				System.out.println("客户端传过来的是数字，即进行id查询");
				int typenumber = json.getInt("client"); 
				System.out.println("已接受到客户端的数据是: "+typenumber);//以此到数据库中搜寻数据
				
				List<Basic_information> basic=hallService.findBasicByIDService(typenumber);//根据id进行搜索

				Gson gson=new Gson();
				String jsonString=gson.toJson(basic);
				System.out.println("查询到的数据是"+jsonString);
				response.getWriter().append(jsonString);
			}else if(false==judge(wtf)) {
				System.out.println("客户端传过来的是字符串，即进行名字查询");
				String typename=json.getString("client");
				System.out.println("已接受到的客户端的数据是："+typename);
				List<Basic_information> basic=hallService.findBasicByNameService(typename);//根据id进行搜索

				Gson gson=new Gson();
				String jsonString=gson.toJson(basic);
				System.out.println("查询到的数据是"+jsonString);
				response.getWriter().append(jsonString);

			}





		}
	}


	public boolean judgetype(String typeStream) {
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(typeStream);
        if( !isNum.matches() ){
            return false;
        }
        return true;
	}
	public boolean judge(String typeStream) {
		String code=typeStream.trim();//获取输入框字符串
        //判断输入的类型
        Pattern pNumber = Pattern.compile("[0-9]*"); //数字
        Pattern pLetter = Pattern.compile("[a-zA-Z]*"); //字母
        Pattern pChinese = Pattern.compile("[\\u4e00-\\u9fa5]*"); //汉字
        if (pNumber.matcher(code).matches()) {//启用id搜索
//            Log.e("测试", "走到了这里，说明是数字");
            return true;
           
        } else if (pLetter.matcher(code).matches()||pChinese.matcher(code).matches()) {//启用人名搜索
//            Log.e("测试", "走到了这里，说明是字母或汉字");
           return false;

        }
        return false;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
