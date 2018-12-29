$("#order").slideUp();
var chek=false;
var cost = 0;
var count = 0;
var delivery = 0;
var numberForm = new Intl.NumberFormat('en-EN',{minimumFractionDigits: 2, minimumFractionDigits: 2});

$("#controlBtn").on('click',function() {
    if(chek==true){
        $("#order").slideUp();
        $("#basketU").slideDown();
        chek=!chek;
        document.getElementById("controlBtn").innerHTML = "Заказать";    
    } else {
    	$("#basketU").slideUp();
        $("#order").slideDown();
        chek=!chek;
        cost = 0;
        document.getElementById("controlBtn").innerHTML = "Назад";
        var price = $("p.bookPrice");
        count = price.length;
        if(count == 0){
        	window.location.replace("/");
        }
        for (var i = 0; i < count; i++) {
       		var string = price[i].innerHTML;
       		cost += parseFloat(string.substring(string.lastIndexOf(":")+2,string.indexOf("грн")-1));
       	}
       	document.getElementById("booksCount").innerHTML = "Количестко книг: " + price.length;
       	document.getElementById("booksPrice").innerHTML = "Стоимость книг: " + numberForm.format(cost) + " грн";
       	document.getElementById("deliveryPrice").innerHTML = "Стоимость доставки: " + numberForm.format(delivery) + " грн";
       	document.getElementById("totalPrice").innerHTML = "Общая стоимость: " + numberForm.format(cost) + " грн";
    }
});
$('#address').change(function() {
    if ($(this).val() === "Курьерская доставка по Украине") {
    	delivery = 35;
    }else if(totalPrice != cost){
    	delivery = 0;
    }
    document.getElementById("deliveryPrice").innerHTML = "Стоимость доставки: " + numberForm.format(delivery) + " грн";
    document.getElementById("totalPrice").innerHTML ="Общая стоимость: " + numberForm.format(cost + delivery) + " грн";
});

function deleteFromBasket(id){
	$.ajax({
		type: "DELETE",
		url: "/basket?id=" + id,
		complete: function(resp){
		    if(resp.status == 202){
		        window.location.replace("/basket");
		    } else if(resp.status == 412){
		        alert("Ошибка! Книга не найдена.");
		    } else if(resp.status >= 400){
		        alert("Ошибка " + resp.status + "!");
		    }
	    }
	});
}

function addOrder(books,form){
	if(form.name.value == "" || form.surname.value == "" || form.phone.value == "" || form.address.value == "" || 
		cost == 0 || totalPrice == 0){
		alert("Заполните все поля в соответствующем формате!");
		return false;
	}
	document.getElementById("createBtn").disabled = 1;
	var orderJson = "{\"name\":\"" + form.name.value + "\",\"surname\":\"" + form.surname.value + "\",";
	orderJson += "\"phone\":\"" + form.phone.value + "\",\"address\":\"" + form.address.value + "\",";
	orderJson += "\"deliveryPrice\":\"" + delivery + "\",\"totalPrice\":\"" + (cost + delivery) + "\",\"books\":[";
	
	for(var i = 0; i < count; i++){
		orderJson += (i==0?"{":",{") + "\"id\":\"";
		books = books.substring(books.indexOf("Book [id=")+9); 
		orderJson += books.substring(0, books.indexOf(",")) + "\",\"name\":\"";
		books = books.substring(books.indexOf("name=") + 5);
		orderJson += books.substring(0, books.indexOf(",")) + "\"}";
	}
	orderJson += "]}";
	$.ajax({
		type: "POST",
		url: "/basket",
		data: orderJson,
		complete: function(resp){
		    if(resp.status == 202){
		        alert("Заказ оформлен.");
		        window.location.replace("/");
		    } else if(resp.status == 204){
		        alert("Извините, книги уже нет в наличии!");
		        document.getElementById("createBtn").disabled = 0;
		    } else if(resp.status == 412){
		        alert("Ошибка! Заказ уже существует.");
		        document.getElementById("createBtn").disabled = 0;
		    } else if(resp.status >= 400){
		        alert("Ошибка " + resp.status + "!");
		        document.getElementById("createBtn").disabled = 0;
		    }
	    },
	    dataType: "json",
	    contentType: "application/json"
	});
	return false;
}

var config = {
  '.chosen-select'           : {},
  '.chosen-select-deselect'  : { allow_single_deselect: true },
  '.chosen-select-no-single' : { disable_search_threshold: 10 },
  '.chosen-select-no-results': { no_results_text: 'Oops, nothing found!' },
  '.chosen-select-rtl'       : { rtl: true },
  '.chosen-select-width'     : { width: '95%' }
}

window.onload = function() {
	$('.description a').each(function() {
      if ($(this).text().length > 23) {
        $(this).text( $(this).text().substring(0, 20) + '…');
      }
    });
	for (var selector in config) {
	   $(selector).chosen(config[selector]);
	}
};

