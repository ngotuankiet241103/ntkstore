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
	<div class="container-post">
		<div class="box-post">
			<h3>Bài viết nổi bật</h3>
			<div class="box-content">
				<ul class="list-post">
					<c:forEach items="${blogs}" var="blog">
						<li class="post-item">
							<div class="post-content">
								<a href=" <c:url value ='/blog/${blog.code}'/>">${blog.title}</a>
								<p>${blog.description}</p>
								<div class="tags">
									<ul class="list-tag">
										<c:forEach items="${blog.tags}" var="tag">
											<li class="tag-item"><a href="<c:url value='/blog/tag/${tag.code}'/>" class="tag-item-link">${tag.name}</a>
											</li>
										</c:forEach>

									</ul>
								</div>
							</div>
							<div class="post-image">
								<img src="${blog.image}" alt="" />
							</div>
						</li>
					</c:forEach>


				</ul>
				<div class="box-topic">
					<h5>Các chủ đề đề xuất</h5>
					<ul class="list-topic">
						<c:forEach items="${topics}" var="topic">
							<li class="topic-item"><a
								href="<c:url value ='/blog/topic/${topic.code}'/>"
								class="topic-item-link">${topic.name}</a></li>
						</c:forEach>

					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>