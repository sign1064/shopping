<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<title>상품메인페이지</title>
<meta charset="utf-8">
</head>
<body>

	<div class="container">
		<h3>new Item</h3>
		<c:choose>
			<c:when test="${empty list}">
				<div class="row">
					<h2>상품 준비중 입니다.</h2>
					<p>
						<img src="${root }/pstorage/default.jpg" class="img-thumbnail"
							width="236" height="306">
					</p>

				</div>
			</c:when>
			<c:otherwise>
				<c:set var="list" value="${list}" />
				<div class="row">
					<c:forEach var="dto" begin="0" end="3" items="${list}">
						<div class="col-sm-3">
							<a href="/contents/detail/${dto.contentsno }">
								<p>
									<img src="${root }/pstorage/${dto.filename }"
										class="img-thumbnail" width="236" height="306">
								</p>
							</a>
							<p>${dto.pname }<br> <b>${dto.price }</b>
							</p>
						</div>
					</c:forEach>
				</div>
				<div class="row">
					<c:forEach var="dto" begin="4" end="7" items="${list}">
						<div class="col-sm-3">
							<a href="/contents/detail/${dto.contentsno }">
								<p>
									<img src="${root }/pstorage/${dto.filename }"
										class="img-thumbnail" width="236" height="306">
								</p>
							</a>
							<p>${dto.pname }<br> <b>${dto.price }</b>
							</p>
						</div>
					</c:forEach>
				</div>
  ${paging}
</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
