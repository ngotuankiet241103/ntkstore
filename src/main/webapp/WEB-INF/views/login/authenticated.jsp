<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="formContent">
		<!-- Tabs Titles -->

		<!-- Icon -->
		<div class="fadeIn first">
			<h3>Login Admin</h3>
		</div>

		<!-- Login Form -->
		<form action="<c:url value ='/admin/authenticated'/>" id="formSubmit"
			method="post" class="d-block">
			<input type="text" id="login" class="fadeIn second " name="email"
				placeholder="username"> <input type="password" id="password"
				class="fadeIn third " name="password" placeholder="password">
			<input type="submit" class="fadeIn fourth " value="login"> <a
				href="<c:url value='/register'/>" class="d-block text-right mr-2">Create
				Account</a>
		</form>

	</div>
</body>
</html>