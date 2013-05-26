<%@ include file="taglibs.jsp"%>
<title>
  <spring:message code="tpa.access.denied.title" />
</title>
<body>
<div id="accessDenied"  class="error">
	<c:if test="${not empty username}">
		<spring:message code="tpa.label.user"/> <c:out value="${username}" />
		</br>
	</c:if>
	<spring:message code="tpa.error.access.denied"/>
</div>
</body>