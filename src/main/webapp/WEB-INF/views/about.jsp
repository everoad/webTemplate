<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<%@ include file="./templates/css.jsp" %>
<link rel="stylesheet" href="<c:url value="/resources/custom/css/list.css"/>">
</head>
<body>
	<div id="wrapper">
		<header>
			<%@ include file="./templates/header.jsp" %>
		</header>
	</div>
	<section class="bg-color-custom content-side-right">
		<header>
			<%@ include file="./templates/front_img.jsp" %>
		</header>
		<div class="container content">
			<div class="col-md-9">
				<div id="root"></div>
			</div>
			
			<aside class="profile col-md-3">
				<div class="aside-box">
					<header class="panel-heading-v2 overflow-h">
	                    <h2 class="heading-xs pull-left"><i class="fa fa-bell-o"></i> 최신글</h2>
	                    <!-- <a href="#"><i class="fa fa-cog pull-right"></i></a> -->
	                </header>
	                <ul class="list-unstyled mCustomScrollbar margin-bottom-20" data-mcs-theme="minimal-dark">
	                	<c:forEach items="${recentList}" var="vo">
	                    <li class="notification">
	                        <!-- <i class="icon-custom icon-sm rounded-x icon-bg-red icon-line icon-envelope"></i> -->
	                        <div class="overflow-h">
	                        	<h4>[${vo.menu_sec_name }]</h4>
	                            <span><a href="<c:url value="/board/${vo.menu_fir_seq}/${vo.menu_sec_seq}/${vo.board_seq}" />">${vo.title }</a></span>
	                            <small>${vo.reg_date }</small>
	                        </div>    
	                    </li>
	                    </c:forEach>
	                </ul>    
	                <!-- <button type="button" class="btn-u btn-u-default btn-u-sm btn-block">Load More</button> -->
                </div>
                <div class="aside-box">
					<header class="panel-heading-v2 overflow-h">
	                    <h2 class="heading-xs pull-left"><i class="fa fa-bell-o"></i> 인기글</h2>
	                    <!-- <a href="#"><i class="fa fa-cog pull-right"></i></a> -->
	                </header>
	                <ul class="list-unstyled mCustomScrollbar margin-bottom-20" data-mcs-theme="minimal-dark">
	                	<c:forEach items="${bestList}" var="vo">
	                    <li class="notification">
	                        <!-- <i class="icon-custom icon-sm rounded-x icon-bg-red icon-line icon-envelope"></i> -->
	                        <div class="overflow-h">
	                        	<h4>[${vo.menu_sec_name }]</h4>
	                            <span><a href="<c:url value="/board/${vo.menu_fir_seq}/${vo.menu_sec_seq}/${vo.board_seq}" />">${vo.title }</a></span>
	                            <small>${vo.reg_date }</small>
	                        </div>    
	                    </li>
	                    </c:forEach>
	                </ul>
	                <!-- <button type="button" class="btn-u btn-u-default btn-u-sm btn-block">Load More</button> -->
                </div>
                
			</aside>
		</div>
	</section>

<script src="<c:url value="/resources/custom/js/bundle.js" />"></script>
<%@ include file="./templates/js.jsp" %>
</body>
</html>