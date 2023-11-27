<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<c:url value='/search' var="url"/>
<body>
	<div class="section-product">
		<h3 class="text-center">Search</h3>
		<div class="feature-search">
			<div class="container-searach">
				<input type="text" value="${query}">
				<button>
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</div>
		</div>

		<div class="grid wide">
			<div class="row home__product">
				<c:forEach items="${products}" var="product">
					<div class="col l-2-4 m-4 c-6 homepage__row">
						<a href=" ${url}/${product.code} " class="linkProduct">
							<div class="homepage__primary-img">
								<img src="${product.image}" alt="">

							</div>
							<div class="homepage__primary-info">
								<h5 class="homepage__primary-info-item">${product.name}</h5>
								<span class="homepage__primary-info-price">
									${product.price} </span>

							</div>
						</a>
					</div>
				</c:forEach>

			</div>
			<ul class="pagination" id="pagination"></ul>
		</div>
		<form action="<c:url value='/search' />" method="get"
			class="form">
			<input type="hidden" value="${query}" name="q">
			<input type="hidden" value="${pageable.pageStart}" name="page" />

		</form>
		<input type="hidden" value="${pageable.totalsPage}" name="totalPage" />
	</div>

	<script type="text/javascript"
		src="<c:url value ='/template/pagination/pagination.js'/>"></script>
	<script type="text/javascript">
		const btnSearch1 = document.querySelector('.container-searach button')
		console.log(btnSearch1)
		btnSearch1.onclick = () => {
			const data = document.querySelector('.container-searach input').value
			console.log("search")
			 window.location.href = "${url}"+ "?q=" + data;
		}
		const totalPage = +document.querySelector('input[name="totalPage"]').value;
		const inputPage = document.querySelector('input[name="page"]');
		const page = +document.querySelector('input[name="page"]').value;
		console.log(totalPage);
		console.log(page - 1);

		const form = document.querySelector('.form');
		pagination({
			page : "#pagination",
			pageItem : "page-item",
			pageIcon : "page-icon",
			totalPage : totalPage,
			startPage : page,
			visiblePages : Math.floor(totalPage / 2),
			onPageClick : function(page) {
				inputPage.value = page
				form.submit();
			},
			handleNextPage : function() {
				this.onPageClick(this.startPage + 1);
			},
			handlePrevPage : function() {
				this.onPageClick(this.startPage - 1);
			}
		});
	</script>
</body>
</html>