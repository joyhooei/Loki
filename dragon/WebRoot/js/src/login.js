//// 发送时的验证
//function login() {
//	var browserName = navigator.appName;
//	var isIE = (browserName == "Microsoft Internet Explorer");
//	var flag = false;
//	var name = $("#name").val();
//	var password = $('#password').val();
//	//提交后锁定提交按钮
//	$("#login").attr("disabled", "disabled");
//	if (formValidate()) {
//		var url = ctx + "/manage/login.do";
//		$.post(url, {
//			"name" : name,
//			"password" : password
//		}, function(data) {
//			if (data.status == '1') {
//				flag = true;
//				if (!isIE) {
//					location.href = ctx + "/manage/index.do";
//				}
//			} else {
//				alert(data.msg);
//			}
//		}, "json");
//	}
//	$("#login").removeAttr("disabled");
//	return flag;
//}
//
//function logining(){
//	document.getElementById("loginForm").action=ctx+"/manage/index.do";
//	document.getElementById("loginForm").submit();
//}
//
///**
// * 点击登录时Form验证
// */
//function formValidate() {
//	return $("#loginForm").validate( {
//		rules : {
//			name : {
//				required : true,
//				maxlength : 50
//			},
//			password : {
//				required : true,
//				maxlength : 50
//			}
//		},
//		messages : {
//			name : {
//				required : "登陆账号不能为空",
//				maxlength : "登陆账号不能超过50个字符"
//			},
//			password : {
//				required : "登陆密码不能为空",
//				maxlength : "登陆密码不能超过50个字符"
//			}
//		}
//	}).form();
//}

/**
 * Form验证
 */
//$(function() {
//	$("#loginForm").validate( {
//		rules : {
//			j_username : {
//				required : true,
//				maxlength : 50
//			},
//			j_password : {
//				required : true,
//				maxlength : 50
//			}
//		},
//		messages : {
//			j_username : {
//				required : "登陆账号不能为空",
//				maxlength : "登陆账号不能超过50个字符"
//			},
//			j_password : {
//				required : "登陆密码不能为空",
//				maxlength : "登陆密码不能超过50个字符"
//			}
//		}
//	});
//});
$(function() {
	$("#loginForm").validate( {
		rules : {
			name : {
				required : true,
				maxlength : 50
			},
			password : {
				required : true,
				maxlength : 50
			}
		},
		messages : {
			name : {
				required : "登陆账号不能为空",
				maxlength : "登陆账号不能超过50个字符"
			},
			password : {
				required : "登陆密码不能为空",
				maxlength : "登陆密码不能超过50个字符"
			}
		}
	});
});