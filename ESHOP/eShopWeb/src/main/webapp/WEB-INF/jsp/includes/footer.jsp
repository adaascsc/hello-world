<div id="footer">
	<jsp:useBean id="now" class="java.util.Date" scope="page" /> 
	<fmt:formatDate value='${now}' pattern='yyyy' var="currentDate"/>
	<fmt:message key="csc.copyright">
		<fmt:param value="${currentDate}"/>
	</fmt:message>
	
</div>