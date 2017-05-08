'use strict'

var JoinValidator = function () {



    //Flag
    var emailFlag = false,
        pwdFlag = false,
        nickFlag = false,
        captchaFlag = false;




    //Element
    var joinForm,
        emailInput,
        passwordInput,
        passwordCfInput,
        nicknameInput,
        captchaInput,
        emailCheckBtn,
        nickCheckBtn,
        nicknameMsgDiv,
        emailMsgDiv,
        passwordMsgDiv,
        errorDiv,
        captchaDiv,
        submitBtn,
        refreshCaptchaBtn

    let key;


    //Ajax Url
    var emailCheckUrl = "/user/check/email",
        nickCheckUrl = "/user/check/nick",
        captchaGetKeyUrl = "/captcha/key",
        captchaGetImageUrl = "/captcha/image?key=",
        captchaCompareUrl = "/captcha/compare"
        



        	
    function init(params) {
        joinForm = params.joinForm
        emailInput = params.emailInput
        passwordInput = params.passwordInput
        passwordCfInput = params.passwordCfInput
        captchaInput = params.captchaInput
        nicknameInput = params.nicknameInput
        emailCheckBtn = params.emailCheckBtn
        nickCheckBtn = params.nickCheckBtn
        submitBtn = params.submitBtn
        refreshCaptchaBtn = params.refreshCaptchaBtn
        nicknameMsgDiv = params.nicknameMsgDiv
        emailMsgDiv = params.emailMsgDiv
        passwordMsgDiv = params.passwordMsgDiv
        captchaDiv = params.captchaDiv
        errorDiv = params.errorDiv
        event()
        getKeyNImage()
    }





    function event() {
        passwordInput.addEventListener('input', (e) => password(), false)
        passwordCfInput.addEventListener('input', (e) => password(), false)
        emailInput.addEventListener('input', (e) => changeValue(e, 'emailInput'), false)
        nicknameInput.addEventListener('input', (e) => changeValue(e, 'nicknameInput'), false)
        
        refreshCaptchaBtn.addEventListener('click', (e) => getKeyNImage())
        emailCheckBtn.addEventListener('click', (e) => checkEmail(e), false)
        nickCheckBtn.addEventListener('click', (e) => checkNick(), false)
        submitBtn.addEventListener('click', (e) => submit(e), false)
       //joinForm.addEventListener('submit', (e) => submit(e), false)
    }







    function checkEmail() {
        var emailAddr = emailInput.value
        var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i

        if (emailAddr.match(regExp) == null) {
            emailFlag = false
            return emailMsgDiv.innerHTML = "이메일 형식이 아닙니다."
        }

       ajax("post", emailCheckUrl, JSON.stringify({ user_email: emailAddr }), (err, data) => {
           if (err) { return alert('email 통신 실패') }
           if (data.success === 'true') {
	          emailFlag = true
	          emailMsgDiv.innerHTML = "사용가능한 이메일입니다."
           } else {
	    	   emailFlag = false
	    	   emailMsgDiv.innerHTML = "이미 가입하셨습니다."
           }
       })
    }





    function checkNick() {
    	var nickVal = nicknameInput.value
    	
    	if (nickVal.length < 4) {
    		nicknameMsgDiv.innerHTML = '4글자 이상 입력해주세요.'
    		return nickFlag = false
    	}
    	
        ajax("post", nickCheckUrl, JSON.stringify({ user_nick: nicknameInput.value }), (err, data) => {
           if (err) { return alert('통신 실패') }

           if (data.success === 'true') {
               nickFlag = true
               nicknameMsgDiv.innerHTML = "사용 가능한 닉네임입니다."
           } else {
              nickFlag = false
              nicknameMsgDiv.innerHTML = "이미 사용중인 닉네임입니다."
           }
        })
    }





    function changeValue(evt, elem) {

        if (elem === 'emailInput') {
            emailFlag = false
        } else if (elem === 'nicknameInput') {
            nickFlag = false
        }

    }






    function password() {
        let pwdVal = passwordInput.value,
            pwdCfVal = passwordCfInput.value

        if (pwdVal.length < 4) {
            pwdFlag = false
            passwordMsgDiv.innerHTML = '일치하지 않습니다.'
        } else if (pwdVal !== pwdCfVal) {
            pwdFlag = false
            passwordMsgDiv.innerHTML = '일치하지 않습니다.'
        } else {
            pwdFlag = true
            passwordMsgDiv.innerHTML = '일치합니다.'
        }
    }

    
    
    
    function getKeyNImage() {
    	ajax('get', captchaGetKeyUrl, null, (err, data) => {
    		key = data.key;
    		captchaDiv.innerHTML ='<img src="' + captchaGetImageUrl+ key + '"/>';
    		captchaInput.value = '';
    	});
    }
    
    
    


    function submit(evt) {
    	let captchaData = {
			key: key,
			value: captchaInput.value
		}

    	ajax('post', captchaCompareUrl, JSON.stringify(captchaData), (err, data) => {
    		//data.result = true or false
    		captchaFlag = data.result;
    
    		if (emailFlag === true && nickFlag === true && pwdFlag === true && captchaFlag === true) {
                joinForm.submit();
            } else {
            	evt.preventDefault();
                errorDiv.innerHTML = '다시 한번 확인해주세요.'
            }
    	})

    }    


 
    function ajax(type, url, data, cb) {

        var xhr
        if (window.ActiveXObject) {
            xhr = new ActiveXObject("Microsoft.XMLHTTP")

        } else if (window.XMLHttpRequest) {
            xhr = new XMLHttpRequest()
        }

        xhr.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                cb(null, JSON.parse(xhr.responseText))
            }
        }

        xhr.open(type, url, true)
        xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest")
        xhr.setRequestHeader("Content-Type", 'application/json')
        xhr.send(data)

    }



    return {
        init: init
    }



} ()