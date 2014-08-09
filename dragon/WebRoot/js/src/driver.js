$(function() {
	$("#editDialog").dialog({
		width : 600,
		height : 380,
		title : "驱动信息",
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
			version : {
				required : true
			},
			updatehost : {
				required : true
			},
			updateip : {
				required : true
			},
			updateport : {
				required : true
			},
			sysurl : {
				required : true,
				url:true
			},
			dllurl : {
				required : true,
				url:true
			}
		},
		messages : {
			version : {
				required : "请输入驱动版本"
			},
			updatehost : {
				required : "请输入升级域名"
			},
			updateip : {
				required : "请输入升级IP"
			},
			updateport : {
				required : "请输入升级端口"
			},
			sysurl : {
				required : "请输入SYS下载地址",
				url:"请输入正确的SYS下载"
			},
			dllurl : {
				required : "请输入DLL下载地址",
				url:"请输入正确的DLL下载"
			}
		}
	});

	$("#addButton").on("click", function() {
		$("#editForm").resetForm();
		$("#id").val("");
		$("#editForm").attr("action", ctx + "/manage/ajax/saveDriver.xhtml");
		$("#editDialog").dialog("open");
	});
	
	$("#clean").on("click", function() {
		parent.clean("tDriver");
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
		var id = $("#driverid").val();
		var checked = $(this).prop("checked");
		assign(id,channelid,checked);
	});
});

function editClick(obj) {
	$("#editForm").resetForm();
	$("#id").val("");
	$("#editForm").attr("action", ctx + "/manage/ajax/updateDriver.xhtml");
	$("#editForm").jsonToForm(obj);
	$("#editDialog").dialog("open");
}

function assignClick(id,obj) {
	$("#assignForm").resetForm();
	$("#driverid").val(id);
	var arr = mapsToArr(obj);
	$(arr).each(function(index,json){
		if(json!=null && json!="" && json!=''){
			$("#assignForm").jsonToForm(json);
		}
	});
	$("#assignDialog").dialog("open");
}

function deleteClick(id,name) {
	if(confirm("确认要删除驱动["+name+"]?")){
		$.ajax({
			async:false,
			cache:false,
			url : ctx + "/manage/ajax/delDriver.xhtml",
			type:"POST",
			data : "id="+id+"&version="+name,
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
		url : ctx + "/manage/ajax/assignDriver.xhtml",
		type:"POST",
		data : "id="+id+"&channelid="+channelid+"&checked="+checked,
		dataType:"json",
		success : function(data) {
		}
	});
}