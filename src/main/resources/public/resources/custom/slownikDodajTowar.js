$(document).ready(function(){

    var globalnaListaSlow = [];

	
		/*	
		$.blockUI({ 
					css: { 
						border: 'none', 
						padding: '15px', 
						backgroundColor: '#000', 
						'-webkit-border-radius': '10px', 
						'-moz-border-radius': '10px', 
						opacity: .5, 
						color: '#fff' 
					}
    			});
				*/
		
        $.ajax({
            type:    "GET",
			url:     "/resources/custom/slownik_out.txt",
            // TO JEST BARZO WAZNE, inaczej nie dziala !!!
            xhrFields: {
			withCredentials: true
                        },
							   
            success: function(slownikPlik) {
				globalnaListaSlow = slownikPlik.split("\n");
				//console.log("success  "+ globalnaListaSlow[4037]);
				//$(this).sprawdzPisownie(globalnaListaSlow);
            },

            error: function(jqXHR, exception) {
				console.log("eeehhh");
				var msg = $(this).getErrorMessage(jqXHR, exception);
				$("#modalZapisDialog").addClass("czerwonyText");
				$("#modalZapisDialog").html(msg);
				$('#zapisDialog').modal("show");
			}		

        });
	
	
	
    $("#towar").focusout(function(){
		$("#modalZapisDialog").removeClass("czerwonyText");
    	$("#modalZapisDialog").html("");
		$(this).sprawdzPisownie(globalnaListaSlow);

    })

  
         $.fn.sprawdzPisownie = function(tablica){
			globalnaListaSlow = tablica;
			var wynik = "SPRAWDŹ PISOWNIE :: ";
			
			var polaTowaru = $(this).podzielTowarNaPola();
					
			for (var i=0; i < polaTowaru.length ;i++){
				var zlapany = "false";
				for (var y=0; y < globalnaListaSlow.length ; y++){
						if (polaTowaru[i].trim().toLowerCase() == globalnaListaSlow[y].trim().toLowerCase() ){
							//console.log("mam cie " + polaTowaru[i]);
							zlapany = "true";
						}
				}
				console.log("flaga " + zlapany);
				if(zlapany == "false"){
					//console.log("zlapany");
					wynik = " " + wynik + " " + polaTowaru[i];
					}
            }

			if ( wynik.length > 20) {	
				$("#slownikWynik").css("color","red").text(wynik);
			}
			else {
				$("#slownikWynik").css("color","").text("");
			}
		}  

		$.fn.podzielTowarNaPola = function() {
			var pola = $("#towar").val().split(" ");
			console.log("aaaaa  "+pola);
			return pola;
		}

               

               

});