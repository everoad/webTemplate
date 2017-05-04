<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">">
<title>${homeVo.home_name}</title>
<%@ include file="../templates/css.jsp" %>
</head>
<body>
	<header>
		<%@ include file="../templates/header.jsp" %>
	</header>
	<section>
		<div class="content-side-right">
		
			<header>
				<%@ include file="../templates/front_img.jsp" %>
			</header>
			
			<c:url var="contextUrl" value="/board/${menu_fir_seq}/${menu_sec_seq}/${boardVo.board_seq}" />
			<div class="container">	
			
				<article class="content col-md-10">
					<div class="text-content">
						<f:form action="${contextUrl}/edit" class="sky-form" method='post' name='wfrm' onSubmit='return ChkForm()'  commandName="boardVo">
							<f:input path="user_email" type="hidden" />
							<f:input path="title"  placeholder="Title.." id="title" class="form-control"/>
							<f:textarea path="content" id="content" rows="10" style="width:100%;display:none;" class="form" />
							<div align="right">
								<button class="btn-u btn-u-default btn-u-sm" id="back">취소</button>
								<input type='submit' value="완료" class="btn-u btn-u-sm">
							</div>
							<f:errors path="*" />
						</f:form>
					</div>
				</article>
				
				<div class="col-md-2"></div>
			</div>
		</div>
	</section>

<%@ include file="../templates/js.jsp" %>
<c:url var="resourceUrl" value="/resources/assets" />
<script type="text/javascript" src="${resourceUrl}/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script>
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "content",
		sSkinURI: "${resourceUrl}/se2/SmartEditor2Skin.html",
		fCreator: "createSEditor2",
		htParams : {
	        // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
	        bUseToolbar : true,             
	        // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
	        bUseVerticalResizer : false,     
	        // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
	        bUseModeChanger : false, 
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
</script>
</body>
</html>