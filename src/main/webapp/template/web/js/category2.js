const headerSubmenu = document.querySelector('.header-submenu')
const url = "http://localhost:8080/e-commerceSpringMvc/product";
fetch("http://localhost:8080/e-commerceSpringMvc/api/category")
	.then(response => response.json())
	.then(renderCategory)

function renderCategory(data) {
	let html = "";
	console.log(data)
	data.forEach((category, index, arr) => {
		console.log(arr)
		html += `<li class="submenu-item tops" data-id="${category.id}"><a
										href="${url}/collections/${category.name}"
										class="submenu-item-link" >${category.name}</a> </li>`


	})
	headerSubmenu.innerHTML = html;
	renderDetailsCategory();

}
function renderDetailsCategory() {

	const categories = document.querySelectorAll(".submenu-item");
	categories.forEach(category => {
	 
		const id = category.getAttribute("data-id")
	
		fetch(`http://localhost:8080/e-commerceSpringMvc/api/category/details/${id}/category`)
			.then(response => response.json())
			.then(response => {

				if (response.length > 0) {
					const ul = document.createElement("ul");
					ul.className = 'header-submenu-item';
					let html = "";
					console.log(response)

					response.forEach(categoryDetails => {
						console.log(categoryDetails)
						html += `<li class="submenu-item"><a
												href="${url}/collections/${categoryDetails.name}"
												class="submenu-item-link">${categoryDetails.name}</a></li>`
					})
					ul.innerHTML = html
					category.appendChild(ul);
				}



			})
	})
}
