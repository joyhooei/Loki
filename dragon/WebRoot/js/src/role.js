$(function() {
	$("#editDialog").dialog({
		width : 600,
		height : 380,
		title : "角色信息",
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
			rolename : {
				required : true
			}
		},
		messages : {
			rolename : {
				required : "请输入角色名称"
			}
		}
	});

	$("#addButton").on("click", function() {
		$("#editForm").resetForm();
		$("#id").val("");
		$("#editForm").attr("action", ctx + "/manage/ajax/saveRole.xhtml");
		$("#editDialog").dialog("open", {
			title : "编辑渠道"
		});
	});
	
	$("#assignDialog").dialog({
		width : 550,
		height : 450,
		title : "分配权限",
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
		}]
	});
	
	$("input[name='moduleid']").on("click", function() {
		var moduleid = $(this).val();
		var id = $("#roleid").val();
		var checked = $(this).prop("checked");
		assign(id,moduleid,checked);
	});
});

function editClick(obj) {
	$("#editForm").resetForm();
	$("#id").val("");
	$("#editForm").attr("action", ctx + "/manage/ajax/updateRole.xhtml");
	$("#editForm").jsonToForm(obj);
	$("#editDialog").dialog("open", {
		title : "编辑渠道"
	});
}

function assignClick(id,obj) {
	$("#assignForm").resetForm();
	$("#roleid").val(id);
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
	if(confirm("确认要删除角色["+name+"]?")){
		$.ajax({
			async:false,
			cache:false,
			url : ctx + "/manage/ajax/delRole.xhtml",
			type:"POST",
			data : "id="+id+"&rolename="+name,
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

function assign(id,moduleid,checked) {
	$.ajax({
		async:false,
		cache:false,
		url : ctx + "/manage/ajax/assignModule.xhtml",
		type:"POST",
		data : "id="+id+"&moduleid="+moduleid+"&checked="+checked,
		dataType:"json",
		success : function(data) {
		}
	});
}