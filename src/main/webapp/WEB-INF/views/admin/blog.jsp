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
	<section class="section-blog">
		<div class="header-blog">
			<div class="header-search">
				<input type="text"
					placeholder="Tìm kiếm mã đơn hàng hoặc tên khách hàng" />
				<button type="button">Tìm kiếm</button>
			</div>
			<div class="header-category">
				<select>
					<option value="">Lọc theo trạng thái đơn hàng</option>
					<option value="">Mới nhất</option>
					<option value="">Cũ nhất</option>
				</select>
				<button type="button">Lọc</button>
			</div>
			<a href=" <c:url value = '/admin/blog/add' />" class="addBlog">Thêm
				bài viết</a>
		</div>
		<div class="content-blog">
			<ul class="list-title">
				<li class="title-item">Mã bài viết</li>
				<li class="title-item">Tiêu đề bài viết</li>
				<li class="title-item">Người tạo</li>
				<li class="title-item">Ngày tạo</li>
				<li class="title-item">Chỉnh sửa mới nhất</li>
				<li class="title-item">Người chỉnh sửa</li>
				<li class="title-item">Thao tác</li>
			</ul>
			<ul class="list-order">
				<li class="blog-item"><c:forEach items="${blogs}" var="item">
						<span>Mã bài viết</span>
						<span>${item.title}</span>
						<span></span>
						<span>${item.content}</span>
						<span>Chỉnh sửa mới nhất</span>
						<span>Người chỉnh sửa</span>
						<span>Thao tác</span>
					</c:forEach></li> <
			</ul>
		</div>
		<ul class="pagination" id="pagination"></ul>
	</section>
</body>
</html>