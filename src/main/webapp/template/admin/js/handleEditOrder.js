 
function handleEditOrder(id) {
  const apiGetDetail = "http://localhost:8080/e-commerceSpringMvc/api/admin/order" + "/" + id;
  function start() {
    getDetailsOrder(render);
  }
  start()
  function getDetailsOrder() {
    fetch(apiGetDetail)
      .then(response => response.json())
      .then(render)
  }
  function render(data) {
	  console.log(data)
    const popUp = document.querySelector('.pop-up')
    const html = `
        <div class="formEditOrder">
	    		 <div class="header-formEdit">
	                <span onclick=removeFormEdit(${popUp})>&times;</span>
	            </div>
              <div class="form-group">
              <h4 for="">Mã đơn hàng</h4> 
              <span>${data.order.code}</span>
            </div>
            <div class="form-group">
              <h4 for="">khách hàng</h4> 
              <span>${data.order.user.fullName}</span>
            </div>
            <div class="form-group">
              <h4 for="">Tình trạng đơn hàng</h4>  
              <select class="status">
                ${data.orderStatus.status.map(status => `<option ${data.order.status == status ? "selected" : ""}>${status}</option>`
                ).join('')}
              
              </select>
            </div>
            <div class="form-group">
              <h4 for="">Tình trạng chi tiết đơn hàng</h4> 
              <select class="detailsStatus">
              ${data.orderStatus.detailsStatus.map(status => `<option ${data.order.detailStatus == status ? "selected" : ""}>${status}</option>`).join('')}
              </select>
            </div>
            <div class="form-group">
              <h4 for="">Ngày đặt</h4>  
              <span>30/7/2023</span>
            </div>
            <div class="form-group">
              <h4 for="">Tổng tiền</h4> 
              <span>${data.order.total}</span>
            </div>
            <div class="btnEditOrder">
                <button type="button" onclick=handleUpdateForm(${data.order.id})>Cập nhập</button>
            </div>
        </div>
    `
    popUp.innerHTML = html;
    popUp.classList.remove("d-none")
  }
}
function removeFormEdit(popUp) {
  popUp.classList.add("d-none")
}
function handleUpdateForm(id) {
	console.log("a")
  const selectStatus = document.querySelector('.status');
  const status = selectStatus.options[selectStatus.selectedIndex].value;
  const selectDetailsStatus = document.querySelector('.detailsStatus');
  const detailStatus = selectDetailsStatus.options[selectDetailsStatus.selectedIndex].value;
  const orderStatus = {
    status,
    detailStatus
  }
  console.log(orderStatus)
	updateOrderDetails(id,orderStatus);
}
function updateOrderDetails(id, data) {
  var option = {
    method: 'PUT',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  }
  console.log(JSON.stringify(data))
  fetch(api + "/" + id, option)
    .then(response => response.json())
    .then(response => {
		
		})
}