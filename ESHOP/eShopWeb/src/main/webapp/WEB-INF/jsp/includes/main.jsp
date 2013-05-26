<%@ include file="taglibs.jsp"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<html>
	<head>
  		<script language="javascript" src="js/jquery-1.9.1.js"></script>
  		<script language="javascript" src="js/jquery-ui.js"></script>
  		<script language="javascript" src="js/jquery.tablescroll.js"></script>
  		<script language="javascript" src="js/jquery.date_input.js"></script>
  		<script language="javascript" src="js/iquery_insurance.js"></script>
  		<link rel="stylesheet" type="text/css" href="css/style.css"/>
  		<link rel="stylesheet" type="text/css" href="css/jquery.tablescroll.css"/>
  		<link rel="stylesheet" type="text/css" href="css/jquery-ui.css"/>
  		<link rel="stylesheet" type="text/css" href="css/date_input.css"/>
  		
		<title><decorator:title default='ADaaS ESHOP'/>
	  	</title>
	</head>  	
	<body id="main">
		<div id="outerDiv">	
			<%@ include file="header.jsp"%>	
			<%@ include file="menu.jsp" %>
			<%@ include file="messages.jsp" %>
			<decorator:body/>
		</div>
			<%@ include file="footer.jsp" %>
  	</body>
</html>