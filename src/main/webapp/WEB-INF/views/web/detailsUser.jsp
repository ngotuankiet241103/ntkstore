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
						<div class="profile-item">
							<h3 class="profile-item-title">Họ tên</h3>
							<div class="profile-item-info">Tên khách hàng</div>
						</div>
						<div class="profile-item">
							<h3 class="profile-item-title">Địa chỉ email</h3>
							<div class="profile-item-info">abc123@gmail.com</div>
						</div>
						<div class="profile-item">
							<h3 class="profile-item-title">Số điện thoại</h3>
							<div class="profile-item-info">0123456789</div>
						</div>
						<div class="profile-item">
							<h3 class="profile-item-title">Mật khẩu</h3>
							<input type="password" class="profile-item-info"
								value="0123456789" disabled />
						</div>
					</div>
					<div class="my-profile-ft">
						<a href="#/profile/edit" class="edit-profile">Chỉnh sửa</a>
					</div>
				</div>
			</div>

	<script>
		      const btnEdit = document.querySelector(".edit-profile");
		      btnEdit.onclick = () => {
		        changeLayoutEdit();
		      };
      
    </script>
</body>
</html>