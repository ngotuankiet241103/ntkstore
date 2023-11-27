const items = document.querySelectorAll(".rate-item");
const btnSubmit = document.querySelector(".send");
items.forEach((item, index, arr) => {
	item.onclick = () => {
		document.querySelector('input[name=starRate]').value = item.dataset.index;
		if (item.classList.contains("active")) {
			for (var i = item.dataset.index; i <= arr.length; i++) {
				document.querySelector(`.rate-item-${i}`).classList.remove("active")
				const image = document.querySelector(`.rate-item-${i} img`)
				console.log(image)
				image.src = `http://localhost:8080/e-commerceSpringMvc/template/assets/web/images/star.png`
			}
		}
		for (var i = 1; i <= item.dataset.index; i++) {
			document.querySelector(`.rate-item-${i}`).classList.add("active")
			const image = document.querySelector(`.rate-item-${i} img`)
			console.log(image)
			image.src = `http://localhost:8080/e-commerceSpringMvc/template/web/assets/images/star-yellow.png`
		}
		btnSubmit.onclick = () => {
			const id = document.querySelector('input[name="orderItemId"]').value
			updateStatus(id);
			document.querySelector('.form-reviews').submit();

		};
	};
});
function updateStatus(id){
	const data = {
		isReview: true
	}
	const option = {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	}
	fetch("http://localhost:8080/e-commerceSpringMvc/api/orderitem/status/"+id,option)
		.then(response => {
			console.log(response);
		})
}