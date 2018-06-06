function searchTopic() {
	var language = document.getElementById("language").value;
	var topic = document.getElementById("topic").value;
	if(topic == "") {
		topic = "0";
	}
	url="main?language=" + language + "&topic=" + topic + "&search=true";
	location.href=url;
	
}
function readTitle() {
	
	var topicDoc = document.getElementById("title");
	var topic = topicDoc.options[topicDoc.selectedIndex];
	
	url="read?topic="+topic.value;
	location.href=url;
	
	
}