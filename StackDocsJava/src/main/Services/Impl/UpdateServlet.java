package Services.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Configuration.StartupContainer;
import Models.Business.Topic;
import Models.DTO.TopicsInfoFrontDTO;
import Services.IFrontService;

public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFrontService _frontService;
	private Topic _topic;

	public UpdateServlet() {
		_frontService = StartupContainer.easyDI.getInstance(FrontServiceImp.class);
		_topic = new Topic();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		_topic.topicId = Integer.parseInt(request.getParameter("topic"));

		getTopicInfo();

		setRequestParams(request);

		request.getRequestDispatcher("update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		postUpdateInfo(request);

		request.setAttribute("topic", _topic.topicId);

		response.sendRedirect("/StackDocsJava/read?update=true");
	}

	private void postUpdateInfo(HttpServletRequest request) {
		List<String> list = new ArrayList<String>();

		list.add(Integer.toString(_topic.languageId));
		list.add(request.getParameter("topic"));
		list.add(Integer.toString(_topic.topicId));
		list.add(request.getParameter("introduction"));
		list.add(request.getParameter("syntax"));
		list.add(request.getParameter("parameters"));
		list.add(request.getParameter("remarks"));

		_frontService.updateTopic(list);
	}

	private void getTopicInfo() {
		TopicsInfoFrontDTO dto = _frontService.getTopicInfoByTopicId(_topic.topicId);
		if (dto.succcess && !dto.topicsInfo.isEmpty()) {

			_topic = dto.topicsInfo.get(0);

		} else {
			_topic.topicTitle = dto.message;
		}
	}

	private void setRequestParams(HttpServletRequest request) {
		request.setAttribute("language", _topic.languageTitle);
		request.setAttribute("languageId", _topic.languageId);
		request.setAttribute("topicId", _topic.topicId);
		request.setAttribute("topic", _topic.topicTitle);
		request.setAttribute("introduction", _topic.introductionHtml);
		request.setAttribute("syntax", _topic.syntaxHtml);
		request.setAttribute("parameters", _topic.parametersHtml);
		request.setAttribute("remarks", _topic.remarksHtml);
	}
}
