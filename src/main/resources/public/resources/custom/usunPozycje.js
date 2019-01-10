$(document).ready(function(){
	
	var validacjaNumerDoUsuniecia = "NOK";
	var numerDoUsunieciaZBazy = 0;
	
	$("#wpiszNumerPozycjiDoUsuniecia").focusout(function() {
		var numerDoUsuniecia = $("#wpiszNumerPozycjiDoUsuniecia").val();
		var walidacjaNumerDoUsuniecia = $(this).walidujNumer(numerDoUsuniecia);
		var dlugoscPola = $("#wpiszNumerPozycjiDoUsuniecia").val().length
		
		if ( isNaN(walidacjaNumerDoUsuniecia) | dlugoscPola == 0 | walidacjaNumerDoUsuniecia <= 0 ) {
			$("#wpiszNumerPozycjiDoUsuniecia").addClass("czerwony");
			$("#pobierzDoUsunPrzycisk").addClass("czerwony");
			validacjaNumerDoUsuniecia = "NOK";
		}
		else {
			$("#wpiszNumerPozycjiDoUsuniecia").removeClass("czerwony");
			$("#wpiszNumerPozycjiDoUsuniecia").removeClass("czerwonyText");	
			$("#pobierzDoUsunPrzycisk").removeClass("czerwony");
			validacjaNumerDoUsuniecia = "OK";
			}
		console.log("validacjaNumerDoUsuniecia na koniec focus "+validacjaNumerDoUsuniecia);
	});
	
	
	
	
	$("#pobierzDoUsunPrzycisk").click(function() {
		console.log("clik na pobierzDoUsunPrzycisk");
		console.log("validacjaNumerDoUsuniecia po click "+validacjaNumerDoUsuniecia);
		$("#divPrzyciskUsun").removeClass("in");
		$("#modalUsunDialog").removeClass("czerwonyText");
		$("#modalUsunDialog").removeClass("zielonyText");
		$("#modalUsunDialog").html("");
		$(this).czyscTabeleUsun();
		
		if (validacjaNumerDoUsuniecia == "NOK"){
			console.log("walidacja NOK");
			$("#modalUsunDialog").addClass("czerwonyText");
			$("#modalUsunDialog").html("Zły numer pozycji, wpisz jeszcze raz !");
			$("#usunDialog").modal("show");
			setTimeout(function () {$('#zamknijDialogUsun').focus();}, 300);
		}
		else {
//			$("#modalUsunDialog").addClass("zielonyText");
			$(this).szukajDoUsunJSON();
		}
		
	});
	
	
	
	$("#usunZBazy").click(function(){
		console.log("klik na uzun z bazy");
		$(this).usunZBazyJSON();
		
		
	});
	
	//FUNKCJE

	
	// JSON do usuniecia z bazy
		$.fn.usunZBazyJSON = function(){
			
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
	    	
			
			$("#modalUsunDialog").html("");
			$("#modalUsunDialog").removeClass("zielonyText");
			$("#modalUsunDialog").removeClass("czerwonyText");
	    	
			$.ajax({
		    	type: 'GET',
		        url: '/remanent/rest/pozycje/usun/'+numerDoUsunieciaZBazy,
		        success: function (response) {
		            console.log("usun szukaj numer : "+response);
		            console.log("OK odebrany JSON z: /remanent/rest/pozycje/usun/");
		            	if (response == numerDoUsunieciaZBazy){
		            		$("#modalUsunDialog").addClass("zielonyText");
		            		$("#modalUsunDialog").html("Pozycja o numerze: " + response +" usunięta z bazy" );
				            $("#divPrzyciskUsun").removeClass("in");
				            $(this).czyscTabeleUsun();
				            $("#usunDialog").modal("show");
							$(this).aktualizujHistoriePoUsun();
							setTimeout(function () {$('#zamknijDialogUsun').focus();}, 300);
		            	}
		            	else {
		            		console.log("Usunięto pozycję o złym numerze");
		            		$("#modalUsunDialog").addClass("czerwonyText");
		            		$("#modalUsunDialog").html("Usunięto pozycję o złym numerze. Numer wysłany to: " + numerDoUsunieciaZBazy +
		            				" Numer usunięty to: "+response);
		            		$("#usunDialog").modal("show");
							setTimeout(function () {$('#zamknijDialogUsun').focus();}, 300);
		            	}
		            
		            
		        },
		        error: function (jqXHR, exception) {
		            var msg = $(this).getErrorMessage(jqXHR, exception);
		            $("#modalUsunDialog").addClass("czerwonyText");
					$("#modalUsunDialog").html(msg);
					$("#usunDialog").modal("show");
					setTimeout(function () {$('#zamknijDialogUsun').focus();}, 300);
		        	},
		    	});
		   
		}
	
	// JSON	do szukania pozycji
	    $.fn.szukajDoUsunJSON = function() {

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
	    	
	    	$('#brakPozycjiDoUsuniecia').html("");
	    	var numerDoUsuniecia = $("#wpiszNumerPozycjiDoUsuniecia").val();
	    	console.log("numerDoUsuniecia: "+numerDoUsuniecia);
	    	console.log("wyslam JSON do: /remanent/rest/pozycje/usun/szukaj/");
	    	$("#modalUsunDialog").removeClass("zielonyText");
	    	$("#modalUsunDialog").removeClass("czerwonyText");
	    	
	    $.ajax({
	    	type: 'GET',
	        url: '/remanent/rest/pozycje/usun/szukaj/'+numerDoUsuniecia,
	        success: function (response) {
	            console.log("usun szukaj numer : "+response.id);
	            console.log("usun szukaj nazwa : "+response.nazwa_towaru);
	            console.log("OK odebrany JSON z: /remanent/rest/pozycje/usun/szukaj/");
	            $(this).czyscTabeleUsun();
	            $("#tabelkaUsun").html("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
	            $(this).pobiezDoUsuniecia(response);
	        },
	        error: function (jqXHR, exception) {
	            var msg = $(this).getErrorMessage(jqXHR, exception);
	            $("#modalUsunDialog").addClass("czerwonyText");
				$("#modalUsunDialog").html(msg);
				$("#usunDialog").modal("show");
				setTimeout(function () {$('#zamknijDialogUsun').focus();}, 300);
	        	},
	    	});
	    }
	    
	    $.fn.pobiezDoUsuniecia = function(response) {
            $("#modalUsunDialog").removeClass("czerwonyText");
            $("#modalUsunDialog").html("");
	    	if (response.id == 0){
	            $("#modalUsunDialog").addClass("czerwonyText");
	            $("#modalUsunDialog").html("Nie ma takiej pozycji w bazie");
	            numerDoUsuniecia = $("#brakPozycjiDoUsuniecia").html("<b>Nie ma takiej pozycji w bazie</b>");
	            $("#usunDialog").modal("show");
				setTimeout(function () {$('#zamknijDialogUsun').focus();}, 300);
	    	}
	    	else {
	            numerDoUsunieciaZBazy = $("#wpiszNumerPozycjiDoUsuniecia").val();
			    $(this).wyswietlPozycje(response);
	    	}
	    }
	    
		$.fn.aktualizujHistoriePoUsun = function(){
			console.log("aktualizuj historie wpisow");
			var nrPozycji = $("#wpiszNumerPozycjiDoUsuniecia").val().trim();
	
			var d = new Date();
			var h = d.getHours();
			var m = d.getMinutes();
			if (m < 10) { m = "0"+m;}
				
			console.log("akt his po usun row "+ nrPozycji);
			$("#tabelka td").filter(function() {
				return $(this).text() == nrPozycji;
				}).parent('tr').css("color","red");
			
		}
		
		
	    $.fn.wyswietlPozycje = function(pozycja) {
		 	$("#tabelkaUsun tr:first").before("<tr><td>"+pozycja.id+"</td>"+
		 			"<td>"+pozycja.nazwa_towaru+"</td>"+
		 			"<td>"+pozycja.cena_brutto+"</td>"+
		 			"<td>"+pozycja.cena_netto+"</td>"+
		 			"<td>"+pozycja.jednostka+"</td>"+
		 			"<td>"+pozycja.ilosc+"</td>"+
		 			"<td>"+pozycja.uzytkownik+"</td></tr>"
		 		);
		 	
		 	$("#divPrzyciskUsun").addClass("in");
		 	
	    }
		
	    $.fn.czyscTabeleUsun = function (){
	    	$('#tabelkaUsun').html("");
	    }
		
	$('#zamknijDialogUsun').click(function(){
		$("#wpiszNumerPozycjiDoUsuniecia").focus();
	});
	
});
$(document).ajaxStop($.unblockUI); 