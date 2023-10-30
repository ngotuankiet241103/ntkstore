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
		<div class="review-detail">
			<h2>Viết đánh giá</h2>
			<form action="<c:url value ='/customer/order/view/review'/>"  method="post"
				class="form-reviews">
				<div class="box-review">

					<p>Nhận xét và đánh giá sản phẩm đã mua (5 sao: Rất Tốt - 1
						sao: Rất Tệ):</p>
					<div class="review-product">
						<div class="image-product">
							<img
								src="https://cdn.britannica.com/35/238335-050-2CB2EB8A/Lionel-Messi-Argentina-Netherlands-World-Cup-Qatar-2022.jpg"
								alt="" />
						</div>
						<div class="rating-product">
							<div class="name-product-revew">${orderItem.product.name}</div>
							<ul class="rating">
								<li class="rate-item rate-item-1" data-index="1"><img
									src=" <c:url value = '/template/web/assets/images/star.png'/>" alt="" /></li>
								<li class="rate-item rate-item-2" data-index="2"><img
									src="<c:url value = '/template/web/assets/images/star.png'/>" alt="" /></li>
								<li class="rate-item rate-item-3" data-index="3"><img
									src="<c:url value = '/template/web/assets/images/star.png'/>" alt="" /></li>
								<li class="rate-item rate-item-4" data-index="4"><img
									src="<c:url value = '/template/web/assets/images/star.png'/>" alt="" /></li>
								<li class="rate-item rate-item-5" data-index="5"><img
									src="<c:url value = '/template/web/assets/images/star.png'/>" alt="" /></li>
							</ul>
							<h6 class="title-rating">Đánh giá chi tiết</h6>
							<textarea name="content" id="" cols="80" rows="10"
								placeholder="Bạn nghĩ gì về sản phẩm này?" class="comment"></textarea>
						</div>
					</div>
					<div class="sendReview">
						<button type="button" class="send">gửi</button>
					</div>
				</div>
				<input type="hidden" name="starRate"> <input type="hidden"
					name="productId" value="${orderItem.product.id}">
			</form>
		</div>
	</div>
	<script src=" <c:url value='/template/web/js/reviewOrder.js'/>"></script>
</body>
</html>