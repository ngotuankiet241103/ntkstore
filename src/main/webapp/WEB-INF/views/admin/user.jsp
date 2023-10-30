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
	<div class="user-container">
		<div class="header-user-manager">
			<div class="header-search">
				<input type="text" placeholder="Tìm kiếm theo emails" />
				<button type="button" class="btnSearch">Tìm kiếm</button>
			</div>
			<div class="header-add">
				<button class="btn-add">Add user</button>
			</div>
		</div>
		<div class="content-manager">
			<ul class="list-title">
				<li class="title-item">Id</li>
				<li class="title-item">Emails</li>
				<li class="title-item">Name</li>
				<li class="title-item">Role</li>
				<li class="title-item">Status</li>
				<li class="title-item">Action</li>
			</ul>
			<ul class="list-order users">
				<c:forEach items="${users}" var="user">
					<li class="user-item"><span>${user.id}</span> <span>${user.email}</span>
						<span>${user.fullName}</span> <span> <c:forEach
								items="${user.role}" var="role">
								${role}
							</c:forEach>
					</span> <c:if test="${user.enabled}">
							<span>active</span>
						</c:if> <c:if test="${!(user.enabled)}">
							<span>non-active</span>

						</c:if> <c:forEach items="${user.role}" var="role">
							<c:if test="${(role != ADMIN)}">
								<span class="future-manager"><i
									class="fa-solid fa-ellipsis-vertical"></i>
									<ul class="sub-feature">
										<li class="edit-permit" data-id="${user.id}">Chỉnh sửa
											quyền</li>
										<li class="block-user" data-id="${user.id}">Khóa người dùng</li>
										<li class="remove-user" data-id="${user.id}">Xóa người dùng</li>
									</ul> </span>
							</c:if>
						</c:forEach></li>
				</c:forEach>


			</ul>
		</div>
	</div>
	<div class="modal-user">
		<div class="form-editUser">
			<div>
				<select>
					<c:forEach items="${roles}" var="role">
						<c:if test="${role.name != ADMIN}">
							<option>${role.name}</option>
						</c:if>
					</c:forEach>
				</select>
				<button class="btn-editUser">Chỉnh sửa</button>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="<c:url value ='/template/admin/js/user.js'/>"></script>
</body>
</html>