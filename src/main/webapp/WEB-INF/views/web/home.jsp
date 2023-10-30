<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/tablib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:url var="bg1" value='/template/web/assets/images/slider-image-2.jpg ' />
</head>
<body>
	<section class="container">
		<div class="slider">
			<div class="slider-item first"
				style="background-image: url(<c:url value='/template/web/assets/images/slider-image-1.jpg'/>);">
				<div class="content-slider">
					<h3 class="title-silde">NTK</h3>
					<p class="context-slide">
						Back to <br /> school
					</p>
					<span>Delevery NTK store now</span>
					<button class="shopping">Shop now</button>
				</div>
				<div class="image-slide">
					<img
						src=" <c:url value='/template/web/assets/images/slide-image-1-removebg-preview.png'/>"
						alt="" />
				</div>
			</div>
			<div class="slider-item"
				style=" background-image: url(<c:url value='/template/web/assets/images/slider-image-1.jpg'/>);">
				<div class="content-slider">
					<h3 class="title-silde">NTK</h3>
					<p class="context-slide">
						Back to <br /> school
					</p>
					<span>Delevery NTK store now</span>
					<button class="shopping">Shop now</button>
				</div>
				<div class="image-slide">
					<img
						src=" <c:url value='/template/web/assets/images/slide-image-2-removebg-preview.png '/>"
						alt="" />
				</div>
			</div>
			<span class="icon right"><i class="fa-solid fa-chevron-right"></i></span>
			<span class="icon left"><i class="fa-solid fa-chevron-left"></i></span>
		</div>
		<div class="new-collection">
			<h3 class="title-new-collection">New Arval</h3>
			<div class="list-newCollection">
				<ul class="new-arrivals">
					<c:forEach items="${products}" var="product">
						<li class="item-new-collection"><a href="<c:url value = '/product/detail/${product.code}'/>"
							class="itemLink-new-collection">
								<div class="image-newItem">
									<img
										src="${product.image}"
										alt="" />
								</div>
								<div class="detailsNewItem">
									<h5 class="detailsNewItem-name">${product.name}</h5>
									<span class="detailsNewItem-price">${product.price }</span>
								</div>
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="collections">
			<div class="box-collections">
				<div class="box-item-collection">
					<div class="collection-item first">
						<div class="content-collection">
							<h4>polo & tops</h4>
							<div class="button-shopping">
								<a href="">Shop now</a>
							</div>
						</div>
					</div>
				</div>
				<div class="box-item-collection">
					<div class="collection-item second">
						<div class="content-collection">
							<h4>PREMIUM ZIP JACKETS</h4>
							<div class="button-shopping">
								<a href="">Shop now</a>
							</div>
						</div>
					</div>
				</div>
				<div class="box-item-collection">
					<div class="collection-item third">
						<div class="content-collection">
							<h4>Jogger & bottom</h4>
							<div class="button-shopping">
								<a href="">Shop now</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="policy">
			<div class="box-policy">
				<h3 class="title-store">NTK</h3>
				<h2 class="title-policy">
					<span>Live life with precision</span> on and off the field.
				</h2>
				<p class="desc-policy">Welcome to the exclusive source of NTK
					own Premium Lifestyle Apparel</p>
				<ul class="list-policy">
					<li class="policy-item">
						<div class="image-policy-item">
							<img
								src=" <c:url value='/template/web/assets/images/p1-removebg-preview.png'/>"
								alt="" />
						</div>
						<div class="content-policy-item">
							<h5>Prenium +</h5>
						</div>
					</li>
					<li class="policy-item">
						<div class="image-policy-item">
							<img
								src=" <c:url value='/template/web/assets/images/c2-removebg-preview.png '/>"
								alt="" />
						</div>
						<div class="content-policy-item">
							<h5>Expertise +</h5>
						</div>
					</li>
					<li class="policy-item">
						<div class="image-policy-item">
							<img
								src=" <c:url value='/template/web/assets/images/c3-removebg-preview.png'/>"
								alt="" />
						</div>
						<div class="content-policy-item">
							<h5>Quick Shipping +</h5>
						</div>
					</li>
					<li class="policy-item">
						<div class="image-policy-item">
							<img
								src=" <c:url value='/template/web/assets/images/c4-removebg-preview.png'/>"
								alt="" />
						</div>
						<div class="content-policy-item">
							<h5>Raving reviews +</h5>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</section>
	<script>
      function slider() {
    	  console.log("a")
          const sliderItem = document.querySelectorAll(".slider-item");
          let margin = 0;
          const sliderItemFirst = document.querySelector(".slider-item.first");
          let index = 0;
          const icons = document.querySelectorAll(".icon");
          const arr = sliderItem.length;
          setInterval(() => {
            margin -= 50;
            index++;
            changeSlide();
          }, 5000);
          icons.forEach((icon) => {
        	  console.log("b")
            icon.onclick = () => {
            	console.log("c")
              if (icon.classList.contains("right")) {
                index++;
                margin -= 50;
                changeSlide();
              } else {
                index--;
                margin -= 50;
                changeSlide();
              }
            };
          });
          function changeSlide() {
            if (index >= arr || index <= 0) {
              index = 0;
              margin = 0;
              sliderItemFirst.style.marginLeft = margin +`%`;
            }

            sliderItemFirst.style.marginLeft = margin +`%`;
          }
        }
        slider();
      </script>
</body>
</html>