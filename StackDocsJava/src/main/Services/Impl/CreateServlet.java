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
import Models.Topic;
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
		TopicsFrontDTO dto2 = frontService.getTopicsByLanguageId(0, "");
		Map<Integer, String> languageDDMap = new HashMap<>();
		if (dto2.is_Succcess()) {
			List<Topic> topics = dto2.get_Topics();
			for (Topic t : topics) {
				languageDDMap.put(t.get_LanguageId(), t.get_LanguageTitle());
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
		
		//suvestos info perdavimas i CRUD'a
		
		response.sendRedirect("/StackDocsJava/main");
	}

}
