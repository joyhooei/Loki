function playCost(id,channelid,createdate){
	$.ajax({
		async:false,
		cache:false,
		url : ctx + "/manage/ajax/playCost.xhtml",
		type:"POST",
		data : "id="+id+"&channelid="+channelid+"&createdate="+createdate,
		dataType:"json",
		success : function(data) {
			var reflush = 0;
			if(1==data.result){
				reflush = 1;
			}
			parent.setMeassge(data.message,reflush);
		}
	});
	parent.showMeassge();
}