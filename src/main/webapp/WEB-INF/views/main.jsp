<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>${homeVo.home_name}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<%@ include file="./templates/css.jsp" %>
<style>
	
.aside-box  {
	background: white;
	margin-top: 30px;
	margin-bottom: 30px;
	border: 1px solid #ddd;
	padding: 10px;
}

.aside-box h4 {
	color: #5fb611;
}
.search-box {
	padding: 11px;
	border: 1px solid #ddd;
	background: white;
	text-align: center;
}

</style>
</head>
<body>
<div class="wrapper">

	<header>
		<%@ include file="./templates/header.jsp" %>
	</header>
	
	<section class="bg-color-custom">
		<div class="content-side-right">
			<!-- Interactive Slider -->
			<header>
				<%@ include file="./templates/front_img.jsp" %>
			</header>
			
			<div class="container content">
				<nav class="col-md-9">
					<form action="<c:url value="/" />" method="get"  class="sky-form search-box">
						<input type="text" name="skey" class="form-control"  value="${param.skey}"  placeholder="Search">
					</form>
				</nav>
				<section class="col-md-9">
				
				<c:forEach items="${list}" var="voList">
					<article class="menu">
						
						<header>
							<c:url var="firUrl" value="/board/${voList.menu_fir_seq}/${voList.boardList[0].menu_sec_seq}" />
							<h1><a href="${(voList.boardList[0].menu_sec_seq != null) ? firUrl : 'javascript:void(0);' }" class="custom-headline">${voList.menu_fir_name}</a></h1>
						</header>
						<br />
						
						<table class="table">
							<tr>
								<td width="15%"><b>Category</b></td>
								<td width="45%"><b>Title</b></td>
								<td width="15%"><b>Author</b></td>
								<td width="15%"><b>Date</b></td>
								<td width="10%"><b>Hit</b></td>
							</tr>
							<c:forEach items="${voList.boardList}" var="vo">
							<c:url var="boardUrl" value="/board/${vo.menu_fir_seq}/${vo.menu_sec_seq}/${vo.board_seq}" />
							<c:url var="secUrl" value="/board/${vo.menu_fir_seq}/${vo.menu_sec_seq}" />
							<tr>
								<td><a href="${secUrl }">${vo.menu_sec_name }</a></td>
								<td><a href="${boardUrl }">${vo.title}</a> (${vo.reply_count })</td>
								<td>${vo.user_nick }</td>
								<td>${vo.reg_date }</td>
								<td>${vo.hit_count }</td>
							</tr>
							</c:forEach>
						</table>
						
					</article>
				</c:forEach>
				</section>
				<aside class="profile col-md-3">
					<div id="aside-box">
						<div class="panel panel-profile no-bg">
                            <div class="panel-heading overflow-h">
                                <h2 class="panel-title heading-sm pull-left"><i class="fa fa-pencil"></i>실시간 검색어</h2>
                            </div>
                            <div id="scrollbar" class="panel-body no-padding mCustomScrollbar" data-mcs-theme="minimal-dark">
                            
                            </div>
                        </div> 
					</div>
				</aside>
			</div>
		</div>
		
	</section>
</div>
<%@ include file="./templates/js.jsp" %>
<script src="<c:url value="/resources/custom/js/popular-Search.js" />"></script>
<script>
$(function () {
	popular_Search.init();
})
</script>
</body>
</html>