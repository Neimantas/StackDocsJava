
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Language;

/**
 * Servlet implementation class IndexServlet
 */

//@WebServlet(urlPatterns = "/main")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String getParamLang = request.getParameter("language");
		System.out.println(getParamLang);
		//int languageValParam = request.get;
		if (getParamLang.equals("")) {
			Language selectedLang = new Language("", 0);
		} else {
			
		}
		//  <Mock>
		List<String> topicList = new ArrayList<String>() {{add("One"); add("Two"); add("Three");}} ;
		//  </Mock>
		
		request.setAttribute("topicList", topicList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		

//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
