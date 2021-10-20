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
  </script>
 
</head>
<body>
<div class="container">
 
   <h2>장바구니 목록</h2>

  
  <table class="table table-striped">
   <thead>
    <tr>
    	<th>상품명</th>
    	<th>수량</th>
    	<th>가격</th>
    	<th>총액</th>
    </tr>
   </thead>
   <tbody>
 
<c:choose>   
	<c:when test="${empty list}">
   		<tr><td colspan="6">등록된 글이 없습니다.</td>
	</c:when>
	<c:otherwise>
  
		<c:forEach var="dto" items="${list}"> 
			<tr>
   				<td><a href="javascript:detail('${dto.contentsno}')">${dto.pname}</a></td>
    			<td>${dto.amount}</td>
				<td>${dto.price}</td>
				<td>${dto.total}</td>
			</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
 
   </tbody>
  </table>
  <div>
      ${paging}
  </div>
</div>
</body> 
</html> 