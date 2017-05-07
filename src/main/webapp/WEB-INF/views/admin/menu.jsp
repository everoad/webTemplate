<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<title>${homeVo.home_name}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<jsp:include page="../templates/css.jsp" />
<link rel="stylesheet" href="<c:url value="/resources/custom/css/menu.css" />">
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
 			<div class="container content">
 				<div class="col-md-12">
					<nav class="middle-nav">
						<ul class="nav navbar-nav navbar-left">
							<li>
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
							</li>
						</ul>
					</nav>
				</div>
				 				
 				<div class="content">
 					<div class="col-md-6">
						<div class="leftbox">
							<button id="addBtn" class="btn btn-default btn-sm">대분류 추가</button>
							<ul id="list"></ul>
						</div> 
					</div>
					<!-- <div class="leftbox">
						<button id="addBtn" class="btn btn-default btn-sm">대분류 추가</button>
							<ul id="list">
								<li id="">
								<div class="title">
									<span id="firTitle-i">  테스트이름1 </span>
							 		<button id="firDelBtn-i" class="btn btn-default btn-sm">－</button>
							 	 	<button id="firAddBtn-i" class="btn btn-default btn-sm">＋</button>
								</div>
								<ul id="list-i">
									<li id="j">
										<div class="title">
											<span id="secTitle-i-j">테스트이름2</span>
											<button id="secDelBtn-i-j" class="btn btn-default btn-sm">－</button>
										</div>
									</li>
								</ul>
							</li>
						</ul>
					</div> -->
					<div class="col-md-6">
						<div class="rightbox inline-group">
							<form action="" name="form" class="sky-form">
								<input type="text" id="title" class="form-control" placeholder="name.."><br><br />
								<div id="radiobox" class="inline-group">
								</div>
							</form>
							<button id="cplteBtn" class="btn btn-default btn-sm">확인</button>
						</div>
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
