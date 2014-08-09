$(function() {
	$("#editDialog").dialog({
		width : 600,
		height : 380,
		title : "配置信息",
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
				required : true,
			},
			content : {
				required : true,
			}
		},
		messages : {
			name : {
				required : "请输入配置名称",
			},
			content : {
				required : "请输入配置内容",
			}
		}
	});

	$("#addButton").on("click", function() {
		$("#editForm").resetForm();
		$("#id").val("");
		$("#editForm").attr("action", ctx + "/manage/ajax/saveConfigini.xhtml");
		$("#editDialog").dialog("open", {
			title : "编辑渠道"
		});
	});
	
	$("#clean").on("click", function() {
		parent.clean("tConfigini");
	});
	$("#cleanAll").on("click", function() {
		parent.clean("all");
	});
	
	$("#assignDialog").dialog({
		width : 550,
		height : 500,
		title : "分配渠道",
		resizable : false,
		autoOpen : false,
		position : {
			my : "center",
			at : "center",
			of : window
		},
		modal : true,
		buttons : [{
			text : '关闭',
			click : function() {
				$("#assignDialog").dialog("close");
				parent.reflushIframe();
			}
		} ]
	});
	
	$("input[name='channelid']").on("click", function() {
		var channelid = $(this).val();
		var id = $("#configid").val();
		var checked = $(this).prop("checked");
		assign(id,channelid,checked);
	});
});

function editClick(obj) {
	$("#editForm").resetForm();
	$("#id").val("");
	$("#editForm").attr("action", ctx + "/manage/ajax/updateConfigini.xhtml");
	$("#editForm").jsonToForm(obj);
	$("#editDialog").dialog("open", {
		title : "编辑渠道"
	});
}

function assignClick(id,obj) {
	$("#assignForm").resetForm();
	$("#configid").val(id);
	var arr = mapsToArr(obj);
	$(arr).each(function(index,json){
		if(json!=null && json!=""){
			$("#assignForm").jsonToForm(json);
		}
	});
	$("#assignDialog").dialog("open", {
		title : "编辑渠道"
	});
}

function deleteClick(id,name) {
	if(confirm("确认要删除配置["+name+"]?")){
		$.ajax({
			async:false,
			cache:false,
			url : ctx + "/manage/ajax/delConfigini.xhtml",
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

function assign(id,channelid,checked) {
	$.ajax({
		async:false,
		cache:false,
		url : ctx + "/manage/ajax/assignConfigini.xhtml",
		type:"POST",
		data : "id="+id+"&channelid="+channelid+"&checked="+checked,
		dataType:"json",
		success : function(data) {
		}
	});
}