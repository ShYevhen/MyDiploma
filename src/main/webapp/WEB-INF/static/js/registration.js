// var image = "";
// File.prototype.convertToBase64 = function(callback){
// 	var reader = new FileReader();
// 	reader.onloadend = function (e) {
// 		callback(e.target.result, e.target.error);
// 	};   
// 	reader.readAsDataURL(this);
// };

// $("#picture").on('change',function(){
// 	var selectedFile = this.files[0];
// 	selectedFile.convertToBase64(function(base64){
// 		image = base64;
// 	}) 
// 	$this = $(this);
// 	if($this.val().length == 0) {
// 		$('#file').text("Выбрать картинку");
// 	}else{
// 		var fileName = $this.val();
// 		fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length);
// 		if(fileName.length>19){
// 			fileName = fileName.substring(0, 17) + "...";
// 		}
// 		$('#file').text(fileName);
// 	}
// });


function sendRegData(f) {
	if(f.login.value == "" || f.password.value == "" || f.eMail.value == "" || f.phone.value == ""){
		alert("Заполните все обязательные поля!")
		return false;
	}
	var jsonForm = "{";
	jsonForm += "\"login\":\""+f.login.value+"\",";
	jsonForm += "\"password\":\""+f.password.value+"\",";
	jsonForm += "\"eMail\":\""+f.eMail.value+"\",";
	jsonForm += "\"phone\":\""+f.phone.value+"\"";
	if(f.name.value != ""){
		jsonForm += ",\"name\":\""+f.name.value+"\"";
	}
	if(f.surname.value != ""){
		jsonForm += ",\"surname\":\""+f.surname.value+"\"";
	}
	if(f.picture.value != ""){
		jsonForm += ",\"image\":\""+f.picture.value+"\"";
	}
	jsonForm += "}";


	$.ajax({
		type: "POST",
		url: "/registration",
		data: jsonForm,
		complete: function(d){
			if (d.status == 202) {
				alert("Вы успешно зарегистрировались!");
				window.location.replace("/login");
			} else if (d.status == 412) {
				alert("Пользователь с таким логином уже существует");
				window.location.replace("/registration");
			}
		},
		dataType: "json",
		contentType : "application/json"
	});
	return false;
}


function boxForm(f) {
	if (f.confirm.checked)
		f.send.disabled = 0
	else
		f.send.disabled = 1
}

function checkPass(f) {
	if (f.password.value != "" && f.r_password.value != "" && f.password.value != f.r_password.value) {
		alert('Пароли не совпадают!')
		f.confirm.disabled = 1;
	} else {
		f.confirm.disabled = 0;
	}
}