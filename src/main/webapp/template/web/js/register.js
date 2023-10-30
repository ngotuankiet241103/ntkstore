import { setToken } from "./homePage.js"
const register = document.querySelector('.register')
const inputRegister = document.querySelectorAll('input.inputRegister')
register.onclick = () => {
	const form = document.querySelector('.form-register');
	const html = `<p>Vui lòng xác thực email để kích hoạt tài khoản</p>`
	form.innerHTML = html
	const data = {}

	console.log(inputRegister)
	inputRegister.forEach(element => {
		console.log(element.name)
		console.log(element.value)
		data[element.name] = element.value
	}
	);
	console.log(data)
	const option = {
		method: "POST",
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	};
	fetch("http://localhost:8080/e-commerceSpringMvc/register", option)
		.then(response => response.json())
		.then(response => {
			setToken(response);
			window.location.href = "http://localhost:8080/e-commerceSpringMvc/trang-chu"
		})
}