function validation(option) {
	console.log(option)
	var totalCheck = 0;
	function validate(inputElement, rule) {
		var errorInput = getParent(inputElement).querySelector(option.error)

		var errorMessage;
		var rules = isAllRule[rule.selector]
		for (var i = 0; i < rules.length; ++i) {

			switch (inputElement.type) {
				case 'radio':
					var inputs = formElement.querySelectorAll(`input[name = '${inputElement.name}'`)

					for (let index = 0; index < inputs.length; index++) {
						if (!inputs[i].checked) {
							totalCheck++
							if (totalCheck === inputs.length) {
								errorMessage = rules[i](inputElement.checked)
							}
						}
						break;
					}


					break;

				default:
					errorMessage = rules[i](inputElement.value)
					break;
			}
			if (errorMessage) break;
		}
		if (errorMessage) {
			errorInput.innerText = errorMessage
			getParent(inputElement).classList.add('invalid')
		}
		else {
			errorInput.innerText = ''
			getParent(inputElement).classList.remove('invalid')
		}
		return !errorMessage
	}
	var isAllRule = {}
	var formElement = document.querySelector(option.form)
	function getParent(inputElement) {
		while (inputElement.parentElement) {
			if (inputElement.parentElement.matches(option.formGroup)) {
				return inputElement.parentElement
			}
			inputElement = inputElement.parentElement
		}
	}
	if (formElement) {
		option.rules.forEach(rule => {
			var inputElements = formElement.querySelectorAll(rule.selector)

			if (Array.isArray(isAllRule[rule.selector])) {
				isAllRule[rule.selector].push(rule.test)
			}
			else {
				isAllRule[rule.selector] = [rule.test]
			}
			inputElements.forEach(inputElement => {

				if (inputElement) {
					// xử lí blur ra khỏi input
					inputElement.onblur = () => {
						console.log(inputElement);
						validate(inputElement, rule)
					}
					// xử lí khi nhập input
					inputElement.oninput = () => {
						var errorInput = getParent(inputElement).querySelector(option.error)
						errorInput.innerText = ''
						inputElement.parentElement.classList.remove('invalid')
					}
					if (inputElement.type == 'radio') {
						inputElement.onclick = () => {
							var errorInput = getParent(inputElement).querySelector(option.error)
							errorInput.innerText = ''
							inputElement.parentElement.classList.remove('invalid')
						}

					}
				}
			})


		});
		const button = document.querySelector(option.buttonSubmit)
		button.onclick = (e) => {
			e.preventDefault()
			console.log("ha")
			var isFormValid = true;
			option.rules.forEach(rule => {
				var inputElements = formElement.querySelectorAll(rule.selector)
				inputElements.forEach(inputElement => {
					var isForm = validate(inputElement, rule)
					if (!isForm) {
						isFormValid = false
					}
				})


			})

			if (isFormValid) {
				if (typeof option.onsubmit === 'function') {
					var enableInput = formElement.querySelectorAll('[name]')
                        console.log(enableInput);
                        var formValue = Array.from(enableInput).reduce((values,input) =>{
                            switch (input.type) {
                                case 'radio':
                                   // values[input.name] = formElement.querySelector("input[name='pay']:checked").value
           
                                    break;
                            
                                default:
                                    values[input.name] = input.value
                                    break;
                            }
                            return values
                        }
                        ,{})
					option.onsubmit(formValue);
				}
			}
		}
	}

}
validation.isRequired = (selector) => {
	return {
		selector,
		test: function(value) {
			return value ? undefined : "Vui lòng nhập trường hợp này"
		}
	}

}
validation.isEmail = (selector) => {
	return {
		selector,
		test: function(value) {
			var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
			return regex.test(value) ? undefined : "Trường này không phải email"
		}
	}
}
validation.isPassword = (selector) => {
	return {
		selector,
		test: function(value) {
			var regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/
			return regex.test(value) ? undefined : "Vui lòng nhập mật khẩu từ 6 đến 20 kí tự"
		}
	}
}
validation.confirmPassword = (selector, password) => {
	return {
		selector,
		test: function(value) {
			return value === password() ? undefined : "Mật khẩu nhập lại không chính xác"
		}
	}
}