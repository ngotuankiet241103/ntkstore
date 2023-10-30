<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.textSignUp {
	margin-right: 10px;
	color: #92badd;
	cursor: pointer;
}
</style>
</head>
<body>
	<div id="formContent">
		<!-- Tabs Titles -->

		<!-- Icon -->
		<div class="fadeIn first">
			<h3>Login</h3>
		</div>

		<!-- Login Form -->
		<form action="login" id="formSubmit" method="post" class="d-block">
			<input type="text" id="login" class="fadeIn second inputLogin" name="email"
				placeholder="username"> <input type="password" id="password"
				class="fadeIn third inputLogin" name="password" placeholder="password">
			<input type="button" class="fadeIn fourth login"
				value="login"> <a href="<c:url value='/register'/>"
				class="d-block text-right mr-2">Create Account</a>
		</form>

	</div>
	
	<script type="module" src="<c:url value='/template/web/js/login.js'/>"></script>

</body>
</html>