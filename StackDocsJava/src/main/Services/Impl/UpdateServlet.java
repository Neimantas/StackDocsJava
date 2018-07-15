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
	private String _topicId;
	private Topic topic;

	public UpdateServlet() {
		_frontService = StartupContainer.easyDI.getInstance(FrontServiceImp.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		_topicId = request.getParameter("topic");

		getTopicInfo();

		setRequestParams(request);

		request.getRequestDispatcher("update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		postUpdateInfo(request);

		request.setAttribute("topic", _topicId);

		response.sendRedirect("/StackDocsJava/read?update=true");
	}

	private void postUpdateInfo(HttpServletRequest request) {
		List<String> list = new ArrayList<String>();

		list.add(Integer.toString(topic.languageId));
		list.add(request.getParameter("topic"));
		list.add(_topicId);
		list.add(request.getParameter("introduction"));
		list.add(request.getParameter("syntax"));
		list.add(request.getParameter("parameters"));
		list.add(request.getParameter("remarks"));

		_frontService.updateTopic(list);
	}

	private void getTopicInfo() {
		TopicsInfoFrontDTO dto = _frontService.getTopicInfoByTopicId(Integer.parseInt(_topicId));
		if (dto.succcess) {
			List<Topic> topics = dto.topicsInfo;
			for (Topic t : topics) {
				topic = new Topic();

				topic.languageId = t.languageId;
				topic.languageTitle = t.languageTitle;
				topic.topicTitle = t.topicTitle;
				topic.introductionHtml = t.introductionHtml;
				topic.syntaxHtml = t.syntaxHtml;
				topic.parametersHtml = t.parametersHtml;
				topic.remarksHtml = t.remarksHtml;
			}
		}
	}

	private void setRequestParams(HttpServletRequest request) {
		request.setAttribute("language", topic.languageTitle);
		request.setAttribute("languageId", topic.languageId);
		request.setAttribute("topicId", _topicId);
		request.setAttribute("topic", topic.topicTitle);
		request.setAttribute("introduction", topic.introductionHtml);
		request.setAttribute("syntax", topic.syntaxHtml);
		request.setAttribute("parameters", topic.parametersHtml);
		request.setAttribute("remarks", topic.remarksHtml);
	}

}
