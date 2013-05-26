<%-- Error Messages only for Mandatory values from server side--%>
<c:if test="${(not empty errors.fieldErrors) || (not empty errors.globalErrors)}">
	<div id="errorMessages"  class="error">
		<c:if test="${not empty errors.fieldErrors}">
			<c:forEach items="${errors.fieldErrors}" var="error"> 
				<div class="left">
   					<spring:message code="${error.code}" arguments="${error.arguments}"/>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${not empty errors.globalErrors}">
			<c:forEach items="${errors.globalErrors}" var="error">   
				<div class="left">
					<spring:message code="${error.code}" arguments="${error.arguments}"/>
				</div>
  			</c:forEach>
  		</c:if>
  	</div>
</c:if>
<c:if test="${not empty infoMsg}">
	<div id="successMessages" class="success">
		<spring:message code="${infoMsg.key}" arguments="${infoMsg.msgValues}"/>
	</div>
</c:if>
	
