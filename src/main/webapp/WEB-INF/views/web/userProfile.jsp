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
	<div class="profile-container">
		<nav class="sidebar-prodfile">
			<ul class="nav-container">
				<li><a href="">Quản lý tài khoản</a>
					<ul class="details-nav">
						<li><a href="">Thông tin cá nhân</a></li>
						<li><a href="">Địa chỉ</a></li>
					</ul></li>
				<li><a href="">Quản lý đơn hàng</a>
					<ul class="details-nav">
						<li><a href="">Đơn hàng đổi trả</a></li>
						<li><a href="">Đơn hàng hủy</a></li>
					</ul></li>
			</ul>
		</nav>
		<div class="details-profile">
			<h2>Thông tin cá nhân</h2>
			<div class="userProfile">
				<div class="box-userProfile">
					<div class="my-profile">
						
					</div>
					<div class="my-profile-ft">
						<button href="#/profile/edit" class="edit-profile">Chỉnh
							sửa</button>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script type="module" src=" <c:url value='/template/web/js/handleProfile.js'/>"></script>
	<script type="module"
		src=" <c:url value='/template/web/js/changeLayoutEdit.js'/>"></script>
</body>
</html>