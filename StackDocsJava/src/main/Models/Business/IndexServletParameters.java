package Models.Business;

import javax.servlet.http.HttpServletRequest;

public class IndexServletParameters {

	public String language;
	public String topic;
	public String page;
	public String search;
	public String udate;
	public String remove;
	public String changePage;
	public String back;

	public IndexServletParameters(HttpServletRequest request) {
		language = request.getParameter("language");
		topic = request.getParameter("topic");
		page = request.getParameter("page");
		search = request.getParameter("search");
		udate = request.getParameter("update");
		remove = request.getParameter("rem");
		changePage = request.getParameter("change");
		back = request.getParameter("back");
	}

}
