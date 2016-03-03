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
名称：${soldItem.getItemName() }，数量：${soldItem.getQuantity() } ${soldItem.getUnit() }，单价：${soldItem.getPrice() }（元）
<c:choose>
	<c:when test="${soldItem.getDiscount() eq 2 or (soldItem.getDiscount() eq 3 and soldItem.getQuantity() lt 3)}">
	，小计：${soldItem.getSub_total() }（元），节省：${soldItem.getSavings() }（元）
	</c:when>
	<c:otherwise>
	，小计：${soldItem.getSub_total() }（元）
	</c:otherwise>
</c:choose>
<br>
</c:forEach>
-------------------------------------
<c:if test="${receipt.isShowFreeItemList() }">
<br>
买二赠一商品：
<br>
<c:forEach var="freeItem" items="${receipt.getFreeItemList() }">
名称：${freeItem.getItemName() }，数量：${freeItem.getQuantity() }${freeItem.getUnit() }<br>
</c:forEach>
-------------------------------------
</c:if>
<br>
总计：${receipt.getTotal() }（元）<br>
<c:if test="${receipt.getTotal_savings() gt 0 }">
节省：${receipt.getTotal_savings() }（元）<br>
</c:if>
*************************************
</body>
</html>