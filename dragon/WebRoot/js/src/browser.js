$(function() {
	showChart();
	$("#channelid").on("change", function() {
		showChart();
	});
	$("#datedeff").on("change", function() {
		showChart();
	});
	
	$( "#startdate" ).datepicker();
});

function showChart() {
	var channelid = $("#channelid").val();
	var datedeff = $("#datedeff").val();
	$.ajax( {
		url : ctx + "/manage/ajax/hplChart.xhtml",
		type : "POST",
		data : "channelid=" + channelid + "&datedeff=" + datedeff,
		dataType : "json",
		success : function(result) {
			var title = "驱动统计"
			$('#chartDialog').highcharts( {
				chart:{
					defaultSeriesType:'spline'
				},
				title : {
					text : title,
					x : -20
				},
				credits :{
					enabled:false
				},
				xAxis : {
					categories : result.createdate
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
				series : [ {
					name : 'xp加载量',
					data : result.xpnum
				}, {
					name : 'win732加载量',
					data : result.win732num
				}, {
					name : '其他加载量',
					data : result.othernum
				}, {
					name : '总加载量',
					data : result.total
				} ]
			});
		}
	});
}
