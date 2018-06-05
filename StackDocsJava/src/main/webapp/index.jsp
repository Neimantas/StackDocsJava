
<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stack Dock Java</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
			<script type="text/javascript" src="resources/js/myScripts.jsp"></script>
</head>

<body>
	<div class="container pt-5">
		<div>

			<div class="form-row p-2">
				<div class="col">
					<select class="custom-select mr-sm-2" id="language">
						<option selected value="0">Choose...</option>
						<option value="3">.Net</option>
						<option value="4">C#</option>
						<option value="5">Java</option>
						<option value="8">Javascript</option>
					</select>
				</div>
				<div class="col-8">
					<div class="input-group">
						<input type="text" class="form-control" id="topic"
							placeholder="Search topic..." aria-label="Recipient's username"
							aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-outline-secondary" type="submit"
								onclick="searchTopic();">Submit</button>
						</div>
					</div>
				</div>

			</div>

			<form>
				<div class="row p-2">
					<div class="col">
						<select class="custom-select" size="10" id="title">
							
							<c:forEach items="${topicMap}" var="topic">
								<option value="${topic.key}">${topic.value}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="row p-2">
					<div class="col">
						<button type="button" class="btn btn-outline-dark float-right"
							onClick="readTitle()")>Open</button>
					</div>
				</div>
			</form>
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
		integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
		crossorigin="anonymous"></script>
</body>

</html>