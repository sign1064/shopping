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
     function detail(contentsno){
       var url = "/contents/detail";
       url += "/"+contentsno;
       url += "?nowPage=${nowPage}";
       location.href=url;
     }
     
     function order(){
		var url = "/cart/orderForm";
		url += "?cartno=${cartno}";
		
    	location.href=url;
     }
     
     
  </script>
 
</head>
<body>
<div class="container">
 
   <h2>장바구니 목록</h2>
	<c:choose>
    <c:when test="${map.count == 0 }">
        장바구니가 비었습니다.
    </c:when>
    
    <c:otherwise>
  
  <table class="table table-striped">
   <thead>
    <tr>
    	<th></th>
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
   				<td><a href="javascript:detail('${dto.contentsno}')">${dto.pname}</a></td>
    			<td>${dto.amount}</td>
				<td>${dto.price}</td>
				<td>${dto.total}</td>
				<td><button type="button" class="btn" onclick="location.href='/cart/delete?cartno=${dto.cartno}&contentsno=${dto.contentsno}'">삭제</button></td>
			</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
 
   </tbody>
  </table>
  <div><button type="button" class="btn" onclick="order()">주문</button></div>
  <div>
      ${paging}
  </div>
  </c:otherwise>
  </c:choose>
</div>
</body> 
</html> 