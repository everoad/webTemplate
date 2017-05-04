<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>

<!-- Meta -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<c:url var="resourceUrl" value="/resources" />
<!-- Web F	onts -->
<link rel='stylesheet' type='text/css' href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

<!-- CSS Global Compulsory -->
<link rel="stylesheet" href="${resourceUrl}/assets/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${resourceUrl}/assets/css/style.css">

<!-- CSS Implementing Plugins -->
<link rel="stylesheet" href="${resourceUrl}/assets/plugins/animate.css">
<link rel="stylesheet" href="${resourceUrl}/assets/plugins/line-icons/line-icons.css">
<link rel="stylesheet" href="${resourceUrl}/assets/plugins/font-awesome/css/font-awesome.min.css">

<!-- CSS Page Style -->    
<link rel="stylesheet" href="${resourceUrl}/assets/css/pages/page_log_reg_v2.css">    

<!-- CSS Customization -->
<link rel="stylesheet" href="${resourceUrl}/assets/css/custom.css">
<style>
	.msg {
		text-align: center;
		height: 20px;
	}
	#captchabox {
		margin-bottom: 10px;
	}
</style>
</head>
<body>

<!--=== Content Part ===-->    
<div class="container">
    <!--Reg Block-->
    <c:url var="form" value="/user/join" />
    <f:form action="${form}" method="post" commandName="userVo" name="joinForm">
	    <div class="reg-block">
	        <div class="reg-block-header">
	            <h2>Sign Up</h2>
	            <p>Already Signed Up? Click <a class="color-green" href="<c:url value="/user/login" />">Sign In</a> to login your account.</p>
	        </div>
	
	        <div class="input-group margin-bottom-0">
	            <span class="input-group-addon"><i class="fa fa-user"></i></span>
	            <f:input path="user_nick"  class="form-control" placeholder="Username"/>
	            <span class="input-group-btn">
	            	<input type="button" name="nickCheck" value="check" class="btn-u">
	            </span>
	        </div>
	        <div id="user_nick_msg" class="msg"></div>
	        <f:errors path="user_nick" element="div" id="user_nick_msg" /> 
	        <div class="input-group margin-bottom-0">
	            <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
	            <f:input path="user_email" placeholder="Email.." class="form-control" />
	            <span class="input-group-btn">
	        	    <input type="button" name="emailCheck" value="check"  class="btn-u">
       		    </span>
	        </div>
	        <div id="user_email_msg" class="msg"></div>
	        <f:errors path="user_email" element="div" id="user_email_msg" /> 
	        <div class="input-group margin-bottom-20">
	            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
	            <input type="password" name="user_pwd" class="form-control" placeholder="Password" />
	        </div>
	        <div class="input-group margin-bottom-0">
	            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
	            <input type="password" name="user_pwd_cf" class="form-control" placeholder="Confirm Password">
		     </div>
	        <div id="user_pwd_msg" class="msg"></div>
	        <f:errors path="user_pwd" element="div"  id="user_pwd_msg" /> 

	        <div id="captchabox">
	        </div>
       		<div class="input-group">
       			<span class="input-group-addon"><i class="fa fa-key"></i></span>
				<input type="text" name="captchaInput" class="form-control" placeholder=""/>
				<span class="input-group-btn">
		        	<input type="button" value="Refresh" name="refreshCaptchaBtn" class='btn-u'  />
		        </span>
	        </div>
	        <div id="error" class="msg"></div>
	        <div class="row">
	            <div class="col-md-10 col-md-offset-1">
	                <input type="button" class="btn-u btn-block" value="Register" name="submitBtn">             
	            </div>
	            <div class="col-md-10 col-md-offset-1">
	            	<a href="<c:url value="/" />" class="btn btn-default btn-block">Home</a>             
	            </div>
	        </div>
	    </div>
    <!--End Reg Block-->
    </f:form>
</div><!--/container-->

<!-- JS Global Compulsory -->           
<script type="text/javascript" src="${resourceUrl}/custom/js/join-validation.js"></script>
<script type="text/javascript" src="${resourceUrl}/assets/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${resourceUrl}/assets/plugins/jquery/jquery-migrate.min.js"></script>
<script type="text/javascript" src="${resourceUrl}/assets/plugins/bootstrap/js/bootstrap.min.js"></script> 
<!-- JS Implementing Plugins -->           
<script type="text/javascript" src="${resourceUrl}/assets/plugins/back-to-top.js"></script>
<script type="text/javascript" src="${resourceUrl}/assets/plugins/backstretch/jquery.backstretch.min.js"></script>
<!-- JS Customization -->
<script type="text/javascript" src="${resourceUrl}/assets/js/custom.js"></script>
<!-- JS Page Level -->           
<script type="text/javascript" src="${resourceUrl}/assets/js/app.js"></script>
<script type="text/javascript" src="${resourceUrl}/custom/js/join-validation.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        App.init();
        var joinForm = document.joinForm
        JoinValidator.init({
            joinForm: joinForm,
            emailInput: joinForm.user_email,
            passwordInput: joinForm.user_pwd,
            passwordCfInput: joinForm.user_pwd_cf,
            nicknameInput: joinForm.user_nick,
            emailCheckBtn: joinForm.emailCheck,
            nickCheckBtn: joinForm.nickCheck,
            submitBtn: joinForm.submitBtn,
            refreshCaptchaBtn: joinForm.refreshCaptchaBtn,
            captchaInput: joinForm.captchaInput,
            captchaDiv: document.getElementById('captchabox'),
            nicknameMsgDiv: document.getElementById('user_nick_msg'),
            emailMsgDiv: document.getElementById('user_email_msg'),
            passwordMsgDiv: document.getElementById('user_pwd_msg'),
            errorDiv: document.getElementById('error')
        })
    
    });
</script>
<script type="text/javascript">
    $.backstretch([
      "${resourceUrl}/assets/img/bg/19.jpg",
      "${resourceUrl}/assets/img/bg/18.jpg",
      ], {
        fade: 1000,
        duration: 7000
    });
    
</script>


<!--[if lt IE 9]>
    <script src="${resourceUrl}/assets/plugins/respond.js"></script>
    <script src="${resourceUrl}/assets/plugins/html5shiv.js"></script>
    <script src="${resourceUrl}/assets/plugins/placeholder-IE-fixes.js"></script>
<![endif]-->
	
	
</body>
</html>