import { apiRequestMethodGet, apiRequestMethodPost, setToken } from "./homePage.js";
import { loginSucess } from "./menuHeader.js";
var user
var cartApi = "http://localhost:8080/e-commerceSpringMvc/api/carts";
apiRequestMethodGet("http://localhost:8080/e-commerceSpringMvc/api/user", getUserData);
function getUserData(response) {
	user = response;
	getCart(response);
	loginSucess(response);
}


function getCart(user) {
	var course = "http://localhost:8080/e-commerceSpringMvc/api/carts/user";
	console.log(course);
	
	const cart = document.querySelector('.header-cart')
	const listCart = document.querySelector('.list-cart')
	const coating = document.querySelector('.header')
	const modelCart = document.querySelector('.modal-cart');
	const closeSidebar = document.querySelector('.header-sibarCart span')
	let btnCheckout = document.querySelector('.btn-check');
	let btnDelete = document.querySelector('.delete');
	console.log(modelCart);
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
		console.log(products)
		const listProduct = document.querySelector(".list-production");
		console.log(products);
		const htmls = products != null && products.id > 0 && products.cartItems.length > 0 && products.cartItems.map(pro => {

			const image = pro.product.image;
			console.log(image);
			return `
	            <li class="item-production product-item-${pro.id}" data-id="${pro.id}">
	            	<input type="checkbox" name="" id="" class="checkBox-cart">
	                <img src="${image}" alt="" >
	                <div class="info-product">
	                            <p>${pro.product.name}</p>
	                            <p>${pro.product.price} x ${pro.quanlity} <span>${pro.totalPrice}.00</span></p>
	                </div>
	                <img src="./images/icon-delete.svg" alt="" class="delete" data-id="${pro.id}">
	                
	            </li>
	            
	            `
		}).join('') + `<button class="btn-check" >checkout</button>` || `<h2 class="cart-empty">Your cart is empty.</h2>`

		if (products != null) {
			listProduct.classList.add('active')
		}
		else {
			listProduct.classList.remove('active')
		}

		listProduct.innerHTML = htmls

		btnDelete = document.querySelector('.delete');
		catchEvent();
	}

	// call api get all cart 
	function getAllCarts(callback) {
		if (user != null) {
			course += "/" + user.id;
			fetch(course)
				.then((response) => response.json())
				.then(callback);
		}
		else {
			render(null)
		}



	}





}
function checkout() {
	console.log("d")
	const list = document.querySelector('.list-production')
	const listCheckbox = list.querySelectorAll('input[type="checkbox"]:checked');
	console.log(listCheckbox)
	const data = {}
	let cartId = []
	listCheckbox.forEach(checkbox => {
		const product = checkbox.parentElement;
		cartId.push(product.getAttribute("data-id"))

	})
	const newUrl = new URL("http://localhost:8080/e-commerceSpringMvc/product/detail/checkout");

	// Thêm header
	newUrl.searchParams.set("cartId", cartId);
	window.location.href = newUrl.toString();

}
function deleteProduct(id) {

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
function handleEventCart() {

	btnDelete.onclick = () => {
		const id = btnDelete.getAttribute("data-id")
		deleteProduct(id);
	}


}
// delete cart-item
function catchEvent() {
	const btnCheckout = document.querySelector('.btn-check');
	const btnDel = document.querySelectorAll('.delete')
	if (btnDel != null) {
		btnDel.forEach(btn => {
			btn.onclick = () => {
				const id = btn.getAttribute("data-id");
				deleteProduct(id);
			}
		})
	}
	btnCheckout.onclick = () => checkout();
}

export { user }
