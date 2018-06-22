<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Stack Doc Update Topic</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<link rel="stylesheet" href="resources/css/myStyles.css">
<link rel="stylesheet" href="resources/css/bootstrap-4.1.1.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-select.css">
<link rel="stylesheet" href="resources/css/fontawesome-all.css">
</head>

<body>
	<div class="container pt-5">
		<form action="/StackDocsJava/update" method="POST" id="form">
			<div class="form-row px-2">
				<div class="col">
					<label for="inputLangauge">Language</label> 
					<select
						class="custom-select border-secondary" disabled>
						<option value="${languageId}" selected>${language}</option>
					</select>
				</div>
				<div class="col-8">
					<label for="inputTopic">Topic</label> <input type="text"
						class="form-control border-secondary" id="topic"
						placeholder="Enter topic's title" value="${topic}" name="${topicId}">
				</div>
			</div>
			<div class="row m-1">
				<div class="col">
					<div class="collapse" id="collapseError">
						<div class="card card-body text-right small border-0 text-danger p-1">Topic's title can't be blank</div>
					</div>
				</div>
			</div>
			<div class="row p-2">
				<div class="col">
					<label for="inputIntroduction">Introduction</label>
					<textarea class="form-control border-secondary"
						id="introduction" rows="3"
						placeholder="Enter topic's introduction">${introduction}</textarea>
				</div>
			</div>
			<div class="row p-2">
				<div class="col">
					<label for="inputSyntax">Syntax</label>
					<textarea class="form-control border-secondary"
						id="syntax" rows="3"
						placeholder="Enter topic's syntax">${syntax}</textarea>
				</div>
			</div>
			<div class="row p-2">
				<div class="col">
					<label for="inputParameters">Parameters</label>
					<textarea class="form-control border-secondary"
						id="parameters" rows="3"
						placeholder="Enter topic's parameters">${parameters}</textarea>
				</div>
			</div>
			<div class="row p-2">
				<div class="col">
					<label for="inputRemarks">Remarks</label>
					<textarea class="form-control border-secondary"
						id="remarks" rows="3"
						placeholder="Enter topic's remarks">${remarks}</textarea>
				</div>
			</div>
			<div class="row p-2">
				<div class="col">
					<button type="button"
						class="btn btn-outline-dark float-right font-weight-bold ml-1" onClick="submitUpdate()">Save</button>
					<button type="button"
						class="btn btn-outline-dark float-right font-weight-bold"
						onClick="updateTopicBack()">Cancel</button>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript"
		src="resources/js/jquery-3.3.1.slim.min.js"></script>
	<script type="text/javascript" src="resources/js/popper.min.js"></script>
	<script type="text/javascript"
		src="resources/js/bootstrap-4.1.1.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-select.js"></script>
	<script type="text/javascript" src="resources/js/myScripts.js"></script>
</body>

</html>