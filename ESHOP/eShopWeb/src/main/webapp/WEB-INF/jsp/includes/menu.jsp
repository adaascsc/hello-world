<script type="text/javascript">
$(document).ready(function() {

	$(function() {
		$('#coolMenu').find('> li').hover(function() {
			$(this).find('ul').removeClass('noJS').stop(true, true).slideToggle('slow');
		});
	});

});
</script>
<div id="menu" >
<ul id="coolMenu"> 
	<li><a href="<c:url value='home.htm'/>">Home</a></li>
	<li>
		<a href="#">Categories</a>     
		<ul class="noJS">
			<li><a href="<c:url value='books.htm'/>">Books</a></li>
			<li><a href="<c:url value='music.htm'/>">Music&Movies</a></li>
			<li><a href="<c:url value='mobile.htm'/>">Mobiles</a></li>
			<li><a href="<c:url value='sunglass.htm'/>">Sunglasses</a></li>
			<li><a href="<c:url value='watch.htm'/>">Watches</a></li>
			<li><a href="<c:url value='camera.htm'/>">Cameras</a></li>
			
		</ul> 
	</li>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li class=""><%@ include file="../search.jsp"%></li>
</ul>
</div>