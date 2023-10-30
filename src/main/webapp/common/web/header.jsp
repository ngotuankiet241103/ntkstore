<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tablib.jsp"%>
<c:url var="apiCarts" value="/api/carts/user/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<header class="header">
		<div class="header-row row">
			<div class="header-logo c-3">
				<img src="<c:url value ='/template/logo/logo_web_edit.png'/>" alt="" />
			</div>
			<div class="header-navbar c-7">
				<div class="header-navbar-item row">
					<div class="header-menuBar c-9">
						<ul class="header-menu row">
							<li class="header-menu-item col"><a href=""> Home</a></li>
							<li class="header-menu-item header-menu-item-product col"><a
								href="<c:url value = '/product'/>">Product</a> <i class="fa-solid fa-angle-down"></i>
								<ul class="header-submenu">
									
								</ul></li>
							<li class="header-menu-item col"><a href="">Blog</a></li>
							<li class="header-menu-item col"><a href="">About Us</a></li>
						</ul>
					</div>

					<div class="header-login c-3">
						<div class="header-cart">
							<i class="fa-solid fa-cart-shopping"></i>
						</div>
						<div class="header-login-user">
							<a href="#"> <i class="fa-solid fa-user"></i></a>
							<div class="userActive">
								<i class="fa-solid fa-user"></i>
								<ul class="box-info">
									<li class="info-item"><a href=" <c:url value = '/profile' />" class="item-link"> <i
											class="fa-regular fa-face-smile-beam"></i> Quản lí tài khoản
									</a></li>
									<li class="info-item"><a href="<c:url value = '/customer/order'/>" class="item-link"> <i
											class="fa-solid fa-box"></i> Quản lí đơn hàng
									</a></li>
									<li class="info-item"><a href="" class="item-link"> <i
											class="fa-solid fa-arrow-right-from-bracket"></i> Đăng xuất
									</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<div class="modal-cart">
		<div class="list-cart cart-sidebar">
			<header class="header-sibarCart">
				<h3 class="title-cart">Cart</h3>
				<span>&times;</span>
			</header>
			<ul class="list-production"></ul>
		</div>
	</div>
	<script type="module"
		src="<c:url value='/template/web/js/header.js'/> "></script>
	<script type="module"
		src="<c:url value='/template/web/js/category.js'/> "></script>
		<script type="module"
		src="<c:url value='/template/web/js/menuHeader.js'/> "></script>

</body>
</html>