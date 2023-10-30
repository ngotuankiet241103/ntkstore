
import { pagination } from './paginarion.js'
function start() {
	api = paramSearch(api);
	getAllOrder(renderView)
}
start()
export function getAllOrder(callback) {
	fetch(api)
		.then(response => response.json())
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
                  onPageClick: function(page){
					const currentUrl = window.location.href;
					let newUrl;
                    const index = api.indexOf("page")
                    const urlIndex = currentUrl.indexOf("page");
                    console.log("before" + api);
                    if( index> 1 && urlIndex > 1){
					  newUrl = currentUrl.substring(0,urlIndex) + "page="+page;
                      api = api.substring(0,index) + "page="+page
                       console.log("1" + api);
                      getAllOrder(callback);
                    }
                    else if(window.location.href.indexOf("?") > 1){
					  newUrl = currentUrl +"&page="+page;
                      api += "&page="+page;
                      console.log("2" + api);
                      getAllOrder(callback);
                    }
                    else if(window.location.href.indexOf("?") < 1){
					  newUrl = currentUrl +"?page="+page;
                      api += "?page="+page;
                      console.log("3" + api);
                      getAllOrder(callback);
					}
					console.log(api)
                    history.pushState({}, 'New Page', newUrl);
                  },
                  handleNextPage: function () {
                    this.onPageClick(this.startPage + 1);
                  },
                  handlePrevPage: function () {
                    this.onPageClick(this.startPage - 1);
                  }
                });
                
            })
}
export function renderView(orders) {

	const listOrder = document.querySelector('.list-order')
	let htmls = orders.orders.map(order => {
		console.log(order.code)
		return `<li class="order-item"><span>${order.code}</span> <span>nguyễn
		                văn a</span> <span>${order.status}</span> <span>${order.paymentStatus}</span>
		                <span>${order.paymentMethod}</span> <span>ngày đặt</span> <span>${order.total}</span>
		                <span class="editOrder" onclick=handleEditOrder(${order.id})>Cập nhập đơn hàng</span></li>`
	}).join('');
	htmls += `<ul class="pagination" id="pagination"></ul>`;
	listOrder.innerHTML = htmls
}
function paramSearch(api){
	const currentUrl = window.location.href;
	let newUrl;
	if(currentUrl.indexOf("?") > 1 && currentUrl.indexOf("&") > 1){
		console.log(currentUrl.indexOf("&") > 1);
		 newUrl = currentUrl.substring(0,currentUrl.indexOf("?")) + "?page=NaN";
	}else if(currentUrl.indexOf("?") > 1 &&  currentUrl.indexOf("=") > 1 && currentUrl.indexOf("&") < 1 ){
		const urlParams = new URLSearchParams(window.location.search);
		const firstParam = currentUrl.substring(currentUrl.indexOf("?")+1,currentUrl.indexOf("="))
		console.log("b")
		const firstParamValue = urlParams.get(firstParam);
		api += "?page="+ firstParamValue;
		newUrl = currentUrl.substring(0,currentUrl.indexOf("?"))+ "?page=" + firstParamValue;
	}
	
	history.pushState({}, 'New Page', newUrl);
	return api;
}
const btnSearch = document.querySelector('.btnSearch');
btnSearch.onclick = () => {
	const valueSearch = document.querySelector('.valueSearch').value;
	console.log(valueSearch)
	const character = valueSearch.charAt(1);
	let newUrl ="";
	if(api.indexOf("?")  > 1 ||api.indexOf("&") >1){
		api = api.substring(0,api.indexOf("?"))
	}
	if(isAlphabetic(character)){
		newUrl = window.location.href + "?name=" + valueSearch;
		api += "?name=" + valueSearch;
	}
	else{
		newUrl = window.location.href + "?code="+ valueSearch;
		api += "?code="+ valueSearch;
	}
	console.log(api)
	history.pushState({}, 'New Page', newUrl);
	getAllOrder(renderView);
	
}
function isAlphabetic(character) {
  return /^[a-zA-Z]+$/.test(character);
}


