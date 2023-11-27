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
			<h2>Hoàn hàng</h2>
			<form action="<c:url value ='/customer/order/view/refund'/>"
				method="post" class="form-refund-order form-reviews" id="form-refund" enctype="multipart/form-data">
				<div class="box-review">
					<div class="review-product">
						<div class="rating-product">
							<div class="name-product-revew">${orderItem.product.name}</div>
							<div class="form-group">
								<h6 class="title-rating">Lí do:</h6>
								<textarea name="reason" id="" cols="80" rows="10"
									placeholder="Lí do bạn hủy đơn hàng?" class="comment"></textarea>
							</div>
							<div class="form-group">
								<label>Ảnh sản phẩm</label>
								<input type="file" id="image" name="fileImage"/>
								 <span class="form-message"></span>
										
							</div>
						</div>
					</div>
					<div class="sendCancel">
						<button type="button" class="send">Gửi</button>
						
					</div>
				</div>
				<input type="hidden" name="orderId" value="${order.id}">
			</form>

		</div>
	</div>
	<script src=" <c:url value = '/template/validation/validation.js'/>"></script>
	<script type="text/javascript">
		validation({
         form: "#form-refund",
         error: ".form-message",
         formGroup: '.form-group',
         buttonSubmit: ".send",
         rules: [
        	 validation.isRequired("#image")
         ],
         onsubmit: function (datas){
        	 document.querySelector('#form-refund').submit();
         }
       
     })
		
    
	</script>
</body>
</html>