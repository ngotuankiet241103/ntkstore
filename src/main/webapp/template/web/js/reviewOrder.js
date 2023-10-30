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
			document.querySelector('.form-comment').submit();

		};
	};
});