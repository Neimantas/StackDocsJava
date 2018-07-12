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

	IFrontService frontService;
	private String topicId;
	private String language;
	private int languageId;
	private String topic;
	private String introduction;
	private String syntax;
	private String parameters;
	private String remarks;

	public UpdateServlet() {
		frontService = StartupContainer.easyDI.getInstance(FrontServiceImp.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		topicId = request.getParameter("topic");

		TopicsInfoFrontDTO dto = frontService.getTopicInfoByTopicId(Integer.parseInt(topicId));

		List<String> content = new ArrayList<String>();

		if (dto.succcess) {

			List<Topic> topics = dto.topicsInfo;
			for (Topic t : topics) {
				languageId = t.languageId;
				language = t.languageTitle;
				topic = t.topicTitle;
				introduction = t.introductionHtml;
				syntax = t.syntaxHtml;
				parameters = t.parametersHtml;
				remarks = t.remarksHtml;
			}

		} else {
			content.add(dto.message);
		}

		request.setAttribute("language", language);
		request.setAttribute("languageId", languageId);
		request.setAttribute("topicId", topicId);
		request.setAttribute("topic", topic);
		request.setAttribute("introduction", introduction);
		request.setAttribute("syntax", syntax);
		request.setAttribute("parameters", parameters);
		request.setAttribute("remarks", remarks);

		request.getRequestDispatcher("update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// surenkama info ir siunciama i update CRUD'a
		List<String> list = new ArrayList<String>();

		list.add(languageId + "");
		list.add(request.getParameter("topic"));
		list.add(topicId);
		list.add(request.getParameter("introduction"));
		list.add(request.getParameter("syntax"));
		list.add(request.getParameter("parameters"));
		list.add(request.getParameter("remarks"));

		frontService.updateTopic(list);

		request.setAttribute("topic", topicId);
		response.sendRedirect("/StackDocsJava/read?update=true");
	}

}
