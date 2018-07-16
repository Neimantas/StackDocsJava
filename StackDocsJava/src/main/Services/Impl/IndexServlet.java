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
import Models.Business.IndexServletParameters;
import Models.Business.Language;
import Models.Business.Topic;
import Models.Const.Settings;
import Models.DTO.DeleteTableDTO;
import Models.DTO.LanguageTagFrontDTO;
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

		IndexServletParameters inputParameters = new IndexServletParameters(request);

		clearServletParametersIfPostBackExecuted(inputParameters);

		getOutputDataIfSearchButtonClicked(inputParameters);

		updateTopicListIfUpdateExecuted(inputParameters);

		deleteTopicAndUpdateTopicListIfDeleteExecuted(inputParameters);

		setPageNumberIfPageButtonClicked(inputParameters);

		setRequestParams(request);

		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void clearServletParametersIfPostBackExecuted(IndexServletParameters indexServletParameters) {
		if (indexServletParameters.search == null 
				&& indexServletParameters.udate == null
				&& indexServletParameters.remove == null 
				&& indexServletParameters.changePage == null
				&& indexServletParameters.back == null) {
			_topicsFrontDTO = null;
			_topic = null;
			_currentLanguageId = 0;
		}
	}

	private void getOutputDataIfSearchButtonClicked(IndexServletParameters inputParameters) {
		if (inputParameters.search != null) {
			_topic = inputParameters.topic;
			_currentLanguageId = Integer.parseInt(inputParameters.language);

			if (_topic.equals("0")) {
				_topic = "";
			}

			if (inputParameters.remove == null) {
				_pageNumber = 1;
			}

			_topicsFrontDTO = _frontService.getTopicsByLanguageId(_currentLanguageId, _topic);
		}
	}

	private void updateTopicListIfUpdateExecuted(IndexServletParameters inputParameters) {
		if (inputParameters.udate != null) {
			_topicsFrontDTO = _frontService.getTopicsByLanguageId(_currentLanguageId, _topic);
		}
	}

	private void deleteTopicAndUpdateTopicListIfDeleteExecuted(IndexServletParameters inputParameters) {
		if (inputParameters.remove != null) {
			DeleteTableDTO deleteTableDTO = _frontService.deleteTopic(Integer.parseInt(inputParameters.remove));
			System.out.println(deleteTableDTO.message);
			_topicsFrontDTO = _frontService.getTopicsByLanguageId(_currentLanguageId, _topic);
		}
	}

	private void setPageNumberIfPageButtonClicked(IndexServletParameters inputParameters) {
		if (inputParameters.changePage != null) {
			_pageNumber = Integer.parseInt(inputParameters.page);
		}
	}

	private void setRequestParams(HttpServletRequest request) {
		request.setAttribute("topic", _topic);
		request.setAttribute("languageId", _currentLanguageId);
		request.setAttribute("pageNumber", _pageNumber);
		request.setAttribute("numberOfPages", countNumberOfPages());
		request.setAttribute("topicMap", createTopicMap());
		request.setAttribute("languageMap", createLanguageMap());
	}

	private int countNumberOfPages() {
		if (_topicsFrontDTO != null && _topicsFrontDTO.success) {
			int numberOfTopics = _topicsFrontDTO.topics.size();
			if (numberOfTopics % Settings.NUMBER_OF_TOPICS_PER_PAGE == 0) {
				return numberOfTopics / Settings.NUMBER_OF_TOPICS_PER_PAGE;
			} else {
				return numberOfTopics / Settings.NUMBER_OF_TOPICS_PER_PAGE + 1;
			}
		}
		return 0;
	}

	private Map<Integer, String> createTopicMap() {
		Map<Integer, String> topicMap = new HashMap<>();
		if (_topicsFrontDTO != null) {
			if (_topicsFrontDTO.success) {

				List<Topic> topics = _topicsFrontDTO.topics;
				// puts only the topics for one page
				for (int i = (_pageNumber - 1) * Settings.NUMBER_OF_TOPICS_PER_PAGE; 
						i < _pageNumber * Settings.NUMBER_OF_TOPICS_PER_PAGE && i < topics.size(); i++) {

					topicMap.put(topics.get(i).topicId, getLanguageTittle(topics, i) + topics.get(i).topicTitle);
				}

			} else {
				topicMap.put(null, _topicsFrontDTO.message);
			}
		}
		return topicMap;
	}

	private String getLanguageTittle(List<Topic> topics, int i) {
		String languageTittle = "";
		if (_currentLanguageId == 0 && !_topic.equals("")) {
			LanguageTagFrontDTO languageTagDTO = _frontService.getLanguageTagByLanguageId(topics.get(i).languageId);
			if (languageTagDTO.success) {
				languageTittle = languageTagDTO.languageTag.get(0).title + " | ";
			}
		} else if (_currentLanguageId == 0) {
			languageTittle = topics.get(i).languageTitle + " | ";
		}
		return languageTittle;
	}

	private Map<Integer, String> createLanguageMap() {
		Map<Integer, String> languageMap = new HashMap<>();
		LanguageTagFrontDTO languageDTO = _frontService.getAllLanguageTag();
		if (languageDTO.success) {
			List<Language> topics = languageDTO.languageTag;
			for (Language t : topics) {
				languageMap.put(t.id, t.title);
			}
		}
		return languageMap;
	}

}
