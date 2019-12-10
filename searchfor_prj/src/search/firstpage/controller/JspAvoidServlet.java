package search.firstpage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import search.entity.PageText;
import search.firstpage.service.FirstPageService;
import search.util.Page;

/**
 * Servlet implementation class JspAvoidServlet
 */
@WebServlet("/jspavoid")
public class JspAvoidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JspAvoidServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		FirstPageService service = new FirstPageService();
		Page<PageText> page = null;
		if(request.getParameter("num")==null) {
			page = service.list(1, 1, 5);
		}else {
			page = service.list(1, Integer.parseInt(request.getParameter("num")), 5);
		}
		
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("/precaution.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
