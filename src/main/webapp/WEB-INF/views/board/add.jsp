<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->  
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->  
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->  
<head>
<title>${homeVo.home_name}</title>

<!-- Meta -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<%@ include file="../templates/css.jsp" %>
</head>	
<body>
<div class="wrapper">
	<header>
		<%@ include file="../templates/header.jsp" %>
	</header>	
	
	<c:url var="resourceUrl" value="/resources" />
	<div class="content-side-right">
		<!-- Interactive Slider -->
		<header>
			<%@ include file="../templates/front_img.jsp" %>
		</header>
	
		<c:url value="/board/${menu_fir_seq}/${menu_sec_seq}" var="contextUrl" />
		<div class="container">
			<div class="text-content">
				<div class="content col-md-10">
					<f:form action="${contextUrl}/add" class="sky-form" method='post' name='wfrm' onSubmit='return ChkForm()'  commandName="boardVo">
						<f:input path="title"  placeholder="Title.." id="title" class="form-control"/>
						<f:textarea path="content" id="content" rows="10" style="width:100%; display:none;" class="content" />
						<!-- <textarea name='content' style='width:620;height:300' class='form'></textarea> -->
						<div align="right">
							<button id="back" class="btn-u btn-u-default btn-u-sm" id="back">취소</button>
							<input type='submit' value="완료" class="btn-u btn-u-sm">
						</div>
						<f:errors path="*" />
					</f:form>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
</div>
<%@ include file="../templates/js.jsp" %>
<script type="text/javascript" src="${resourceUrl}/assets/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
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

	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "content",
		sSkinURI: "${resourceUrl}/assets/se2/SmartEditor2Skin.html",
		fCreator: "createSEditor2",
		htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,             
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : false,     
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true, 
        }
	});

	function ChkForm() { // 글을 쓸 때 필수적으로 입력해야 할 값이 있는데, 입력 안 했을 경우 경고 문구 출력하도록 함수 만들기
	
		
		// 에디터의 내용이 textarea에 적용된다.
		oEditors.getById["content"  ].exec("UPDATE_CONTENTS_FIELD", []);
		// 에디터의 내용에 대한 값 검증은 이곳에서
		// document.getElementById("ir1").value를 이용해서 처리한다.
		try {
			elClickedObj.form.submit();
		} catch(e) {}
	}
	
	$('#back').on('click', function(event) {
		history.back();
	});
	
</script>

<!--[if lt IE 9]>
    <script src="/template/resources/assets/plugins/respond.js"></script>
    <script src="/template/resources/assets/plugins/html5shiv.js"></script>
    <script src="/template/resources/assets/plugins/placeholder-IE-fixes.js"></script>      
<![endif]-->
</body>
</html>