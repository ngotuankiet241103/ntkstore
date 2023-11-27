<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:url var="url" value='/api/blog/${blog.id}/comment' />
</head>
<body>
	<div class="content-blog">
		<div class="content-blog-container">
			<h3>${blog.title}</h3>
			<div class="author">
				<span>${blog.userName}</span> <span>${blog.createdDate}</span>
			</div>
			<div class="content">${blog.content}</div>
			<div class="oparation-blog">
				<span><i class="fa-solid fa-heart"></i></span> <span
					class="showModalComment"><i class="fa-solid fa-comment"></i></span>
			</div>

		</div>
	</div>
	
	<div class="modal-comment">
		<div class="form-comment">
			<div class="header-close">
				<i class="fa-solid fa-xmark"></i>
			</div>
			<h4>Bình luận</h4>
			<div class="rootComment"></div>
			<ul class="comments"></ul>
		</div>
	</div>
	<input type="hidden" name="blogId" value="${blog.id}" class="blogId" />
	<input type="hidden" name="userId" value="${userId}" />
	 <script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/ckeditor.js"></script>
	<script src="<c:url value = '/template/web/js/commentBLog.js'/>"></script>
	<script type="text/javascript">
		const api = "${url}";
		console.log(api)
		commentBlog(api);
	</script>
</body>
</html>