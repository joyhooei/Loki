var show = 0;
$(function() {
	//showChart();
	$("#chartDiv #userid").on("change", function() {
		showChart();
	});
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
	$("tspan").attr("width","30px");
});

function showChart(){
	var userid = $("#chartDiv #userid").val();
	var channelid = $("#chartDiv #channelid").val();
	var datedeff = $("#chartDiv #datedeff").val();
	$.ajax({
		url : ctx + "/manage/ajax/iauChart.xhtml",
		type:"POST",
		data : "userid="+userid+"&channelid="+channelid+"&datedeff="+datedeff,
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
		        },
		        {
		            name: '活跃量',
		            data: result.activenum
		        },
		        {
		            name: '卸载量',
		            data: result.uninstallnum
		        },
		        {
		            name: '下载量',
		            data: result.driverdown
		        },
		        {
		            name: 'xp安装量',
		            data: result.xpnum
		        },
		        {
		        	name: 'win732安装量',
		        	data: result.win732num 
		        },
		        {
		        	name: 'win764安装量',
		        	data: result.win764num 
		        },
		        {
		        	name: 'win8安装量',
		        	data: result.win8num 
		        },
		        {
		        	name: '其他安装量',
		        	data: result.othernum 
		        }]
		    });
		}
	});
}
