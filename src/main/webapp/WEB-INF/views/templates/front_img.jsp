<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="interactive-slider-v1 img-v3" style="background: url(<c:url value="/uploads/${homeVo.home_img}" />) no-repeat;background-size: cover; background-position: center center;">
    <div class="container">
        <h2>${homeVo.home_name }</h2>
        <p>Home</p>
    </div>
</div>