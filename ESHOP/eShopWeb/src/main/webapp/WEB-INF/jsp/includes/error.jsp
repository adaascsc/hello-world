<%@ include file="taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="css/style.css">
<body>
<div id="errorTitle" class="error">
	<c:if test="${not empty requestScope.instructions}">
		<div id="exceptionMsg" class="line">
			<c:choose>
				<c:when test="${requestScope.instructions == 'contactAdmin'}">
					<spring:message code="application.exception.contact.administrator.referenceHash" arguments="'${errorRefNumber}'"/>
				</c:when>
				<c:when test="${requestScope.instructions == 'dataAccessException'}">
					<spring:message code="application.data.access.exception" arguments="'${errorRefNumber}'"/>
				</c:when>
				<c:when test="${requestScope.instructions == 'staleConnection'}">
					<spring:message code="application.stale.connection.exception.msg" arguments="'${errorRefNumber}'"/>
				</c:when>
			</c:choose>
		</div>
	</c:if>
</div>
<br>
</body>