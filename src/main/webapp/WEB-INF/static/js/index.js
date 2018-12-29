$('.main').each(function(){
	$.getJSON('/new', function(data){
  		var items = "";
  		$.each(data, function(key, val){
    	items += "<div class='book'><div class='image'><a href='/books?id=" + val.id + "'><img src='" + val.image + "' alt='" 
    	+ val.name + "'></a></div><div class='description'><p><a href='/books?id=" + val.id + "'>" + myEllipsis(val.name) 
    	+ "</a></p>";
    	items += "<p>Цена: " + val.price + " грн</p>";
      if (val.availability > 0) {
        items += "<button id='inBasket' onclick='addToBasket("+val.id+")'>В корзину</button></div></div>";
      }else{
        items += "<p>Ожидается поставка</p></div></div>";
      }
    	
    });
  		$(".main").html(items);
  	});
});
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

function myEllipsis(text){
  if(text.length > 23){
    return text.substring(0, 20) + "...";
  }
  return text;
}