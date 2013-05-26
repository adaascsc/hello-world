<div id="pageHeader" align="center">
	<img align="left" src="images/eshop_logo.gif"/><h2><spring:message code="csc.appl.name"/></h2>
</div>
<div id="subHeader">
	<span id="user">
		<c:if test="${not empty firstname}">
			<spring:message code="tpa.label.signed.in"/><c:out value="${firstname}" />&nbsp;|&nbsp;
				<c:if test="${not empty lastAccessed}">
				&nbsp;|&nbsp;
				<spring:message code="tpa.label.last.accessed.time"/>
				<fmt:timeZone value="Asia/Calcutta">
					<fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${lastAccessed}" />
				</fmt:timeZone>
			</c:if>
		</c:if>
	</span>
	<a id="logout" href=<c:url value="/j_spring_security_logout"/>><spring:message code="tpa.label.logout"/></a>
</div>