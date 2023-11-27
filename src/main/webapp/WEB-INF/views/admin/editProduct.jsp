<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="form-edit-container">
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
			<div class="form-categories">
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
					<label for="">Giảm giá</label> <input type="text" name="discount"
						class="name">
				</div>
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
</body>
</html>