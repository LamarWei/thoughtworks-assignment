<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ThoughtWorks Assignment Demo</title>
<script src="resources/js/jquery.min.js"></script>
</head>
<body>
<div id="message">
<c:if test="${error ne null }">
	<h3>${error }</h3>
</c:if>
</div>
<c:forEach var="item" items="${items }">
编号：${item.getItemSerial() }，名称：${item.getItemName() }，单价：${item.getPrice() }（元/${item.getUnit() }），类别：${item.getItemCategory() }
<c:if test="${item.getDiscount() ne 'BUYTWOGETONEFREE' }" >
	<input id="${item.getItemSerial() }" class="discount_btn_1" type="button" value="买二赠一" />
</c:if>
<c:if test="${item.getDiscount() ne 'FIVEPERCENTOFF' }" >
	<input id="${item.getItemSerial() }" class="discount_btn_2" type="button" value="95折" />
</c:if>
<c:if test="${item.getDiscount() ne 'ALLSALE' }" >
	<input id="${item.getItemSerial() }" class="discount_btn_3" type="button" value="95折和买二赠一同时参加" />
</c:if>
<c:if test="${item.getDiscount() ne 'NODISCOUNT' }" >
	<input id="${item.getItemSerial() }" class="discount_btn_0" type="button" value="不优惠" />
</c:if>
<br>
</c:forEach>
<form action="" method="post">
<div>
	<label>请输入数据：</label>
	<dir>
		<textarea rows="4" cols="60" name="itemStr" >'ITEM0000001','ITEM0000001','ITEM0000001','ITEM0000001','ITEM0000001','ITEM0000012-2','ITEM0000005','ITEM0000005','ITEM0000005'</textarea>
	</dir>
</div>
<button type="submit" >打印</button>
</form>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$(".discount_btn_1,.discount_btn_2,.discount_btn_0,.discount_btn_3").on("click",function(){
		var id = this.id;
		var discount = getDiscount(this.className.split('_')[2]);
		
		$.ajax({
			async:false,
			type:"put",
			url:"discount",
			data:new Object({
				id:id,
				discount:discount
			}),
			dataType:"json",
			success:function(data){
				if(data.status == 'success'){
					window.location.reload();
				}else{
					$("#message").html("<h3>"+data.error+"</h3>");
				}
			}
		});
	});
	function getDiscount(obj){
		switch (obj) {
		case "1":
			return "buytwogetonefree";
			break;
		case "2":
			return "fivepercentoff";
			break;
		case "3":
			return "allsale";
			break;
		case "0":
			return "nosale";
			break;
	}
	}
});

</script>
</html>