<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s" %>

<!--=== Header v7 Left ===-->
<div class="header-v7 header-left-v7">
    <nav class="navbar navbar-default mCustomScrollbar" role="navigation" data-mcs-theme="minimal-dark">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="menu-container">
            <!-- Toggle get grouped for better mobile display -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!-- End Toggle -->

            <!-- Logo -->
            <div class="logo">
                <a href="<c:url value="/" />">
                    <img id="logo-header" src="<c:url value="/uploads/${homeVo.home_logo}" />" alt="Logo">
                </a>
            </div>
            <!-- End Logo -->
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->

        <div class="collapse navbar-collapse navbar-responsive-collapse">
            <div class="menu-container">
                <ul class="nav navbar-nav">
                    <!-- Home -->
                    <li class="${menu_fir_seq eq null and adminType eq null ? 'active' : '' }">
                        <a href="<c:url value="/" />">
                            Home
                        </a>
                    </li>
                    <li>
                    	<a href="<c:url value="/about" />">
                    		About
                    	</a>
                    </li>
                    <!-- End Home -->

                    <!-- About Pages -->
                    <c:forEach items="${menuList}" var="menu_fir">
                    <li class="dropdown ${menu_fir_seq eq menu_fir.menu_fir_seq ? 'active open' : ''}">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            ${menu_fir.menu_fir_name}
                        </a>
                        <ul class="dropdown-menu">
                        	<c:forEach items="${menu_fir.menuSecList }" var="menu_sec">
							<li class="${menu_sec_seq eq menu_sec.menu_sec_seq ? 'active' : '' }" >
								<a href="<c:url value="/board/${menu_fir.menu_fir_seq}/${menu_sec.menu_sec_seq}" />">${menu_sec.menu_sec_name}</a>
							</li>
                            </c:forEach>	
                        </ul>
                    </li>
                    <!-- End About Pages -->
					</c:forEach>
					
					<s:authorize access="hasAnyRole('ADMIN')">
						<li class="dropdown ${menu_fir_seq eq null and adminType ne null ? 'active open' : ''}">
							<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
								Admin
							</a>
							 <ul class="dropdown-menu">
							 	<li class="${adminType eq 'home' ? 'active' : '' }">
							 		<a href="<c:url value="/admin/home"/>">Home</a>
							 	</li>
							 	<li class="${adminType eq 'menu' ? 'active' : '' }">
							 		<a href="<c:url value="/admin/menu"/>">Menu</a>
							 	</li>
							 </ul>
						</li>
					</s:authorize>
                </ul>
				 <ul class="list-inline header-socials">
	                <s:authorize access="isAnonymous()">
						<!-- 비로그인 상태 -->
						<a href="<c:url value="/user/login"/>" class="btn btn-default btn-sm">Sign In</a>
						<a href="<c:url value="/user/join"/>"  class="btn btn-success btn-sm">Sign Up</a>
					</s:authorize>
					<s:authorize access="isAuthenticated()">
						<!-- 로그인상태 -->
						<p class="copyright-text"><s:authentication property="principal.user_nick" /> 님, 반갑습니다.</p>
						<form action="<c:url value="/user/logout"/>" method="post">
							<input type="submit" value="Logout" class="btn btn-default btn-sm" />
						</form>
					</s:authorize>
                </ul>
              	<p class="copyright-text">&copy; 2015 Unify. All Rights Reserved.</p>
                <ul class="list-inline header-socials">
                	<li><a href="javascript:void(0);">공지사항</a></li>
                	<li><a href="javascript:void(0);">회원정보</a></li>
                </ul>
            </div>  
        </div>
        <!-- End Navbar Collapse -->
    </nav>
</div>    
<!--=== End Header v7 Left ===-->