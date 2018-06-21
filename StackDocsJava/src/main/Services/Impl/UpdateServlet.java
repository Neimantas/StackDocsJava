package Services.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Configuration.StartupContainer;
import Models.Topic;
import Models.DTO.TopicsFrontDTO;
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

		if (dto.is_Succcess()) {

			List<Topic> topics = dto.get_Topics();
			for (Topic t : topics) {
				languageId = t.get_LanguageId();
				language = t.get_LanguageTitle();
				topic = t.get_TopicTitle();
				introduction = t.get_IntroductionHtml();
				syntax = t.get_SyntaxHtml();
				parameters = t.get_ParametersHtml();
				remarks = t.get_RemarksHtml();
			}

		} else {
			content.add(dto.get_Message());
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
		String topicId = request.getParameter("topic");
		
		//surenkama info ir siunciama i update CRUD'a
		
//		response.sendRedirect("/StackDocsJava/read");
		request.setAttribute("topic", topicId);
		System.out.println("Tarpine stotele");
		response.sendRedirect("/StackDocsJava/read");
	}

}
