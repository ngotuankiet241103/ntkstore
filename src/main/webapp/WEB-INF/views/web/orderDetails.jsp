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
	<div class="profile-container">
        <nav class="sidebar-prodfile">
          <ul class="nav-container">
            <li>
              <a href="">Quản lý tài khoản</a>
              <ul class="details-nav">
                <li>
                  <a href="">Thông tin cá nhân</a>
                </li>
                <li><a href="">Địa chỉ</a></li>
              </ul>
            </li>
            <li>
              <a href="">Quản lý đơn hàng</a>
              <ul class="details-nav">
                <li><a href="">Đơn hàng đổi trả</a></li>
                <li><a href="">Đơn hàng hủy</a></li>
              </ul>
            </li>
          </ul>
        </nav>
        <div class="details-orders">
          <h2>Đơn hàng của tôi</h2>
          <ul class="listStatus">
            <li class="orderStatus active all" >
              Tất cả 
            </li>
            <li class="orderStatus">
              Đang xử lý
            </li>
            <li class="orderStatus">
              Đã đóng gói
            </li>
            <li class="orderStatus">
              Đang giao hàng
            </li>
          </ul>
          <ul class="listOrder">
          </ul>
         <ul class="pagination" id="pagination"></ul>
        </div>
      </div>
      <script  type="module" src=" <c:url value ='/template/web/js/orderDetails.js'/>"></script>
     
      
</body>
</html>