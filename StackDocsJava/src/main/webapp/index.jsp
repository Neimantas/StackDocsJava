<%@page import="org.omg.CORBA.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Stack Doc Java</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<link rel="stylesheet" href="resources/css/myStyles.css">
<link rel="stylesheet" href="resources/css/bootstrap-4.1.1.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-select.css">
<link rel="stylesheet" href="resources/css/fontawesome-all.css">
<script type="text/javascript" src="resources/js/myScripts.js"></script>
</head>

<body>
	<div class="container pt-5">
		<div>
			<div class="form-row p-2">
				<div class="col">
					<select class="selectpicker form-control" data-size="6"
						data-style="border-secondary bg-white" id="language"
						data-live-search="true" title="Choose language...">
						<option value="0" ${languageId==0?'selected':''}>All
							languages</option>
						<c:forEach items="${languageDD}" var="language">
							<option value="${language.key}"
								${languageId==language.key?'selected':''}>${language.value}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-8">
					<div class="input-group">
						<input type="text" value="${topic!=null?topic:''}"
							class="form-control border-secondary" id="topic"
							placeholder="Search topic...">
						<div class="input-group-append">
							<button class="btn btn-outline-secondary" type="submit"
								onclick="searchTopic();">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row p-2">
				<div class="col">
					<select class="custom-select border-secondary" size="10" id="title">
						<c:forEach items="${topicMap}" var="topic">
							<option value="${topic.key}">${languageMap.get(topic.key)}${topic.value}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row p-2">

				<!-- Pagination -->
				<div class="col">
					<ul class="pagination">
						<c:if test="${numberOfPages>3 && pageNumber>2}">
							<li onclick="setPage(1);" class="page-item"><a
								class="page-link text-dark" href="#" tabindex="-1">1</a></li>
						</c:if>
						<c:if test="${pageNumber>3 && numberOfPages!=4}">
							<li
								onclick="setPage(${pageNumber!=numberOfPages?(pageNumber-3>1?pageNumber-3:1):pageNumber-4});"
								class="page-item"><a class="page-link text-dark" href="#"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
									<span class="sr-only">Previous</span>
							</a></li>
						</c:if>
						<c:if test="${numberOfPages>1}">
							<c:set var="pageBtn1"
								value="${pageNumber==1?pageNumber:(pageNumber==numberOfPages&&numberOfPages!=2?pageNumber-2:pageNumber-1)}" />
							<li onclick="setPage(${pageBtn1==pageNumber?0:pageBtn1});"
								class="page-item ${pageBtn1==pageNumber?'active disabled':''}">
								<a class="page-link text-dark" href="#">${pageBtn1}</a>
							</li>
						</c:if>
						<c:if test="${numberOfPages>1}">
							<c:set var="pageBtn2"
								value="${pageNumber==1?pageNumber+1:(pageNumber==numberOfPages&&numberOfPages!=2?pageNumber-1:pageNumber)}" />
							<li onclick="setPage(${pageBtn2==pageNumber?0:pageBtn2});"
								class="page-item ${pageBtn2==pageNumber?'active disabled':''}">
								<a class="page-link text-dark" href="#">${pageBtn2}</a>
							</li>
						</c:if>
						<c:if test="${numberOfPages>2}">
							<c:set var="pageBtn3"
								value="${pageNumber==1?pageNumber+2:(pageNumber==numberOfPages?pageNumber:pageNumber+1)}" />
							<li onclick="setPage(${pageBtn3==pageNumber?0:pageBtn3});"
								class="page-item ${pageBtn3==pageNumber?'active disabled':''}">
								<a class="page-link text-dark" href="#">${pageBtn3}</a>
							</li>
						</c:if>
						<c:if test="${(numberOfPages>4) && (pageNumber<numberOfPages-2)}">
							<li
								onclick="setPage(${pageNumber!=1?(pageNumber+3<numberOfPages?pageNumber+3:numberOfPages):pageNumber+4});"
								class="page-item"><a class="page-link text-dark" href="#"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
									class="sr-only text-dark">Next</span>
							</a></li>
						</c:if>
						<c:if test="${(numberOfPages>3) && (pageNumber<numberOfPages-1)}">
							<li onclick="setPage(${numberOfPages});" class="page-item">
								<a class="page-link text-dark" href="#">${numberOfPages}</a>
							</li>
						</c:if>
					</ul>
				</div>

				<div class="col">
					<button type="button"
						class="btn btn-outline-dark float-right ml-1 font-weight-bold"
						onClick="readTitle();">Open</button>
					<button type="button" class="btn btn-outline-dark float-right ml-1"
						data-toggle="modal" data-target="#deleteTopic"
						data-placement="bottom" title="Delete topic"
						onClick="changeModalText();">
						<i class="fas fa-trash-alt"></i>
					</button>
					<button type="button" class="btn btn-outline-dark float-right"
						data-toggle="tooltip" data-placement="bottom"
						title="Add new topic" onClick="">
						<i class="fas fa-plus-square"></i>
					</button>
				</div>
			</div>
		</div>
	</div>


	<!-- Modal -->
	<div class="modal fade" id="deleteTopic" tabindex="-1" role="dialog"
		aria-labelledby="deleteTopic" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body" id="modalBody">Please select topic and
					try again</div>
				<div class="modal-footer">
					<button type="button" id="cancelBtn" class="btn btn-secondary"
						data-dismiss="modal">OK</button>
					<button type="button" id="deleteBtn"
						class="btn btn-primary" onClick="deleteTopic();">Delete
						topic</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="resources/js/jquery-3.3.1.slim.min.js"></script>
	<script type="text/javascript" src="resources/js/popper.min.js"></script>
	<script type="text/javascript"
		src="resources/js/bootstrap-4.1.1.min.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap-select.js"></script>
	<script type="text/javascript">
 	 $(function () { $('[data-toggle="tooltip"]').tooltip()})
  </script>

</body>

</html>