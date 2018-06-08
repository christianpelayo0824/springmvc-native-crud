<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!doctype html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webjars/materializecss/0.100.2/css/materialize.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<meta charset="UTF-8" />
<title>Web Application</title>
</head>
<body>
	<div class="container">
		<h3 class="regular center-align">User List</h3>
		<button class="btn"
			onclick="window.location.href='showFormForAddUser'; return false;">Add
			User</button>
	</div>
	<div class="container">
		<table class="striped highlight centered responsive-table">
			<thead>
				<tr>
					<th>Firstname</th>
					<th>Lastname</th>
					<th>Email</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${USER}" var="user">
					<c:url var="updateLink" value="showFormForUpdate">
						<c:param name="userId" value="${user.getId() }"></c:param>
					</c:url>

					<c:url var="deleteLink" value="delete">
						<c:param name="userId" value="${user.getId() }"></c:param>
					</c:url>
					<tr>
						<td><c:out value="${user.getFirstname() }"></c:out></td>
						<td><c:out value="${user.getLastname() }"></c:out></td>
						<td><c:out value="${user.getEmail() }"></c:out></td>
						<td><a href="${updateLink }" class="btn">Update</a> | <a
							href="${deleteLink }" class="btn">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>