package Services.Impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Configuration.StartupContainer;
import Models.Business.Topic;
import Models.DTO.TopicsFrontDTO;
import Services.IFrontService;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFrontService frontService;
	private TopicsFrontDTO dto;
	private String topic;
	private int currentLanguageId;
	private int pageNumber;

	public IndexServlet() {
		frontService = StartupContainer.easyDI.getInstance(FrontServiceImp.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String getParamSearch = request.getParameter("search");
		String getParamChange = request.getParameter("change");
		String getParamRemove = request.getParameter("rem");
		String getParamBack = request.getParameter("back");
		String getParamUpdate = request.getParameter("update");

		// renkam info dropdown language uzpildymui
		TopicsFrontDTO dto2 = frontService.getTopicsByLanguageId(0, "");
		Map<Integer, String> languageDDMap = new HashMap<>();
		if (dto2.success) {
			List<Topic> topics = dto2.topics;
			for (Topic t : topics) {
				languageDDMap.put(t.languageId, t.languageTitle);
			}
		} else {
			languageDDMap.put(null, null);
		}

		if (getParamSearch == null && getParamChange == null && getParamRemove == null && getParamBack == null
				&& getParamUpdate == null) { // jei uzkrauname puslapi is naujo neperkrovus serverio
			dto = null;
			topic = null;
			currentLanguageId = 0;
		}

		if (getParamSearch != null) { // jei atliekame paieska
			String getParamLang = request.getParameter("language");
			topic = request.getParameter("topic");
			currentLanguageId = Integer.parseInt(getParamLang);

			if (topic.equals("0")) {
				topic = "";
			}

			if (getParamRemove == null) {
				pageNumber = 1;
			}

			dto = frontService.getTopicsByLanguageId(currentLanguageId, topic);
		}

		if (getParamRemove != null) { // jei triname
			System.out.println(frontService.deleteTopic(Integer.parseInt(getParamRemove)).message);
			dto = frontService.getTopicsByLanguageId(currentLanguageId, topic);
		}

		if (getParamUpdate != null) { // po topic koregavimo
			dto = frontService.getTopicsByLanguageId(currentLanguageId, topic);
		}

		if (getParamChange != null) { // jei keiciame puslapi
			String getParamPageNumber = request.getParameter("page");
			pageNumber = Integer.parseInt(getParamPageNumber);
		}

		Map<Integer, String> topicMap = new HashMap<>();
		Map<Integer, String> languageMap = new HashMap<>();
		if (dto != null) {
			if (dto.success) {

				List<Topic> topics = dto.topics;
				// Atvaizduojame reikiamo puslapio temas
				for (int i = (pageNumber - 1) * 10; i < pageNumber * 10 && i < topics.size(); i++) {
					topicMap.put(topics.get(i).topicId, topics.get(i).topicTitle);
					languageMap.put(topics.get(i).topicId,
							topics.get(i).languageTitle != null ? topics.get(i).languageTitle + " | " : "");
				}

			} else {
				topicMap.put(null, dto.message);
				languageMap.put(null, null);
			}
		}
		request.setAttribute("topic", topic);
		request.setAttribute("languageId", currentLanguageId);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("numberOfPages", countNumberOfPages());
		request.setAttribute("topicMap", topicMap);
		request.setAttribute("languageMap", languageMap);
		request.setAttribute("languageDD", languageDDMap);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private int countNumberOfPages() {

		if (dto != null && dto.success) {
			int numberOfTopics = dto.topics.size();
			if (numberOfTopics % 10 == 0) {
				return numberOfTopics / 10;
			} else {
				return numberOfTopics / 10 + 1;
			}
		}
		return 0;
	}

}
