<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->  
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->  
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->  
<head>
    <title>Login</title>
    <!-- Meta -->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<meta name="google-signin-client_id" content="977647411111-u2fgrg4ckdbicumv9eskfdbk3lcoa97n.apps.googleusercontent.com">
  
	<c:url var="resourceUrl" value="/resources" />
    <!-- Web Fonts -->
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
    	.social-login-box  div {
    		display: inline-block;
    	}
    </style>
</head> 
<body>

<!--=== Content Part ===-->    
<div class="container">
    <!--Reg Block-->
    <c:url var="form" value="/j_spring_security_check" />
   	<f:form action="${form}" method="post" commandName="userVo" name="loginForm">
		<div class="reg-block">
	        <div class="reg-block-header">
	            <h2>Sign In</h2>
					<div class="social-login-box">
			            <div class="g-signin2" data-onsuccess="onSignIn"></div>
						<div id="naver_id_login"></div>
					</div>
			
	            <p>Don't Have Account? Click <a class="color-green" href="<c:url value="/user/join" />">Sign Up</a> to registration.</p>            
	        </div>
		
	        <div class="input-group margin-bottom-20">
	            <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
	            <f:input type="text" path="user_email" class="form-control" placeholder="Email" />
	        </div>
	        <div class="input-group margin-bottom-20">
	            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
	            <input type="password" name="user_pwd" class="form-control" placeholder="Password">
	        </div>
	        <div id="loginMsgDiv"></div>
	        <f:errors path="*" />
  	        <hr>
        	
	        <div class="checkbox">
	            <label>
	                <input type="checkbox" name="cookieCheck" ${userVo.user_email ne null ? 'checked' : '' }> 
	                <p>Remember email</p>
	            </label>
	        </div>
	        <div class="row">
	            <div class="col-md-10 col-md-offset-1">
	                <button type="submit" class="btn-u btn-block">Log In</button>
	            </div>
	            <div class="col-md-10 col-md-offset-1">
	            	<a href="<c:url value="/" />" class="btn btn-default btn-block">Home</a>             
	            </div>
	        </div>
		</div>
    </f:form>
    
    <form action="" name="user">
		<input type="hidden" name="user_email" />
		<input type="hidden" name="user_nick" />
		<input type="hidden" name="access_token"  />
		<input type="hidden" name="social_type" />
	</form>
    <!--End Reg Block-->
</div><!--/container-->
<!--=== End Content Part ===-->

<!-- JS Global Compulsory -->           
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
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script type="text/javascript" src="${resourceUrl}/custom/js/login-validation.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        App.init();
        var loginForm = document.loginForm;
        LoginValidator.init({
        	emailInput: loginForm.user_email,
        	pwdInput: loginForm.user_pwd,
        	loginForm: loginForm,
        	loginMsgDiv: document.getElementById('loginMsgDiv')
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
<script type="text/javascript">
	var host = window.location.host;
	var requestContextPath = '${pageContext.request.contextPath}';
  	var naver_id_login = new naver_id_login("9ukoh9TDWU815iwLmDoH", "http://"+host+ requestContextPath + "/user/login");
  	var state = naver_id_login.getUniqState();
  	naver_id_login.setButton("white", 2,40);
  	naver_id_login.setDomain("http://"+host+ requestContextPath);
  	naver_id_login.setState(state);
  	//naver_id_login.setPopup();
  	naver_id_login.init_naver_id_login();
	naver_id_login.get_naver_userprofile("naverSignInCallback()");

	function naverSignInCallback() {
		var user_email = naver_id_login.getProfileData('email');
		var user_nick = naver_id_login.getProfileData('nickname');
		var access_token = naver_id_login.oauthParams.access_token;
		movePage(user_email, user_nick, access_token,"naver");
	}
	
	
    function onSignIn(googleUser) {
		var profile = googleUser.getBasicProfile();
		var user_email = profile.getEmail();
		var user_nick = user_email.split("@")[0];
		var access_token  = googleUser.getAuthResponse().id_token;
		movePage(user_email, user_nick, access_token, "google");
    }
  
	
	
	function movePage(user_email, user_nick, access_token, social_type) {
		var form = document.user;
		form.user_email.value = user_email;
		form.user_nick.value = user_nick;
		form.access_token.value = access_token;
		form.social_type.value = social_type;
		form.action = "http://"+host + requestContextPath + "/j_spring_security_check";
		form.method="post";
		form.submit();
	}
	
	
</script>


<!--[if lt IE 9]>
    <script src="${resourceUrl}/assets/plugins/respond.js"></script>
    <script src="${resourceUrl}/assets/plugins/html5shiv.js"></script>
    <script src="${resourceUrl}/assets/plugins/placeholder-IE-fixes.js"></script>
<![endif]-->
</body>
</html>