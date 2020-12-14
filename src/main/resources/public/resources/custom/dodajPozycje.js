$(document).ready(function(){

	// filtr na tabelke historii
	 $("#szukajDodajTabelka").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#tabelka tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	
	$("#jednostkaLista li a").on("click", function(){
	    $("#jednostka").text($(this).html());
	});
	
	// tabelka historia dodaj ilosc
	$(document).on("focus","#zmienilosc", function() {
		$(this).addClass( "historiaPozycja" );
	});
	$(document).on("focusout","#zmienilosc", function() {
		$(this).removeClass( "historiaPozycja" );
	});

	$(document).on("focus","#tabelka tr", function() {
		//$(this).css("background-color":"orange");
		$(this).addClass( "historiaPozycja" );
		
	});
	$(document).on("focusout","#tabelka tr", function() {
		$(this).removeClass( "historiaPozycja" );
	});

	var nowaIosc = "";
	var pozycjaNr = "";
	$(document).on("focus","#ilosctd", function() { // czyli KLIK na kolumne z przyciskiem DODAJ zmiana ilosci historii
		//$(this).css("background-color":"orange");
		nowaIosc = $(this).prev().children().val();
		pozycjaNr = $(this).prev().prev().prev().prev().prev().prev().prev().text(); // pobranie pozycji z tabelki
		
	});
	
	$(document).on( "click", "#zmienIloscBaza", function(){ // przycisk dodaj ilosc
		$("#modalZapisDialog").removeClass("czerwonyText");
    	$("#modalZapisDialog").html("");
			
			var nowaIloscPowalidacji = $(this).walidujLiczbe(nowaIosc);
			console.log("yyyyyyy" + nowaIloscPowalidacji);
			console.log("yyyyyyy pozycjaNr " + pozycjaNr);

			if ( isNaN(nowaIloscPowalidacji) || nowaIloscPowalidacji == 0 ){
				$("#modalZapisDialog").addClass("czerwonyText");
            	$("#modalZapisDialog").html("To nie jest liczba dodatnia !");
            	$("#zapisDialog").modal("show");
				setTimeout(function () {$('#zamknijDialogZapisz').focus();}, 500);
			}
			else{
			//  rest na update ilosci dla numeru pozycji
			$(this).wyslijZmienIloscPozycjiJSON(pozycjaNr, nowaIloscPowalidacji )
			}
	
	});


	$(document).on( "click", "#zmienDodajPrzycisk", function(){ // przycisk zmien
            $("a")[1].click(); // kliknij tab Zmien
            var id = $(this).closest('tr').children('td:eq(1)').text().trim(); //pobierz ID pozycji z wiersza tabelki
            $("#wpiszNumerPozycji").val(id);
            $("#pobierzPozycjePoNumerzePrzycisk").click();
    });


	var numerPozycjiBazyDoHistori = 0;
	
	//walidacja pol
	var validacjaDodajTowar = "NOK";
	var validacjaDodajCenaB = "NOK";
	var validacjaDodajCenaN = "NOK";
	var validacjaDodajIlosc = "NOK";
	var validacjaUzytkownik = "NOK";
	
	$("#towar").focusout(function() {
			if ( $("#towar").val().length > 51 | $("#towar").val().trim().length == 0 | $("#towar").val().trim() == $("#towar").val().trim().toUpperCase() ) {
				$("#towar").addClass("czerwony");
				validacjaDodajTowar = "NOK";
			}
			else {
				$("#towar").removeClass("czerwony");
				validacjaDodajTowar = "OK";
				}
		});
	
	$("#cenaB").focusout(function() {
		var cenaB = $("#cenaB").val().trim();
		var walidacjaCenaB = $(this).walidujLiczbe(cenaB);
//		console.log("walidacjaCenaB " +walidacjaCenaB);

		if ( isNaN(walidacjaCenaB) ) {
			$("#cenaB").addClass("czerwony");
			validacjaDodajCenaB = "NOK";
		}
		else {
			$("#cenaB").removeClass("czerwony");
			validacjaDodajCenaB = "OK";
			}
	});
	
	$("#cenaN").focusout(function() {
		var cenaN = $("#cenaN").val().trim();
		var walidacjaCenaN = $(this).walidujLiczbe(cenaN);
		
		if ( isNaN(walidacjaCenaN) ) {
			$("#cenaN").addClass("czerwony");
			validacjaDodajCenaN = "NOK";
		}
		else {
			$("#cenaN").removeClass("czerwony");
			validacjaDodajCenaN = "OK";
			}
	});
	
	$("#ilosc").focusout(function() {
		var ilosc = $("#ilosc").val().trim();
		var walidacjaIlosc = $(this).walidujLiczbe(ilosc);
		
		if ( isNaN(walidacjaIlosc) ) {
			$("#ilosc").addClass("czerwony");
			validacjaDodajIlosc = "NOK";
		}
		else {
			$("#ilosc").removeClass("czerwony");
			validacjaDodajIlosc = "OK";
			}
	});
	
	$("#uzytkownik").focusout(function() {
		if ( $("#uzytkownik").val().trim().length == 0 | $("#uzytkownik").val().trim() == $("#uzytkownik").val().trim().toUpperCase() ) {
			$("#uzytkownik").addClass("czerwony");
			validacjaUzytkownik = "NOK";
		}
		else {
			$("#uzytkownik").removeClass("czerwony");
			validacjaUzytkownik = "OK";
			}
	});

	$("#jednostka").focusout(function() {
//		// dopiero po clicku zmienia wartosc !! dodac jakis  inny event ? houver out ?

	});
	
	
	// waliduj pola i zapisz do bazy
    $("#zapiszDoBazy").click(function(){
    	$("#jednostka").removeClass("czerwony");
    	var numerPozycjiDoHistori = 0;
    	$("#modalZapisDialog").removeClass("czerwonyText");
//    	$('#modalZapisDialog').removeClass("zielonyText");
    	if(validacjaDodajTowar == "NOK" | validacjaDodajCenaB == "NOK" | validacjaDodajCenaN == "NOK" | validacjaDodajIlosc == "NOK" | validacjaUzytkownik == "NOK" ){
    		var info ="!!! Niepoprawny wpis w jednym z pol !!!" + "<br/>"+ "wpisz jeszcze raz ...";
    		$("#modalZapisDialog").addClass("czerwonyText").html(info);	
    		$("#zapisDialog").modal("show");
			setTimeout(function () {$('#zamknijDialogZapisz').focus();}, 300);
    		if (validacjaDodajTowar == "NOK") {
    			$("#towar").addClass("czerwony");
    		}
    		if (validacjaDodajCenaB == "NOK") {
    			$("#cenaB").addClass("czerwony");
    		}
    		if (validacjaDodajCenaN == "NOK") {
    			$("#cenaN").addClass("czerwony");
    		}
    		if (validacjaDodajIlosc == "NOK") {
    			$("#ilosc").addClass("czerwony");
    		}
			if (validacjaUzytkownik == "NOK") {
    			$("#uzytkownik").addClass("czerwony");
    		}

    		return 0;
    	}
    	if(($("#towar").val().trim().length == 0) | ($("#cenaB").val().trim().length == 0) | ($("#cenaN").val().trim().length == 0) | ($("#ilosc").val().trim().length == 0) | ($("#ilosc").val() <= 0)){
    		var info ="!!! Niepoprawny wpis w jednym z pol !!!" + "<br/>"+ "wpisz jeszcze raz ...";
    		$("#modalZapisDialog").addClass("czerwonyText").html(info);
    		$("#zapisDialog").modal("show");
			setTimeout(function () {$('#zamknijDialogZapisz').focus();}, 300);
    		if ($("#towar").val().trim().length == 0) {
    			$("#towar").addClass("czerwony");
    		}
    		if ($("#cenaB").val().trim().length == 0) {
    			$("#cenaB").addClass("czerwony");
    		}
    		if ($("#cenaN").val().trim().length == 0) {
    			$("#cenaN").addClass("czerwony");
    		}
    		if ($("#ilosc").val().trim().length == 0 | ($("#ilosc").val() <= 0) ) {
    			$("#ilosc").addClass("czerwony");
    		}
//    		if ($("#jednostka").text().length > 5) {
//    			$("#jednostka").addClass("czerwony");
//    		}
    		return 0;
    	}
		 //console.log("ostatni czek");
		 
		var cenaBLiczba = $(this).konwertujDoLiczby($("#cenaB").val().trim());
		var cenaNLiczba = $(this).konwertujDoLiczby($("#cenaN").val().trim());
		
	console.log("ceny " +cenaBLiczba + "   " + cenaNLiczba);
		if ( (cenaBLiczba == 0) && (cenaNLiczba >= 0)  ) {
		        console.log("www true");
		        $("#cenaB").removeClass("czerwony");
        		$("#cenaN").removeClass("czerwony");
            	$(this).wyslijDodajJSON();
            return 0;
		}

		if ( ( cenaBLiczba <= cenaNLiczba ) ){
			$("#cenaB").addClass("czerwony");
			$("#cenaN").addClass("czerwony");
			//console.log("zle ceny");
		return 0;
		}
		
    	else {
			$("#cenaB").removeClass("czerwony");
			$("#cenaN").removeClass("czerwony");
    		$(this).wyslijDodajJSON();
    	}


	});

	$("#zamknijDialog").click(function(){
		if ( $("#modalZapisDialog").hasClass("czerwonyText") )  {
			// nic nie rob
		}
		else {
			$(this).wyszyscPola();
		}
	});	
   
   
   // FUNKCJE
	$.fn.zbierzPola = function() {
		var towarNaZywca = $("#towar").val().trim();
		var towar = towarNaZywca.charAt(0).toUpperCase() + towarNaZywca.slice(1);
		var cenaB = $("#cenaB").val().trim();
		var cenaN = $("#cenaN").val().trim();
		var ilosc = $("#ilosc").val().trim();
//		var jednostka = $("#jednostka").val();
		var jednostka = $("#jednostka").text().trim();
		var uzytkownik = $("#uzytkownik").val().trim();
		
		cenaB = $(this).konwertujDoLiczby(cenaB);
		cenaN = $(this).konwertujDoLiczby(cenaN);
		ilosc = $(this).konwertujDoLiczby(ilosc);
		
		var pola = '{"cena_netto":"'+cenaN+'","cena_brutto":"'+cenaB+'","nazwa_towaru":"'+towar+'","jednostka":"'+jednostka+'","ilosc":"'+ilosc+'","uzytkownik":"'+uzytkownik+'"}';
		return pola;
	}
   
   
  // json dodaj ilosc do pozycji 
   $.fn.wyslijZmienIloscPozycjiJSON = function(pozycja, iloscDoDodania) {
    
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
		//var delta = "{"+ pozycja + "," + iloscDoDodania + "}";
    	$("#modalZapisDialog").removeClass("czerwonyText");
    	$("#modalZapisDialog").html("");
    	
    $.ajax({
    	type: 'POST',
    	contentType: "application/json",
    	dataType: 'json',
    	//data: delta,
//    	data: '{"cenaNetto":"59","cenaBrutto":"34","nazwaTowaru":"aaa","jednostka":"m33b","ilosc":"12","uzytkownik":"2"}',
        url: '/remanent/rest/pozycje/dodajIloscDoPozycji/'+pozycja+'/'+iloscDoDodania,
        success: function (response) {
        	console.log("OK odebrany JSON z: /remanent/rest/pozycje/dodajIloscDoPozycji");
			console.log("odebrana pozycja po dodaj ilosc: " + response);
			$("#tabelka tr").removeClass( "historiaPozycja" );
                $("#modalZapisDialog").addClass("zielonyText");
            	$("#modalZapisDialog").html("Pozycja numer: "+ response.id +" zmieniona w bazie o "+ iloscDoDodania);
            	$("#zapisDialog").modal("show");
				$(this).aktualizujHistoriePoZmienIlosc(response);
				console.log("z bazy ilosc po zmianie to :" + response.ilosc)
				setTimeout(function () {$('#zamknijDialogZapisz').focus();}, 500);	
            
        },
        error: function (jqXHR, exception) {
        	console.log("a jednak");
        	console.log("e: "+exception);
        	
            var msg = $(this).getErrorMessage(jqXHR, exception);
            $("#modalZapisDialog").addClass("czerwonyText");
			$("#modalZapisDialog").html(msg);
			$('#zapisDialog').modal("show");
			setTimeout(function () {$('#zamknijDialogZapisz').focus();}, 1000);
        	},
    	});
    }
   
   $.fn.aktualizujHistoriePoZmienIlosc = function(pozycja){
   console.log("wwwwwwwwwww")
		console.log("aktualizuj historie wpisow po dodaj: " +pozycja); 
		var nrPozycji = pozycja.id;
		var d = new Date();
		var h = d.getHours();
		var m = d.getMinutes();
		if (m < 10) { m = "0"+m;}
		
		var nowyRow = "<td>"+h+" : "+m+"</td>"+
	 		"<td>"+pozycja.id+"</td>"+
	 		"<td>"+pozycja.nazwa_towaru+"</td>"+
	 		"<td>"+pozycja.cena_brutto+"</td>"+
	 		"<td>"+pozycja.cena_netto+"</td>"+
	 		"<td>"+pozycja.jednostka+"</td>"+
			"<td>"+pozycja.ilosc +"</td>"+
			
			"<td>"+
				
                        				"<input type="+"text" + " "+ "id="+"zmienilosc"+
                             				" style=" + "width:30px;color:DodgerBlue;padding:1px;border-radius:4px;padding-left:5px;margin-top:-2px;font:bold" +" >" +


                             				"</td>"+
                             				"<td id="+"ilosctd"+"           >"+

                             				"<button id="+"zmienIloscBaza"+" type="+"button"+
                             				" style=" + "width:60px;color:DodgerBlue;background-color:LightGray;padding:1px;border-radius:4px"+
                             				" <span></span>"+
                             				"<b>&nbsp;Dodaj&nbsp;</b><span" +"></span></button>"


                             				+"</td>" +

                             				"</td>"+
                                                                         				"<td id=''>"+

                                                                         				"<button id="+"zmienDodajPrzycisk"+" type="+"button"+
                                                                         				" style=" + "width:60px;color:DodgerBlue;background-color:LightGray;padding:1px;border-radius:4px"+
                                                                         				" <span></span>"+
                                                                         				"<b>&nbsp;Zmień&nbsp;</b><span" +"></span></button>"


                                                                         				+"</td>"+
                             				"</tr>"
			
			console.log("wwwwwwwwwww")
		console.log(" nowy row 111"+ nowyRow);
		$("#tabelka td").filter(function() {
			return $(this).text() == nrPozycji;
			}).parent('tr').html(nowyRow);
		
	}
	
// JSON DODAJ DO BAZY	
    $.fn.wyslijDodajJSON = function() {
    
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
    	var pola = $(this).zbierzPola();
    	console.log("zebrane pola: "+pola);
    	console.log("wyslam JSON do: /remanent/rest/pozycje/dodaj");
    	$("#modalZapisDialog").removeClass("czerwonyText");
    	$("#modalZapisDialog").html("");
    	
    $.ajax({
    	type: 'POST',
    	contentType: "application/json",
    	dataType: 'json',
    	data: pola,
//    	data: '{"cenaNetto":"59","cenaBrutto":"34","nazwaTowaru":"aaa","jednostka":"m33b","ilosc":"12","uzytkownik":"2"}',
        url: '/remanent/rest/pozycje/dodaj',
        success: function (response) {
        	console.log("OK odebrany JSON z: /remanent/rest/pozycje/dodaj");
            numerPozycjiBazyDoHistori = response;
            $(this).dodajWpisDoHistori(numerPozycjiBazyDoHistori);
//        	$('#modalZapislDialog').html("Pozycja wyslana do zapisu na baze danych ");
			$("#towar").focus();
			$("#slownikWynik").css("color","").text("");
        },
        error: function (jqXHR, exception) {
        	console.log("a jednak");
        	console.log("e: "+exception);
        	
            var msg = $(this).getErrorMessage(jqXHR, exception);
            $("#modalZapisDialog").addClass("czerwonyText");
			$("#modalZapisDialog").html(msg);
			$('#zapisDialog').modal("show");
			setTimeout(function () {$('#zamknijDialogZapisz').focus();}, 1000);
        	},
    	});
    }
    
    
	 $.fn.dodajWpisDoHistori = function(numerPozycjiBazyDoHistori) { 
		 var numerPozycjiBazyDoHistori = numerPozycjiBazyDoHistori;
		 console.log("numerPozycjiBazyDoHistori f historia "+numerPozycjiBazyDoHistori);
		 var d = new Date()
		 var h = d.getHours();
		 var m = d.getMinutes();
		 var towarNaZywca = $("#towar").val().trim();
		 var towar = towarNaZywca.charAt(0).toUpperCase() + towarNaZywca.slice(1);
		 
		 if (m < 10) { m = "0"+m;}
		
	 	$("#tabelka tr:first").before("<tr><td>"+h+" : "+m+"</td>"+
	 			"<td>"+numerPozycjiBazyDoHistori+"</td>"+
	 			"<td>"+towar+"</td>"+
	 			"<td>"+$("#cenaB").val().trim()+"</td>"+
	 			"<td>"+$("#cenaN").val().trim()+"</td>"+
	 			"<td>"+$("#jednostka").text()+"</td>"+
	 			"<td>"+$("#ilosc").val().trim()+"</td>"+
				"<td>"+


                             				"<input type="+"text" + " "+ "id="+"zmienilosc"+
                             				" style=" + "width:30px;color:DodgerBlue;padding:1px;border-radius:4px;padding-left:5px;margin-top:-2px;font:bold" +" >" +


                             				"</td>"+
                             				"<td id="+"ilosctd"+"           >"+

                             				"<button id="+"zmienIloscBaza"+" type="+"button"+
                             				" style=" + "width:60px;color:DodgerBlue;background-color:LightGray;padding:1px;border-radius:4px"+
                             				" <span></span>"+
                             				"<b>&nbsp;Dodaj&nbsp;</b><span" +"></span></button>"


                             				+"</td>" +

                             				"</td>"+
                                                                         				"<td id=''>"+

                                                                         				"<button id="+"zmienDodajPrzycisk"+" type="+"button"+
                                                                         				" style=" + "width:60px;color:DodgerBlue;background-color:LightGray;padding:1px;border-radius:4px"+
                                                                         				" <span></span>"+
                                                                         				"<b>&nbsp;Zmień&nbsp;</b><span" +"></span></button>"


                                                                         				+"</td>"+
                             				"</tr>"
				
					
				
				
				
	 		);
		
			
	 	$(this).wyszyscPolaPoUdanymWpisieDoBazy();
         }

	
	 $.fn.wyszyscPola = function() { 
        $("#towar").val('');
		$("#cenaB").val('');
		$("#cenaN").val('');
		$("#ilosc").val('');
		//$("#jednostka").text('- wybierz - ');	
		$("#jednostka").text('szt.');
      }
	  
	 $.fn.wyszyscPolaPoUdanymWpisieDoBazy = function() { 
        $("#towar").val('');
		$("#cenaB").val('');
		$("#cenaN").val('');
		$("#ilosc").val('');
		//$("#jednostka").text('- wybierz - ');	
		//$("#jednostka").text('szt.');
      }
	 
	 $('#zamknijDialogZapisz').click(function(){
		$("#towar").focus();
	
	});




});
$(document).ajaxStop($.unblockUI); 