$(function() {
	$("#editDialog").dialog({
		width : 600,
		height : 330,
		title : "用户信息",
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
			username : {
				required : true
			},
			password : {
				required : true,
				minlength : 6
			}
		},
		messages : {
			username : {
				required : "请输入姓名"
			},
			password : {
				required : "请输入密码",
				minlength : jQuery.format("密码不能小于{0}个字 符")
			}
		}
	});

	$("#addButton").on("click", function() {
		$("#editForm").resetForm();
		$("#id").val("");
		$("#editForm").attr("action", ctx + "/manage/ajax/saveUser.xhtml");
		$("input[name='password']").removeAttr("disabled");
		$("input[name='password']").parent().parent().show();
		$("#editDialog").dialog("open", {
			title : "编辑用户"
		});
	});
});

function editClick(obj) {
	$("#editForm").resetForm();
	$("#id").val("");
	$("#editForm").attr("action", ctx + "/manage/ajax/updateUser.xhtml");
	$("input[name='password']").attr("disabled", "disabled");
	$("#editForm").jsonToForm(obj);
	$("#editDialog").dialog("open", {
		title : "编辑用户"
	});
}

function deleteClick(id,name) {
	if(confirm("确认要删除用户["+name+"]?")){
		$.ajax({
			async:false,
			cache:false,
			url : ctx + "/manage/ajax/delUser.xhtml",
			type:"POST",
			data : "id="+id+"&username="+name,
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

function changeEnableClick(id,enable,username) {
	$.ajax({
		async:false,
		cache:false,
		url : ctx + "/manage/ajax/enableUser.xhtml",
		type:"POST",
		data : "id="+id+"&enable="+enable+"&username="+username,
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

function resetPasswordClick(id,username) {
	$.ajax({
		async:false,
		cache:false,
		url : ctx + "/manage/ajax/resetUserPwd.xhtml",
		type:"POST",
		data : "id="+id+"&username="+username,
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