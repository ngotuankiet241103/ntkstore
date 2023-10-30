const features = document.querySelectorAll('.sub-feature li')
const btnSearch = document.querySelector('.btnSearch')
console.log(features)
btnSearch.onclick = () => {
	const email = btnSearch.parentElement.querySelector('input').value;
	const api = window.location.href + "?email=" + email;
	fetch(api)
		.then(response => response.json())
		.then(renderUser)
}
function renderUser(data) {
	const users = document.querySelector('.users');
	const htmls = data.map(user => {
		const html = `<li class="user-item"><span>${user.id}</span> <span>${user.email}</span> <span>${user.fullName}</span> <span> `
		html += user.role.map(role => role).join('');
		html += user.enabled ? "span>active</span>" : "<span>non-active</span>";
		if (user.role != "ADMIN") {
			html += `<span class="future-manager"><i
								class="fa-solid fa-ellipsis-vertical"></i>
								<ul class="sub-feature">
									<li class="edit-permit" data-id="${user.id}">Chỉnh sửa
										quyền</li>
									<li class="block-user" 	data-id="${user.id}>Khóa người dùng</li>
									<li class="remove-user" data-id="${user.id}>Xóa người dùng</li>
								</ul> </span>`
		}
		html += "</li>";
		return html;
	}).join('');
	users.innerHTML = htmls;
}
features.forEach(item => {
	item.onclick = () => {
		const id = item.getAttribute("data-id")
		if (item.classList.contains("edit-permit")) {

			formEdit(id)
		}
		else if (item.classList.contains("block-user")) {
			enabledUser(id);
		}
		else {
			console.log(id)
			removeUser(id);
		}
	}
})
function removeUser(id) {
	const api = window.location.href + "/" + id;
	const option = {
		method: 'DELETE',
		headers: {
			'Content-Type': 'application/json'
		},
	}
	const request = fetch("http://localhost:8080/e-commerceSpringMvc/admin/api/manager/user?id=" + id, option);
	request.then(response => {
		if (response.status === 20) {
			window.history.back();
		}
	})
}
function enabledUser(id) {
	const data = {
		id: 1
	}
	const api = window.location.href + "/" + id;
	const option = {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	}
	const request = fetch("http://localhost:8080/e-commerceSpringMvc/admin/api/manager/user/" + id , option);
	request.then(response => {
		if (response.status === 200) {
			window.history.back();
		}
	})
}
function formEdit(id) {
	const modal = document.querySelector('.modal-user')
	modal.classList.add("active");
	const btnEditUser = modal.querySelector('.btn-editUser')
	btnEditUser.onclick = () => {
		const select = modal.querySelector('select');
		const role = select.options[select.selectedIndex].value;
		const user = {
			id,
			role
		}
		editUser(user)
	}
}
function editUser(user) {
	const option = {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(user)
	}
	const api = window.location.href;
	const request = fetch(api, option);
	request.then(response => {
		if (response.status === 200) {
			window.history.back();
		}
	})
}