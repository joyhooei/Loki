var isnMonths=new initArray("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月");
var isnDays=new initArray("星期日","星期一","星期二","星期三","星期四","星期五","星期六","星期日");
//document.write("<a id=\"timer\"></a>");
var today=new Date();
function initArray()
{
	for(var i=0;i<initArray.arguments.length;i++) {
		this[i]=initArray.arguments[i];
	}
}
function getFullYear()
{
	yr=(new Date()).getFullYear();
	if(yr<1000)
	yr+=1900;
	return yr;
}
function show(o) {
	today=new Date();
	hrs=today.getHours();
	min=today.getMinutes();
	sec=today.getSeconds();
	clckh=((hrs<10)?"0":"")+hrs;
	clckm=((min<10)?"0":"")+min;
	clcks=((sec<10)?"0":"")+sec;
	clck=(hrs>=12)?"下午":"上午";
//	var htmlText=++getFullYear(today)+"-"+(today.getMonth()+1)+"-"+today.getDate()+"&nbsp;"+clck+"&nbsp;"+clckh+":"+clckm+":"+clcks+"";
	var htmlText=getFullYear(today)+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日"+"&nbsp;"+isnDays[today.getDay()]+"&nbsp;"+clckh+":"+clckm+":"+clcks;
	document.getElementById('timer').innerHTML=htmlText;
}
if(typeof document.getElementById("timer") !="undefined") {
	show('timer');
	setInterval("show('timer');",1000);
}
