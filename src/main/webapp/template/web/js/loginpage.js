import { apiRequestMethodGet, apiRequestMethodPost,setToken } from "./homePage.js";

const btnLogin = document.querySelector('.login')
const inputLogin = document.querySelectorAll('input.inputLogin')
btnLogin.onclick = () => {
	const data = {}
	inputLogin.forEach(element => {
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
	fetch("http://localhost:8080/e-commerceSpringMvc/login",option)
		.then(response => response.json())
		.then(response => {
			console.log("a");
			console.log(history.back());
			setToken(response);
			
		})
}
	
		
		

