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

		<form action="register" id="formSubmit" method="post" class="d-block form-register">
			<input type="email" id="login" class="fadeIn second inputRegister"
				name="email" placeholder="email"> <input type="password"
				id="password" class="fadeIn third inputRegister" name="password"
				placeholder="password"> <input type="text" id="fullName"
				class="fadeIn third inputRegister" name="fullName"
				placeholder="your name"> <input type="hidden" value="login"
				name="action"> <input type="button" value="register"
				class="fadeIn fourth register"> <a href="<c:url value='/login'/>">Login</a>
		</form>
	</div>
	<script type="module" src="<c:url value ='/template/web/js/register.js'/>"></script>
</body>
</html>