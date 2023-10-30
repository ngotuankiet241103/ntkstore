let apiCategory = "http://localhost:8080/e-commerceSpringMvc/api/admin/order/status"
import { getAllOrder,renderView } from "./renderOrders.js";
function startRender(){
	getCategoryStatus(renderStatus);
	
	
}
startRender();
function getCategoryStatus(callback){
	fetch(apiCategory)
				     .then(response => response.json())
				     .then(response => {
						 	callback(response)
							 handleFilter();
							  
							 
						 }
						)
}
function renderStatus(categories){
	const headerCategory = document.querySelector('.header-category')
	let htmls = `<select class="optionStatus"><option value="">Lọc theo trạng thái đơn hàng</option>`
	htmls += categories.map(category => `<option value="${category}">${category}</option>`)
	htmls += `	</select> 
				<select class="optionPrice">
					<option value="">Sắp xếp theo tổng tiền</option>
					<option value="ASC" >Từ cao đến thấp</option>
					<option value="DESC">Từ thấp đến cao</option>
				</select>
				<button type="button" class="filterStatus">Lọc</button>`
	headerCategory.innerHTML = htmls;
}
function handleFilter(){
		const filterStatus = document.querySelector('.filterStatus')
		filterStatus.onclick = () => {
		
		const select = document.querySelector(".optionPrice");
		const selectedValuePrice = select.options[select.selectedIndex].value;
		const selectStatus = document.querySelector(".optionStatus");
		const selectedValueStatus = selectStatus.options[selectStatus.selectedIndex].value;
		
		handleParamFilter(selectedValueStatus,selectedValuePrice)
		getAllOrder(renderView)
	}
	
}
function handleParamFilter(selectedValueStatus, selectedValuePrice){
	let newUrl="";
	let status = selectedValueStatus != "" ? "orderStatus="+selectedValueStatus: ""
    let price = selectedValuePrice != "" ? "sortName=total&sortBy="+selectedValuePrice: ""
    let character = selectedValueStatus != "" && selectedValuePrice != "" ? "&" : ""
    if(window.location.href.indexOf("?") > 1){
        if(window.location.href.indexOf("&") > 1){
            api = api.substring(0, api.indexOf("&")) +"&" + status + character + price;
            newUrl =  window.location.href.substring(0, window.location.href.indexOf("&")) + "&"+  status + character + price;
        }
        else{
            api = api + "&" + status + price;
            newUrl =  window.location.href + "&"+  status + character + price;
        }  
    }
    else{
        api = api + "?" + status + price;
        newUrl =  window.location.href + "?"+  status + character + price;
    }
 
    history.pushState({}, 'New Page', newUrl);
    
}