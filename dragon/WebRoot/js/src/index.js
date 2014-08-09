$(function() {
	$(".submenu").hide();
	$(".menu").on("click", function() {
		var i = $(".menu").index(this);
		//$(".submenu").hide();
		$(".submenu").each(function(index, obj) {
			if (index == i) {
				if($(obj).is(":hidden")){
					$(obj).show();
				}else{
					$(obj).hide();
				}
			}
		});
	});
	$(".menuparent").on("click", function() {
		$(".menuparent").each(function(index, obj) {
			obj.style.color = "";
		});
		this.style.color = "#f60";
	});
	$(".menuchild").on("click", function() {
		$(".menuchild").each(function(index, obj) {
			obj.style.color = "";
		});
		this.style.color = "#f60";
	});
	$("#contentFrame").load(function() {
		setIframe();
	});
	
	if($.isEmptyObject($("#messageDialog").get(0))){
		$("body").append(createElement("div",{"id":"messageDialog","style":"display:none;"}));
		$("#messageDialog").html("<font color=\"red\" size=\"3\">拼命提交中……</font>");
	}
	
	$("#messageDialog").dialog({
		width : 270,
		height : 120,
		title : "提示信息",
		resizable : true,
		autoOpen : false,
		position : {
			my : "center",
			at : "center",
			of : window
		},
		modal: true,
		buttons : [ {
			text : "确认",
			click : function() {
				$("#messageDialog").dialog("close");
				if(1==$("#reflush").val()){
					reflushIframe();
				}
				$("#messageDialog").html("<font color=\"red\" size=\"3\">拼命提交中……</font>");
			}
		}]
	});
	
	$("#passwordDialog").dialog({
		width : 600,
		height : 230,
		title : "修改密码",
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
				if (!$("#passwordForm").validate().form()) {
					return;
				}
				var options = {
					url:$("#passwordForm").attr('action'),
					type:'POST',
					success: function(data){
						if(1==data.result){
							$("#passwordDialog").dialog("close");
						}
						$("#messageDialog").html(data.message);
						$("#messageDialog").append(createElement("input",{"type":"hidden","id":"reflush","value":0}));
					},
					error:function(){
						parent.setMeassge("提交失败：网络不通或系统故障",0);
					}
				};
				$("#passwordForm").ajaxSubmit(options);
				$("#messageDialog").dialog("open");
			}
		}, {
			text : '关闭',
			click : function() {
				$("#passwordDialog").dialog("close");
			}
		}]
	});
	
	$("#passwordForm").validate({
		rules : {
			password : {
				required : true,
				minlength : 6
			},
			newpassword : {
				required : true,
				minlength : 6
			},
			confirmpassword : {
				required : true,
				minlength : 6,
				equalTo : password
			}
		},
		messages : {
			password : {
				required : "请输入旧密码",
				minlength : jQuery.format("密码不能小于{0}个字 符")
			},
			newpassword : {
				required : "请输入新密码",
				minlength : jQuery.format("密码不能小于{0}个字 符")
			},
			confirmpassword : {
				required : "请输入确认密码",
				minlength : jQuery.format("密码不能小于{0}个字 符"),
				equalTo: "密码不一致"
			}
		}
	});
});

$(window).resize(function() {
	setIframe();
});

function reflushIframe(){
	var currWindow = $("#contentFrame")[0].contentWindow;
	currWindow.pageing($("#contentFrame").contents().find("input[id='pageIndex']").val());
}

function setIframe() {
	var wWidth = $(document).contents().find("body").width();
	var wHeight = $(document).contents().find("body").height();
	var height = $("#contentFrame").contents().find("body").height();
	var width = $("#contentFrame").contents().find("body").width();
	if (width < 800 || wWidth < 1000) {
		width = 800;
	} else {
		width = wWidth - $(".left").width() - 5;
	}
	if (height < 615) {
		height = 615;
	} 
//	else if (height > wHeight) {
//		height = wHeight;
//	}
	$("#contentFrame").height(height);
	$(".right").width(width);
	var tWidth = $(".nav-menuright").width();
	if (wWidth < 1000) {
		tWidth = 727;
	}else{
		tWidth = wWidth - $(".nav-menuleft").width()-5;
	}
	$(".nav-menuright").width(tWidth);
}

function openIframe(url) {
	$("#contentFrame").attr("src", url);
}

function clean(name) {
	$.ajax({
		async:false,
		cache:false,
		url : "/cloudy/cc.xhtml",
		type:"POST",
		data : "name="+name,
		dataType:"json",
		success : function(data) {
			setMeassge(data);
		},
		error:function(a,b,c){
			setMeassge("失败");
		}
	});
	showMeassge(0);
}

function updatepassword(){
	$("#passwordForm").attr("action", ctx + "/manage/ajax/updatepwd.xhtml");
	$("#passwordDialog").dialog("open", {
		title : "编辑用户"
	});
}