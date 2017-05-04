'use strict'


var LoginValidator = function () {

    //Element
	var emailInput,
		pwdInput,
		loginForm,
		loginMsgDiv
		
		  
	
		
		
	function init(params) {
		emailInput = params.emailInput
		pwdInput = params.pwdInput
		loginForm = params.loginForm
		loginMsgDiv = params.loginMsgDiv
		
		event()
	}
	
	
	
	function event() {
		loginForm.addEventListener('submit', (e) => submit(e), false)
	}
	
	
	

	
	function checkTextLength() {
        var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i

		
		if (emailInput.value.length < 4 || pwdInput.value.length < 4) {
			loginMsgDiv.innerHTML = "4자 이상 써주세요."
			return false
			
		} else if (emailAddr.match(regExp) == null) {
			 loginMsgDiv.innerHTML = "이메일 형식이 아닙니다."
			return false
			
		} else {
			return true
		}
	}
	
	
	
	
	function submit(evt) {

		if (checkTextLength()) {
			loginForm.submit()
		} else {
			evt.preventDefault()
		}
		
	}
	
	
	
	
	return {
		init: init
	}
}()