$(function() {
	$(".listTr").on("mouseover", function() {
		this.style.background = "#f6f9fd";
	});

	$(".listTr").on("mouseout", function() {
		this.style.background = "#ffffff";
	});

	$(".sj .btn").on("click", function() {
		pageing(1);
	});

	$("#editDialog").hide();
	
	$(".resizable").on("mousedown",function(e) {
		e.preventDefault();
		var left,x,index,nextwidth,nnextwidth,next,width,newwidth,pageX,_this=this;
		left = $(this).position().left;
		width = $(this).width();
		x = pageX = e.pageX;
		if(x>=left+width-15 && x<=left+width){
			index = $(".resizable").index($(this));
			if(index+1<$(".resizable").size()){
				next = $(".resizable").eq(index+1);
				nextwidth = next.width();
			}
			$(document).on("mousemove.rc",function(e){
				if(index+1==$(".resizable").size() && e.pageX+50>$(_this).parent().width()){
					pageX = e.pageX-50;
				}else{
					pageX = e.pageX;
				}
				newwidth = pageX-left;
		        if(newwidth<50){
		        	newwidth = 50;
		        }
		        nnextwidth = nextwidth+width-newwidth;
	        	if(nnextwidth<50){
	        		nnextwidth = 50;
	        		newwidth = nextwidth+width-50;
		        }
		        $(_this).width(newwidth);
		        if(index+1<$(".resizable").size()){
		        	$(next).width(nnextwidth);
				}
			});
			return $(document).one('mouseup', function() {
		        $(document).off('mousemove.rc');
		        return;
		      });
		}
	});
});

/**
 * 分页跳转
 * 
 * @param {Object}
 *            index
 */
function pageing(index) {
	$("#queryForm").append(createElement("input", {
		"type" : "hidden",
		"name" : "pageIndex"
	}));
	$("#queryForm input[name='pageIndex']").val(index);
	$("#queryForm")[0].submit();
}