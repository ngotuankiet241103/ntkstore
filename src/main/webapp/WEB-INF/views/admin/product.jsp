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

	<nav class="nav">
		<div class="operation">
			<div class="findByCategory w-33">
				<form action="product" method="get" class="form-filter">
					<select name="categoryId">
						<option value="-1">Lọc theo phân loại</option>
						<c:forEach items="${model}" var="item">
							<c:if test="${categoryId != null}">
								<c:if test="${item.id == categoryId}">
									<option value="${item.id}" selected>${item.name}</option>
								</c:if>
								<c:if test="${item.id != categoryId}">
									<option value="${item.id}">${item.name}</option>
								</c:if>
							</c:if>
							<c:if test="${categoryId == null}">
								<option value="${item.id}">${item.name}</option>
							</c:if>
						</c:forEach>
					</select> <select name="status">

						<option value="-1">Lọc theo trạng thái</option>
						<option value="1">Còn hàng</option>
						<option value="0">Hết hàng</option>

					</select>

					<button class="filter" type="submit">Lọc</button>
					<input type="hidden" value="${pageable.pageStart}" name="page" />

				</form>
			</div>
			<button class="btnAddProduct">Thêm sản phẩm</button>
		</div>
		<ul class="detail-product">
			<li>Tên sản phẩm</li>
			<li>ảnh sản phẩm</li>
			<li>giá sản phẩm</li>
			<li>trạng thái</li>
			<li>thao tác</li>
		</ul>
		<ul class="list-product">
			<c:forEach items="${listProduct}" var="product">
				<li>
					<h3 class="w-2">${product.name}</h3>
					<div class="w-2">
						<img src="${product.image}">
						<c:choose>
							<c:when test="${product.status == 1}">

								<c:set var="result" value="còn hàng" />
							</c:when>
							<c:otherwise>
								<c:set var="result" value=" hết hàng" />
							</c:otherwise>
						</c:choose>
					</div> <span class="w-2">${product.price}</span> <span class="w-2">${result}</span>
					<div class="w-2">
						<span>delete</span>
					</div>
				</li>
			</c:forEach>

		</ul>
		<ul class="pagination" id="pagination"></ul>
	</nav>
	<div class="pop-up d-none">
		<form action="product" class="formProduct" method="post"
			enctype="multipart/form-data">
			<div class="form-group">
				<label for="">Tên sản phẩm</label> <input type="text" name="name"
					class="name">
			</div>
			<div class="form-group">
				<label for="">Ảnh sản phẩm</label> <input type="file"
					name="fileImage">
			</div>
			<div class="form-group">
				<label for="">Mô tả sản phẩm</label> <input type="hidden"
					name="description" />
				<textarea id="description" cols="74" rows="10"></textarea>
			</div>
			<div class="form-group">
				<label for="">Phân loại</label> <select name="categoryId">
					<c:forEach items="${category}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="">Cụ thể phân loại</label> <select
					name="categoryCommonId">
					<c:forEach items="${categoryCommon}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="">Giá sản phẩm</label> <input type="text" name="price">
			</div>


			<div class="form-group">
				<label for="">size-số lượng</label>
				<div class="size">
					<div class="form-group">
						<label for="">Size</label>
						<div class="size-form">
							<label for="">X</label> <input type="checkbox" name="size"
								value="X">
						</div>
						<div class="size-form">
							<label for="">M</label> <input type="checkbox" name="size"
								value="M">
						</div>
						<div class="size-form">
							<label for="">L</label> <input type="checkbox" name="size"
								value="L">
						</div>

					</div>
					<div class="form-group">
						<label for="">Số lượng</label>
						<div class="quantity-form">
							<input type="text" name="amount">
						</div>
						<div class="quantity-form">
							<input type="text" name="amount">
						</div>
						<div class="quantity-form">
							<input type="text" name="amount">
						</div>

					</div>
				</div>
			</div>
			<input type="hidden" name="status" value="1">
			<div class="buttonAddProduct">
				<button type="button" class="addProduct">Thêm</button>
			</div>
		</form>
		<input type="hidden" value="${pageable.totalsPage}" name="totalPage" />

	</div>
	<link href="<c:url value= '/template/admin/css/ckeditor.css' />"
		rel="stylesheet">
	<script type="text/javascript"
		src="<c:url value ='/template/pagination/pagination.js'/>"></script>
	<script
		src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/ckeditor.js"></script>

	<script type="text/javascript">
		
		const editor = ClassicEditor
		  .create(document.querySelector('#description'),{
			 
		  })
		  .catch(error => {
		    console.error(error);
		  });
		
		editor
		  .then(editor => {
			 
			  document.querySelector('.addProduct').onclick = () => {
				  document.querySelector('input[name="description"]').value = editor.getData();
				  document.querySelector('.formProduct').submit();
			  }
			 
		    
		    // Do something with the data
		  })
		  .catch(error => {
		    // Handle the error
		  });
		
		const buttonAddProduct = document.querySelector('.btnAddProduct');
		buttonAddProduct.addEventListener("click", function(){
			document.querySelector('.name').onchange = (e) => {
				console.log(e.target.value)
			}
			
			const popUp = document.querySelector('.pop-up')
			popUp.classList.remove("d-none")
		})
       	const totalPage = +document.querySelector('input[name="totalPage"]').value;
		const inputPage = document.querySelector('input[name="page"]');
		const page = +document.querySelector('input[name="page"]').value;
		console.log(totalPage);
		console.log(page);
		const filter = document.querySelector('.filter');
		const formFilter = document.querySelector('.form-filter');
		filter.onclick = () => {
			document.querySelector('input[name="page"]').value = "1";
		}
      pagination({
        page: "#pagination",
        pageItem: "page-item",
        pageIcon: "page-icon",
        totalPage: totalPage,
        startPage: page,
        visiblePages: Math.floor(totalPage / 2),
        onPageClick: function(page){
        	inputPage.value = ""+page
        	formFilter.submit();
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