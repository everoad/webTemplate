<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>


<c:url value="/board/${menu_fir_seq}/${menu_sec_seq}" var="contextUrl" />
<c:if test="${param.skey != null}">
	<c:set var="searchUrl" value="&skey=${param.skey}" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>${homeVo.home_name}</title>
<%@ include file="../templates/css.jsp" %>
<link rel="stylesheet" href="<c:url value="/resources/custom/css/list.css"/>">
<style>
	.navbar-right {
		margin-right: 0px;
	}
	

</style>
</head>
<body>
<div class="wrapper bg-color-custom">

	<header>
		<%@ include file="../templates/header.jsp" %>
	</header>
	
	<section>
		<div class="content-side-right">	
			<header>
				<%@ include file="../templates/front_img.jsp" %>
			</header>
			<div class="container">
				<div class="content">
					<nav class="col-md-7">
						<!-- 공통기능 : 검색과 쓰기 -->
						<div class="middle-nav">
							<ul class="nav navbar-nav navbar-left">
								<li>
									<div>
									    <ul class="pull-left breadcrumb">
							                <c:forEach items="${menuFirVo.menuSecList }" var="secVo">
											<li class="${menu_sec_seq eq secVo.menu_sec_seq ? 'active' : '' }" >
												<a href='<c:url value="/board/${menu_fir_seq}/${secVo.menu_sec_seq}"/>'>${secVo.menu_sec_name}</a>
											</li>
											</c:forEach>
							            </ul>

									</div>
								</li>
							</ul>
							
							<ul class="nav navbar-nav navbar-right">
								<li>
									<a href="${contextUrl}/add"><i class="icon-note"></i></a>
								</li>
							</ul>
						</div>
					</nav>
					
					<aside class="col-md-5">
						<form action="${contextUrl}" method="get"  class="sky-form search-box">
								<input type="text" name="skey" class="form-control"  value="${param.skey}"  placeholder="Search">
						</form>
					</aside>
	
					<!-- 리스트 형식 -->
					<article class="col-md-9">
						<c:if test="${menuFirVo.menu_fir_type eq 'list' && !device }">
						<div class="menu">
							<header>
								<h1><a href="${contextUrl}" class="custom-headline">${menuSecVo.menu_sec_name}</a></h1>
							</header>
							<br />
							
							<table class="table">
								<tr>
									<td width="10%"><b>No</b></td>
									<td width="60%"><b>Title</b></td>
									<td width="15%"><b>Author</b></td>
									<td width="15%"><b>Date</b></td>
									<td width="10%"><b>Hit</b></td>
								</tr>
								
								<c:forEach items="${boardVoList.boardList }" var ="vo" varStatus="status">
								<tr>
									<td>${boardVoList.countNum - status.index }</td>
									<td><a href="${contextUrl}/${vo.board_seq}?index=${boardVoList.index}${searchUrl}">${vo.title}</a> (${vo.reply_count })</td>
									<td>${vo.user_nick }</td>
									<td>${vo.reg_date }</td>
									<td>${vo.hit_count }</td>
								</tr>
								</c:forEach>
							</table>
							
						</div>
						</c:if>
						
						<!-- Grid 형식 -->
						<c:if test="${device || menuFirVo.menu_fir_type eq 'grid' }">
						<div class="grid-out-box row profile">
						<c:forEach items="${boardVoList.boardList}" var="vo">
						    <article class="grid-article col-md-4">
						    	<div class="gridbox">
						    		<c:if test="${vo.front_img ne null}">
							        <div class="easy-block-v1">
							        	<div>
							    	        <a href="${contextUrl}/${vo.board_seq }"><img class="img-responsive" src="${vo.front_img }" alt=""></a>
							      		</div>
							        </div>
							        </c:if>
						            <header class="projects">
			                            <h2><a class="color-dark" href="${contextUrl}/${vo.board_seq }">${vo.title }</a></h2>
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
						
						<!-- 공통기능 : 페이징 -->
						<footer class="text-center">
							<ul class="pagination">
								<c:set var="morepreviousUrl" value='${contextUrl}?index=${boardVoList.previous}${searchUrl}' />
								<c:set var="morenextUrl" value='${contextUrl}?index=${boardVoList.next}${searchUrl}' />
								<li><a href='${(boardVoList.index == 1) ? "javascript:void(0);" : morepreviousUrl }'>&laquo;</a></li>
								<c:forEach begin="${boardVoList.startBlock}" end="${boardVoList.endBlock}" varStatus="status">
									<c:if test="${status.count == boardVoList.index }">
										<li class="active"><a href="javascript:void(0);">${status.count}</a></li>
									</c:if>
									<c:if test="${status.count != boardVoList.index }">
										<li><a href='${contextUrl}?index=${status.count}${searchUrl}'> ${status.count } </a></li>
									</c:if>
								</c:forEach>
								<li><a href='${(boardVoList.index == boardVoList.totalPage) ? "javascript:void(0);" : morenextUrl }'>&raquo;</a></li>
							</ul>
						</footer> 
					</article>
			
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
			</div>
		</div>
	</section>
	
</div>
<%@ include file="../templates/js.jsp" %>

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
        
    });
    
    
</script>
</body>
</html>