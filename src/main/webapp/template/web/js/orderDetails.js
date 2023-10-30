
import { pagination } from './pagination.js';
import { apiRequestMethodGet, apiRequestMethodPost } from "./homePage.js";
var user
apiRequestMethodGet("http://localhost:8080/e-commerceSpringMvc/api/user", getUserData);
function getUserData(response) {
	user = response;
	console.log(response)
	start();
}
var order = "http://localhost:8080/e-commerceSpringMvc/api/order/user";
function start() {
	order += "/" + user.id;
	console.log(order)
	order = paramSearch(order);
	getAllOrder(callback);
}


function getAllOrder(callback) {
	apiRequestMethodGet(order, callback)
}
function callback(response) {
	console.log(response)
	render(response);
	pagination({
		page: "#pagination",
		pageItem: "page-item",
		pageIcon: "page-icon",
		totalPage: response.paginartion.totalsPage,
		startPage: response.paginartion.pageStart,
		visiblePages: Math.floor(response.paginartion.totalsPage / 2),
		onPageClick: function(page) {
			const currentUrl = window.location.href;
			let newUrl;
			const index = order.indexOf("page")
			const urlIndex = currentUrl.indexOf("page");

			if (index > 1 && urlIndex > 1) {
				newUrl = currentUrl.substring(0, urlIndex) + "page=" + page;
				order = order.substring(0, index) + "page=" + page

				getAllOrder(callback);
			}
			else if (window.location.href.indexOf("?") > 1) {
				newUrl = currentUrl + "&page=" + page;
				order += "&page=" + page;

				getAllOrder(callback);
			}
			else if (window.location.href.indexOf("?") < 1) {
				newUrl = currentUrl + "?page=" + page;
				order += "?page=" + page;

				getAllOrder(callback);
			}

			history.pushState({}, 'New Page', newUrl);

		},
		handleNextPage: function() {
			this.onPageClick(this.startPage + 1);
		},
		handlePrevPage: function() {
			this.onPageClick(this.startPage - 1);
		}
	});
}
function store() {
	fetch(order)
		.then(response => {
			console.log(response)
			return response.json()
		})
		.then(response => {
			callback(response)
			console.log(response)
			pagination({
				page: "#pagination",
				pageItem: "page-item",
				pageIcon: "page-icon",
				totalPage: response.paginartion.totalsPage,
				startPage: response.paginartion.pageStart,
				visiblePages: Math.floor(response.paginartion.totalsPage / 2),
				onPageClick: function(page) {
					const currentUrl = window.location.href;
					let newUrl;
					const index = api.indexOf("page")
					const urlIndex = currentUrl.indexOf("page");
					console.log("before" + api);
					if (index > 1 && urlIndex > 1) {
						newUrl = currentUrl.substring(0, urlIndex) + "page=" + page;
						api = api.substring(0, index) + "page=" + page
						console.log("1" + api);
						getAllOrder(callback);
					}
					else if (window.location.href.indexOf("?") > 1) {
						newUrl = currentUrl + "&page=" + page;
						api += "&page=" + page;
						console.log("2" + api);
						getAllOrder(callback);
					}
					else if (window.location.href.indexOf("?") < 1) {
						newUrl = currentUrl + "?page=" + page;
						api += "?page=" + page;
						console.log("3" + api);
						getAllOrder(callback);
					}
					console.log(api)
					history.pushState({}, 'New Page', newUrl);

				},
				handleNextPage: function() {
					this.onPageClick(this.startPage + 1);
				},
				handlePrevPage: function() {
					this.onPageClick(this.startPage - 1);
				}
			});
		})
}
function render(orders) {
	const listOrder = document.querySelector('.listOrder')

	console.log(orders);
	let htmls = ""

	htmls = orders.orders.map(order => {
		let html = "";
		html += `<li class="orderItem">
            <div class="orderDetailsStatus"> 
              <span >${order.status}</span>
            </div>
            <ul class="subListOrderItem">
            `


		html += order.orderItems.map(orderItem => {

			return `<li class="detailsOrderItem">
                  <div class="image-orderItem">
                    <img src="https://cdn.britannica.com/35/238335-050-2CB2EB8A/Lionel-Messi-Argentina-Netherlands-World-Cup-Qatar-2022.jpg" alt="">
                  </div>
                  <div class="infoOrderItem">
                    <p class="name-orderItem">${orderItem.product.name}</p>
                    <span>${orderItem.size}</span>
                  </div>
                  <div class="priceOrderItem">
                    <span>${orderItem.totalPrice}</span>
                  </div>
                  <div class="quanlityOrderItem">
                    <p>
                      Số lượng: 
                    </p>
                    <span>${orderItem.quantity}</span>
                  </div>
              </li>`
		}).join('')
		const url = window.location.href.indexOf("?") > 0 ? window.location.href.subString(0, window.location.href.indexOf("?"))  : window.location.href
		if( order.status === "Đã giao hàng"){
			html +=  `<footer class="footerOrder"><a href="${url}/view?code=${order.code}">Xem chi tiết</a</footer>`
		}
		else if(order.status === "Đang xử lý"){
			html += `<footer class="footerOrder"> <a href="#">Hủy đơn hàng</a></footer>`
		}
		 

		html += `
            </ul>
            </li>
        `

		return html;
	}).join('');

	listOrder.innerHTML = htmls
}
const categoryStatusOrder = document.querySelectorAll('.orderStatus')
categoryStatusOrder.forEach(category => {
	category.onclick = () => {
		const status = document.querySelector('.orderStatus.active')
		status.classList.remove("active")
		category.classList.add("active")
		order = "http://localhost:8080/e-commerceSpringMvc/api/order/user/" + user.id;

		if (!category.classList.contains("all")) {
			const currentUrl = window.location.href;
			order += "?orderStatus=" + category.textContent.trim();
			console.log(order)
			const newUrl = currentUrl.substring(0, currentUrl.indexOf('?')) + "?orderStatus=" + category.textContent.trim();
			history.pushState({}, 'New Page', newUrl);

		}

		getOrderByOrderStatus(render);
	}

})
function getOrderByOrderStatus(callback) {
	fetch(order)
		.then(response => {
			console.log(response)
			return response.json()
		})
		.then(response => {
			callback(response)
			console.log(response.paginartion.pageStart)
			console.log(response.paginartion.totalsPage)
			pagination({
				page: "#pagination",
				pageItem: "page-item",
				pageIcon: "page-icon",
				totalPage: response.paginartion.totalsPage,
				startPage: response.paginartion.pageStart,
				visiblePages: Math.floor(response.paginartion.totalsPage / 2),
				onPageClick: function(page) {
					const currentUrl = window.location.href;
					let newUrl;
					const index = order.indexOf("page")
					const urlIndex = currentUrl.indexOf("page");

					if (index > 1 && urlIndex > 1) {
						newUrl = currentUrl.substring(0, urlIndex) + "page=" + page;
						order = order.substring(0, index) + "page=" + page
						getOrderByOrderStatus(callback);
					}
					else if (window.location.href.indexOf("?") > 1) {
						newUrl = currentUrl + "&page=" + page;
						order += "&page=" + page;
						getOrderByOrderStatus(callback);
					}
					else if (window.location.href.indexOf("?") < 1) {
						newUrl = currentUrl + "?page=" + page;
						order += "?page=" + page;
						console.log("3" + api);
						getOrderByOrderStatus(callback);
					}

					history.pushState({}, 'New Page', newUrl);
				},
				handleNextPage: function() {
					this.onPageClick(this.startPage + 1);
				},
				handlePrevPage: function() {
					this.onPageClick(this.startPage - 1);
				}
			});
		})
}
function paramSearch(api) {
	const currentUrl = window.location.href;
	let newUrl;
	if (currentUrl.indexOf("?") > 1 && currentUrl.indexOf("&") > 1) {
		console.log(currentUrl.indexOf("&") > 1);
		newUrl = currentUrl.substring(0, currentUrl.indexOf("?")) + "?page=NaN";
	} else if (currentUrl.indexOf("?") > 1 && currentUrl.indexOf("=") > 1 && currentUrl.indexOf("&") < 1) {
		const urlParams = new URLSearchParams(window.location.search);
		const firstParam = currentUrl.substring(currentUrl.indexOf("?") + 1, currentUrl.indexOf("="))
		console.log("b")
		const firstParamValue = urlParams.get(firstParam);
		api += "?page=" + firstParamValue;
		newUrl = currentUrl.substring(0, currentUrl.indexOf("?")) + "?page=" + firstParamValue;
	}

	history.pushState({}, 'New Page', newUrl);
	return api;
}
// function pagenation(){
//   let page;
//   let totalPage;
//   return {
//     getPage: () => this.page,
//     getTotalPage: () => this.totalPage,
//     setPagenation: (page,totalPage) => {
//       this.page = page;
//       this.totalPage = totalPage
//     }

//   }
// }
// const pagination = pagenation()
// pagination.setPagenation