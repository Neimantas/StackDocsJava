
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

<link rel="stylesheet" href="resources/css/bootstrap-4.1.1.min.css">
<link rel="stylesheet" href="resources/css/myStyles.css">
<script type="text/javascript" src="resources/js/myScripts.js"></script>
</head>

<body>
	<div class="container pt-5">
		<div>

			<div class="form-row p-2">
				<div class="col">
					<select class="custom-select mr-sm-2" id="language">
						<option value="0" ${languageId==0?'selected':''}>Choose...</option>
						<option value="3" ${languageId==3?'selected':''}>.Net</option>
						<option value="4" ${languageId==4?'selected':''}>C#</option>
						<option value="5" ${languageId==5?'selected':''}>Java</option>
						<option value="8" ${languageId==8?'selected':''}>Javascript</option>
					</select>
				</div>
				<div class="col-8">
					<div class="input-group">
						<input type="text" value="${topic!=null?topic:''}" class="form-control" id="topic"
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
									class="page-item ${pageBtn1==pageNumber?'active disabled':''}"><a
									class="page-link text-dark" href="#">${pageBtn1}</a></li>
							</c:if>
							<c:if test="${numberOfPages>1}">
								<c:set var="pageBtn2"
									value="${pageNumber==1?pageNumber+1:(pageNumber==numberOfPages&&numberOfPages!=2?pageNumber-1:pageNumber)}" />
								<li onclick="setPage(${pageBtn2==pageNumber?0:pageBtn2});"
									class="page-item ${pageBtn2==pageNumber?'active disabled':''}"><a
									class="page-link text-dark" href="#">${pageBtn2}</a></li>
							</c:if>
							<c:if test="${numberOfPages>2}">
								<c:set var="pageBtn3"
									value="${pageNumber==1?pageNumber+2:(pageNumber==numberOfPages?pageNumber:pageNumber+1)}" />
								<li onclick="setPage(${pageBtn3==pageNumber?0:pageBtn3});"
									class="page-item ${pageBtn3==pageNumber?'active disabled':''}"><a
									class="page-link text-dark" href="#">${pageBtn3}</a></li>
							</c:if>
							<c:if test="${(numberOfPages>4) && (pageNumber<numberOfPages-2)}">
								<li
									onclick="setPage(${pageNumber!=1?(pageNumber+3<numberOfPages?pageNumber+3:numberOfPages):pageNumber+4});"
									class="page-item"><a class="page-link text-dark" href="#"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
										<span class="sr-only text-dark">Next</span>
								</a></li>
							</c:if>
							<c:if test="${(numberOfPages>3) && (pageNumber<numberOfPages-1)}">
								<li onclick="setPage(${numberOfPages});" class="page-item"><a
									class="page-link text-dark" href="#">${numberOfPages}</a></li>
							</c:if>
						</ul>
					</div>
					<div class="col">

						<button type="button" class="btn btn-outline-dark float-right"
							onClick="readTitle()")>Open</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript"
		src="resources/js/jquery-3.3.1.slim.min.js"></script>
	<script type="text/javascript" src="resources/js/popper.min.js"></script>
	<script type="text/javascript"
		src="resources/js/bootstrap-4.1.1.min.js"></script>
</body>

</html>