
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
								onclick="searchTopic()">Submit</button>
						</div>
					</div>
				</div>

			</div>

			<form>
				<div class="row p-2">
					<div class="col">
						<select class="custom-select" size="10" id="title">
						<%--
							<c:forEach items="${topicList}" var="topic">
								<option value="1">${topic}</option>
							</c:forEach>
														<option value="1">Title #1</option>
							<option value="2">Title #2</option>
							<option value="3">Title #3</option>
							<option value="4">Title #4</option>
							<option value="5">Title #5</option>
							<option value="6">Title #6</option>
							<option value="7">Title #7</option>
							<option value="8">Title #8</option>
							<option value="9">Title #9</option>
							<option value="10">Title #10</option> --%>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<button type="button" class="btn btn-outline-dark float-right"
							data-toggle="modal" data-target="#exampleModalCenter"
							onclick="changeTitle()">Open</button>
						<div class="modal fade" id="exampleModalCenter" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalCenterTitle"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalCenterTitle">Title</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">Lorem ipsum dolor sit amet,
										consectetur adipisicing elit. Sapiente numquam explicabo
										magnam quod inventore quia? Voluptates et facilis magni,
										placeat dolor quisquam blanditiis repellat necessitatibus?
										Corporis ducimus id quibusdam minus cum nemo labore earum
										repellendus maxime fugit officiis, magni illo debitis minima
										dolores consequatur modi, pariatur aliquam nulla! Tenetur,
										voluptatum!</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-outline-dark"
											data-dismiss="modal">Previous page</button>
										<button type="button" class="btn btn-outline-dark"
											data-dismiss="modal">Next page</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="resources/js/myScripts.js"></script>
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