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
	<div class="box-container">
		<div class="box">
			<c:if test="${orderStatus != null}">
				<c:if test="${paymentStatus != null}">
					<h3>Bạn đã ${paymentStatus}</h3>
					<span><i class="fa-regular fa-clock"></i></span>
					<p>Đơn hàng của bạn đang được xử lý</p>
					<a href="">Quay lại trang mua hàng</a>
				</c:if>
				<c:if test="${paymentStatus == null}">
					<h3>Bạn đã ${orderStatus}</h3>
					<span><i class="fa-regular fa-clock"></i></span>
					<p>Đơn hàng của bạn đang được xử lý</p>
					<a href="">Quay lại trang mua hàng</a>
				</c:if>
			</c:if>
			<c:if test="${orderStatus == null}">
				<h3>Bạn đã ${paymentStatus}</h3>
				<span><i class="fa-regular fa-clock"></i></span>
				<p>Đơn hàng của bạn đang được xử lý</p>
				<a href="">Quay lại trang mua hàng</a>
			</c:if>

		</div>
	</div>
</body>
</html>