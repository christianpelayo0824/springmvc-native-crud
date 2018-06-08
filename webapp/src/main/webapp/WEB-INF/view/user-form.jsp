<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webjars/materializecss/0.100.2/css/materialize.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<title>Web Application</title>
</head>
<body>
	<header>
		<div class="container">
			<h3 class="center-align regular">Add User</h3>
			<form:form action="addUserToDb" modelAttribute="user" method="POST"
				id="myForm">
				<form:hidden path="id"></form:hidden>
				<form:input path="firstname" id="firstname" placeholder="Firstname"></form:input>
				<form:input path="lastname" id="lastname" placeholder="Lastname"></form:input>
				<form:input path="email" id="email" placeholder="Email"></form:input>
				<input type="submit" class="btn" />
			</form:form>
		</div>
	</header>
</body>
</html>