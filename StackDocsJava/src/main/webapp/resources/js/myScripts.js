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

function createTopic() {
		
	url="create";
	location.href=url;
	
}

function createTopicBack() {
	
	url="main";
	location.href=url;
	
}

function updateTopic() {
	
	url="update";
	location.href=url;
	
}

function updateTopicBack() {
	
	url="read";
	location.href=url;
	
}

function setPage(page) {
	if(page != "0") {
		url="main?change=true&page=" + page;
		location.href=url;
	}

}

function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}

window.onscroll = function() {scrollFunction()};

function scrollFunction() {
		 if (document.body.scrollTop > 40 || document.documentElement.scrollTop > 40) {
   document.getElementById("toTop").style.display = "block";
		 } else {
   document.getElementById("toTop").style.display = "none";
	}
}

function expandTextarea(id) {
	var textarea = document.getElementById(id);
    textarea.addEventListener('keyup', function() {
        this.style.overflow = 'hidden';
        this.style.height = 0;
        this.style.height = this.scrollHeight + 'px';
    }, false);
}

expandTextarea("introduction");
expandTextarea("syntax");
expandTextarea("parameters");
expandTextarea("remarks");