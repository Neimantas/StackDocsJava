function searchTopic() {
	var language = document.getElementById("language").value;
	var topic = document.getElementById("topic").value;
	if(topic == "") {
		topic = "0";
	}
	url="IndexServlet?language=" + language + "&topic=" + topic;
	location.href=url;
}