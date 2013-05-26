beneficiaryLoadErrMsg = "";
policyLoadErrMsg = "";
claimsLoadErrMsg = "";
serverErr = "";
selectRecordForDelete = "";
comma = ", ";

$(document).ready(function() {
	beneficiaryLoadErrMsg = $("#beneficiaryLoadErrMsg").val();
	policyLoadErrMsg = $("#policyLoadErrMsg").val();
	claimsLoadErrMsg = $("#claimsLoadErrMsg").val();
	serverErr = $("#serverErr").val();
	selectRecordForDelete = $("#selectRecordForDelete").val();
	
	$(".result_odd").hover(function() {
		$(this).removeClass("result_odd");
		$(this).addClass("result_odd_hover");
	},function() {
		$(this).removeClass("result_odd_hover");
		$(this).addClass("result_odd");
	});

	$(".result_even").hover(function() {
		$(this).removeClass("result_even");
		$(this).addClass("result_even_hover");
	},function() {
		$(this).removeClass("result_even_hover");
		$(this).addClass("result_even");
	});
	
	$("#custTable tbody tr").click(function() {
		$("#custTable tbody").children().removeClass('highlight');
		$(this).addClass('highlight'); 
	});
	
	$("#beneTable tbody tr").click(function() {
		$("#beneTable tbody").children().removeClass('highlight');
		$(this).addClass('highlight'); 
	});
	
	$("#claimsTable tbody tr").click(function() {
		$("#claimsTable tbody").children().removeClass('highlight');
		$(this).addClass('highlight'); 
	});
	
	//$("#deleteButton").attr("disabled", "disabled");
	
	$(".cb-element").click(function () {
		if($( '.cb-element:checked' ).length > 0) {
			$("#deleteButton").attr("disabled", "");
		} else {
			$("#deleteButton").attr("disabled", "disabled");
		}
	});
});

function isRecordSelectedForDelete() {	
	if($( '.cb-element:checked' ).length > 0) {
		return true;
	}
	return false;
}

function unCheck(){
	$("INPUT[type='checkbox']").attr('checked',false);
}

function doDelete(form,action,confirmMsg) {
	if (isRecordSelectedForDelete()) {
		if (confirm(confirmMsg)) {
			doSubmit(form,action);
		} else {
			$('#deleteButton').attr('disabled', 'disabled');
			unCheck();
		}

	} else {
		alert(selectRecordForDelete);
		$("input[type=button]").attr("disabled", "");

	}   
}

function doSubmit(form,action) {
	var form = "#" + form;
	$(form).attr("action",action);
	$(form).submit();
}

