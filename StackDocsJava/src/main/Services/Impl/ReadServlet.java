package Services.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Topic;
import Models.DTO.TopicsInfoFrontDTO;
import Services.IFrontService;

//@WebServlet(urlPatterns = "/ReadServlet")
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String topic = request.getParameter("topic");
		String str = null;

		IFrontService fs = new FrontServiceImp();
		TopicsInfoFrontDTO dto = fs.getTopicInfoByTopicId(Integer.parseInt(topic));

		List<String> content = new ArrayList<String>();

		if (dto.is_Succcess()) {

			List<Topic> topics = dto.get_Topics();
			for (Topic t : topics) {
				
				str = t.get_TopicTitle();
				content.add(t.get_IntroductionHtml());
				content.add(t.get_SyntaxHtml());
				content.add(t.get_ParametersHtml());
				content.add(t.get_RemarksHtml());
			}

		} else {
			content.add(dto.get_Message());
		}
		
		request.setAttribute("topics", str);
		request.setAttribute("contents", content);

		request.getRequestDispatcher("read.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//request.getRequestDispatcher("index.jsp").forward(request, response);
		response.sendRedirect("/StackDocsJava/main");
	}

}
