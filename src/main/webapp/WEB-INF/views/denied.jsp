<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->  
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->  
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->  
<head>
    <title>404 Error Page | Unify - Responsive Website Template</title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
	
	<c:url var="contextUrl"  value="/resources/assets"/>
    <!-- Web Fonts -->
    <link rel='stylesheet' type='text/css' href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600&amp;subset=cyrillic,latin'>

    <!-- CSS Global Compulsory -->
    <link rel="stylesheet" href="${contextUrl}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextUrl}/css/style.css">

    <!-- CSS Header and Footer -->
    <link rel="stylesheet" href="${contextUrl}/css/headers/header-default.css">
    <link rel="stylesheet" href="${contextUrl}/css/footers/footer-v1.css">

    <!-- CSS Implementing Plugins -->
    <link rel="stylesheet" href="${contextUrl}/plugins/animate.css">
    <link rel="stylesheet" href="${contextUrl}/plugins/line-icons/line-icons.css">
    <link rel="stylesheet" href="${contextUrl}/plugins/font-awesome/css/font-awesome.min.css">

    <!-- CSS Page Style -->    
    <link rel="stylesheet" href="${contextUrl}/css/pages/page_404_error.css">

    <!-- CSS Customization -->
    <link rel="stylesheet" href="${contextUrl}/css/custom.css">
</head> 

<body>    

<div class="wrapper">
    <!--=== Content Part ===-->
    <div class="container content">		
        <!--Error Block-->
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="error-v1">
                    <span class="error-v1-title">Denied</span>
                    <span>접근 권한이 없습니다.</span>
                    <p>The requested URL was not found on this server. That’s all we know.</p>
                    <a class="btn-u btn-bordered" href="<c:url value="/" />">Back Home</a>
                </div>
            </div>
        </div>
        <!--End Error Block-->
    </div>	
    <!--=== End Content Part ===-->

</div><!--/wrapper-->

<!-- JS Global Compulsory -->           
<script type="text/javascript" src="${contextUrl}/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${contextUrl}/plugins/jquery/jquery-migrate.min.js"></script>
<script type="text/javascript" src="${contextUrl}/plugins/bootstrap/js/bootstrap.min.js"></script> 
<!-- JS Implementing Plugins -->           
<script type="text/javascript" src="${contextUrl}/plugins/back-to-top.js"></script>
<script type="text/javascript" src="${contextUrl}/plugins/smoothScroll.js"></script>
<!-- JS Customization -->
<script type="text/javascript" src="${contextUrl}/js/custom.js"></script>
<!-- JS Page Level -->           
<script type="text/javascript" src="${contextUrl}/js/app.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        App.init();
        });
</script>
<!--[if lt IE 9]>
    <script src="${contextUrl}/plugins/respond.js"></script>
    <script src="${contextUrl}/plugins/html5shiv.js"></script>
    <script src="${contextUrl}/plugins/placeholder-IE-fixes.js"></script>
<![endif]-->

</body>
</html> 