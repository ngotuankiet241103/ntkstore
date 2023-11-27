function handleSearch(form) {
	const btnClose = form.querySelector('i');
	btnClose.onclick = () => {
		form.classList.remove("active")
		return;
	}
	form.onkeyup = () => {
		const btnViewDetail = document.querySelector('.footer-search a')
		const data = form.querySelector('input').value;
		btnViewDetail.href +=  data;
		if (data != "") {
			getResultSearch(data)
		}
		else{
			const form = document.querySelector('.result-search-list')
			form.innerHTML = "";
			visibleBtn();
		}

	}
}
function getResultSearch(data) {
	const api = "http://localhost:8080/e-commerceSpringMvc/api/search?q=" + data;
	fetch(api)
		.then(response => response.json())
		.then(renderResultSearch)
}
function renderResultSearch(response) {
	const form = document.querySelector('.result-search-list')
	
	let html = "";
	html += response.map(data => {
		return `
		 	<li class="result-search-item">
		 		<a href="http://localhost:8080/e-commerceSpringMvc/product/detail/${data.code}" class="result-search-link"> 
		 			<img src="${data.image}" alt="">
		 			<h4>${data.name}</h4>
		 		</a> 
		 	</li>
		 `
	}).join('');
	form.innerHTML = html;
	if(response.length > 0){
		showBtn();
	}
	
}
function visibleBtn(){
	const btnViewDetail = document.querySelector('.footer-search')
	btnViewDetail.classList.remove("active")
}
function showBtn(){
	const btnViewDetail = document.querySelector('.footer-search')
	btnViewDetail.classList.add("active")
}