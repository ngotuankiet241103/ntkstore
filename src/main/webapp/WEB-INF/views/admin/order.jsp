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
	<section class="section-order">
		<div class="header-order">
			<div class="header-search">
				<input type="text"
					placeholder="Tìm kiếm mã đơn hàng hoặc tên khách hàng" class="valueSearch">
				<button type="button" class="btnSearch">Tìm kiếm</button>
			</div>
			<div class="header-category">
				
			</div>
		</div>
		<div class="content-order">
			<ul class="list-title">
				<li class="title-item">Mã đơn hàng</li>
				<li class="title-item">Khách hàng</li>
				<li class="title-item">Tình trạng đơn hàng</li>
				<li class="title-item">Thanh toán</li>
				<li class="title-item">Hình thức</li>
				<li class="title-item">Ngày đặt</li>
				<li class="title-item">Tổng tiền</li>
				<li class="title-item">Thao tác</li>
			</ul>
			<ul class="list-order">
			</ul>
		</div> 
	</section>
	<div class="pop-up d-none">
	</div>
	<script type="text/javascript">
		var api = "http://localhost:8080/e-commerceSpringMvc/api/admin/order";
	</script>
	<script type="module" src="<c:url value='/template/admin/js/renderOrders.js'/>"></script>
	<script type="module" src="<c:url value='/template/admin/js/renderCategory.js'/>"></script>
	<script src="<c:url value='/template/admin/js/handleEditOrder.js'/>"></script>
</body>
</html>