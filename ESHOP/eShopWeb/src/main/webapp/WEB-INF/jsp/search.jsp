<script type="text/javascript">
$(function() {

	
var availableTags = [
${productName}
];

$( "#name" ).autocomplete({
source: availableTags
});
});


</script>


<div class="ui-widget">
<form name="searchform" id="searchform" method="POST" action="searchResult.htm">
<input name="name" id="name"/>
<input type="submit"  value="Search" />
</form>
</div>
	
