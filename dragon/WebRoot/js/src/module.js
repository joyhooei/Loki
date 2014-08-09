$(function() {
	$("#editDialog").dialog({
		width : 600,
		height : 330,
		title : "模块信息",
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
			modulename : {
				required : true
			},
			modulecode : {
				required : true
			},
			moduleleave:{
				required : true,
				digits:true
			},
			sortindex:{
				required : true,
				digits:true
			}
		},
		messages : {
			modulename : {
				required : "请输入名称"
			},
			modulecode : {
				required : "请输入模块代码（模块名称首字母）"
			},
			moduleleave:{
				required : "请输入模块级别",
				digits: "请输入整数"
			},
			sortindex:{
				required : "请输入模块顺序",
				digits: "请输入整数"
			}
		}
	});

	$("#addButton").on("click", function() {
		$("#editForm").resetForm();
		$("#id").val("");
		$("#editForm").attr("action", ctx + "/manage/ajax/saveModule.xhtml");
//		$("input[name='password']").removeAttr("disabled");
//		$("input[name='password']").parent().parent().show();
		$("#editDialog").dialog("open", {
			title : "编辑渠道"
		});
	});
	$(".module-menu a").hide();
	$(".module-submenu a").hide();
	
	$(".module-menu").on("mouseover",function(){
		$("a",this).show();
	});
	
	$(".module-menu").on("mouseout",function(){
		$("a",this).hide();
	});
	
	$(".module-submenu").on("mouseover",function(){
		$("a",this).show();
	});
	
	$(".module-submenu").on("mouseout",function(){
		$("a",this).hide();
	});
	
//	$("#editDialog").show();
});

function editClick(obj) {
	$("#editForm").resetForm();
	$("#id").val("");
	$("#editForm").attr("action", ctx + "/manage/ajax/updateModule.xhtml");
	$("#editForm").jsonToForm(obj);
	$("#editDialog").dialog("open", {
		title : "编辑渠道"
	});
}

function deleteClick(id,name) {
	if(confirm("确认要删除模块["+name+"]?")){
		$.ajax({
			async:false,
			cache:false,
			url : ctx + "/manage/ajax/delModule.xhtml",
			type:"POST",
			data : "id="+id+"&modulename="+name,
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