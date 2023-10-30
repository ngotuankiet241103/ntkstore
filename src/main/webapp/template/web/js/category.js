const headerSubmenu = document.querySelector('.header-submenu')
const url = "http://localhost:8080/e-commerceSpringMvc/product";
fetch("http://localhost:8080/e-commerceSpringMvc/api/category")
	.then(response => response.json())
	.then(renderCategory)

function renderCategory(data) {
	let html = "";
	console.log(data)
	data.forEach((category,index,arr) => {
		html += `<li class="submenu-item tops"><a
										href="${url}/collections/${category.name}"
										class="submenu-item-link">${category.name}</a>`
	fetch(`http://localhost:8080/e-commerceSpringMvc/api/category/details/${category.id}/category`)
			.then(response => response.json())
			.then(response => {
				console.log(response)
				if (response.length > 0) {
					console.log(response)
					html += `<ul class="header-submenu-item">`
					response.forEach(categoryDetails => {
						console.log(categoryDetails)
						html += `<li class="submenu-item"><a
												href="${url}/collections/${categoryDetails.name}"
												class="submenu-item-link">${categoryDetails.name}</a></li>`
					})
					html += `</ul></li>`
					console.log(html)
				}
				else{
					html += `</li>`
				}
				
				headerSubmenu.innerHTML = html
			})
			
			
		
	})
	
}
