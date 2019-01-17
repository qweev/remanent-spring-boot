$(document).ready(function(){

    var listaWzorcowa = [];
	var listaNowaPozycji = [];
	var listaDelta = [];


     $.ajax({
            type:    "GET",
			url:     "/resources/custom/podpowiadacz_stary.txt",
            // TO JEST BARZO WAZNE, inaczej nie dziala !!!
            xhrFields: {
			withCredentials: true
                        },
							   
            success: function(slownikPlik) {
				listaWzorcowa = slownikPlik.split("\n"); 
				console.log("stary wzorzec " + listaWzorcowa.length);
				
				 $.ajax({
						type:    "GET",
						url:     "/resources/custom/podpowiadacz_nowy.txt",
						// TO JEST BARZO WAZNE, inaczej nie dziala !!!
						xhrFields: {
						withCredentials: true
									},
										   
						success: function(slownikPlik) {
							listaNowaPozycji = slownikPlik.split("\n");
							console.log("nowy pozycje = " + listaNowaPozycji.length);
							
								//console.log("czekaj 5 sekund");   
								//sleep(10000);
								delta(listaWzorcowa, listaNowaPozycji);
							
						},

						error: function(jqXHR, exception) {
							console.log("eeehhh");
						}		

				});
				
				
            },

            error: function(jqXHR, exception) {
				console.log("eeehhh");
			}		

        });


		
		
		
			   
	function delta(wzor, nowePozycje){
	
	
		console.log("delta");
		var suma = wzor.concat(nowePozycje);
		listaDelta = Array.from(new Set(suma));		
		console.log("koniec == " + listaDelta);
		var sorted = listaDelta.sort();
		
		var druk = "";
		for (var z=0; z < sorted.length ; z++){
			druk = druk + "</br>" + sorted[z];
		}
		
		$("#slownik").html(druk);
	}
			   
	
	function sleep(delay) {
        var start = new Date().getTime();
        while (new Date().getTime() < start + delay);
      }

});