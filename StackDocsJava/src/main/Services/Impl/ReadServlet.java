package Services.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.Topic;
import Models.DTO.TopicsFrontDTO;
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
		String str = "lorem lorem lorem lorem lorem lorem lorem lorem";
		String topic = request.getParameter("topic");
		request.setAttribute("contents", str);
		request.setAttribute("topics", topic);

		request.getRequestDispatcher("read.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
