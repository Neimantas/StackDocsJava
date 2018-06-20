<%@page import="org.omg.CORBA.Request"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
  <title>Stack Doc Topic Content</title>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

  <link rel="stylesheet" href="resources/css/bootstrap-4.1.1.min.css">
  <link rel="stylesheet" href="resources/css/myStyles.css">
  <link rel="stylesheet" href="resources/css/fontawesome-all.css">
  <script type="text/javascript" src="resources/js/myScripts.js"></script>
</head>

<body>
	<div class="container pt-5">
		<form action="/StackDocsJava/read" method="POST">
			<div class="form-row p-2">
				<div class="col">
					<ul class="list-group">
						<li class="list-group-item text-center border-secondary">
							<h2>${topic}</h2>
						</li>
						<li class="list-group-item border-secondary">
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
					<button type="submit" class="btn btn-outline-dark"><i class="fas fa-arrow-left"></i></button>
				</div>
				<div class="col">
					<button type="button" id="toTop" class="btn btn-outline-dark float-right ml-1" onClick="topFunction()"><i class="fas fa-angle-double-up"></i></button>
					<button type="button" id="edit" class="btn btn-outline-dark float-right ml-1" data-toggle="tooltip" data-placement="bottom" title="Edit topic info" onClick="updateTopic()"><i class="fas fa-edit"></i></button>
					<button type="button" class="btn btn-outline-dark float-right font-weight-bold"
						data-toggle="modal" data-target="#exampleModalLong">Examples</button>
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