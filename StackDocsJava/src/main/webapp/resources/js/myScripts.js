function searchTopic() {
	var language = document.getElementById("language").value;
	var topic = document.getElementById("topic").value;
	if (language == "") {
		language = "0";
	}
	if (topic == "") {
		topic = "0";
	}
	url = "main?search=true&language=" + language + "&topic=" + topic;
	location.href = url;
}

function readTitle() {

	var topicDoc = document.getElementById("title");
	var topic = topicDoc.options[topicDoc.selectedIndex];
	if (topic != undefined) {
		url = "read?topic=" + topic.value;
		location.href = url;
	}
}

function setPage(page) {
	if (page != "0") {
		url = "main?change=true&page=" + page;
		location.href = url;
	}

}

function changeModalText() {

	var topicDoc = document.getElementById("title");
	var topic = topicDoc.options[topicDoc.selectedIndex];
	if (topic != undefined) {
		document.getElementById("modalBody").innerHTML = "Are you sure you want to delete<br/> \""
				+ topic.text + "\"";
		document.getElementById("cancelBtn").innerHTML = "Cancel";
		document.getElementById("deleteBtn").style.display = "block";
	}

	$('#deleteTopic').modal('show');
}

function deleteTopic() {

	var topicDoc = document.getElementById("title");
	var topicID = topicDoc.options[topicDoc.selectedIndex].value;

	// Kad uzkrauti ta pati puslapi po delete
	var language = document.getElementById("language").value;
	var topic = document.getElementById("topic").value;
	if (language == "") {
		language = "0";
	}
	if (topic == "") {
		topic = "0";
	}
	//

	url = "main?rem=" + topicID + "&search=true&language=" + language + "&topic=" + topic;
	location.href = url;

}

function topFunction() {
	document.body.scrollTop = 0;
	document.documentElement.scrollTop = 0;
}

window.onscroll = function() {
	scrollFunction()
};

function scrollFunction() {
	if (document.body.scrollTop > 40 || document.documentElement.scrollTop > 40) {
		document.getElementById("toTop").style.display = "block";
	} else {
		document.getElementById("toTop").style.display = "none";
	}
}

window.onload = function() {
	dataToggle()
};

function dataToggle() {

	$('[data-toggle="tooltip"]').tooltip();

}
