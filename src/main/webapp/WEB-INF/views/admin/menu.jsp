<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>${homeVo.home_name}</title>
<jsp:include page="../templates/css.jsp" />

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="/template/resources/custom/css/menu_edit.css"> -->
  <style media="screen">
    .leftbox {
    	background: white;
		margin: 0;
		padding: 10px;
		float: left;
		border: 1px solid #ddd;
		width: 48%;
    }
    
    .rightbox {
 	   background: white;
		margin: 0;
		padding: 10px;
		float: left;
		border: 1px solid #ddd;
		width: 48%;
    }
    
    .rightbox form {
    	padding: 10px;
    }
    
    #list {

    	border: 1px solid #ccc;
		list-style: none;
		padding-left: 0;
    }
    
    #list ul {
		list-style: none;
    }
    
    #list button {
    	margin: 0px;
		float: right;
		height: 27px;
    }
    
    #list span {
		margin-top: 3px;
    }
    
    #list li {
    	color: #666;
    	font-size: 15px;
    	margin: 3px;
    	padding: 0;
  		border: 1px solid #ccc;
    }
    
    .title {
    	margin: 0px;
    	padding-left: 10px;
    	height: 30px;
    	background: #ddd;
    	border: 1px solid #ccc;
    }
    
  </style>
</head>
<body>
<div class="wrapper">
	<header>
		<c:set value="menu" var="adminType" />
		<%@ include file="../templates/header.jsp" %>
	</header>
	<section class="bg-color-custom">
 		<div class="content-side-right">
 			<!-- Interactive Slider -->
			<header>
				<%@ include file="../templates/front_img.jsp" %>
			</header>
 			<div class="container">
 				<div>
				    <ul class="pull-left breadcrumb">
		               <li>
		               		<a href="<c:url value="/admin/home" />">Home</a>
		               </li>
		               <li class="active">
		               		<a href="javascript:void(0);">Menu</a>
		               </li>
		            </ul>
				</div>
 				
 				<div class="content">
					<div class="leftbox">
						<button id="addBtn" class="btn btn-default btn-sm">대분류 추가</button>
						<ul id="list"></ul>
					</div>
					<div class="rightbox inline-group">
						<form action="" name="form" class="sky-form">
							<input type="text" id="title" placeholder="name.."><br><br />
							<div id="radiobox" class="inline-group">
							</div>
						</form>
						<button id="cplteBtn" class="btn btn-default btn-sm">확인</button>
					</div>
				</div>
 			</div>
		</div>
	</section>
</div>

	

<jsp:include page="../templates/js.jsp" />
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="<c:url value="/resources/custom/js/menu-es6.js" />"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
      	App.init();
        App.initCounter();
        App.initParallaxBg();
        App.initScrollBar();
        FancyBox.initFancybox();        
        App.initAnimateDropdown();
        OwlCarousel.initOwlCarousel();
        ProgressBar.initProgressBarHorizontal();
    });
</script>
</body>
</html>
