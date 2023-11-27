
import { apiRequestMethodGet, apiRequestMethodPost, setToken } from "./homePage.js";
import { loginSucess } from "./menuHeader.js";

apiRequestMethodGet("http://localhost:8080/e-commerceSpringMvc/api/user", getUserData);
function getUserData(response) {
	render(response)
}
function render(user) {
	console.log(user)
	const myProfile = document.querySelector('.my-profile');
	const html = `
		<div class="profile-item">
			<h3 class="profile-item-title">Họ tên</h3>
			<div class="profile-item-info">${user.fullName}</div>
		</div>
		<div class="profile-item">
			<h3 class="profile-item-title">Địa chỉ email</h3>
			<div class="profile-item-info">${user.email}</div>
		</div>
		<div class="profile-item">
			<h3 class="profile-item-title">Số điện thoại</h3>
			<div class="profile-item-info">${user.phone != null ? user.phone : ""}</div>
		</div>
		<div class="profile-item">
			<h3 class="profile-item-title">Mật khẩu</h3>
			<input type="password" class="profile-item-info" value="" disabled />
		</div>
	`
	myProfile.innerHTML = html;
}
render();