<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="util" uri="/ELFunctions" %>
 
<!DOCTYPE html> 
<html> 
<head>
  <title>장바구니 목록</title>
  <meta charset="utf-8">
 
   <script type="text/javascript">
     
     function order(){
    	var corder = document.getElementById("corder");
    	
    	for(i=0; i<corder.length; i++){
    		console.log("hi");
    	}
    	
    	//console.log("hi");
    	
		//var url = "/cart/orderForm";
		//url += "?cartno=${cartno}";
		
    	//location.href=url;
     }
     
     
     
     
  </script>
 
</head>
<body>
<div class="container">
 
   <h2>장바구니 목록</h2>
   <form method="post" action="/cart/orderForm">
	<c:choose>
    <c:when test="${map.count == 0 }">
        장바구니가 비었습니다.
    </c:when>
    
    <c:otherwise>
  
  <table class="table table-striped">
   <thead>
    <tr>
    	<th style="font-size:20%"><input type="checkbox" name="allCheck" id="allCheck"> 전체 선택</th>
    	<th>상품명</th>
    	<th>수량</th>
    	<th>가격</th>
    	<th>총액</th>
    	<th></th>
    </tr>
   </thead>
   <tbody>
 
<c:choose>   
	<c:when test="${empty list}">
   		<tr><td colspan="6">장바구니가 비었습니다.</td>
	</c:when>
	<c:otherwise>
  
		<c:forEach var="dto" items="${list}"> 
			<tr>
				<td><input type="checkbox" id="corder" name="corder" value="${dto.cartno}"></td>
   				<td><a href="/contents/detail/${dto.contentsno }">${dto.pname}</a></td>
    			<td>${dto.amount}개</td>
				<td>${dto.price}원</td>
				<td>${dto.total}원</td>
				<td><button type="button" class="btn" onclick="location.href='/cart/delete?cartno=${dto.cartno}'">삭제</button></td>
			</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
 
   </tbody>
  </table>
  <div align="right"></div>
  <div><button>주문</button></div>
  <div>
      ${paging}
  </div>
  </c:otherwise>
  </c:choose>
  </form>
</div>
</body> 
</html> 