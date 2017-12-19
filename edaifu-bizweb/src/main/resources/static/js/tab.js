$(function(){
	$('#b1').click(function(){
		$obj = $('#unselected option:selected').clone(true);
		if($obj.size() == 0){
			alert("至少选择一条记录!");
		}
		$('#selected').append($obj);
		$('#unselected option:selected').remove();
	});
	
	$('#b2').click(function(){
		$('#selected').append($('#unselected option'));
	});
	
	$('#b3').click(function(){
		$obj = $('#selected option:selected').clone(true);
		if($obj.size() == 0){
			alert("至少选择一条记录!");
		}
		$('#unselected').append($obj);
		$('#selected option:selected').remove();
	});
	
	$('#b4').click(function(){
		$('#unselected').append($('#selected option'));
	});
	
	$('#multiple_select').on("dblclick","option",function(){
		var flag = $(this).parent().attr('id');
		if(flag == "unselected"){
			var $obj = $(this).clone(true);
			$('#selected').append($obj);
			$(this).remove();
		} else {
			var $obj = $(this).clone(true);
			$('#unselected').append($obj);
			$(this).remove();
		}
	});
	
});