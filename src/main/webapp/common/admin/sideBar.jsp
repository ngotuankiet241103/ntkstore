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
	<nav class="nav-header">
		<ul class="side-bar-admin">
			<li class="page-item"><a href="<c:url value = '/admin-trang-chu' />"> <i
					class="fa-brands fa-dashcube"></i> Trang chủ
			</a></li>
			<li class="page-item"><a href=" <c:url value = '/admin/manager'/>"> <i
					class="fa-solid fa-user"></i> Quản lí người dùng
			</a></li>
			<li class="page-item"><a href="<c:url value='/admin/product'/>"> <i
					class="fa-solid fa-box"></i> Quản lí sản phẩm
			</a></li>
			<li class="page-item"><a href="<c:url value='/admin/blog'/>"> <i
					class="fa-solid fa-box"></i> Quản lí bài viết
			</a></li>
			<li class="page-item"><a href="<c:url value='/admin/order'/>"> <i
					class="fa-solid fa-cart-flatbed"></i> Quản lí đơn hàng
			</a></li>

			
		</ul>
	</nav>
	<script type="text/javascript">
		const url = window.location.href;
		const links = document.querySelectorAll('.page-item a')
		links.forEach(link => {
				console.log(url);
			if(url.includes(link.href)){
				
				const parent = link.parentElement;
				console.log(parent);
				parent.classList.add("active")
			}
		})
	</script>
</body>
</html>