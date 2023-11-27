<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<title>Flexy Admin Lite Template by WrapPixel</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="canonical"
	href="https://www.wrappixel.com/templates/Flexy-admin-lite/" />

<link href="<c:url value= '/template/admin/css/product.css' />"
	rel="stylesheet">
<link href=" <c:url value ='/template/admin/css/edit.css" '/>"
	rel="stylesheet">
<link href="<c:url value= '/template/admin/css/header.css' />"
	rel="stylesheet">
<link href="<c:url value ='/template/pagination/pagination.css'/>"
	rel="stylesheet">
<link href="<c:url value= '/template/admin/css/order.css' />"
	rel="stylesheet">
<link href="<c:url value= '/template/admin/css/category.css' />"
	rel="stylesheet">

<link href="<c:url value= '/template/admin/css/blog.css' />"
	rel="stylesheet">
<link href="<c:url value= '/template/admin/css/blogPageAdd.css' />"
	rel="stylesheet">
<link href="<c:url value= '/template/admin/css/user.css' />"
	rel="stylesheet">
<link href="<c:url value= '/template/admin/css/additional.css' />"
	rel="stylesheet">
</head>

<body>
	<div class="app">
		<%@include file="/common/admin/header.jsp"%>

		<div class="container">
			<%@include file="/common/admin/sideBar.jsp"%>
			<dec:body />
		</div>
	</div>

	<script>
        const menuItem = document.querySelectorAll('.page-item');
        menuItem.forEach(item => {
            item.onclick = () => {
                console.log(item)
                const itemActive = document.querySelector('.page-item.active');
                itemActive.classList.remove("active");
                item.classList.add("active")
            }
        });
    </script>
</body>
</html>