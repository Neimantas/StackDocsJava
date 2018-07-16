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
import Models.Business.Language;
import Models.Business.Topic;
import Models.DTO.CreateTableDTO;
import Models.DTO.LanguageTagFrontDTO;
import Models.DTO.TopicsFrontDTO;
import Services.IFrontService;

public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFrontService _frontService;

	public CreateServlet() {
		_frontService = StartupContainer.easyDI.getInstance(FrontServiceImp.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("languageMap", createLanguageMap());

		request.getRequestDispatcher("create.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> list = createNewTopic(request);
		
		response.sendRedirect("/StackDocsJava/main?search=true&language=" + list.get(0) + "&topic=" + list.get(1));
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
	
	private List<String> createNewTopic(HttpServletRequest request) {
		List<String> list = new ArrayList<String>();

		list.add(request.getParameter("language"));
		list.add(request.getParameter("topic"));
		list.add(request.getParameter("introduction"));
		list.add(request.getParameter("syntax"));
		list.add(request.getParameter("parameters"));
		list.add(request.getParameter("remarks"));

		CreateTableDTO dto = _frontService.createTopic(list);
		return list;
	}

}
