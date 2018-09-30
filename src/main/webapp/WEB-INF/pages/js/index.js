var formData = JSON.stringify($("#login-form").serializeArray());
$.ajax({
  type: "POST",
  url: "/login",
  data: formData,
  success: function(){},
  dataType: "json",
  contentType : "application/json"
});

function show()  
        {  
            $.ajax({  
                url: "index.html",  
                cache: false, 
     dataType: "json",
                success: function(data){ 
        var json= $parseJson(data) 
    }
            });  
        };
       var $select = $('#jsonId');
       $select.append(json)
        $(document).ready(function(){  
            show();  
            setInterval('show()',1000);  
        });


function boxForm(f) {
		if (f.confirm.checked)
			f.send.disabled = 0
		else
			f.send.disabled = 1
	}
function checkPass(f) {
		if (f.user_password.value != f.r_password.value) {
			alert('Passwords don\'t match!')
			return false;
		} else {
			f.r_password.disabled = 1
			return true;
		}
	}

