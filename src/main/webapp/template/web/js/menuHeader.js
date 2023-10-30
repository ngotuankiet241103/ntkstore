

export function loginSucess(response){
	const userLogin = document.querySelector('.header-login-user');
	console.log(userLogin)
	if(response != null){
		userLogin.classList.add("active")
	}
	
}