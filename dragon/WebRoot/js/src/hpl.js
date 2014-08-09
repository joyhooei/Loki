$(function() {
	$("#editDialog").dialog( {
		width : 600,
		height : 380,
		title : "主页信息",
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

	$("#addButton").on("click", function() {
		$("#editForm").resetForm();
		$("#id").val("");
		$("#editForm").attr("action", ctx + "/manage/ajax/saveHomepage.xhtml");
		$("#editDialog").dialog("open");
	});

	$("#clean").on("click", function() {
		parent.clean("tHomepagelock");
	});
	$("#cleanAll").on("click", function() {
		parent.clean("all");
	});

	$("#assignDialog").dialog( {
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
		buttons : [ {
			text : '关闭',
			click : function() {
				$("#assignDialog").dialog("close");
				parent.reflushIframe();
			}
		} ]
	});

	$("input[name='channelid']").on("click", function() {
		var channelid = $(this).val();
		var id = $("#homepageid").val();
		var checked = $(this).prop("checked");
		assign(id, channelid, checked);
	});
	
	$("#editForm").validate( {
		rules : {
			url : {
				required : true,
				url : true
			}
		},
		messages : {
			url : {
				required : "请输入主页地址",
				url : "请输入正确的地址"
			}
		}
	});
});

function editClick(obj) {
	$("#editForm").resetForm();
	$("#id").val("");
	$("#editForm").attr("action", ctx + "/manage/ajax/updateHomepage.xhtml");
	$("#editForm").jsonToForm(obj);
	$("#editDialog").dialog("open");
}

function assignClick(id, obj) {
	var idleChannelList;
	$.ajax( {
		async:false,
		cache:false,
		url : ctx + "/manage/ajax/idleChannel.xhtml",
		type : "POST",
		data : "name=homepagechannel",
		dataType : "json",
		success : function(data) {
			idleChannelList = data.idleChannelList;
		}
	});
	$("#assignForm").resetForm();
	$("#homepageid").val(id);
	var channelList = mapsToArr(""+$("#channelList").val()+"");
	var arr = mapsToArr(obj);
	$(".select-menu").html("");
	$(channelList).each(function(index,json){
		var jsonStr = mapToJson(json);
		json = $.parseJSON(jsonStr);
		$(".select-menu").append("<div class=\"select-submenu\"><input type=\"checkbox\" name=\"channelid\" value=\""+json.id+"\"/>("+json.id+")"+json.name+"</div>");
	});
	$(arr).each(function(index, json) {
		if (json != null && json != "" && json != '') {
			$("#assignForm").jsonToForm(json);
		}
	});
	$(".select-submenu input").each(function(index, obj) {
		if(obj.checked!=true){
			$(obj).parent().remove();
		}
	});
	$(idleChannelList).each(function(index,json){
		$(".select-menu").append("<div class=\"select-submenu\"><input type=\"checkbox\" name=\"channelid\" value=\""+json.id+"\"/>("+json.id+")"+json.name+"</div>");
	});
	$("#assignDialog").dialog("open");
}

function deleteClick(id, name) {
	if(confirm("确认要删除主页["+name+"]?")){
		$.ajax( {
			async:false,
			cache:false,
			url : ctx + "/manage/ajax/delHomepage.xhtml",
			type : "POST",
			data : "id=" + id + "&url=" + name,
			dataType : "json",
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

function assign(id, channelid, checked) {
	$.ajax( {
		async:false,
		cache:false,
		url : ctx + "/manage/ajax/assignHomepage.xhtml",
		type : "POST",
		data : "id=" + id + "&channelid=" + channelid + "&checked=" + checked,
		dataType : "json",
		success : function(data) {
		}
	});
}