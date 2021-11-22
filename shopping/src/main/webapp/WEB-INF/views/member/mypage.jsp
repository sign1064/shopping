<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-sm-3">
				<h3>
					<span class="glyphicon glyphicon-user"></span> 나의 정보
				</h3>
				<img class="img-rounded" src="${root }/member/storage/${dto.fname}"
					style="width: 280px">
				<p>
					<a href="/member/updateFile?oldfile=${dto.fname}">사진수정</a> <br /> <a
						href="/member/download?filename=${dto.fname}">다운로드</a>
				</p>
				<div class="caption">
					<li>ID:${dto.id}</li>
					<li>성명:${dto.mname}</li>
					<li>배송지:(${dto.postcode })${dto.address1 } ${dto.address2}</li>
				</div>
			</div>

		</div>
	</div>

</body>
</html>