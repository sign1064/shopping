<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="util" uri="/ELFunctions"%>

<!DOCTYPE html>
<html>
<head>
<title>장바구니 목록</title>
<meta charset="utf-8">

<script>
	function order() {
		alert("상품이 주문되었습니다. 결제 기능은 준비 중입니다.");
	}
</script>

</head>
<body>
	<div class="container">

		<h2>장바구니 목록</h2>
		<form>
			<c:choose>
				<c:when test="${map.count == 0 }">
        장바구니가 비었습니다.
    </c:when>

				<c:otherwise>

					<table class="table table-striped">
						<thead>
							<tr>
								<th style="text-align: center;">상품명</th>
								<th>수량</th>
								<th>가격</th>
								<th>총액</th>
								<th>삭제</th>
								<th>주문</th>
							</tr>
						</thead>
						<tbody>

							<c:choose>
								<c:when test="${empty list}">
									<tr>
										<td colspan="6">장바구니가 비었습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>

									<c:forEach var="dto" items="${list}">
										<tr>
											<td style="text-align: center;"><a
												href="/contents/detail/${dto.contentsno }">${dto.pname}</a></td>
											<td>${dto.amount}개</td>
											<td>${dto.price}원</td>
											<td>${dto.total}원</td>
											<td><button type="button" class="btn"
													onclick="location.href='/cart/delete?cartno=${dto.cartno}'">삭제</button></td>
											<td><button type="button" class="btn" onclick="order()">주문</button></td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>

						</tbody>
					</table>
					<div>${paging}</div>
				</c:otherwise>
			</c:choose>
		</form>
	</div>
</body>
</html>
