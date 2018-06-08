package Services.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Example;
import Models.Topic;
import Models.DTO.ExamplesFrontDTO;
import Models.DTO.TopicsInfoFrontDTO;
import Services.IFrontService;

//@WebServlet(urlPatterns = "/ReadServlet")
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String topic;
	private String introduction;
	private String syntax;
	private String parameters;
	private String remarks;

	public ReadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String topicId = request.getParameter("topic");

		IFrontService fs = new FrontServiceImp();
		TopicsInfoFrontDTO dto = fs.getTopicInfoByTopicId(Integer.parseInt(topicId));

		List<String> content = new ArrayList<String>();

		if (dto.is_Succcess()) {

			List<Topic> topics = dto.get_Topics();
			for (Topic t : topics) {

				topic = t.get_TopicTitle();
				introduction = t.get_IntroductionHtml();
				syntax = t.get_SyntaxHtml();
				parameters = t.get_ParametersHtml();
				remarks = t.get_RemarksHtml();
			}

		} else {
			content.add(dto.get_Message());
		}

		ExamplesFrontDTO dtoE = fs.getExamplesByID(Integer.parseInt(topicId));

		List<String> contExamples = new ArrayList<String>();	
		
		if (dtoE.is_Succcess()) {
			
			List<Example> examples = dtoE.get_Examples();
			System.out.println("Size > "+examples.size());
			for (Example e : examples) {
				
				contExamples.add(e.bodyHtml);
				
			}
			
		} else {
			contExamples.add(dtoE.get_Message());
		}

		request.setAttribute("topic", topic);
		request.setAttribute("introduction", introduction);
		request.setAttribute("syntax", syntax);
		request.setAttribute("parameters", parameters);
		request.setAttribute("remarks", remarks);
		request.setAttribute("example", contExamples);

		request.getRequestDispatcher("read.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/StackDocsJava/main");
	}

}