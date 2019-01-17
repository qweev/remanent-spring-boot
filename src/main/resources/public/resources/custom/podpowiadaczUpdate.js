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
	
	
		console.log("delta nnnn");
		/*var suma = wzor.concat(nowePozycje);
		listaDelta = Array.from(new Set(suma));	// set nie dziala ?
		console.log("koniec == " + listaDelta);
		var sorted = listaDelta.sort();
		*/
		
		for(var i=0; i < wzor.length; i++){
		//console.log("for i " + wzor[i].trim() );
				for(var y=0; y < nowePozycje.length; y++){
				//console.log("for y " + nowePozycje[y].trim() );
					if(nowePozycje[y].trim() == wzor[i].trim() ){
					console.log("hit" +nowePozycje[y]);
						nowePozycje.splice(y,1);
						
					}
				}
		}
		
		var suma = wzor.concat(nowePozycje);
		var sorted = suma.sort();
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