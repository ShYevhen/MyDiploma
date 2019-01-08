function login(f){
	if(f.ulogin.value == "" || f.upassword.value == ""){
		return false;
	}
	
	var jsonForm = "{\"login\":\""+f.ulogin.value+"\", \"password\":\""+f.upassword.value+"\"}";

	$.ajax({
	  type: "POST",
	  url: "/login",
	  data: jsonForm,
	  dataType: "json",
	  contentType : "application/json"
	});
	return false;
}
