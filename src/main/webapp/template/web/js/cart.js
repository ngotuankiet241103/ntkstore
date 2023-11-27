import { apiRequestMethodPost, apiRequestMethodGet } from "./homePage.js";
import { user } from "./header.js";
const selectSize = document.querySelectorAll('.selectSize');
var product;
selectSize.forEach(s => s.onclick = () => {
	const textWarning = document.querySelector('.notEmpty.active')
	textWarning != null ? textWarning.classList.remove("active") : "";
	const sizeAcitve = document.querySelector('.selectSize.active');
	const inputChecked = document.querySelector('input[type="radio"]:checked');
	if (inputChecked != null && sizeAcitve != null) {

		inputChecked.setAttribute('selected', 'false')
		sizeAcitve.classList.remove("active")

	}
	s.classList.add("active")
	let value = s.parentElement.querySelector('.entry').value
	showText(value);
	const input = s.parentElement.querySelector('input[type="radio"]')
	input.setAttribute('selected', 'true')
});
function showText(value) {
	const text = document.querySelector('.text')
	if (value > 10) {
		text.innerHTML = `Sản phẩm còn ` + value;
		text.style.color = "green";
	}
	else {
		text.innerHTML = `Sản phẩm hiện còn ` + value;
		text.style.color = "yello";
	}
}
const minus = document.querySelector('.minus')
let inputValue = document.querySelector('.input').value;
let input = document.querySelector('.input');
const plus = document.querySelector('.plus')
const price = document.querySelector('.final-price') != null ? document.querySelector('.final-price').innerText : document.querySelector('.price').innerText;
console.log(price)
input.value = inputValue
totalPrice(inputValue, price)
console.log("b")
plus.onclick = (e) => {

	if (checkSize()) {
		const sizeAcitve = document.querySelector('.selectSize.active');
		let value = sizeAcitve.parentElement.querySelector('.entry').value
		if (value == inputValue) {
			return;
		}
		inputValue = ++inputValue;
		input.value = inputValue
		totalPrice(inputValue, price)
	}

}
minus.onclick = () => {
	if (checkSize()) {
		if (inputValue == 1) {
			return;
		}
		inputValue = --inputValue;
		input.value = inputValue
		totalPrice(inputValue, price)
	}
}
function totalPrice(quanlity, price) {
	const totalPrice = parseInt(quanlity) * parseFloat(price);
	const inputTotalPrice = document.querySelector('.totalPrice');
	console.log(quanlity,price);
	inputTotalPrice.value = totalPrice
	console.log(inputTotalPrice)
}
const btnAddProductToCart = document.querySelector('.order-toCart');
const buyCart = document.querySelector('.btn-buy');
btnAddProductToCart.onclick = () => {
	product = getValueToObject();
	console.log(product);
	addProduct(product);
}
const btnBuy = document.querySelector('.btn-buy')
btnBuy.onclick = () => {
	if (!checkSize()) {
		return;
	}
	apiRequestMethodGet("http://localhost:8080/e-commerceSpringMvc/api/user", getUserData);
	
}
function getUserData(response) {
	if(response != null){
		document.querySelector('.checkout').submit();
	}
}
function checkSize() {
	const sizeAcitve = document.querySelector('.selectSize.active');
	if (sizeAcitve != null) {
		return true;
	}
	const textWarning = document.querySelector('.notEmpty');
	textWarning.classList.add("active")
	return false;
}
function getValueToObject() {
	if (!checkSize()) {
		return;
	}
	const id = document.querySelector('.idProduct').value;
	const size = document.querySelector("input[type='radio']:checked").value;
	const quanlity = document.querySelector('.quanlity').value;
	const price = document.querySelector('.price').textContent;
	const total = parseInt(quanlity) * parseFloat(price)
	const product = {
		"productId": parseInt(id),
		"size": size,
		"quanlity": parseInt(quanlity),
		"totalPrice": total
	}
	console.log(product)
	return product;
}
function addProduct(data) {
	let cartApi = "http://localhost:8080/e-commerceSpringMvc/api/product/order"
	if(user != null){
		cartApi += "/" + user.id;
	}
	apiRequestMethodPost(cartApi, hanldeAfterAddToCart, data);


}
function hanldeAfterAddToCart(response) {
	
}