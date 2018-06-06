package Services.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

// @WebServlet(urlPatterns = "/main") //yra ivesta i web.xml rankiniu budu
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	IFrontService fs;
	TopicsFrontDTO dto;

	public IndexServlet() {
		super();
		fs = new FrontServiceImp();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String getParamSearch = request.getParameter("search");
		int pageNumber = 1;
		if (getParamSearch != null) { // jei atliekame paieska
			pageNumber = 1;
			String getParamLang = request.getParameter("language");
			String getParamTopic = request.getParameter("topic");
			if (getParamTopic.equals("0")) {
				getParamTopic = "";
			}
			dto = fs.getTopicsByLanguageId(Integer.parseInt(getParamLang), getParamTopic);
		}

		// String getParamPageNumber = request.getParameter("page");
		// pageNumber = Integer.parseInt(getParamPageNumber);

		Map<Integer, String> topicMap = new HashMap<>();

		if (dto.is_Succcess()) {

			List<Topic> topics = dto.get_Topics();
			// Atvaizduojame reikiamo puslapio temas
			for (int i = (pageNumber - 1) * 10; i < pageNumber * 10 && i < topics.size(); i++) {
				topicMap.put(topics.get(i).get_TopicId(), topics.get(i).get_TopicTitle());

			}

		} else {

			topicMap.put(null, dto.get_Message());

		}
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("numberOfPages", countNumberOfPages());
		request.setAttribute("topicMap", topicMap);
		request.getRequestDispatcher("index.jsp").forward(request, response);

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
	
	private int countNumberOfPages() {
		int numberOfTopics = dto.get_Topics().size();
		if (numberOfTopics % 10 == 0) {
			return numberOfTopics / 10;
		} else {
			return numberOfTopics / 10 + 1;
		}
	}

}
