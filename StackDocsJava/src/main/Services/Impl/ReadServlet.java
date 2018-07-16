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
	private Topic _topic;
	private String _update;
	private String _open;

	public ReadServlet() {
		_frontService = StartupContainer.easyDI.getInstance(FrontServiceImp.class);
		_topic = new Topic();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getRequestParameters(request);

		getTopicInfoIfUpdateExecuted();

		getTopicInfoOnOpen(request);

		getExampleInfoOnOpen();

		updateTopicObject();

		setRequestParameters(request);

		request.getRequestDispatcher("read.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (_update != null) {
			response.sendRedirect("/StackDocsJava/main?update=true");
		} else {
			response.sendRedirect("/StackDocsJava/main?back=true");
		}
	}

	private void getRequestParameters(HttpServletRequest request) {
		_open = request.getParameter("open");
		_update = request.getParameter("update");
	}

	private void getTopicInfoIfUpdateExecuted() {
		if (_update != null) {
			_topicsInfoFrontDTO = _frontService.getTopicInfoByTopicId(_topic.topicId);
		}
	}

	private void getTopicInfoOnOpen(HttpServletRequest request) {
		if (_open != null) {
			_topic.topicId = Integer.parseInt(request.getParameter("topic"));
			_topicsInfoFrontDTO = _frontService.getTopicInfoByTopicId(_topic.topicId);
		}
	}

	private void getExampleInfoOnOpen() {
		if (_open != null) {
			_examplesFrontDTO = _frontService.getExamplesByID(_topic.topicId);
		}
	}

	private void updateTopicObject() {
		if (_topicsInfoFrontDTO.succcess && !_topicsInfoFrontDTO.topicsInfo.isEmpty()) {

			_topic = _topicsInfoFrontDTO.topicsInfo.get(0);

		} else {
			_topic.topicTitle = _topicsInfoFrontDTO.message;
		}
	}

	private void setRequestParameters(HttpServletRequest request) {
		request.setAttribute("topic", _topic.topicTitle);
		request.setAttribute("topicId", _topic.topicId);
		request.setAttribute("introduction", _topic.introductionHtml);
		request.setAttribute("syntax", _topic.syntaxHtml);
		request.setAttribute("parameters", _topic.parametersHtml);
		request.setAttribute("remarks", _topic.remarksHtml);
		request.setAttribute("example", createExampleInfoList());
	}

	private List<String> createExampleInfoList() {
		List<String> contExamples = new ArrayList<String>();

		if (_examplesFrontDTO.success) {

			List<Example> examples = _examplesFrontDTO.examples;
			for (Example e : examples) {

				contExamples.add(e.bodyHtml);

			}

		} else {
			contExamples.add(_examplesFrontDTO.message);
		}
		return contExamples;
	}

}