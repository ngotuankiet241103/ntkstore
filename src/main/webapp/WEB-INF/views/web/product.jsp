<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<c:url var="url" value="http://localhost:8080/e-commerceSpringMvc/product/detail" />
</head>
<body>
	<div class="section-product">
		<c:if test="${categoryName != null}">
			<h3 class="text-center">${categoryName}</h3>
		</c:if>
		<c:if test="${categoryName == null}">
			<h3 class="text-center">Shop All</h3>
		</c:if>
		
		<div class="grid wide">
			<div class="row home__product">
				<c:forEach items="${model}" var="product">
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
		<form action="<c:url value='/product?page=' />" method="get" class="form">
			<input type="hidden" value="${pageable.pageStart}" name="page" />
			
		</form>
		<input type="hidden" value="${pageable.totalsPage}" name="totalPage" />
		
	</div>
	<script type="text/javascript"
		src="<c:url value ='/template/pagination/pagination.js'/>"></script>
	<script type="text/javascript">
		
	
       	const totalPage = +document.querySelector('input[name="totalPage"]').value;
		const inputPage = document.querySelector('input[name="page"]');
		const page = +document.querySelector('input[name="page"]').value;
		console.log(totalPage);
		console.log( page - 1);
		
		const form = document.querySelector('.form');
		
		
      pagination({
        page: "#pagination",
        pageItem: "page-item",
        pageIcon: "page-icon",
        totalPage: totalPage,
        startPage: page,
        visiblePages: Math.floor(totalPage / 2),
        onPageClick: function(page){
        	inputPage.value = page
        	form.submit();
        },
        handleNextPage: function () {
          this.onPageClick(this.startPage + 1);
        },
        handlePrevPage: function () {
          this.onPageClick(this.startPage - 1);
        }
      });
  
	</script>
</body>
</html>