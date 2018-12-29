
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

function addToFavorite(f){
    var target ="/profile/favorite/add?id=" + f;
    $.ajax({
            url: target
            , type:'GET'
            , success: function() {
                location.reload();
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
    href = href.substring(href.indexOf("/books"), ((href.indexOf("&page")>0)?href.indexOf("&page"):href.length));
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