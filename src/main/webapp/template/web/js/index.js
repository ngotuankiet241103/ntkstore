import {user} from "./header.js"
console.log(user)
var course = "http://localhost:8080/e-commerceSpringMvc/api/carts/user/" + user.id;
console.log(course);
var cartApi = "http://localhost:8080/e-commerceSpringMvc/api/carts";
const cart = document.querySelector('.header-cart')
const listCart = document.querySelector('.list-cart')
const coating = document.querySelector('.header_section')
const modelCart = document.querySelector('.modal-cart');
const closeSidebar = document.querySelector('.header-sibarCart span')
console.log(cart);
// open sidebar cart
listCart.onclick = (e) => e.stopPropagation()
cart.onclick = () => {
	coating.classList.add("active")
	modelCart.classList.add("active")

}
// close sidebar cart
closeSidebar.onclick = () => {
    coating.classList.remove("active")
    modelCart.classList.remove("active")
}
function start() {
	getAllCarts(render);
}
start();
// render cart item 
function render(products) {
	const listProduct = document.querySelector(".list-production");
	console.log(products);
	const htmls = products != null && products.cartItems.map(pro => {

		const image = pro.product.image;
		console.log(image);
		return `
	            <li class="item-production product-item-${pro.id}">
	            	<input type="checkbox" name="" id="" class="checkBox-cart">
	                <img src="${image}" alt="" >
	                <div class="info-product">
	                            <p>${pro.product.name}</p>
	                            <p>${pro.price} x ${pro.quanlity} <span>${pro.totalPrice}.00</span></p>
	                </div>
	                <img src="./images/icon-delete.svg" alt="" class="delete" onclick = deleteProduct(${pro.id})>
	                
	            </li>
	            
	            `
	}).join('') + `<button class="btn-check" onclick=checkout()>checkout</button>` || `<h2 class="cart-empty">Your cart is empty.</h2>`

	if (products != null) {
		listProduct.classList.add('active')
	}
	else {
		listProduct.classList.remove('active')
	}

	listProduct.innerHTML = htmls
}
// call api get all cart 
function getAllCarts(callback) {
	if (userId != "null") {
		course += userId;
		fetch(course)
		.then((response) => response.json())
		.then(callback);
	}
	else{
		render(null)
	}
	


}
// delete cart-item
function deleteProduct(id) {
	alert("a")
	const option = {
		method: 'DELETE',
		headers: {
			'Content-Type': 'application/json'
		},
	}
	fetch(cartApi + "/" + id, option)
		.then(response => response.json())
		.then(function() {
			alert("a")
			var productItem = document.querySelector('.product-item-' + id)
			productItem.remove()
		})
}

function checkout(){
	const list = document.querySelector('.list-production') 
	const listCheckbox = list.querySelectorAll('input[type="checkbox"]:checked');
	
}