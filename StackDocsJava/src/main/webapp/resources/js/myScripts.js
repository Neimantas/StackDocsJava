function searchTopic() {
	var language = document.getElementById("language").value;
	var topic = document.getElementById("topic").value;
	if(language == "") {
		language = "0";
	}
	if(topic == "") {
		topic = "0";
	}
	url="main?search=true&language=" + language + "&topic=" + topic;
	location.href=url;
}

function readTitle() {
	
	var topicDoc = document.getElementById("title");
	var topic = topicDoc.options[topicDoc.selectedIndex];
	
	url="read?topic="+topic.value;
	location.href=url;
	
}

function setPage(page) {
	if(page != "0") {
		url="main?change=true&page=" + page;
		location.href=url;
	}

}