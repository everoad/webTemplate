<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="panel panel-profile no-bg">
    <header class="panel-heading overflow-h">
        <h2 class="panel-title heading-sm pull-left"><i class="fa fa-briefcase"></i>공지사항</h2>
    </header>
    <section id="scrollbar2" class="panel-body no-padding mCustomScrollbar" data-mcs-theme="minimal-dark">
        
        <c:forEach items="${recentList}" var="board">
	        <article class="profile-event">
	            <div class="date-formats">
	                <span>25</span>
	                <small>05, 2014</small>
	            </div>
	            <div class="overflow-h">
	            	<header>
	               		<h3 class="heading-xs"><a href="<c:url value="/board/${board.menu_fir_seq}/${board.menu_sec_seq}" />">${board.title }</a></h3>
	               	</header>
	                <p>ha</p>
	            </div>    
	        </article>
        </c:forEach>
    </section>    
</div>