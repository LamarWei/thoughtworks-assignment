<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Receipt</title>
</head>
<body>
***&lt; ${receipt.getShopName() } &gt;购物清单***
<br>
<c:forEach var="soldItem" items="${receipt.getItems() }">
名称：${soldItem.getItemName() }，数量：${soldItem.getQuantity() }（${soldItem.getUnit() }），单价：${soldItem.getPrice() }（元）
<c:choose>
	<c:when test="${soldItem.getDiscount() eq 2 }">
	，小计：${soldItem.getSub_total() }（元），节省：${soldItem.getSavings() }（元）
	</c:when>
	<c:otherwise>
	，小计：${soldItem.getSub_total() }（元）
	</c:otherwise>
</c:choose>
<br>
</c:forEach>
-------------------------------------
</body>
</html>