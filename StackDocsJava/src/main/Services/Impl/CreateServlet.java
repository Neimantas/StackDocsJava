package Services.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Configuration.StartupContainer;
import Models.Business.Topic;
import Models.DTO.CreateTableDTO;
import Models.DTO.TopicsFrontDTO;
import Services.IFrontService;

public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IFrontService frontService;

	public CreateServlet() {
		frontService = StartupContainer.easyDI.getInstance(FrontServiceImp.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// renkam info dropdown language uzpildymui
		TopicsFrontDTO dto = frontService.getTopicsByLanguageId(0, "");
		Map<Integer, String> languageDDMap = new HashMap<>();
		if (dto.is_Succcess()) {
			List<Topic> topics = dto.get_Topics();
			for (Topic t : topics) {
				languageDDMap.put(t._LanguageId, t._LanguageTitle);
			}
		} else {
			languageDDMap.put(null, null);
		}

		request.setAttribute("languageDD", languageDDMap);
		request.getRequestDispatcher("create.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> list = new ArrayList<String>();

		list.add(request.getParameter("language"));
		list.add(request.getParameter("topic"));
		list.add(request.getParameter("introduction"));
		list.add(request.getParameter("syntax"));
		list.add(request.getParameter("parameters"));
		list.add(request.getParameter("remarks"));

		CreateTableDTO dto = frontService.createTopic(list);
		System.out.println(dto.getMessage());
		response.sendRedirect("/StackDocsJava/main?search=true&language=" + list.get(0) + "&topic=" + list.get(1));
	}

}
