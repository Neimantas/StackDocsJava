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

	private IFrontService _frontService;
	private TopicsFrontDTO _topicsFrontDTO;
	private String _topic;
	private int _currentLanguageId;
	private int _pageNumber;

	public IndexServlet() {
		_frontService = StartupContainer.easyDI.getInstance(FrontServiceImp.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String getParamSearch = request.getParameter("search");
		String getParamChange = request.getParameter("change");
		String getParamRemove = request.getParameter("rem");
		String getParamBack = request.getParameter("back");
		String getParamUpdate = request.getParameter("update");

		// renkam info dropdown language uzpildymui
		TopicsFrontDTO dto2 = _frontService.getTopicsByLanguageId(0, "");
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
			_topicsFrontDTO = null;
			_topic = null;
			_currentLanguageId = 0;
		}

		if (getParamSearch != null) { // jei atliekame paieska
			String getParamLang = request.getParameter("language");
			_topic = request.getParameter("topic");
			_currentLanguageId = Integer.parseInt(getParamLang);

			if (_topic.equals("0")) {
				_topic = "";
			}

			if (getParamRemove == null) {
				_pageNumber = 1;
			}

			_topicsFrontDTO = _frontService.getTopicsByLanguageId(_currentLanguageId, _topic);
		}

		if (getParamRemove != null) { // jei triname
			System.out.println(_frontService.deleteTopic(Integer.parseInt(getParamRemove)).message);
			_topicsFrontDTO = _frontService.getTopicsByLanguageId(_currentLanguageId, _topic);
		}

		if (getParamUpdate != null) { // po topic koregavimo
			_topicsFrontDTO = _frontService.getTopicsByLanguageId(_currentLanguageId, _topic);
		}

		if (getParamChange != null) { // jei keiciame puslapi
			String getParamPageNumber = request.getParameter("page");
			_pageNumber = Integer.parseInt(getParamPageNumber);
		}

		Map<Integer, String> topicMap = new HashMap<>();
		Map<Integer, String> languageMap = new HashMap<>();
		if (_topicsFrontDTO != null) {
			if (_topicsFrontDTO.success) {

				List<Topic> topics = _topicsFrontDTO.topics;
				// Atvaizduojame reikiamo puslapio temas
				for (int i = (_pageNumber - 1) * 10; i < _pageNumber * 10 && i < topics.size(); i++) {
					topicMap.put(topics.get(i).topicId, topics.get(i).topicTitle);
					languageMap.put(topics.get(i).topicId,
							topics.get(i).languageTitle != null ? topics.get(i).languageTitle + " | " : "");
				}

			} else {
				topicMap.put(null, _topicsFrontDTO.message);
				languageMap.put(null, null);
			}
		}
		request.setAttribute("topic", _topic);
		request.setAttribute("languageId", _currentLanguageId);
		request.setAttribute("pageNumber", _pageNumber);
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

		if (_topicsFrontDTO != null && _topicsFrontDTO.success) {
			int numberOfTopics = _topicsFrontDTO.topics.size();
			if (numberOfTopics % 10 == 0) {
				return numberOfTopics / 10;
			} else {
				return numberOfTopics / 10 + 1;
			}
		}
		return 0;
	}

}
