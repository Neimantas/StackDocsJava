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
		url = "read?open=true&topic=" + topic.value;
		location.href = url;
	} else {
		$('#selectTopic').modal('show');
	}
}

function createTopic() {

	url = "create";
	location.href = url;

}

function createTopicBack() {

	url = "main?back=true";
	location.href = url;

}

function updateTopic() {

	var topic = document.getElementById("edit").value;

	url = "update?topic=" + topic;
	location.href = url;

}

function updateTopicBack() {

	url = "read";
	location.href = url;

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
		document.getElementById("selectModalBody").innerHTML = "Are you sure you want to delete<br/> \""
				+ topic.text + "\"";
		document.getElementById("cancelBtn").innerHTML = "Cancel";
		document.getElementById("deleteBtn").style.display = "block";
	}

	$('#selectTopic').modal('show');
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

	url = "main?rem=" + topicID + "&search=true&language=" + language
			+ "&topic=" + topic;
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

function expandTextarea(id) {
	var textarea = document.getElementById(id);
	textarea.addEventListener('keyup', function() {
		this.style.overflow = 'hidden';
		this.style.height = 0;
		this.style.height = this.scrollHeight + 'px';
	}, false);
}

//expandTextarea("introduction");
//expandTextarea("syntax");
//expandTextarea("parameters");
//expandTextarea("remarks");

function sendTopicInfo() {
	var language = document.getElementById("language");
	var topic = document.getElementById("topic");

	if (topic.value === "" || language.value === "") {
		if (language.value === "") {
			$('#language').selectpicker('setStyle', 'border-secondary',
					'remove');
			$('#language')
					.selectpicker('setStyle', 'btn-outline-danger', 'add');
			$('#language').selectpicker('setStyle', 'text-secondary', 'add');
			$('#collapseErrorL').collapse('show')
		}
		if (topic.value === "") {
			topic.classList.remove('border-secondary');
			topic.classList.add('is-invalid');
			$('#collapseErrorT').collapse('show')
		}

	} else {
		document.getElementById('form').submit();
	}
}

function submitUpdate() {
	var topic = document.getElementById("topic");
	
	if (topic.value === "") {
		topic.classList.remove('border-secondary');
		topic.classList.add('is-invalid');
		$('#collapseError').collapse('show')
	} else {
		document.getElementById('formUpdate').submit();
	}
}
