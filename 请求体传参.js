// 请求体传参 @RequestBody
$.ajax({
	type: "POST",
	url: "<c:out value='${ctx}'/>/msgSecurity/save",
	data: JSON.stringify(params()),
	contentType: 'application/json;charset=utf8',
	processData: false,
	success: function(ret){
		if(!ret){
			$.alert("保存成功！");
		}else{
			$.alert("保存失败！");
		}
	},
	error: function(XMLHttpRequest, textStatus, errorThrown){
		if(XMLHttpRequest.status >= 500){
			$.alert("服务器错误，请联系管理员！");
		}else{
			$.alert("请求错误！");
		}
	}
});