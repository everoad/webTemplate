<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>${homeVo.home_name}</title>
<jsp:include page="../templates/css.jsp" />
</head>
<body>
	<div class="wrapper">
		<header>
			<c:set value="home" var="adminType" />
			<%@ include file="../templates/header.jsp" %>
		</header>
		<section class="bg-color-custom">
			<div class="content-side-right">
				<!-- Interactive Slider -->
				<header>
					<%@ include file="../templates/front_img.jsp" %>
				</header>
				<div class="container content">
					<nav>
						<ul class="pull-left breadcrumb">
							<li class="active">
								<a href="javascript:void(0);">Home</a>
							</li>
							<li>
								<a href="<c:url value="/admin/menu" />">Menu</a>
							</li>
						</ul>
					</nav>
					
					<div class="col-md-1"></div>
					<div class="col-md-9">
						<div class="menu sky-form">
							<c:url var="form" value="/admin/home" />
							<f:form action="${form}" method="post" enctype="multipart/form-data" commandName="homeVo">
								<label for="">홈페이지 이름</label>
								<label class="input">
									<f:input type="text" path="home_name" />
								</label>
								<br />
								<label for="">홈페이지 로고</label>
								<label class="input input-file" for="logo">
									<div class="button">
										<input type="file" name="home_logo_file"  onchange="this.parentNode.nextSibling.nextSibling.value = this.value"/>
										Browse
									</div>
									<input type="text"  readonly="readonly" />
								</label>
								<br />
								<label for="">홈페이지 이미지</label>
								<label for="mainImg" class="input input-file">
									<div class="button">
										<input type="file" name="home_img_file"  id="mainImg" onchange="this.parentNode.nextSibling.nextSibling.value = this.value" />
										Browse
									</div>
									<input type="text"  readonly="readonly" />
								</label>
								<br />
								<input type="submit" value="완료" class="btn btn-u btn-sm" />
							</f:form>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>
		</section>
	</div>
	<jsp:include page="../templates/js.jsp" />
</body>
</html>