$(document).ready(function(){

			//var t1 = new Date().getTime();
			//console.log("t1 - t2 start == ");

	$("#podpowiadaczLista").hide();
	
	var globalnaListaSlow = [];
	var uniqueItems = [];
	
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
			url:     "resources/custom/pozycje_rem.txt",
            // TO JEST BARZO WAZNE, inaczej nie dziala !!!
            xhrFields: {
			withCredentials: true
                        },
							   
            success: function(slownikPlik) {
				globalnaListaSlow = slownikPlik.split("\n");
				
				//console.log("wypisz slowa" + globalnaListaSlow.length);
				uniqueItems = Array.from(new Set(globalnaListaSlow));
				//console.log("wypisz slowa uniq" + uniqueItems.length);
				//console.log("podpowiadacz zaladowany");
				
				//var t2 = new Date().getTime();
				//var delta = t2-t1;
				//console.log("t delta == "+ delta);
            },

            error: function(jqXHR, exception) {
				//console.log("eeehhh");
				var msg = $(this).getErrorMessage(jqXHR, exception);
				$("#modalZapisDialog").addClass("czerwonyText");
				$("#modalZapisDialog").html(msg);
				$('#zapisDialog').modal("show");
			}		

        });
	
	

	
	$("#podpowiadaczCheckbox").click(function(){
		if($("#podpowiadaczCheckbox").is(":checked") == false){
		//console.log("OFF");
		$("#podpowiadaczText").html("podpowiadacz nieaktywny");
			globalnaListaSlow = [];
			uniqueItems = [];
		}
		if($("#podpowiadaczCheckbox").is(":checked") == true){
		//console.log("ON");
		$("#podpowiadaczText").html("podpowiadacz aktywny");

		
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
		
		
		
		    $.ajax({
            type:    "GET",
			url:     "resources/custom/pozycje_rem.txt",
            // TO JEST BARZO WAZNE, inaczej nie dziala !!!
            xhrFields: {
			withCredentials: true
                        },
							   
            success: function(slownikPlik) {
				globalnaListaSlow = slownikPlik.split("\n");
				
				//console.log("wypisz slowa" + globalnaListaSlow.length);
				uniqueItems = Array.from(new Set(globalnaListaSlow));
				//console.log("wypisz slowa uniq" + uniqueItems.length);
				//console.log("podpowiadacz zaladowany");
            },

            error: function(jqXHR, exception) {
				console.log("eeehhh");
				var msg = $(this).getErrorMessage(jqXHR, exception);
				$("#modalZapisDialog").addClass("czerwonyText");
				$("#modalZapisDialog").html(msg);
				$('#zapisDialog').modal("show");
			}		

        });
		
		
		}
			
	});
	
	
	 //console.log("podpowiedzi");
	 
		
		var wyniki = [];
		
		$("#towar").keyup(function(){
		
			if($("#towar").val().trim().length < 3){
				$("#podpowiadaczLista").hide();
			}
			
			if($("#towar").val().trim().length == 0){
			    	
			uniqueItems = Array.from(new Set(globalnaListaSlow));
			
			}
			
			var sortedArray = [];
			
			
			
			if($("#towar").val().trim().length == 3){
			
			//var n = new Date().getTime();
			//console.log("3333333 start == ");
			
				wyniki = [];
				var towar = $("#towar").val().trim().toLowerCase();
				sortedArray = uniqueItems.sort(function (a, b) {
					if (a.toLowerCase().startsWith(towar)) return -1;
					else if (!a.toLowerCase().startsWith(towar)) return 1;
					return 0;
				});
		
			//console.log("sortedArray length " + sortedArray.length);
			//console.log("sortedArray " + sortedArray);
			
			for(var i=0; i<  uniqueItems.length ;i++){
				//console.log("warunek " + uniqueItems[i].trim().toLowerCase().startsWith(towar) );				
					if( !uniqueItems[i].trim().toLowerCase().startsWith(towar)){
						break;		
					}					
						//console.log("trafiony");
					if(uniqueItems[i].trim().length > 8){
						wyniki.push(uniqueItems[i]);
					}
						//wyniki.push(uniqueItems[i]);
						//console.log("wynik " + wyniki.length);
						//console.log("wynik caly = " + wyniki);
				}
				//console.log("wynik " + wyniki.length);
				//var n2 = new Date().getTime();
				//var w = n2-n;
				//console.log("3333333 koniec ==" + w);
				//console.log("3333333 koniec ==" );
		}
			
		var noweWyniki =[];
			if($("#towar").val().trim().length > 3 ){
			
				//console.log("dzialamy z podpowiedziami");
				
				var towar = $("#towar").val().trim().toLowerCase();
				//console.log(" przed "+ towar);
	
				noweWyniki = [];
				for(var i=0; i<  wyniki.length ;i++){		
					
				//console.log("warunek " + wyniki[i].trim().toLowerCase().startsWith(towar) );				
					if( wyniki[i].trim().toLowerCase().startsWith(towar)){
						
						//console.log("trafiony");
						noweWyniki.push(wyniki[i]);
						
						//console.log("nowe wynik dlugosc " + noweWyniki.length);
						//console.log("nowe wynik caly = " + noweWyniki);
						
					}					
				}
				
				//console.log("nowe wynik dlugosc " + noweWyniki.length);
				
			// wyswietl podpowiadacz z wynikami
			var dodaj = ""; 
			var selectWys = "18"
			var row = 0;
			for(var w = 0 ; w < 10 ; w++){
				row = w;
				var u = noweWyniki[w];
					if( u == undefined){
						break;
					}		
			dodaj = dodaj + "<option>" + noweWyniki[w] + "</option>";
			}
			
			selectWys = selectWys * row;

				if(selectWys >17){ //przynajmniej jeden wynik do pokazania
					$("#podpowiadaczLista").height( selectWys )
					//console.log("dodaj " + dodaj);
					$("#podpowiadaczLista").html(dodaj);
					$("#podpowiadaczLista").show();
				}else{
					$("#podpowiadaczLista").hide();
				}
			
			}
			
		});
		
    $("#podpowiadaczLista").dblclick(function(){
		var doTowar = $("#podpowiadaczLista option:selected" ).text();
		$("#towar").val(doTowar);
		$("#podpowiadaczLista").hide();
		$("#slownikWynik").hide();
		$("#towar").focus();
	
	});
	
	
	
	$("#towar").keyup(function(e){
		if (e.keyCode == 40) { // w dol
		  $("#podpowiadaczLista").focus();  
		  $("#slownikWyniki").html("");
		  }   	
		if (e.keyCode == 27) { // escape 
		  $("#podpowiadaczLista").hide();
		  }		  
	});
	
	$("#podpowiadaczLista").keyup(function(e){      
		if (e.keyCode == 27) { // escape
		  $("#towar").focus();  
		  $("#podpowiadaczLista").hide();
		  }
		if (e.keyCode == 13) { // enter
		  var wartoscSelecta = $("#podpowiadaczLista option:selected").text();
		  console.log("wartoscSelecta "  + wartoscSelecta);
		  $("#towar").val(wartoscSelecta);
		  $("#towar").focus();  
		  $("#podpowiadaczLista").hide();
		  }
	});
	
	$("#podpowiadaczLista").keydown(function(e){      
		if (e.keyCode == 9) { // tab  
		  $("#podpowiadaczLista").hide();
		  }
	});
	
	/*
	$("#towar").keydown(function(e){      
		if (e.keyCode == 9) { // tab  
		
		  $("#podpowiadaczLista").hide();
		  //$("#towar").focusout();
		  $("#uzytkownik").focus();
		  }
	});
	
	

	*/
	
	
	$("#uzytkownik").focus(function(){
		$("#podpowiadaczLista").hide();
	});
		
	$("#cenaB").focus(function(){
		$("#podpowiadaczLista").hide();
	});
	
	$("#cenaN").focus(function(){
		$("#podpowiadaczLista").hide();
	});
	
	$("#ilosc").focus(function(){
		$("#podpowiadaczLista").hide();
	});
	
	$("#jednostka").focus(function(){
		$("#podpowiadaczLista").hide();
	});
	
	
	function sleep(delay) {
        var start = new Date().getTime();
        while (new Date().getTime() < start + delay);
      }
	
});
$(document).ajaxStop($.unblockUI); 
