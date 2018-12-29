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

function checkPass(f) {
		if (f.password.value != "" && f.r_password.value != "" && f.password.value != f.r_password.value) {
			alert("Пароли не совпадают!");
			f.changeBtm.disabled = 1;
		} else {
			if (f.password.value != "" && f.r_password.value != "" && f.old_password.value != "" && f.old_password.value == f.password.value) {
				alert("Новый и старый пароли не должны совпадать!");
				f.changeBtm.disabled = 1;
			} else {
				f.changeBtm.disabled = 0;
			}
		}
	}

function checkPassword(f) {
		if (f.password.value != "" && f.r_password.value != "" && f.password.value != f.r_password.value) {
			alert("Пароли не совпадают!");
			f.changeBtm.disabled = 1;
		} else {
			f.changeBtm.disabled = 0;
		}
	}

function sendChangeData(f) {
	if(f.name.value == "" && f.surname.value == "" && f.password.value == "" && f.eMail.value == "" &&
	 f.phone.value == "" && image == ""){
		alert("Для изменения данных введите новое значение в соответствующее поле.");
		return false;
	};
	if(f.r_password.value == "" && f.password.value != "" ){
		alert("Повторите новый пароль!");
		return false;
	};
	var jsonForm = "{";
	jsonForm += "\"login\":\""+f.old_password.value+"\"";
	if(f.password.value != ""){
		jsonForm += ",\"password\":\""+f.password.value+"\"";
	}else{
		jsonForm += ",\"password\":\""+null+"\"";
	}
	if(f.eMail.value != ""){
		jsonForm += ",\"eMail\":\""+f.eMail.value+"\"";
	}else{
		jsonForm += ",\"eMail\":\""+null+"\"";
	}
	if(f.phone.value != ""){
		jsonForm += ",\"phone\":\""+f.phone.value+"\"";
	}else{
		jsonForm += ",\"phone\":\""+null+"\"";
	}
	if(f.name.value != ""){
		jsonForm += ",\"name\":\""+f.name.value+"\"";
	}else{
		jsonForm += ",\"name\":\""+null+"\"";
	}
	if(f.surname.value != ""){
		jsonForm += ",\"surname\":\""+f.surname.value+"\"";
	}else{
		jsonForm += ",\"surname\":\""+null+"\"";
	}
	if(image != ""){
		jsonForm += ",\"image\":\""+image+"\"";
	}else{
		jsonForm += ",\"image\":\""+null+"\"";
	}
	jsonForm += "}";


$.ajax({
  type: "POST",
  url: "/profile/user",
  data: jsonForm,
  complete: function(d){
  	if (d.status == 202) {
  		window.location.reload();
  	} else if (d.status == 412) {
  		alert("Неправильный пароль!");
  	}
  },
  dataType: "json",
  contentType : "application/json"
});
return false;
}

function addToBasket(f){
	var target ="/basket/add?id=" + f;
	$.ajax({
            url: target
            , type:'GET'
            , success: function() {
                location.reload();
            }

        });
}

function deleteFromFavorite(f){
	var target ="/profile/favorite?id=" + f;
	$.ajax({
		url: target,
		type: 'DELETE',
		success: function(){
			location.reload();
		}
	});
}

function createOper(f){
	var jsonOper ="{\"login\":\""+f.login.value+"\"";
	jsonOper += ",\"password\":\""+f.password.value+"\"";
	jsonOper += ",\"role\":\""+f.role.value+"\"}";
	$.ajax({
		url: "/profile/operators",
		type: 'POST',
		data: jsonOper,
		complete: function(d){
			if (d.status == 202) {
				window.location.reload();
			} else if (d.status == 412) {
				alert("Пользователь с таким логином уже существует!");
			}
		},
		dataType: "json",
		contentType: "application/json"
	});
	return false;
}

function changeOper(f){
	var select = document.getElementById(f);
	var sel = select.options[select.selectedIndex].value;
	var updateOper = "{\"login\":\""+f+"\",\"role\":\""+sel+"\"}";
	$.ajax({
		url: "/profile/operators/update",
		type: 'POST',
		data: updateOper,
		complete: function(d){
			if (d.status == 202) {
				window.location.reload();
			} else if (d.status == 412) {
				alert("Пользователь с таким логином не существует!");
			}
		},
		dataType: "json",
		contentType: "application/json"
	});
	return false;
}

function deleteOper(f){
	$.ajax({
		url: "/profile/operators?id="+f,
		type: 'DELETE',
		success: function(){
			window.location.replace("/profile/operators");
		}
	});
}

function changeOrder(f, b){
	var select = document.getElementById(f);
	var sel = select.options[select.selectedIndex].value;
	var updateOrder = "{\"id\":\""+f+"\",\"status\":\""+sel+"\"";
	if(b == 't'){
		var name = document.getElementById("name").value;
		if(name != ""){
			updateOrder += ",\"name\":\""+name+"\"";
		}
		var surname = document.getElementById("surname").value;
		if(surname != ""){
			updateOrder += ",\"surname\":\""+surname+"\"";
		}
		var phone = document.getElementById("phone").value;
		if(phone != ""){
			updateOrder += ",\"phone\":\""+phone+"\"";
		}
	}
	updateOrder += "}";
	$.ajax({
		url: "/profile/orders/update",
		type: 'POST',
		data: updateOrder,
		complete: function(d){
			if (d.status == 202) {
				window.location.reload();
			} else if (d.status == 412) {
				alert("Не удалось сохранить изменения!");
			}
		},
		dataType: "json",
		contentType: "application/json"
	});
	return false;
}

function deleteOrder(f){
	$.ajax({
		url: "/profile/orders?id="+f,
		type: 'DELETE',
		complete: function(d){
			if (d.status == 202) {
				window.location.replace("/profile/orders");
			} else if (d.status == 412) {
				alert("Заказ с таким номером не существует!");
			}
		}
	});
}

function deleteFromOrder(f,b){

	$.ajax({
		url: "/profile/orders/update?id="+f+"&bookId="+b,
		type: 'DELETE',
		complete: function(d){
			if (d.status == 202) {
				window.location.replace("/profile/orders?id="+f);
			} else if (d.status == 412) {
				alert("Не удалось сохранить изменения!");
			}
		}
	});
}

function orderSelected(){
    var select = document.getElementById("orderSel");
    window.location.replace(select.options[select.selectedIndex].value);
}

$(document).ready(function(){
    $('.description a').each(function() {
      if ($(this).text().length > 23) {
        $(this).text( $(this).text().substring(0, 20) + '…');
      }
    });
    var href = document.location.href;
    href = href.substring(href.indexOf("/profile"), ((href.indexOf("&page")>0)?href.indexOf("&page"):href.length));
    $('select option[value="' + href + '"]').prop('selected', true);
    for (var selector in config) {
        $(selector).chosen(config[selector]);
    }
});

var config = {
  '.chosen-select'           : {},
  '.chosen-select-deselect'  : { allow_single_deselect: true },
  '.chosen-select-no-single' : { disable_search_threshold: 10 },
  '.chosen-select-no-results': { no_results_text: 'Oops, nothing found!' },
  '.chosen-select-rtl'       : { rtl: true },
  '.chosen-select-width'     : { width: '95%' }
}