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
		<form action="/StackDocsJava/update" method="POST">
			<div class="form-row p-2">
				<div class="col">
					<label for="inputLangauge">Language</label> <select
						class="selectpicker form-control" data-size="6"
						data-style="border-secondary bg-white" id="language"
						data-live-search="true" title="Choose language...">
						<c:forEach items="${languageDD}" var="language">
							<option value="${language.key}">${language.value}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-8">
					<label for="inputTopic">Topic</label> <input type="text"
						class="form-control border-secondary" id="topic"
						placeholder="Enter topic's title">
				</div>
			</div>
			<div class="row p-2">
				<div class="col">
					<label for="inputIntroduction">Introduction</label>
					<textarea class="form-control border-secondary"
						id="exampleFormControlTextarea1" rows="3"
						placeholder="Enter topic's introduction"></textarea>
				</div>
			</div>
			<div class="row p-2">
				<div class="col">
					<label for="inputSyntax">Syntax</label>
					<textarea class="form-control border-secondary"
						id="exampleFormControlTextarea1" rows="3"
						placeholder="Enter topic's syntax"></textarea>
				</div>
			</div>
			<div class="row p-2">
				<div class="col">
					<label for="inputParameters">Parameters</label>
					<textarea class="form-control border-secondary"
						id="exampleFormControlTextarea1" rows="3"
						placeholder="Enter topic's parameters"></textarea>
				</div>
			</div>
			<div class="row p-2">
				<div class="col">
					<label for="inputRemarks">Remarks</label>
					<textarea class="form-control border-secondary"
						id="exampleFormControlTextarea1" rows="3"
						placeholder="Enter topic's remarks"></textarea>
				</div>
			</div>
			<div class="row p-2">
				<div class="col">
					<button type="submit"
						class="btn btn-outline-dark float-right font-weight-bold ml-1">Save</button>
					<button type="button"
						class="btn btn-outline-dark float-right font-weight-bold" onClick="updateTopicBack()">Cancel</button>
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