<%@page import="org.omg.CORBA.Request"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>StackDoc Topic content</title>
<link rel="stylesheet" href="resources/css/bootstrap-4.1.1.min.css">
<link rel="stylesheet" href="resources/css/myStyles.css">
<script type="text/javascript" src="resources/js/myScripts.js"></script>
</head>

<body>
	<div class="container pt-5">
		<form action="/read" method="POST">
			<div class="form-row p-2">
				<div class="col">
					<ul class="list-group">
						<li class="list-group-item text-center">
							<h2>${topic}</h2>
						</li>
						<li class="list-group-item">
							<h4>Introduction:</h4>
							<p>${introduction}</p>
							<h4>Syntax:</h4>
							<p>${syntax}</p>
							<h4>Parameters:</h4>
							<p>${parameters}</p>
							<h4>Remarks:</h4>
							<p>${remarks}</p>
						</li>
					</ul>
				</div>
			</div>
			<div class="form-row p-2">
				<div class="col">
					<input class="btn btn-outline-dark" type="submit" value="Back">
				</div>
				<div class="col">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-outline-dark float-right"
						data-toggle="modal" data-target="#exampleModalLong">Examples</button>

					<!-- Modal -->
					<div class="modal fade" id="exampleModalLong" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLongTitle"
						aria-hidden="true">
						<div class="modal-dialog modal-lg" role="document">
							<div class="modal-content">
								<div class="modal-header text-center">
									<h5 class="modal-title" id="exampleModalLongTitle">${topic}
										examples</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">${example}</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript"
		src="resources/js/jquery-3.3.1.slim.min.js"></script>
	<script type="text/javascript" src="resources/js/popper.min.js"></script>
	<script type="text/javascript"
		src="resources/js/bootstrap-4.1.1.min.js"></script>
</body>

</html>