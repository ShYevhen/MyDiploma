$(".visibility").slideUp();
var chek=false;

var image = "";
File.prototype.convertToBase64 = function(callback){
                var reader = new FileReader();
                reader.onloadend = function (e) {
                    callback(e.target.result, e.target.error);
                };   
                reader.readAsDataURL(this);
        };

        $("#picture").on('change',function(){
          var selectedFile = this.files[0];
          selectedFile.convertToBase64(function(base64){
               image = base64;
          }) 
        });

$("#changeBtn").on('click',function() {
    if(chek==true){
        $(".visibility").slideUp();
        chek=!chek;
        document.getElementById("changeBtn").value = "Изменить";
        
    } else {
        $(".visibility").slideDown();
        chek=!chek;
        document.getElementById("changeBtn").value = "Отменить";
    }
});
function authorUpdate(id,form){
	if(form.surname.value == "" && form.name.value == "" && form.biography.value == "" && 
		form.birthday.value == "" && image == ""){
		alert("Вы не заполнили ни одного поля!");
		return false;
	}
	var authorJson = "{\"id\":\""+id+"\"";
	
	if (form.name.value != "") {
		authorJson += ",\"name\":\""+form.name.value+"\"";
		if(patternTest(form.name.value)){
			alert("Имя и Фамилия должны состоять только из букв русского алфавита!");
			return false;
		}
	}
	if(form.surname.value != ""){
		authorJson+=",\"surname\":\""+form.surname.value+"\"";
		if(patternTest(form.surname.value)){
			alert("Имя и Фамилия должны состоять только из букв русского алфавита!");
			return false;
		}
	}
	if(form.biography.value != ""){
		authorJson += ",\"biography\":\""+form.biography.value.replace(/[\\]/g, "\\\\")
                                      .replace(/[\/]/g, "\\/")
									  .replace(/[\n]/g, "\\n")
                                      .replace(/\\'/g, "\\'")
                                      .replace(/[\"]/g, '\\"')
                                      .replace(/\\&/g, "\\&")
                                      .replace(/[\r]/g, "\\r")
                                      .replace(/[\t]/g, "\\t")
                                      .replace(/[\b]/g, "\\b")
                                      .replace(/[\f]/g, "\\f") + "\"";
	}
	if(form.birthday.value != ""){
		authorJson += ",\"birthday\":\""+form.birthday.value+"\"";
	}
	if(image != ""){
		authorJson += ",\"image\":\""+image+"\"";
	}
	authorJson+="}";
	$.ajax({
		type: "POST",
		url: "/authors/author/update",
		data: authorJson,
		complete: function(resp){
			if(resp.status == 202){
				alert("Изменения сохранены!");
				window.location.reload();
			} else if(resp.status == 412){
				alert("Ошибка! Автор не найден.");
			} else if(resp.status >= 400){
				alert("Ошибка " + resp.status + "!");
			}
		},
		dataType: "json",
		contentType: "application/json"
	});
	return false;
}

function addAuthor(form){
	if(form.name.value == "" || form.surname.value == "" || form.biography.value == "" || 
		form.birthday.value == "" || image == ""){
		alert("Необходимо заполнить все поля!");
		return false;
	}
	if(patternTest(form.surname.value) || patternTest(form.name.value)){
		alert("Имя и Фамилия должны состоять только из букв русского алфавита!");
		return false;
	}
	var authorJson = "{\"name\":\""+form.name.value+"\"";
	authorJson +=",\"surname\":\""+form.surname.value+"\"";
	authorJson += ",\"biography\":\""+form.biography.value.replace(/[\\]/g, "\\\\")
                                      .replace(/[\/]/g, "\\/")
									  .replace(/[\n]/g, "\\n")
                                      .replace(/\\'/g, "\\'")
                                      .replace(/[\"]/g, '\\"')
                                      .replace(/\\&/g, "\\&")
                                      .replace(/[\r]/g, "\\r")
                                      .replace(/[\t]/g, "\\t")
                                      .replace(/[\b]/g, "\\b")
                                      .replace(/[\f]/g, "\\f")+"\"";
	authorJson += ",\"birthday\":\""+form.birthday.value+"\"";
	authorJson += ",\"image\":\""+image+"\"}";
	$.ajax({
		type: "POST",
		url: "/authors/add",
		data: authorJson,
		complete: function(resp){
			if(resp.status == 202){
				alert("Автор добавлен.");
				window.location.replace("/authors/author?id=" + resp.responseText);
			} else if(resp.status == 412){
				alert("Ошибка! Автор уже существует.");
			} else if(resp.status >= 400){
				alert("Ошибка " + resp.status + "!");
			}
		},
		dataType: "json",
		contentType: "application/json"
	});
	return false;
}

function patternTest(text){
	var pat = /^[А-Яа-яЁё-\s]+$/;
	return !pat.test(text);
}