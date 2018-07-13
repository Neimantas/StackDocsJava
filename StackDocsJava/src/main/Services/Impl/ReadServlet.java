package Services.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Configuration.StartupContainer;
import Models.Business.Example;
import Models.Business.Topic;
import Models.DTO.ExamplesFrontDTO;
import Models.DTO.TopicsInfoFrontDTO;
import Services.IFrontService;

public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFrontService _frontService;
	private TopicsInfoFrontDTO _topicsInfoFrontDTO;
	private ExamplesFrontDTO _examplesFrontDTO;
	private String _topicId;
	private String _topic;
	private String _introduction;
	private String _syntax;
	private String _parameters;
	private String _remarks;
	private String _update;

	public ReadServlet() {
		_frontService = StartupContainer.easyDI.getInstance(FrontServiceImp.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String open = request.getParameter("open");
		_update = request.getParameter("update");

		if (_update != null) {
			_topicsInfoFrontDTO = _frontService.getTopicInfoByTopicId(Integer.parseInt(_topicId));
		}

		if (open != null) {
			_topicId = request.getParameter("topic");
			_topicsInfoFrontDTO = _frontService.getTopicInfoByTopicId(Integer.parseInt(_topicId));
		}

		List<String> content = new ArrayList<String>();

		if (_topicsInfoFrontDTO.succcess) {

			List<Topic> topics = _topicsInfoFrontDTO.topicsInfo;
			for (Topic t : topics) {

				_topic = t.topicTitle;
				_introduction = t.introductionHtml;
				_syntax = t.syntaxHtml;
				_parameters = t.parametersHtml;
				_remarks = t.remarksHtml;
			}

		} else {
			content.add(_topicsInfoFrontDTO.message);
		}

		if (open != null) {
			_examplesFrontDTO = _frontService.getExamplesByID(Integer.parseInt(_topicId));
		}

		List<String> contExamples = new ArrayList<String>();

		if (_examplesFrontDTO.success) {

			List<Example> examples = _examplesFrontDTO.examples;
			for (Example e : examples) {

				contExamples.add(e.bodyHtml);

			}

		} else {
			contExamples.add(_examplesFrontDTO.message);
		}

		request.setAttribute("topicId", _topicId);
		request.setAttribute("topic", _topic);
		request.setAttribute("introduction", _introduction);
		request.setAttribute("syntax", _syntax);
		request.setAttribute("parameters", _parameters);
		request.setAttribute("remarks", _remarks);
		request.setAttribute("example", contExamples);

		request.getRequestDispatcher("read.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (_update != null) {
			response.sendRedirect("/StackDocsJava/main?update=true&topic=" + _topic);
		} else {
			response.sendRedirect("/StackDocsJava/main?back=true");
		}
	}

}