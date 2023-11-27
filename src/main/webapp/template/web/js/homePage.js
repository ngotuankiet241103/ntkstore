function getCookieValue(key) {
	// Tách chuỗi cookie thành mảng các cặp key-value
	const cookies = document.cookie.split(";");
	console.log(cookies);
	// Duyệt qua mảng cookies
	for (let i = 0; i < cookies.length; i++) {
		// Tách từng cặp key-value
		const cookie = cookies[i].split("=");

		// Lấy key và value
		const cookieKey = cookie[0].trim();
		const cookieValue = cookie[1];

		// Kiểm tra xem key có trùng với key mong muốn không
		if (cookieKey === key) {
			// Trả về giá trị tương ứng với key
			return cookieValue;
		}
	}

	// Trả về undefined nếu không tìm thấy key
	return undefined;
}
function render(response) {
	console.log(response);
}
function getAccessToken() {
	const token = localStorage.getItem("accessToken");
	return token
}
export function apiRequestMethodGet(url, callback) {
	const headers =
		getAccessToken() != null
			? {
				"Content-Type": "application/json",
				Authorization: "Bearer " + getAccessToken(),
			}
			: {};
	console.log(headers);
	const option = {
		headers,
	};
	fetch(url, option)
		.then((response) => {
			
			if (response.status === 403) {
				window.location.href = "http://localhost:8080/e-commerceSpringMvc/login";
			}
			else if (response.status === 401) {
				if (getRefeshToken(url, callback)) {
					return apiRequestMethodGet(url, callback);
				}
			}
			else if (response.status === 400) {
				return null;
			}
			console.log(response);
			return response.json();
		})
		.then(callback)
		.catch((error) => {
			console.log(error);
			if (error.status === 401) {

			} else if (error.status === 403) {
				console.log("403");
			}
		});
}
export function apiRequestMethodPost(url, callback, data) {
	console.log(url);
	const headers =
		getAccessToken() != null
			? {
				"Content-Type": "application/json",
				Authorization: "Bearer " + getAccessToken(),
			}
			: {
				"Content-Type": "application/json",
			};
	const option = {
		method: "POST",
		headers,
		body: JSON.stringify(data),
	};

	fetch(url, option)
		.then((response) => {
			if (response.status === 403) {
				window.location.href = "http://localhost:8080/e-commerceSpringMvc/login";
			}
			else if (response.status === 401) {
				if (getRefeshToken(url, callback)) {
					return apiRequestMethodGet(url, callback);
				}
			}
			else if (response.status === 400) {
				return null;
			}
			console.log(response)
			return response.json();
		})
		.then(callback)
		.catch((error) => {
			console.log(error);
			if (error.status === 401) {
				if (getRefeshToken(url, callback)) {
					apiRequestMethodPost(url, callback, data);
				}
			} else if (error.status === 403) {
				console.log("403");
			}
		});
}
function getRefeshToken(url, callback) {
	const refeshToken = {
		refeshToken: getCookieValue("refeshToken")
	};
	var option = {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(refeshToken),
	};
	console.log(refeshToken)
	fetch("http://localhost:8080/e-commerceSpringMvc/refesh-token", option)
		.then(response => response.json())
		.then((response) => {
			
			setToken(response);
			return true;
		})
		.catch((error) => {
			if (error.status === 401) {
				return false;
			}
		});
}
export function setToken(response) {
	console.log(response);
	localStorage.setItem("accessToken", response.access_token);
	document.cookie = `refeshToken=${response.refresh_token}; max-age=2678400;`;
}
