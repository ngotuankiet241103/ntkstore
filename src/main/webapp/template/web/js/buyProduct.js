import { apiRequestMethodPost } from "./homePage.js";
function buyProduct() {
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
	// apiRequestMethodPost("http://localhost:8080/e-commerceSpringMvc/checkout", redirectResponse, data);

}

function redirectResponse(url) {

	window.location.href = url.url;

}