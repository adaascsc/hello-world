<%@ include file="includes/taglibs.jsp"%>
<body align="center">
	<div id="home">
		
		
	</div>
	<div id="Container" align="center">
	
	<table width="100%" border="0" cellspacing="0" cellpadding="1">
				
				
				<tr>
				<c:forEach var="product" items="${productObj}" varStatus="rowCounter">
				<tr>
				<td></td>
				<td></td>
				</tr>
								<tr class="${rowStyle}">
									
									<td>${product.description}</td>
									<td> <ul class="productCat"> <c:out value="${product.name}" /></ul>
									<ul class="productPrice"><c:out value="${product.price}" /></ul>
									<ul class="productCat"> <c:out value="${product.attr1}" /></ul>
									<ul class="productCat"> <c:out value="${product.attr2}" /></ul>
									<ul class="productCat"><spring:message code="tpa.label.product.category"/>: <c:out value="${product.category}" /></ul>
									
									<ul class="button"><input  type="submit" value="<spring:message code='tpa.button.add2c'/>"/></ul>
									</td>
									
																								
								</tr>
						</c:forEach>	
				</tr>
				
		</table>
		
		</div>
		
</body>	


