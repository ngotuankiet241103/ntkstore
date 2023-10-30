import { user } from './header.js';
const btnEdit = document.querySelector(".edit-profile");


btnEdit.onclick = function a()  {
	changeLayoutEdit();
};
function changeLayoutEdit() {
	getInfoUser(render);
}

var course = "http://localhost:8080/e-commerceSpringMvc/api/user"
function getInfoUser(callback) {
	fetch(course + "/" + user.id)
		.then((response) => response.json())
		.then(callback);
}
function render(response) {
	const detailsProfile = document.querySelector(".details-profile");
	const htmls = `<h2>Chỉnh sửa</h2>
<div class="userProfile">
<div class="box-userProfile">
  <div class="my-profile input">
    <div class="profile-item input">
      <h3 class="profile-item-title">Họ tên</h3>
      <input
        type="text"
        value="${response.fullName}"
        class="profile-item-info input"
        name="fullName"
      />
    </div>
    <div class="profile-item input">
      <h3 class="profile-item-title">Địa chỉ email </h3>
      <input
        type="email"
        value="${response.email}"
        class="profile-item-info input email"
        name="email"
      />
    </div>
    <div class="profile-item input">
      <h3 class="profile-item-title">Số điện thoại</h3>
      <input
        type="text"
        value="${response.phone}"
        class="profile-item-info input"
        name="phone"
      />
    </div>
    <div class="profile-item input">
      <h3 class="profile-item-title">
        Mật khẩu
        <span>Chỉnh sửa</span>
      </h3>
      <input
        type="password"
        class="profile-item-info input"
        value="${response.password}"
        disabled
      />
    </div>
  </div>
  <div class="my-profile-ft">
    <button class="edit-profile" onclick=handleUserEdit(${response.id})>Chỉnh sửa</button>
  </div>
</div>
</div>`;
	detailsProfile.innerHTML = htmls;
	startEdit()
}

function handleUserEdit(id) {

	const inputs = document.querySelectorAll('input');
	const userProfile = {}
	inputs.forEach(input => {
		if (input.name != "") {
			userProfile[input.name] = input.value;
		}
	})
	updateProfileUser(id, userProfile, render)
}
function startEdit() {
	const editPassword = document.querySelector(
		".profile-item.input .profile-item-title span"
	);
	if (editPassword != null) {
		editPassword.onclick = () => {
			const myProfile = document.querySelector(".my-profile.input");
			const htmls = document.querySelector(".my-profile.input").innerHTML;
			console.log(htmls);

			const html =
				htmls +
				`
      <div class="profile-item input">
          <h3 class="profile-item-title">Nhập lại mật khẩu cũ</h3>
          <input type="password" value="" class="profile-item-info input" name="password"/>
        </div>
        <div class="profile-item input">
          <h3 class="profile-item-title">Mật khẩu mới</h3>
          <input type="password" value="" class="profile-item-info input" name="passwordNew"/>
        </div>

      `;
			myProfile.innerHTML = html;
		};
	}

}
function updateProfileUser(id, data, callback) {
	var option = {
		method: 'PUT',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	}
	console.log(JSON.stringify(data))
	fetch(course + "/" + id, option)
		.then(response => {
			if (response.status === 200) {
				const data = response.json();
				return data;
			}
			else if (response.status === 400) {
				console.log(response.body);

			}
		}

		)

}