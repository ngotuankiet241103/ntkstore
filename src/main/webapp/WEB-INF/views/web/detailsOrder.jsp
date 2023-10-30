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
		<div class="details-orders">
			<h2>Chi tiết đơn hàng</h2>
			<div class="box-detail-container">
				<div class="box-detail-order orderItem">
					<div class="orderDetailsStatus">
						<span>${order.status}</span>
					</div>
					<ul class="subListOrderItem">
						<c:forEach items="${order.orderItems}" var="item">
							<li class="detailsOrderItem">
								<div class="image-orderItem">
									<img src="${item.product.image}" alt="" />
								</div>
								<div class="infoOrderItem">
									<p class="name-orderItem">${item.product.name}</p>
									<span>${item.size}</span>
								</div>
								<div class="priceOrderItem">
									<span>${item.totalPrice}</span>
								</div>
								<div class="quanlityOrderItem">
									<p>Số lượng:</p>
									<span>${item.quantity}</span>
								</div>
								<div class="reviewOrderItem">
									<a href=" <c:url value = '/customer/order/view/review?orderItemId=${item.id}' />">Viết nhận xét</a>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="codeOrder">
					<h4>Đơn hàng ${order.code}</h4>
					<p>Đặt ngày ${order.createdDate}</p>
					<span>Thanh toán khi nhận hàng</span>
				</div>
				<div class="addressOrder">
					<div class="detail-adress">
						<span>${order.detailsOrder.userName}</span> <span>${order.detailsOrder.address}</span> <span>${order.detailsOrder.phone}</span>
					</div>
					<div class="costOrder">
						<h3>Tổng cộng</h3>
						<ul>
							<li><span>Tổng tiền </span> <span>300.000đ</span></li>
							<li><span>Phí vận chuyển</span> <span>28.500 ₫</span></li>

						</ul>
						<div class="totalOrder">
							<span>Tổng cộng</span> <span>328.500</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>