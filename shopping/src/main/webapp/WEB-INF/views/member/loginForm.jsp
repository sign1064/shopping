<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인 처리</title>
<meta charset="utf-8">
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="403080930715-me73pi7l9c03l9csee5vbs511a0j2l8v.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>

<script>
	function defalut() {
		alert("아직 완성되지 않은 기능입니다.");
	}
</script>
</head>
<body>
	<div class="container">

		<h1 class="col-sm-offset-2 col-sm-10" style="font-weight: bold">Login</h1>
		<br />
		<br />
		<br />
		<br />
		<form class="form-horizontal" action="/member/login" method="post"
			name="from">
			<input type="hidden" name="rurl" value="${param.rurl}"> <input
				type="hidden" name="bbsno" value="${param.bbsno}"> <input
				type="hidden" name="nowPage" value="${param.nowPage}"> <input
				type="hidden" name="nPage" value="${param.nPage}"> <input
				type="hidden" name="col" value="${param.col}"> <input
				type="hidden" name="word" value="${param.word}">

			<div class="form-group">
				<label class="control-label col-sm-2" for="id">아이디</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="id"
						placeholder="Enter id" name="id" required="required"
						value='${c_id_val}'>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="password">비밀번호</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="password"
						placeholder="Enter password" name="password" required="required">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-6">
					<div class="checkbox">
						<label> <c:choose>
								<c:when test="${c_id =='Y'}">
									<input type="checkbox" name="c_id" value="Y" checked="checked"> Remember ID
          </c:when>
								<c:otherwise>
									<input type="checkbox" name="c_id" value="Y"> Remember ID
          </c:otherwise>
							</c:choose>
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-8">
					<button type="submit" class="btn btn-default">로그인</button>
					<button type="button" class="btn btn-default"
						onclick="location.href='agree'">회원가입</button>
					<button type="button" class="btn btn-default" onclick="defalut()">계정
						찾기</button>
				</div>
		</form>

	</div>
</body>
</html>