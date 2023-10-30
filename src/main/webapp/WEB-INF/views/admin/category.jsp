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
	<section class="category-container">

		<div class="category-header">
			<button class=" add-category-common">Add category</button>
			<button class=" add-category">Add details category</button>
		</div>

		<div class="details-category">

			<ul class="title-common-category">
				<li class="item-title-category">Id</li>
				<li class="item-title-category">Name</li>
				<li class="item-title-category">Category</li>
				<li class="item-title-category">Create At</li>
				<li class="item-title-category">Status</li>
				<li class="item-title-category">Action</li>
			</ul>
			<ul class="list-order">
				<c:forEach items="${listCategoryDetails}" var="item">
					<li class="category-item"><span>${item.id}</span> <span>${item.name}</span>
						<span>${item.category.name}</span> <span>null</span> <span>null</span>
						<span><i class="fa-solid fa-ellipsis-vertical"></i></span></li>
				</c:forEach>
			</ul>
		</div>
	</section>
	<div class="box">
		<div class="box-category">
			<form action="category" method="post">
				<h4>Add category</h4>
				<div class="group">
					<label for="name">Name</label> <input type="text" id="name"
						name="name">
				</div>
				<div class="group">
					<label for="code">Code</label> <input type="text" id="code"
						name="code">
				</div>
				<button class="btn-addCategory">Add</button>
			</form>
		</div>
		<div class="box-category-details">
			<form action="category/detail" method="post">
				<h4>Add category</h4>
				<div class="group">
					<label for="name">Name</label> <input type="text" name="name" id="name">
				</div>
				<div class="group">
					<label for="code">Code</label> <input type="text" name="code" id="code">
				</div>
				<div class="group">
					<label for="code">Category</label> <select name="categoryId" id=""
						class="group">
						<c:forEach items="${category}" var="item">
							<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select>
				</div>

				<button class="btn-addCategory">Add</button>
			</form>
		</div>
	</div>
	<script>
        const modal = document.querySelector('.box')
        const addCategory = document.querySelector('.add-category-common')
        const addCategoryDetails = document.querySelector('.add-category')
        addCategory.onclick = () => modal.classList.add('category-visible');
       
        addCategoryDetails.onclick = () => modal.classList.add('category-detals-visible');
    </script>
</body>
</html>