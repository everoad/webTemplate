<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>${homeVo.home_name}</title>
<%@ include file="../templates/css.jsp" %>
<link rel="stylesheet" href="<c:url value="/resources/custom/css/detail.css"/>">
<style>
	.anonymous_comment{
		text-align: center;
	}
</style>
</head>
<%-- <script>
    function share() {
      var url = encodeURI(encodeURIComponent(location.href));
      var title = encodeURI('everoad');
      var shareURL = "http://share.naver.com/web/shareView.nhn?url=" + url + "&title=" + title;
      document.location = shareURL;
    }
  </script> --%>
<body>
<div class="wrapper">

	<header>
		<%@ include file="../templates/header.jsp" %>
	</header>
	
	<section class="bg-color-custom">
		<c:url var="contextUrl" value="/board/${boardVo.menu_fir_seq}/${boardVo.menu_sec_seq}" />
		<div class="content-side-right">
			<div class="container">
				<div class="content">
					<div class="col-md-10">
						<div class="sky-form">
				            <div class="testimonials-v6">
		                        <header class="testimonials-info">
		                            <div class="testimonials-desc">
		                                <h3>${boardVo.title}</h3>
										<h5 align="left">
											<i class="fa  fa-user"></i>${boardVo.user_nick } &nbsp;&nbsp;
											<i class="fa fa-eye"></i> ${boardVo.hit_count } &nbsp;&nbsp;
											<i class="fa fa-comments"></i> ${boardVo.reply_count } &nbsp;&nbsp;
											<i class="fa fa-clock-o"></i>${boardVo.reg_date } &nbsp;&nbsp;
											<s:authorize access="hasRole('ADMIN') OR (isAuthenticated() AND principal.user_email == #boardVo.user_email)">
											<span class="cstmspan">
												<a href="${contextUrl}/${boardVo.board_seq}/edit"><i class="fa fa-pencil"></i>수정</a>
												<form action="${contextUrl}/${boardVo.board_seq}/delete" method="post">
													<input type="hidden" name="user_email" value="${boardVo.user_email}" />
													<button class="btn btn-link"><i class="fa fa-trash"></i>&nbsp;삭제</button>
												</form>
											</span>
											</s:authorize>
										</h5>
		                            </div>
		                        </header>
				             	<div style="background-color:white; font-family:'Open Sans', Arial, sans-serif" class="detail">${boardVo.content }</div>
								<div align="right" class="testimonials-info">
									<c:if test="${param.skey != null}">
										<c:set var="searchUrl" value="&skey=${param.skey}" />
									</c:if>
									<h5><a href="${contextUrl}?index=${param.index ne null ? param.index : 1}${searchUrl}"><i class="icon-list"></i>목록</a></h5>
									<%-- <span class="naver-share">
										<script type="text/javascript" src="http://share.naver.net/js/naver_sharebutton.js"></script>
										<script type="text/javascript">
											new ShareNaver.makeButton({"type": "a"});
										</script>
									</span>
								    <div class="fb-share-button" data-href="https://218.235.230.197:8080${contextUrl}" data-layout="button" data-size="small" data-mobile-iframe="false"><a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fdevelopers.facebook.com%2Fdocs%2Fplugins%2F&amp;src=sdkpreparse"></a></div> --%>
				          		</div>
				          	</div>	
						
		                    <div class="panel panel-profile">
		                        <div class="panel-heading">
		                            <h2 class="panel-title pull-left">
		                            	<i class="fa fa-comments"></i>댓글
		                            </h2>
		                        </div>	
		                        <div class="panel-body">
		                        <s:authorize access="isAnonymous()">
		                        	<div class="anonymous_comment">
			                        	<p>로그인 후 사용해 주세요.</p>
			                        	<a href="<c:url value="/user/login" />">Login</a>
		                        	</div>
		                        </s:authorize>
								<s:authorize access="isAuthenticated()">
									<s:authentication property="principal.user_email" var="my_user_email"/>
			                        <label class="textarea">
			                        	<textarea rows="3" cols="" id="content"></textarea>
			                        </label>
			                        <div align="right">
			                        	<button id="addReplyBtn" class="btn-u">완료</button>
		                        	</div>
	                        	</s:authorize>
		                        </div>
		                        <div class="panel-body" id="comment" data-board-seq="${boardVo.board_seq}" data-user-email="${my_user_email}">
		                    		
		                        </div>
		    			    </div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</div>
	</section>
</div>
<div id="fb-root"></div>
<c:url var="resourceUrl" value="/resources" />
<%@ include file="../templates/js.jsp" %>
<script type="text/javascript" src="${resourceUrl}/custom/js/reply.js"></script>
<script>

	Reply.initReply()
	
</script>

<%-- <script type="text/javascript">	
	//FaceBook 공유
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id)) return;
		js = d.createElement(s); js.id = id;
		js.src = "//connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v2.8";
		fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
</script> --%>
</body>
</html>