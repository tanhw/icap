function pageUrl(u)
{
	var arg=arguments;
	if (arg.length==0 || u=="")
		return "";
	var urls=u.split("?");
    var paths=urls[0].split("/");
    var pageName= paths[paths.length-1];
    var querys=new Object();
    querys.pageName=pageName;
    
    if(u.indexOf("?")!=-1)
    {
        $.each(urls[1].split("&"),function(i,n){
            var v=n.split("=");
            querys[v[0]]=v[1];
        });
    }
    if (arg.length==2)
    {
    	return querys[arg[1]] || "";
    }
    if (arg.length==3)
    {
    	querys[arg[1]]=arg[2];
    	var new_url="";
    	$.each(querys,function (i,n){
    		new_url += (i=="pageName")?urls[0]+"?":i+"="+n +"&";
    	});
    	return new_url.substr(0,new_url.length-1);
    }
}


function checkUrl(url){
	if (url.indexOf("(")!=-1) return false;
	else return true;
}


$.fn.datetimebox.defaults.formatter=function(date){
    var h = date.getHours();
    var M = date.getMinutes();
    var s = date.getSeconds();
    function formatNumber(value){
        return (value < 10 ? '0' : '') + value;
    }
    return $.fn.datebox.defaults.formatter(date) + ' ' + formatNumber(h)+':'+formatNumber(M)+':'+formatNumber(s);
};

$.fn.datetimebox.defaults.parser = function(s){
	if(s) {			
		var re=/^(\d{1,4})-(\d{1,2})-(\d{1,2})\s(\d{1,2}):(\d{1,2}):(\d{1,2})$/;
		   
		if(re.test(s))
		{
		
			var a = s.split('-');
			
			var arr=s.split(" ");   
			var arr1=arr[0].split("-");   
			var arr2=arr[1].split(":"); 
			
			y=parseInt(arr1[0],10);
			m=parseInt(arr1[1],10)-1;
			d=parseInt(arr1[2],10);
			
			h=parseInt(arr2[0],10);
			mi=parseInt(arr2[1],10);
			s=parseInt(arr2[2],10);
			
			var rDate= new Date(y,m,d,h,mi,s);
			if (rDate) 
				return rDate;
			else 
				return new Date();;
		}else
			return new Date();;

	}else
	{
		return new Date();
	}
};


$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?'0'+m:m)+'-'+(d<10?'0'+d:d);
};

$.fn.datebox.defaults.parser = function(s){
	if(s) {
		
		var re=/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
		if(re.test(s))
		{
		
			var a = s.split('-');

			y=parseInt(a[0],10);
			m=parseInt(a[1],10)-1;
			d=parseInt(a[2],10);
			var rDate= new Date(y,m,d);
			if (rDate) 
				return rDate;
			else 
				return new Date();;
		}else
			return new Date();;

	}else
	{
		return new Date();
	}
};

$.extend($.fn.validatebox.defaults.rules, {
    date: {
        validator: function(value, param){
			var re=/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
            return re.test(value);
        },
        message: '请选择或者输入正确的日期。'
    },
    dateTime: {
        validator: function(value, param){
			var re=/^(\d{1,4})-(\d{1,2})-(\d{1,2})\s(\d{1,2}):(\d{1,2}):(\d{1,2})$/;
            return re.test(value);
        },
        message: '请选择或者输入正确的日期时间。'
    }
});