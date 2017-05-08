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
<link rel="stylesheet" href="<c:url value="/resources/custom/css/list.css"/>">

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
	
					<c:if test="${ !device }">
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
					</c:if>
					<c:if test="${device}">
						<div class="grid-out-box row profile">
						<c:forEach items="${voList.boardList}" var="vo">
							<c:url var="boardUrl" value="/board/${vo.menu_fir_seq}/${vo.menu_sec_seq}/${vo.board_seq}" />
						    <article class="grid-article col-md-4">
						    	<div class="gridbox">
						    		<c:if test="${vo.front_img ne null}">
							        <div class="easy-block-v1">
							        	<div>
							    	        <a href="${boardUrl}"><img class="img-responsive" src="${vo.front_img }" alt=""></a>
							      		</div>
							        </div>
							        </c:if>
						            <header class="projects">
			                            <h2><a class="color-dark" href="${boardUrl}">${vo.title }</a></h2>
			                             <div class="grid-content">
			                            	${vo.content}
		                            	</div>
			                            <ul class="list-unstyled list-inline blog-info-v2">
			                                <li>By: <a class="color-green" href="#">${vo.user_nick }</a></li>
			                                <li><i class="fa fa-clock-o"></i>${vo.reg_date }</li>
			                            </ul>
			                        </header>
			                       
							        <footer class="project-share">
							            <ul class="list-inline comment-list-v2">
							                <li><i class="fa fa-eye"></i> <a href="#">${vo.hit_count }</a></li>
							                <li><i class="fa fa-comments"></i> <a href="#">${vo.reply_count }</a></li>
							            </ul>    
							        </footer>
						        </div>
						    </article>
					
					    </c:forEach>
						</div>
						</c:if>
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
	
	let gridContents = $('.grid-content');
	for (let i = 0; i < gridContents.length; i++) {
		let res = doRemoveTag(gridContents[i].innerHTML);
		res = res.trim();
		if (res.length > 6) {
    		res = res.substr(0, 100) + '...';
		}
		gridContents[i].innerHTML = res;    			    		
	}
    	
   	
	function removeTag( html ) {
		return html.replace(/(<([^>]+)>)/gi, "");
	}

	function doRemoveTag(beforeText) {
		return removeTag( beforeText );
		
	}
})
</script>
</body>
</html>