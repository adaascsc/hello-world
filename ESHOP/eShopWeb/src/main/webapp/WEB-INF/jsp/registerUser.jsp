<%@ include file="includes/taglibs.jsp"%>
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
	<h2><spring:message code="csc.appl.name"/></h2>
</div>
		
<div id="dialogReg">
	</br>
	</br>
	
	</br>
	<form:form id="registrationForm" name="registrationForm" commandName="registerUserObj" method="POST" action="registerNewUser.htm">
		<%@ include file="includes/messages.jsp" %>
		<div id="dialog">
			<fieldset>
				<spring:hasBindErrors name="registerUserObj">
					<%@ include file="includes/messages.jsp" %>
				</spring:hasBindErrors>
				
					<table width="60%" border="0" cellspacing="0" cellpadding="1" align="center">
					<tr>
						<td class="labelHeads" colspan="4"><spring:message code="label.registration.page.title"/></td>
							
					</tr>
					<tr class="even">
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					<tr class="odd">
						<td class="labelNames_left">
							<spring:message code="label.registration.username"/>
						</td>
						<td class="labelNames">
							<form:input path="username" cssClass="textfield_200" />
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					<tr class="even">
						<td class="labelNames_left"><spring:message code="label.registration.password"/></td>
						<td class="labelNames">
							<form:password path="password" cssClass="textfield_200"/>
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					<tr class="odd">
						<td class="labelNames_left"><spring:message code="label.registration.confirmpassword"/></td>
						<td class="labelNames">
							<form:password path="confirmPassword" cssClass="textfield_200"/>
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					<tr class="even">
						<td class="labelNames_left"><spring:message code="label.registration.firstname"/></td>
						<td class="labelNames">
							<form:input path="firstname" cssClass="textfield_200"/>
							
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					<tr class="odd">
						<td class="labelNames_left"><spring:message code="label.registration.lastname"/></td>
						<td class="labelNames">
							<form:input path="lastname" cssClass="textfield_200"/>
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					
									
					<tr class="even">
						<td class="labelNames_left"><spring:message code="label.registration.addr1"/></td>
						<td class="labelNames">
						<form:input path="addr1" cssClass="textfield_200"/>
						
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>  
					<tr class="odd">
						<td class="labelNames_left"><spring:message code="label.registration.addr2"/></td>
						<td class="labelNames">
							<form:input path="addr2" cssClass="textfield_200"/>
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					
					<tr class="even">
						<td class="labelNames_left"><spring:message code="label.registration.city"/></td>
						<td class="labelNames">
							<form:input path="city" cssClass="textfield_200"/>
						
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>  
					<tr class="odd">
						<td class="labelNames_left"><spring:message code="label.registration.state"/></td>
						<td class="labelNames">
							
							<form:select path="state" cssClass="dropdown_150">
								<form:options items="${stateMap}"/>
							</form:select>
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					
					<tr class="even">
						<td class="labelNames_left"><spring:message code="label.registration.zip"/></td>
						<td class="labelNames">
							<form:input path="zip" cssClass="textfield_200"/>
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>  
					<tr class="odd">
						<td class="labelNames_left"><spring:message code="label.registration.country"/></td>
						<td class="labelNames">
							<form:input path="country" cssClass="textfield_200"/>
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>
					
					<tr class="even">
						<td class="labelNames_left"><spring:message code="label.registration.phone"/></td>
						<td class="labelNames">
							<form:input path="phone" cssClass="textfield_200"/>
						</td>
						<td class="labelNames">&nbsp;</td>
						<td class="labelNames">&nbsp;</td>
					</tr>  
				</table>
				<ul id="buttons">
					<div id="createCustomerButtonContainer">
						<input type="submit" id="registerUserButton" value="<spring:message code='label.button.registration.registeruser'/>"/>
						<a href="<c:url value='login.htm'/>">Login to EShop</a>
					</div>
															
				</ul>
			</fieldset>
			
		</div>
	</form:form>
	<%@ include file="includes/footer.jsp" %>
</div>
	
</body>
