function searchTopic() {
	var language = document.getElementById("language").value;
	var topic = document.getElementById("topic").value;
	url="IndexServlet?language=" + language + "&topic=" + topic;
	location.href=url;
}