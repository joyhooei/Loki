$(function() {
	$("#editDialog").dialog({
		width : 600,
		height : 480,
		title : "渠道信息",
		resizable : false,
		autoOpen : false,
		position : {
			my : "center",
			at : "center",
			of : window
		},
		modal : true,
		buttons : [ {
			text : "保存",
			click : function() {
				if (!$("#editForm").validate().form()) {
					return;
				}
				$("#editForm")._ajaxSubmit(1);
			}
		}, {
			text : '关闭',
			click : function() {
				$("#editDialog").dialog("close");
			}
		} ]
	});

	$("#editForm").validate({
		rules : {
			name : {
				required : true
			},
			userid : {
				required : true
			},
			deduct:{
				required : true,
				number:true,
				range:[0,100]
			},
			startnum:{
				required : true,
				digits:true
			},
			price:{
				required : true,
				number:true,
				min:0.1
			}
		},
		messages : {
			name : {
				required : "请输名称"
			},
			userid : {
				required : "请选择用户"
			},
			deduct:{
				required : "请输入扣量比，不扣量则输入0",
				number: "请输入合法的数字(整数，小数)",
				range : jQuery.format("扣量比必须介于{0}和{1}之间"),
			},
			startnum:{
				required : "请输入起始值",
				digits: "请输入整数"
			},
			price:{
				required : "请输入单价",
				number: "请输入合法的数字(整数，小数)",
				min : "单价必须大于{0}"
			}
		}
	});

	$("#addButton").on("click", function() {
		$("#editForm").resetForm();
		$("#id").val("");
		$("#editForm").attr("action", ctx + "/manage/ajax/saveChannel.xhtml");
		$("#editDialog").dialog("open", {
			title : "编辑渠道"
		});
	});
});

function editClick(obj) {
	$("#editForm").resetForm();
	$("#id").val("");
	$("#editForm").attr("action", ctx + "/manage/ajax/updateChannel.xhtml");
	$("#editForm").jsonToForm(obj);
	$("#editDialog").dialog("open", {
		title : "编辑渠道"
	});
}

function deleteClick(id,name) {
	if(confirm("确认要删除渠道["+name+"]?")){
		$.ajax({
			async:false,
			cache:false,
			url : ctx + "/manage/ajax/delChannel.xhtml",
			type:"POST",
			data : "id="+id+"&name="+name,
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
}

function changeEnableClick(id,enable,name) {
	$.ajax({
		async:false,
		cache:false,
		url : ctx + "/manage/ajax/changeChannelStatus.xhtml",
		type:"POST",
		data : "id="+id+"&status="+enable+"&name="+name,
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