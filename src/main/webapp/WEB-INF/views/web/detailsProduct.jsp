a
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tablib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Template 5</title>
<c:url var="apiUrl" value="/api/product/order" />
<c:url var="Url" value="/product" />
<c:url var="checkout" value="/checkout" />
</head>
<body>
	<div id="app">
		<div class="grid wide">
			<form action="checkout" method="post" class="checkout">
				<div class="container-product ">
					<div class="single-img-product">
						<input type="hidden" value="${product.id}" class="idProduct"
							name="productId"> <img src="${product.image}" alt=""
							class="main-img">

						<div class="img-prev img">
							<img src="./images/icon-previous.svg" alt="">
						</div>
						<div class="img-next img">
							<img src="./images/icon-next.svg" alt="">
						</div>
					</div>

					<div class="content-product">
						<h1 class="name-product">${product.name}</h1>
						<div class="price">${product.price}</div>
						<div class="listSize">
							<c:if test="${product.status == 1}">

								<c:forEach items="${product.sizeQuantityMap}" var="entry">

									<div class="varient-input">
										<input type="radio" value="${entry.key}"
											id="size-${entry.key}" class="d-none" name="size"> <label
											for="size-${entry.key}" class="selectSize">${entry.key}</label>
										<input type="hidden" value="${entry.value}" class="entry">
									</div>


								</c:forEach>

							</c:if>

							<c:if test="${product.status != 1}">
								<span class="soldOut">Sẩn phẩm hiện đang hết hàng</span>
							</c:if>

						</div>
						<span class="text"></span> <span class="notEmpty">Chọn size
							bạn muốn</span>
						<div class="order">
							<div class="order-amount">
								<button type="button" class="minus">
									<img
										src=" <c:url value ='/template/web/assets/images/icon-minus.svg' />"
										alt="">
								</button>
								<input type="number" value="1" name="quanlity"
									class="input quanlity">
								<button type="button" class="plus">
									<img
										src=" <c:url value ='/template/web/assets/images/icon-plus.svg' />"
										alt="">
								</button>
							</div>

							<button class="order-toCart" type="button">Add to cart</button>
						</div>
						<input class="totalPrice" name="totalPrice" type="hidden">
						<button class="btn-buy" type="button">Buy</button>
						<p class="desc-product">${product.description}</p>
					</div>

				</div>
				<input type="hidden" name="Authorization" />
			</form>
		</div>
		<div class="modal">
			<div class="header-modal">
				<span>&times;</span>
			</div>
			<div class="single-img-modal">
				<img src="./images/image-product-1.jpg" alt=""
					class="main-img-modal">
			</div>
		</div>
		<div class="modal-mobile">
			<div class="side-bar-mobile">
				<div class="header-side-bar-mobile">
					<img src="./images/icon-close.svg" alt="">
				</div>
				<ul class="list-menu-mobile">
					<li class="side-bar-item-mobile"><a href="#"
						class="item-link-mobile">collections</a></li>
					<li class="side-bar-item-mobile"><a href="#"
						class="item-link-mobile">men</a></li>
					<li class="side-bar-item-mobile"><a href="#"
						class="item-link-mobile">women</a></li>
					<li class="side-bar-item-mobile"><a href="#"
						class="item-link-mobile">about</a></li>
					<li class="side-bar-item-mobile"><a href="#"
						class="item-link-mobile">contact</a></li>
				</ul>
			</div>
		</div>
		<div class="box-comment">
			<div class="title-comment">
				<h4>Reviews</h4>
			</div>
			<c:if test="${reviews.size() <= 0}">
				<div class="comments-empty">
					<span>Sản phẩm này chưa có đánh giá</span>
				</div>
			</c:if>
			<c:if test="${reviews.size() > 0}">
				<ul class="list-comment">
					<c:forEach items="${reviews}" var="review">
						<li>
							<h3>${review.email}</h3> <span>${review.createdDate}</span>
							<ul class="rating">

							</ul>
							<p>${review.content}</p> <input type="hidden" name="starRate"
							value="${review.starRate}">
						</li>

					</c:forEach>

				</ul>
			</c:if>

		</div>
		<div class="relatedProduct">
			<h3 class="title-new-collection">Related Product</h3>
			<div class="list-newRelatedProduct">
				<ul class="new-arrivals">
					<c:forEach items="${products}" var="product">
						<li class="item-related-collection"><a href="<c:url value = '/product/detail/${product.code}'/>"
							class="itemLink-related-collection">
								<div class="image-relatedItem">
									<img
										src="${product.image}"
										alt="" />
								</div>
								<div class="detailsRelatedItem">
									<h5 class="detailsNewItem-name">${product.name}</h5>
									<span class="detailsNewItem-price">${product.price}</span>
								</div>
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		
		const index =document.querySelectorAll('input[name="starRate"]')
		
		index.forEach(index => {
			let html  = "";
			const parent = index.parentElement;
			console.log(parent)
			const starRate = parent.querySelector('ul')
			for(let i = 1; i <= 5; i++ ){
				console.log(index.value)
				if(i <= index.value){
					html += `<li class="rate-item rate-item-1" data-index="1"><img
						src=" <c:url value = '/template/web/assets/images/star-yellow.png'/>" alt="" /></li>`
				}
				else{
					html += `<li class="rate-item rate-item-1" data-index="1"><img
						src=" <c:url value = '/template/web/assets/images/star.png'/>" alt="" /></li>`
				}
			}
			starRate.innerHTML = html;
		})
		
		
	</script>
	<script type="module" src="<c:url value='/template/web/js/carts.js' />"></script>

</body>
</html>