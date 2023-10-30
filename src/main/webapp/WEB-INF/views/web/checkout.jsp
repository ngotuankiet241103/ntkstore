<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<c:url var="payCheckout" value='/checkout' />
<c:url var="codCheckout" value='/checkout/cod' />
</head>
<body>
	<form action="" method="get" class="form-pay">
		<div class="container-checkout row">

			<div class="form-checkout" id="form-1">
				<h4 class="">Thông tin giao hàng</h4>
				<div class="form-group">
					<label for="">Họ và Tên</label> <input id="fullName" type="text"
						name="userName" class="form-control"> <span
						class="form-message"></span>
				</div>
				<div class="form-group">
					<label for="">Email</label> <input id="email" type="email"
						name="email" class="form-control"> <span
						class="form-message"></span>
				</div>
				<div class="form-group">
					<label for="">Số điện thoại</label> <input id="phone" type="text"
						name="phone" class="form-control"> <span
						class="form-message"></span>
				</div>
				<div class="form-group">
					<label for="">Địa chỉ</label> <input id="address" type="text"
						name="address" class="form-control"> <span
						class="form-message"></span>
				</div>
				<div class="form-group pay">
					<label for="">Phương thức thanh toán</label>
					<div class="payment">
						<input type="radio" name="pay" class="" id="payAfter"> <label
							for="payAfter" id="" class="form-radio">Thanh toán khi
							nhận hàng</label>

					</div>
					<div class="payment">
						<input type="radio" name="pay" id="payInternetBanking"> <label
							for="payInternetBanking" id="" class="form-radio">Thanh
							toán qua paypal</label>

					</div>
					<span class="form-message"></span>
				</div>
				<button type="button" class="buyProduct" id="buttonSubmit">Đặt
					hàng</button>
			</div>
			<div class="allProduct">
				<h4>Danh sách sản phẩm</h4>
				<ul class="list-product">
					<c:forEach items="${orderItems}" var="orderItem">
						<li class="productItem">
							<div class="productItem-img">
								<img src="${orderItem.product.image}" alt="">
								<div class="quanlity-product">
									<p>${orderItem.quanlity}</p>
								</div>
							</div>
							<div class="productItem-info">
								<h5>${orderItem.product.name}</h5>
								<p>${orderItem.size}</p>
							</div>
							<div class="productItem-price">
								<p>${orderItem.totalPrice}</p>
							</div> <input name="productId" type="hidden"
							value="${orderItem.product.id}"> <input name="quantity"
							type="hidden" value="${orderItem.quanlity}"> <input
							name="totalPrice" type="hidden" value="${orderItem.totalPrice}">
							<input name="size" type="hidden" value="${orderItem.size}">
						</li>
					</c:forEach>
				</ul>
			</div>

		</div>
	</form>
	<script src=" <c:url value = '/template/validation/validation.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value = '/template/web/js/apiWithToken.js' />"></script>
	<script type="text/javascript">
		
		validation({
         form: "#form-1",
         error: ".form-message",
         formGroup: '.form-group',
         buttonSubmit: "#buttonSubmit",
         rules: [
             validation.isRequired("#fullName"),
             validation.isRequired("#email"),
             validation.isEmail("#email"),
             validation.isRequired("#phone"),
             validation.isRequired("#address"),
             validation.isRequired("input[name = 'pay']")
            
         ],
         onsubmit: function (datas){
        	 const pay = document.querySelector('input[type="radio"]:checked')
        	 const formPay = document.querySelector('.form-pay')
        	 if (pay.id == "payAfter") {
     			formPay.action = "${codCheckout}"
     		} else {
     			
     			formPay.action = "${payCheckout}"
     		}
        	 const formElement = document.querySelector('#form-1')
        		var enableInput = formElement.querySelectorAll('[name]')

        		var formValue = Array.from(enableInput).reduce((values, input) => {
        			switch (input.type) {
        				case 'radio':
        					// values[input.name] = formElement.querySelector("input[name='pay']:checked").value

        					break;

        				default:
        					values[input.name] = input.value
        					break;
        			}
        			return values
        		}
        			, {})
        		const listProduct = document.querySelector('.allProduct');
        		const values = listProduct.querySelectorAll('input');
        		const data = formValue;
        		values.forEach(value => {
        			if (Array.isArray(data[value.name])) {
        				data[value.name].push(value.value)
        			}
        			else {
        				data[value.name] = [value.value];
        			}
        		})
        		const api = document.querySelector('.form-pay').action;
        		console.log(JSON.stringify(data));
        		console.log(api)
        		apiRequestMethodPost(api, redirectResponse, data);
         }
     })
		function redirectResponse(url) {
			const curUrl = window.location.href;
			if(curUrl.indexOf("?") > 0){
				var option = {
						method: "DELETE",
						headers: {
							"Content-Type": "application/json",
						},
					};
				fetch(curUrl,option)
				.then(response => { 
					if(response.status === 200){
						window.location.href = url.url;
					}
					});
			}
			else{
				
				window.location.href = "http://localhost:8080/e-commerceSpringMvc/" +  url.url;
			}
			
		
		}
    
	</script>
</body>
</html>