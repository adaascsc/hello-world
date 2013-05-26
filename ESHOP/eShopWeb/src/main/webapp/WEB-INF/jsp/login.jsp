<%@ include file="includes/taglibs.jsp"%>
<html>
<head>
	<title><spring:message code="tpa.login.title"/></title>
	<script language="javascript" src="js/jquery-1.4.2.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
<div id="pageHeader">
	<img align="left" src ="images/eshop_logo.gif"/><h2><font color="#0A273D"> <spring:message code="csc.appl.name"/></font></h2>
</div>
</br>
</br>
</br>
</br>
</br>
</br>
<c:if test="${not empty logout}">
	<div id="signout" class="signout">
		<spring:message code="tpa.logout.msg"/>
	</div>
</c:if>

<c:if test="${not empty loginFailed}">
	<div id="loginFailed" class="error">
		<spring:message code="tpa.login.failed"/>
		<c:choose>
			
			<c:when test="${error == 'UsrNotActive'}">
				<spring:message code="tpa.user.not.active"/>
			</c:when>
			
			<c:when test="${error == 'lockedException'}">
				<spring:message code="tpa.user.locked"/>
			</c:when>
			<c:when test="${error == 'BadCredentials'}">
				<spring:message code="tpa.bad.crendentials"/>	
			</c:when>
			<c:when test="${error == 'UsrNotFound'}">
				<spring:message code="tpa.user.not.found"/>	
			</c:when>
			<c:when test="${error == 'credentialExpired'}">
				<spring:message code="tpa.bad.credential.expired"/>	
			</c:when>
		</c:choose>
	</div>
</c:if>
<div class="dialog">
	<span class="dialogHeader"><spring:message code="tpa.login.message"/></span>
	<form:form id="loginForm" name="loginForm" method="POST" action="j_spring_security_check">
		<p> 
			<label for="j_username"><spring:message code="tpa.username"/></label>
			<input id="j_username" name="j_username" type="text" 
					value="<c:out value='${SPRING_SECURITY_LAST_USERNAME}'/>" />
		</p>
		<p> 
			<label for="j_password"><spring:message code="tpa.password"/></label>
			<input id="j_password" name="j_password" type="password" />
		</p>
		<input  type="submit" value="<spring:message code='tpa.button.login'/>"/>
	</form:form>
	
</div>
<a href="<c:url value='registerUser.htm'/>">New User? SignUP</a>
<br/>
<br/>
<br/>
<div align="center">
<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>
<script>
new TWTR.Widget({
  version: 2,
  type: 'profile',
  rpp: 4,
  interval: 30000,
  width: 250,
  height: 300,
  theme: {
    shell: {
      background: '#0A273D',
      color: '#ffffff'
    },
    tweets: {
      background: '#f5f5f5',
      color: '#0A273D',
      links: '#f50a0a'
    }
  },
  features: {
    scrollbar: false,
    loop: false,
    live: true,
    behavior: 'all'
  }
}).render().setUser('cscnews').start();
</script>
<a href="https://twitter.com/CSCNews" class="twitter-follow-button" data-show-count="false">Follow @CSCNews</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
</div>

</body>
</html>