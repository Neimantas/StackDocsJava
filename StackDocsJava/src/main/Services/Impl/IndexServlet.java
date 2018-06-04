package Services.Impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.DTO.TopicsFrontDTO;
import Services.IFrontService;
import Models.Topic;

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
		IFrontService fs = new FrontServiceImp();
		TopicsFrontDTO dto = fs.getTopicsByLanguageId(Integer.parseInt(getParamLang));
//		HigherServiceImpl hs = new HigherServiceImpl();
//		TopicsDTO dto = hs.getTopicsByLanguageId(5); //hardCode
		List<String> topicList = new ArrayList<>();
		
		if(dto.is_Succcess()) {
			
			List<Topic> topics = dto.get_Topics();
			for(Topic t : topics) {
				topicList.add(t.get_TopicTitle());
			}
			
		} else {
			
			topicList.add(dto.get_Message());
			
		}
		//int languageValParam = request.get;
//		if (getParamLang.equals("")) {
//			Language selectedLang = new Language("", 0);
//		} else {
//			
//		}
		//  <Mock>
//		List<String> topicList = new ArrayList<String>() {{add("One"); add("Two"); add("Three");}} ;
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
