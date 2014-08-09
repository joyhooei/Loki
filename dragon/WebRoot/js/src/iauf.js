var show = 0;
$(function() {
	$("#chartDiv #channelid").on("change", function() {
		showChart();
	});
	$("#chartDiv #datedeff").on("change", function() {
		showChart();
	});
	
	$("#showButton").on("click", function() {
		if(show==0){
			showChart();
		}
		$("#showButton").hide();
		$("#hideButton").show();
		$("#chartDiv").show();
	});
	$("#hideButton").on("click", function() {
		$("#chartDiv").hide();
		$("#hideButton").hide();
		$("#showButton").show();
	});
});

function showChart(){
	var channelid = $("#chartDiv #channelid").val();
	var datedeff = $("#chartDiv #datedeff").val();
	$.ajax({
		url : ctx + "/crm/ajax/iauChart.xhtml",
		type:"POST",
		data : "channelid="+channelid+"&datedeff="+datedeff,
		dataType:"json",
		beforeSend:function(){
			$('#chartDialog').html("<font color=\"red\" size=\"5\">拼命加载中……</font>");
		},
		success : function(result) {
			show = 1;
			var title = "安装总量:"+result.total;
			$('#chartDialog').highcharts({
		        chart:{
					defaultSeriesType:'spline'
				},
				title : {
					text : '安装统计',
					x : -20
				},
				subtitle: {
					text : title,
					x : -20
				},
				credits :{
					enabled:false
				},
		        xAxis: {
		            categories: result.createdate
		        },
		        yAxis : {
		        	title : {
						text : ''
					},
					min:0
				},
				tooltip : {
					crosshairs : true,
					shared : true
				},
		        series: [{
		            name: '安装量',
		            data: result.installnum
		        }]
		    });
		}
	});
}
