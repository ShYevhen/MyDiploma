$(".visibility").slideUp();
var chek=false;

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

// var image = "";
// File.prototype.convertToBase64 = function(callback){
//   var reader = new FileReader();
//   reader.onloadend = function (e) {
//     callback(e.target.result, e.target.error);
//   };   
//   reader.readAsDataURL(this);
// };

// $("#picture").on('change',function(){
//   var selectedFile = this.files[0];
//   selectedFile.convertToBase64(function(base64){
//    image = base64;
//  }) 
//   $this = $(this);
//   if($this.val().length == 0) {
//     $('#file').text("Выбрать картинку");
//   }else{
//     var fileName = $this.val();
//     fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length);
//     if(fileName.length>17){
//       fileName = fileName.substring(0, 15) + "...";
//     }
//     $('#file').text(fileName);
//   }
// });


function addNewBook(form){
  var authors = $(form.authors).val();
  if(form.name.value == "" || authors.length < 1 || form.genre.value == "" || form.imprintDate.value < 1000 || 
    form.pages.value == "" || form.bookCover.value == "" || form.language.value == "" || form.price.value == "" ||
    form.picture.value == "" || form.description.value == ""){
      alert("Заполните все обязательные поля в соответствующем формате!");
      return false;
  }

  var bookJson = "{\"name\":\""+form.name.value+"\",";
  bookJson += "\"authors\":[";
  for (var i = 0; i < authors.length; i++) {
    bookJson += i==0?"{":",{";
    var items = authors[i].split(",");
    for (var j = 0; j < items.length; j++) {
      var iValue = items[j].split(":");
      bookJson += (j==0?"\"":",\"")+iValue[0]+"\":\""+iValue[1]+"\"";
    }
    bookJson += "}";
  }
  bookJson += "],\"genre\":\""+form.genre.value+"\",";
  if(form.bookSeries.value != "" && form.bookInSeries.value >= 0){
    bookJson += "\"bookSeries\":\""+form.bookSeries.value+"\",";
    bookJson += "\"bookInSeries\":\""+form.bookInSeries.value+"\",";
  }else if(form.bookSeries.value != "" && form.bookInSeries.value < 0){
    alert("Укажите номер книги в серии!");
    return false;
  }
  bookJson += "\"imprintDate\":\""+form.imprintDate.value+"\",";
  bookJson += "\"pages\":\""+form.pages.value+"\",";
  bookJson += "\"bookCover\":\""+form.bookCover.value+"\",";
  bookJson += "\"language\":\""+form.language.value+"\",";
  bookJson += "\"price\":\""+form.price.value.replace(",",".")+"\",";
  if(form.availability.value >= 0){
    bookJson += "\"availability\":\""+form.availability.value+"\",";
  }
  bookJson += "\"image\":\""+form.picture.value+"\",";
  bookJson += "\"description\":\""+form.description.value.replace(/[\\]/g, "\\\\")
    .replace(/[\/]/g, "\\/")
    .replace(/[\n]/g, "\\n")
    .replace(/\\'/g, "\\'")
    .replace(/[\"]/g, '\\"')
    .replace(/\\&/g, "\\&")
    .replace(/[\r]/g, "\\r")
    .replace(/[\t]/g, "\\t")
    .replace(/[\b]/g, "\\b")
    .replace(/[\f]/g, "\\f") + "\",";
  bookJson += "\"visible\":\""+form.visible.value+"\"}";
  $.ajax({
    type: "POST",
    url: "/books/add",
    data: bookJson,
    complete: function(resp){
      if(resp.status == 202){
        alert("Книга добавлена.");
        window.location.replace("/books?id=" + resp.responseText);
      } else if(resp.status == 412){
        alert("Ошибка! Книга уже существует.");
      } else if(resp.status >= 400){
        alert("Ошибка " + resp.status + "!");
      }
    },
    dataType: "json",
    contentType: "application/json"
  });
  return false;
}

var authors = [];
$("#authors").on('change',function(){
  authors = $("#authors").val();
});

function changeBook(id,form,visible){
  if(form.name.value == "" && authors.length < 1 && form.genre.value == "" && form.imprintDate.value == "" && 
    form.pages.value == "" && form.bookCover.value == "" && form.language.value == "" && form.price.value == "" &&
    form.picture.value == "" && form.description.value == "" && form.bookSeries.value == "" && form.bookInSeries.value == "" && 
    form.availability.value =="" && form.visible.value == ""){
      alert("Вы не внесли изменений!");
      return false;
  }
  var changeJson = "{\"id\":\"" + id + "\"";
  if(form.name.value != ""){
    changeJson += ",\"name\":\"" + form.name.value + "\"";
  }
  if(authors.length >= 1){
    changeJson += ",\"authors\":[";
    for (var i = 0; i < authors.length; i++) {
      changeJson += i==0?"{":",{";
      var items = authors[i].split(",");
      for (var j = 0; j < items.length; j++) {
        var iValue = items[j].split(":");
        changeJson += (j==0?"\"":",\"")+iValue[0]+"\":\""+iValue[1]+"\"";
      }
      changeJson += "}";
    }
    changeJson += "]";
  }
  if(form.genre.value != ""){
    changeJson += ",\"genre\":\"" + form.genre.value + "\"";
  }
  if(form.bookSeries.value != ""){
    changeJson += ",\"bookSeries\":\"" + form.bookSeries.value + "\"";
  }
  if(form.bookInSeries.value != "" && form.bookInSeries.value >= 0){
    changeJson += ",\"bookInSeries\":\"" + form.bookInSeries.value + "\"";
  }else{
    changeJson += ",\"bookInSeries\":\"-1\"";
  }
  changeJson += ",\"imprintDate\":\"" + ((form.imprintDate.value != "")?form.imprintDate.value:-1) + "\"";
  changeJson += ",\"pages\":\"" + ((form.pages.value > 0)?form.pages.value:-1) + "\"";
  if(form.bookCover.value != ""){
    changeJson += ",\"bookCover\":\"" + form.bookCover.value + "\"";
  }
  if(form.language.value != ""){
    changeJson += ",\"language\":\"" + form.language.value + "\"";
  }
  if(form.price.value != ""){
    changeJson += ",\"price\":\"" + form.price.value.replace(",",".") + "\"";
  }
  if(form.availability.value != "" && form.availability.value >= 0){
    changeJson += ",\"availability\":\"" +form.availability.value + "\"";
  }else{
    changeJson += ",\"availability\":\"-1\"";
  }
  changeJson += ",\"visible\":\"" +((form.visible.value != "")?form.visible.value:visible) + "\"";
  if(form.description.value != ""){
    changeJson += ",\"description\":\"" + form.description.value.replace(/[\\]/g, "\\\\")
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
  if(form.picture.value != ""){
    changeJson += ",\"image\":\"" + form.picture.value + "\"";
  }
  changeJson += "}";

  $.ajax({
    type: "POST",
    url: "/books/update",
    data: changeJson,
    complete: function(resp){
      if(resp.status == 202){
        alert("Данные успешно изменены.");
        window.location.replace("/books?id=" + id);
      } else if(resp.status == 412){
        alert("Ошибка! Книга не существует.");
      } else if(resp.status >= 400){
        alert("Ошибка " + resp.status + "!");
      }
    },
    dataType: "json",
    contentType: "application/json"
  });
  return false;
}

function addToBasket(f){
	var target ="/basket/add?id=" + f;
	$.ajax({
    url: target
    , type:'GET'
    , success: function() {
      window.location.reload();
    }

  });
  return false;
}

function addToFavorite(f){
  var target ="/profile/favorite/add?id=" + f;
  $.ajax({
    url: target
    , type:'GET'
    , success: function() {
      window.location.reload();
    }

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
  for (var selector in config) {
    $(selector).chosen(config[selector]);
  }
};


