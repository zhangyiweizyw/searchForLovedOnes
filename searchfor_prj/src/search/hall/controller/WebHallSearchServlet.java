package search.hall.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import search.entity.Basic_information;
import search.hall.service.HallService;
import search.util.Page;

/**
 * Servlet implementation class WebHallSearchServlet
 * 专门给网页端设的servlet
 */
@WebServlet("/WebHall")
public class WebHallSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebHallSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 相应网页端请求，在数据库中取全部数据出来
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("夭寿啦， WebHall被调用了！！！");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HallService hallService=new HallService();
//		List<Basic_information> allData=hallService.findAllDataService();//获取数据库中四张表的全部数据
		Page<Basic_information> page=new Page<Basic_information>();
		if(request.getParameter("num")==null) {//如果是第一页
			page = hallService.list(1, 6);
		}else {
			page = hallService.list(Integer.parseInt(request.getParameter("num")), 6);
		}
		
		//跳转页面
		request.setAttribute("page", page);
		request.getRequestDispatcher("/hall.jsp").forward(request, response);
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
