// 手机号码验证
function isMobile(num) {
	var reg = /^(86)?1(([358]\d{9})|(4[7]\d{8}))$/;
	return reg.test(num);
}

$.validator.addMethod("isMobile", function(value, element, params) {
	var reg = /^(86)?1(([358]\d{9})|(4[7]\d{8}))$/;
	return reg.test(value);
}, "请输入正确的手机号");

function createElement(element, attr, css, html) {
	var tagUL = document.createElement(element);
	if (typeof attr != "undefined" && $.trim(attr) != "") {
		$(tagUL).attr(attr);
	}
	if (css != "undefined" && $.trim(css) != "") {
		$(tagUL).css(css);
	}
	if (typeof html != "undefined" && $.trim(html) != "") {
		$(tagUL).html(html);
	}
	return tagUL;
}

function checkedAll(obj, name) {
	var checkbox = $("input[name='" + name + "']");
	if (obj.checked) {
		for ( var i = 0; i < checkbox.length; i++) {
			checkbox[i].checked = true;
		}
	} else {
		$("input[name='" + name + "']").attr("checked", false);
	}
}
function batchDel(name, url) {
	var checkbox = $("input[name='" + name + "']:checked");
	if (checkbox.length > 0) {
		if (confirm("所选择的数据删除后将不能恢复，是否要删除？")) {
			var ids = new Array();
			for ( var i = 0; i < checkbox.length; i++) {
				ids[i] = checkbox[i].value;
			}
			location.href = url + "?" + name + "=" + ids;
		}
	} else {
		alert("请选择你要删除的数据");
	}
}
$(document)
		.bind(
				'keydown',
				function(event) {
					event = window.event || event;
					if (event.keyCode == 116) {
						if (document.getElementById("contentFrame")) {
							document.getElementById("contentFrame").contentWindow.location
									.reload();
						} else {
							document.location.reload();
						}
						event.keyCode = 0;
						event.cancelBubble = true;
						return false;
					}
				});

$.fn.extend({
	jsonToForm : function(data) {
		var form = $(this);
		var json = null;
		if (!$.isPlainObject(data)) {
			var jsonStr = mapToJson(data);
			if (!(typeof jsonStr == "undefined")) {
				json = $.parseJSON(jsonStr);
			}
		} else {
			json = data;
		}
		if (json != null && json!='') {
			$.each(json, function(i, n) {
				var field = form.find('input[name=' + i + ']');
				if(!field.attr("disabled")){
					switch (field.attr('type')) {
					case 'hidden':
						field.val(n);
						break;
					case 'text':
						field.val(n);
						break;
					case 'radio':
						field.each(function() {
							var value = $(this).val();
							if (value == 'true') {
								value = true;
							} else if (value == 'false') {
								value = false;
							}
							if (value == n) {
								$(this).get(0).checked = true;
							}
						});
						break;
					case 'checkbox':
						field.each(function() {
							var value = $(this).val();
							if (value == 'true') {
								value = true;
							} else if (value == 'false') {
								value = false;
							}
							if (value == n) {
								$(this).get(0).checked = true;
							}
						});
						break;
					default:
						field = form.find('select[name=' + i + ']');
						if (!$.isEmptyObject(field.get(0))) {
							field.children('option').each(function() {
								if ($(this).val() == n) {
									this.selected = true;
								}else{
									this.selected = false;
								}
							});
						}
						field = form.find('textarea[name=' + i + ']');
						if (!$.isEmptyObject(field.get(0))) {
							field.val(n);
						}
						break;
					}
				}else{
					$(field).parent().parent().hide();
				}
			});
		}
	},
	_ajaxSubmit: function(reflush) {
		var options = {
				url:$(this).attr('action'),
				type:'POST',
				success: function(data){
					if(1==data.result){
						$("#editDialog").dialog("close");
					}else{
						reflush = 0;
					}
					parent.setMeassge(data.message,reflush);
				},
				error:function(){
					parent.setMeassge("提交失败：网络不通或系统故障",0);
				}
		};
		$(this).ajaxSubmit(options);
		parent.showMeassge();
	}
});

function mapToJson(mapStr) {
	mapStr = mapStr.replace(/\w*{/g, "{\"");
	mapStr = mapStr.replace(/\s=\s/g, "\":\"");
	mapStr = mapStr.replace(/,/g, "\",\"");
	mapStr = mapStr.replace(/\s/g, "");
	mapStr = mapStr.replace("}", "\"}");
	return mapStr;
}
function mapsToArr(mapsStr){
	mapsStr = mapsStr.replace(/\[\w*{/g, "[{");
	mapsStr = mapsStr.replace(/,\s\w*{/g, ",,{");
	mapsStr = mapsStr.replace("[", "");
	mapsStr = mapsStr.replace("]", "");
	var arr = mapsStr.split(",,");
	return arr;
}

function showMeassge(){
	$("#messageDialog").dialog("open");
}

function setMeassge(message,reflush){
	$("#messageDialog").html(message);
	$("#messageDialog").append(createElement("input",{"type":"hidden","id":"reflush","value":reflush}));
}

$(function() {
	var backToTopTxt = "返回顶部";
	var backToTopEle = $('<div class="backToTop"></div>').appendTo($("body"))
			.text(backToTopTxt).attr("title", backToTopTxt).click(function() {
				$("html, body").animate({
					scrollTop : 0
				}, 120);
			});
	var backToTopFun = function() {
		var st = $(document).scrollTop(), winh = $(window).height();
		(st > 0) ? backToTopEle.show() : backToTopEle.hide();
		// IE6下的定位
		if (!window.XMLHttpRequest) {
			backToTopEle.css("top", st + winh - 166);
		}
	};
	$(window).bind("scroll", backToTopFun);
	$(function() {
		backToTopFun();
	});
});