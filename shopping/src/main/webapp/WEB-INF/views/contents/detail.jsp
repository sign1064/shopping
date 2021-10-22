<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>상품 상세 조회</title>
<meta charset="utf-8">

<script type="text/javascript">
	function updateM() {
		var url = "/admin/update";
		url += "?contentsno=${dto.contentsno}";
		location.href = url;
	}
	function deleteM() {
		var url = "/admin/delete";
		url += "?contentsno=${dto.contentsno}";
		location.href = url;
	}

	function addCart() {
		var contentsno = ${dto.contentsno};
		var amount = document.getElementById("amount").value;
		
		var url = "/cart/addCart?contentsno=";
		url += contentsno;
		url += "&amount=";
		url += amount;

		location.href = url;
	}
	
</script>

</head>
<body>
	<div class="container">

		<h2>상품 상세페이지</h2>
		
		<img src="/pstorage/${dto.filename }" class="img-thumbnail" width="236" height="306">
		
		<div class="panel panel-default">

			<div class="panel-heading">상품명</div>
			<div class="panel-body">${dto.pname}</div>

			<div class="panel-heading">가격</div>
			<div class="panel-body">${dto.price}</div>

			<div class="panel-heading">상세 내용</div>
			<div class="panel-body">${dto.detail}</div>

			<div class="panel-heading">재고량</div>
			<div class="panel-body">${dto.stock}</div>

		</div>
		<div>
			<c:choose>
    		<c:when test="${not empty sessionScope.id && sessionScope.grade == 'A'}">
			<button type="button" class="btn" onclick="location.href='/admin/create'">등록</button>
			<button type="button" class="btn" onclick="updateM()">수정</button>
			<button type="button" class="btn" onclick="deleteM()">삭제</button>
			</c:when>
			</c:choose>
			<select name="amount" id="amount">
		        <option value="1">1</option>
		        <option value="2">2</option>
		        <option value="3">3</option>
		        <option value="4">4</option>
		        <option value="5">5</option>
		        <option value="6">6</option>
		        <option value="7">7</option>
		        <option value="8">8</option>
		        <option value="9">9</option>
		        <option value="10">10</option>       
		     </select>
			<button type="button" class="btn" onclick="addCart()">장바구니 담기</button>
			<button type="button" class="btn" onclick="history.back()">목록</button>
		</div>
	</div>
</body>
</html>