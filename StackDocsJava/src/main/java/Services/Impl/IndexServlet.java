package Services.Impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Configuration.StartupContainer;
import Models.DTO.TopicsFrontDTO;
import Services.IFrontService;
import eu.lestard.easydi.EasyDI;
import Models.Topic;

/**
 * Servlet implementation class IndexServlet
 */

@WebServlet(urlPatterns = "/main") //yra ivesta i web.xml rankiniu budu
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private IFrontService frontService;
	private TopicsFrontDTO dto;
	private String topic;
	private int currentLanguageId;
	private int pageNumber;
	private EasyDI di;

	public IndexServlet(EasyDI in) {
//		frontService = StartupContainer.easyDI.getInstance(FrontServiceImp.class);
		di = in;
		frontService = di.getInstance(FrontServiceImp.class);
	}

	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String getParamSearch = request.getParameter("search");
		String getParamChange = request.getParameter("change");

		if (getParamSearch != null) { // jei atliekame paieska
			String getParamLang = request.getParameter("language");
			topic = request.getParameter("topic");
			currentLanguageId = Integer.parseInt(getParamLang);

			if (topic.equals("0")) {
				topic = "";
			}
			pageNumber = 1;
			dto = frontService.getTopicsByLanguageId(currentLanguageId, topic);
		}

		if (getParamChange != null) { // jei keiciame puslapi
			String getParamPageNumber = request.getParameter("page");
			pageNumber = Integer.parseInt(getParamPageNumber);
		}

		Map<Integer, String> topicMap = new HashMap<>();
		Map<Integer, String> languageMap = new HashMap<>();
		if (dto != null) {
			if (dto.is_Succcess()) {

				List<Topic> topics = dto.get_Topics();
				// Atvaizduojame reikiamo puslapio temas
				for (int i = (pageNumber - 1) * 10; i < pageNumber * 10 && i < topics.size(); i++) {
					topicMap.put(topics.get(i).get_TopicId(), topics.get(i).get_TopicTitle());
					languageMap.put(topics.get(i).get_TopicId(),
							topics.get(i).get_LanguageTitle() != null ? topics.get(i).get_LanguageTitle() + " | " : "");
				}

			} else {

				topicMap.put(null, dto.get_Message());
				languageMap.put(null, null);

			}
		}
		request.setAttribute("topic", topic);
		request.setAttribute("languageId", currentLanguageId);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("numberOfPages", countNumberOfPages());
		request.setAttribute("topicMap", topicMap);
		request.setAttribute("languageMap", languageMap);
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
		System.out.println("################CIA TURI KEIKTIS>>>>>");
		System.out.println(dto.get_Message());
		System.out.println(dto.is_Succcess());
		System.out.println("################CIA TURI KEIKTIS>>>>>");
		if (dto != null) {
			int numberOfTopics = dto.get_Topics().size();
			if (numberOfTopics % 10 == 0) {
				return numberOfTopics / 10;
			} else {
				return numberOfTopics / 10 + 1;
			}
		}
		return 0;
	}


}
