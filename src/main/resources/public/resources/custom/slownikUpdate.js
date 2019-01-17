$(document).ready(function(){

    var listaWzorcowa = [];
	var listaNowaSlow = [];
	var listaNowaPozycji = [];
	var listaDelta = [];


     $.ajax({
            type:    "GET",
			url:     "/resources/custom/slownik_stary.txt",
            // TO JEST BARZO WAZNE, inaczej nie dziala !!!
            xhrFields: {
			withCredentials: true
                        },
							   
            success: function(slownikPlik) {
				listaWzorcowa = slownikPlik.split("\n"); 
				console.log("stary wzorzec " + listaWzorcowa.length);
				
				 $.ajax({
						type:    "GET",
						url:     "/resources/custom/slownik_nowy.txt",
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
	
		for (var y=0; y < nowePozycje.length ; y++){
				var temp = nowePozycje[y].split(" ");
					for (var x=0; x < temp.length ; x++){
						if(temp[x].trim().length > 0){
								listaNowaSlow.push(temp[x].trim());
						}
					}
		}
				
		console.log("nowy slowa = " + listaNowaSlow.length);
	
		console.log("delta");
		var suma = wzor.concat(listaNowaSlow);
		listaDelta = Array.from(new Set(suma));		
		console.log("koniec == " + listaDelta);
		
		sortedArray = listaDelta.sort(); 
		
		var druk = "";
		for (var z=0; z < sortedArray.length ; z++){
			druk = druk + "</br>" + sortedArray[z];
		}
		
		$("#slownik").html(druk);
	}
			   

	
	function sleep(delay) {
        var start = new Date().getTime();
        while (new Date().getTime() < start + delay);
      }

});