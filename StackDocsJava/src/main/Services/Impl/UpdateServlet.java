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
	private String _language;
	private int _languageId;
	private String _topic;
	private String _introduction;
	private String _syntax;
	private String _parameters;
	private String _remarks;

	public UpdateServlet() {
		_frontService = StartupContainer.easyDI.getInstance(FrontServiceImp.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		_topicId = request.getParameter("topic");

		TopicsInfoFrontDTO dto = _frontService.getTopicInfoByTopicId(Integer.parseInt(_topicId));

		List<String> content = new ArrayList<String>();

		if (dto.succcess) {

			List<Topic> topics = dto.topicsInfo;
			for (Topic t : topics) {
				_languageId = t.languageId;
				_language = t.languageTitle;
				_topic = t.topicTitle;
				_introduction = t.introductionHtml;
				_syntax = t.syntaxHtml;
				_parameters = t.parametersHtml;
				_remarks = t.remarksHtml;
			}

		} else {
			content.add(dto.message);
		}

		request.setAttribute("language", _language);
		request.setAttribute("languageId", _languageId);
		request.setAttribute("topicId", _topicId);
		request.setAttribute("topic", _topic);
		request.setAttribute("introduction", _introduction);
		request.setAttribute("syntax", _syntax);
		request.setAttribute("parameters", _parameters);
		request.setAttribute("remarks", _remarks);

		request.getRequestDispatcher("update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// surenkama info ir siunciama i update CRUD'a
		List<String> list = new ArrayList<String>();

		list.add(_languageId + "");
		list.add(request.getParameter("topic"));
		list.add(_topicId);
		list.add(request.getParameter("introduction"));
		list.add(request.getParameter("syntax"));
		list.add(request.getParameter("parameters"));
		list.add(request.getParameter("remarks"));

		_frontService.updateTopic(list);

		request.setAttribute("topic", _topicId);
		response.sendRedirect("/StackDocsJava/read?update=true");
	}

}
