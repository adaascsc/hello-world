<%@ include file="includes/taglibsMobile.jsp"%>
<head>
<title>  <spring:message code="label.registration.page.title" /></title>
  		
  		<link rel="stylesheet" type="text/css" href="css/style.css"/>
  		<link rel="stylesheet" type="text/css" href="css/jquery.tablescroll.css"/>
  		<script language="javascript" src="js/jquery-1.4.2.js"/>
  		<script type="text/javascript">$($.date_input.initialize);</script>
<script language="JavaScript" type="text/javascript">
	$(document).ready(function() {
		
		$("#registerUserButton").click(function() {
			doSubmit('registrationForm', 'registerNewUser.htm');
		});
					
		
	});
</script>
  		
 </head>

<body>
<div id="pageHeader">
	<h2><spring:message code="tpa.application.name"/></h2>
</div>
		
<div id="container">
	</br>
	</br>
	
	</br>
	<form:form id="registrationForm" name="registrationForm" commandName="registerUserObj" method="POST" action="registerNewUser.htm">
		<%@ include file="includes/messages.jsp" %>
		<div id="registerUserContainer">
			<fieldset>
				<spring:hasBindErrors name="registerUserObj">
					<%@ include file="includes/messages.jsp" %>
				</spring:hasBindErrors>
					<table width="100%" border="0" cellspacing="0" cellpadding="1">
					<tr>
						<td class="labelHeads" colspan="4"><spring:message code="label.registration.page.title"/></td>
							
					</tr>
					<tr class="even">
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					<tr class="odd">
						<td class="labelNames_left">
							<spring:message code="label.registration.userid"/>
						</td>
						<td class="labelNames">
							<form:input path="userId" cssClass="textfield_100" />
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					<tr class="even">
						<td class="labelNames_left"><spring:message code="label.registration.password"/></td>
						<td class="labelNames">
							<form:password path="password" cssClass="textfield_100"/>
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					<tr class="odd">
						<td class="labelNames_left"><spring:message code="label.registration.confirmpassword"/></td>
						<td class="labelNames">
							<form:password path="confirmPassword" cssClass="textfield_100"/>
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					<tr class="even">
						<td class="labelNames_left"><spring:message code="label.registration.language"/></td>
						<td class="labelNames">
							<form:select path="language" cssClass="dropdown_90">
								<form:options items="${languageMap}"/>
							</form:select>
							
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					<tr class="odd">
						<td class="labelNames_left"><spring:message code="label.registration.displayName"/></td>
						<td class="labelNames">
							<form:input path="displayName" cssClass="textfield_100"/>
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					
					<tr class="even">
						<td class="labelNames_left"><spring:message code="label.registration.tenantName"/></td>
						<td class="labelNames">
						<form:select path="tenantDTO.tenantId" cssClass="dropdown_90">
								<form:options items="${tenantMap}"/>
							</form:select>
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>  
					
				</table>
				<ul id="buttons">
					<div id="createCustomerButtonContainer">
						<input type="submit" id="registerUserButton" value="<spring:message code='label.button.registration.registeruser'/>"/>
						<a href="<c:url value='login.htm'/>">Login to TPA</a>
					</div>
															
				</ul>
			</fieldset>
			
		</div>
	</form:form>
	<%@ include file="includes/footer.jsp" %>
</div>
	
</body>
