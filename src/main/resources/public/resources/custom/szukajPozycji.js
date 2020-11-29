$(document).ready(function(){

	// tabelka szukaj - dodaj ilosc
	$(document).on("focus","#zmieniloscPoSzukaj", function() {
		$(this).addClass( "historiaPozycja" );
	});
	$(document).on("focusout","#zmieniloscPoSzukaj", function() {
		$(this).removeClass( "historiaPozycja" );
	});

	$(document).on("focus","#tabelkaSzukaj tr", function() {
		$(this).addClass( "historiaPozycja" );

	});
	$(document).on("focusout","#tabelkaSzukaj tr", function() {
		$(this).removeClass( "historiaPozycja" );
	});


	wyczyscZaawansowaneNaRefresh();
	function wyczyscZaawansowaneNaRefresh(){
		console.log("czyscimy zaawansowane");
		$("#zaaNazwaTowaru").prop("checked", false);
		$("#zaaUzytkownik").prop("checked", false);
		$("#zaaCenaNetto").prop("checked", false);
		$("#zaaCenaBrutto").prop("checked", false);
		$("#zaaIlosc").prop("checked", false);
		$("#zaaNazwaTowaruPole").val("");
		$("#zaaUzytkownikPole").val("");
		$("#zaaCenaNettoPole").val("");
		$("#zaaCenaBruttoPole").val("");
		$("#zaaIloscPole").val("");
		$("#zaaCenaBruttoRadio1").prop("checked", true);
		$("#zaaCenaNettoRadio1").prop("checked", true);
		$("#zaaIloscRadio1").prop("checked", true);
	}
	
	var radioValue = "nazwa";
	var validacjaopcjaCenaNetto = "OK";
	var validacjaOpcjaCenaBrutto = "OK";
	var validacjaOpcjaIlosc = "OK";
	var validacjaNazwaTowaru = "OK";
	var validacjaUzytkownik = "OK";
	
	$("#szukajPozycjiPrzycisk").click(function() {
		var pobranaNazwa = $("#szukajPozycji").val().trim();
		console.log("na click "+ pobranaNazwa);
		$("#modalSzukajDialog").removeClass("czerwonyText");
		$("#modalSzukajDialog").html("");
			if ( pobranaNazwa.length == 0 ) {
				$("#szukajPozycji").addClass("czerwony");
				$("#modalSzukajDialog").addClass("czerwonyText");
				$("#modalSzukajDialog").html("nic nie wpisane do szukania");
				$("#szukajDialog").modal("show");
				setTimeout(function () {$('#zamknijDialogSzukaj').focus();}, 300);
			}
			else {
				$("#szukajPozycji").removeClass("czerwony");
				$(this).szukajPozycjiJSON(pobranaNazwa);				
			}
	});
	
	$("#szukajPozycji").focusout(function() {
		$("#szukajPozycji").removeClass("czerwony");					
	});
	

	$('input:radio[name=radioButton]').click(function() {
		  radioValue = $('input:radio[name=radioButton]:checked').val();
		  console.log("radio :" + radioValue);
		});
	
	// filtr na tabelke
	 $("#szukajSzukajTabelka").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#tabelkaSzukaj tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	$("#przyciskSzukanieZaawansowane").click(function() {
		if ($("#przyciskSzukanieZaawansowane").attr("aria-expanded") == "true"){
		console.log("zaawansowane niewidoczne")
		$(this).wyczyscZaawansowane();
		}
	
	});
	
	
	$("#zaaSzukajPrzycisk").click(function(){
		console.log("click zaawansowane");
		$("#modalSzukajDialog").html("");
    	$("#modalSzukajDialog").html("");
    	$("#modalSzukajDialog").removeClass("czerwonyText");
		var pusteWszystkieCheckboxy = $(this).czyWszystkieCheckboxyPuste();
		console.log("bb = " + pusteWszystkieCheckboxy);
		if (pusteWszystkieCheckboxy == false){
			$(this).wyslijZaawansowaneSzukanieJSON();
		}
		else{
			console.log("puste wszystkie pola w szukaniu zaa");
			$("#modalSzukajDialog").addClass("czerwonyText");
			$("#modalSzukajDialog").html("puste wszystkie pola w szukaniu zaawansowanym !");
			$("#szukajDialog").modal("show");
			setTimeout(function () {$('#zamknijDialogSzukaj').focus();}, 300);
			
		}
	});
	
	$("#zaaCenaNettoPole").focusout(function() {
		if ( $("#zaaCenaNetto").is(":checked") == true ){
			var opcjaCenaNetto = $("#zaaCenaNettoPole").val().trim();
			validacjaopcjaCenaNetto = $(this).walidujNumerZaa(opcjaCenaNetto,"#zaaCenaNettoPole" );
		}
	});
	
	$("#zaaCenaBruttoPole").focusout(function() {
		if ( $("#zaaCenaBrutto").is(":checked") == true ){
			var opcjaCenaBrutto = $("#zaaCenaBruttoPole").val().trim();
			validacjaOpcjaCenaBrutto = $(this).walidujNumerZaa(opcjaCenaBrutto,"#zaaCenaBruttoPole" );
		}
	});
	
	
	$("#zaaIloscPole").focusout(function() {
		if ( $("#zaaIlosc").is(":checked") == true ){
			var opcjaIlosc = $("#zaaIloscPole").val().trim();
			validacjaOpcjaIlosc = $(this).walidujNumerZaa(opcjaIlosc,"#zaaIloscPole" );
		}
	});
	
	$("#zaaNazwaTowaruPole").focusout(function() {
		if ( $("#zaaNazwaTowaru").is(":checked") == true ){
			var opcjaNazwaTowaru = $("#zaaNazwaTowaruPole").val().trim();
			if ( opcjaNazwaTowaru.length == 0 ) {
				validacjaNazwaTowaru = "NOK";
				$("#zaaNazwaTowaruPole").addClass("czerwony");
			}
			else {
				$("#zaaNazwaTowaruPole").removeClass("czerwony");	
				validacjaNazwaTowaru = "OK";
			}
		}
		else {
		$("#zaaNazwaTowaruPole").removeClass("czerwony");	
		validacjaNazwaTowaru = "OK";
		}
	});
	
	$("#zaaUzytkownikPole").focusout(function() {
		if ( $("#zaaUzytkownik").is(":checked") == true ){
			var opcjaUzytkownik = $("#zaaUzytkownikPole").val().trim();
			if ( opcjaUzytkownik.length == 0 ) {
				validacjaUzytkownik = "NOK";
				$("#zaaUzytkownikPole").addClass("czerwony");
			}
			else {
				$("#zaaUzytkownikPole").removeClass("czerwony");	
				validacjaUzytkownik = "OK";
			}
		}
		else {
		$("#zaaUzytkownikPole").removeClass("czerwony");	
		validacjaUzytkownik = "OK";
		}
	});
	
	$("#zaaIlosc").click(function() {
		if ( $("#zaaIlosc").is(":checked") == true ){
			var opcjaIlosc = $("#zaaIloscPole").val().trim();
			validacjaOpcjaIlosc = $(this).walidujNumerZaa(opcjaIlosc,"#zaaIloscPole" );
		}
		else {
		$("#zaaIloscPole").removeClass("czerwony");	
		validacjaOpcjaIlosc = "OK";
		}
	});
	
	$("#zaaCenaNetto").click(function() {
		if ( $("#zaaCenaNetto").is(":checked") == true ){
			var opcjaCenaNetto = $("#zaaCenaNettoPole").val().trim();
			validacjaOpcjaCenaNetto = $(this).walidujNumerZaa(opcjaCenaNetto,"#zaaCenaNettoPole" );
		}
		else {
		$("#zaaCenaNettoPole").removeClass("czerwony");	
		validacjaOpcjaCenaNetto = "OK";
		}
	});
		
	$("#zaaCenaBrutto").click(function() {
		if ( $("#zaaCenaBrutto").is(":checked") == true ){
			var opcjaCenaBrutto = $("#zaaCenaBruttoPole").val().trim();
			validacjaOpcjaCenaBrutto = $(this).walidujNumerZaa(opcjaCenaBrutto,"#zaaCenaBruttoPole" );
		}
		else {
		$("#zaaCenaBruttoPole").removeClass("czerwony");	
		validacjaOpcjaCenaBrutto = "OK";
		}
	});
	
	$("#zaaNazwaTowaru").click(function() {
		if ( $("#zaaNazwaTowaru").is(":checked") == true ){
			var opcjaNazwaTowaru = $("#zaaNazwaTowaruPole").val().trim();
			if ( opcjaNazwaTowaru.length == 0 ) {
				validacjaNazwaTowaru = "NOK";
				$("#zaaNazwaTowaruPole").addClass("czerwony");
			}
		}
		else {
		$("#zaaNazwaTowaruPole").removeClass("czerwony");	
		validacjaNazwaTowaru = "OK";
		}
	});
	
	$("#zaaUzytkownik").click(function() {
		if ( $("#zaaUzytkownik").is(":checked") == true ){
			var opcjaNazwaTowaru = $("#zaaUzytkownikPole").val().trim();
			if ( opcjaNazwaTowaru.length == 0 ) {
				validacjaUzytkownik = "NOK";
				$("#zaaUzytkownikPole").addClass("czerwony");
			}
		}
		else {
		$("#zaaUzytkownikPole").removeClass("czerwony");	
		validacjaUzytkownik = "OK";
		}
	});
	
	//FUNKCJE

	//JSON zaawansowane szukanie
	$.fn.wyslijZaawansowaneSzukanieJSON = function() {
		$("#modalSzukajDialog").removeClass("czerwonyText");
		$("#modalSzukajDialog").html("");
		var opcje = $(this).zbierzZaaOpcje();
		if ( opcje == "NOK" ) {
		    $("#modalSzukajDialog").addClass("czerwonyText");
			$("#modalSzukajDialog").html("walidajca zaawansowane szukanie");
			$("#szukajDialog").modal("show");
			setTimeout(function () {$('#zamknijDialogSzukaj').focus();}, 300);
		}
		else {
			$(this).wyslijZaaSzukanieJSON(opcje);
			console.log("wyslijZaawansowaneSzukanieJSON");
		}
	
	}
	
	
	$.fn.czyWszystkieCheckboxyPuste = function() {
		if(
		$("#zaaNazwaTowaru").is(":checked") == false &&
		$("#zaaUzytkownik").is(":checked") == false &&
		$("#zaaCenaBrutto").is(":checked") == false &&
		$("#zaaCenaNetto").is(":checked") == false &&
		$("#zaaIlosc").is(":checked") == false
		){return true;}
		else {return false;}
	}
	
	$.fn.zbierzZaaOpcje = function() {
		var opcje= "";
		console.log("zbieram opcje");
		
		var opcjaTowar = "false";
		var opcjaUzytkownik = "false";
		var opcjaCenaNetto = 0;
		var opcjaCenaBrutto = 0;
		var opcjaIlosc = 0;
		var radioNetto = 0;
		var radioBrutto = 0;
		var radioIlosc = 0;
		
		if (  $("#zaaNazwaTowaru").is(":checked") == true ){
				opcjaTowar = $("#zaaNazwaTowaruPole").val().trim();
		}
		
		if ( $("#zaaUzytkownik").is(":checked") == true ){
				opcjaUzytkownik = $("#zaaUzytkownikPole").val().trim();
		}
		
		if ( $("#zaaCenaNetto").is(":checked") == true ){
				opcjaCenaNetto = $("#zaaCenaNettoPole").val().trim();
				validacjaopcjaCenaNetto = $(this).walidujNumerZaa(opcjaCenaNetto,"#zaaCenaNettoPole" );
				console.log("validacjaopcjaCenaNetto " + validacjaopcjaCenaNetto);
				opcjaCenaNetto = $(this).konwertujDoLiczby(opcjaCenaNetto);

				radioNetto = $('input:radio[name=szukajCenaNettoRadio]:checked').val();
		}
		
		if ( $("#zaaCenaBrutto").is(":checked") == true ){
				opcjaCenaBrutto = $("#zaaCenaBruttoPole").val().trim();
				validacjaOpcjaCenaBrutto = $(this).walidujNumerZaa(opcjaCenaBrutto,"#zaaCenaBruttoPole" );
				console.log("validacjaOpcjaCenaBrutto " + validacjaOpcjaCenaBrutto);
				opcjaCenaBrutto = $(this).konwertujDoLiczby(opcjaCenaBrutto);
				
				radioBrutto = $('input:radio[name=szukajCenaBruttoRadio]:checked').val();
		}

		if ( $("#zaaIlosc").is(":checked") == true ){
				opcjaIlosc = $("#zaaIloscPole").val().trim();
				validacjaOpcjaIlosc = $(this).walidujNumerZaa(opcjaIlosc,"#zaaIloscPole" );
				console.log("validacjaOpcjaIlosc " + validacjaOpcjaIlosc);
				opcjaIlosc = $(this).konwertujDoLiczby(opcjaIlosc);
				radioIlosc = $('input:radio[name=szukajIloscRadio]:checked').val();
		}
		
		opcje = "?towar="+opcjaTowar+"&uzytkownik="+opcjaUzytkownik+"&cenaNetto="+opcjaCenaNetto+"&radioCenaNetto="+radioNetto+"&cenaBrutto="+opcjaCenaBrutto+"&radioCenaBrutto="+radioBrutto+"&ilosc="+opcjaIlosc+"&radioIlosc="+radioIlosc;

		console.log("zebrane opcje = " + opcje);
		
		console.log("validacjaopcjaCenaNetto " + validacjaopcjaCenaNetto);
		console.log("validacjaOpcjaCenaBrutto "+ validacjaOpcjaCenaBrutto);
		console.log("validacjaOpcjaIlosc "+ validacjaOpcjaIlosc);
		console.log("validacjaNazwaTowaru "+ validacjaNazwaTowaru);
		console.log("validacjaUzytkownik "+ validacjaUzytkownik);
		
		if ( validacjaopcjaCenaNetto == "NOK" || validacjaOpcjaCenaBrutto == "NOK" || validacjaOpcjaIlosc == "NOK" || validacjaNazwaTowaru == "NOK" || validacjaUzytkownik == "NOK") {
			return "NOK";
		}
		else {
			return opcje;
		}
		
	}
	
	$.fn.walidujNumerZaa = function (val, id) {
		var wynik = NaN;
		var validacja = "NOK";
		
		wynik = $(this).walidujLiczbe(val);
		if ( isNaN(wynik) ) {
			$(id).addClass("czerwony");
			validacja = "NOK";
		}
		else {
			$(id).removeClass("czerwony");
			validacja = "OK";
			}
		
		return validacja;
	}

// JSON	do szukania zaawansowanego	
	$.fn.wyslijZaaSzukanieJSON = function(opcje) {
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
	    $("#modalSzukajDialog").html("");
    	$("#brakWynikow").html("");
    	console.log("wyslam JSON do: /remanent/rest/pozycje/szukaj/zaawansowany+opcje");
    	$("#modalSzukajDialog").removeClass("czerwonyText");
		
		$.ajax({
    	type: 'GET',
        url: '/remanent/rest/pozycje/szukaj/zaawansowany'+opcje,
        success: function (response) {
            console.log("rozmiar tablicy dlugosc : "+response.length);
            console.log("OK odebrany JSON z: /remanent/rest/pozycje/szukaj/zaawansowany+opcje");
            $(this).czyscTabeleSzukania();
            $("#tabelkaSzukaj").html("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");

            $(this).pobiezWynikiSzukania(response);
        },
        error: function (jqXHR, exception) {
            var msg = $(this).getErrorMessage(jqXHR, exception);
            $("#modalSzukajDialog").addClass("czerwonyText");
			$("#modalSzukajDialog").html(msg);
			$("#szukajDialog").modal("show");
			setTimeout(function () {$('#zamknijDialogSzukaj').focus();}, 1000);
        	},
    	});
		
	}
	
// JSON	do szukania pozycji
    $.fn.szukajPozycjiJSON = function(pobranaNazwa) {
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
    	console.log("blokuemy");
    	$("#modalSzukajDialog").html("");
    	$("#brakWynikow").html("");
    	//var pobranaNazwa = $("#szukajPozycji").val().trim();
    	console.log("wyslam JSON do: /remanent/rest/pozycje/szukaj/");
    	$("#modalSzukajDialog").removeClass("czerwonyText");
		console.log("pobranaNazwa do json "+ pobranaNazwa);
		console.log("radioValue do json "+ radioValue);
    $.ajax({
    	type: 'GET',
//        url: '/remanent/rest/pozycje/szukaj/'+pobranaNazwa,
        url: '/remanent/rest/pozycje/szukaj/'+pobranaNazwa+"/"+radioValue, // DZIALA :) !!!
        success: function (response) {
            console.log("rozmiar tablicy dlugosc : "+response.length);
            console.log("OK odebrany JSON z: /remanent/rest/pozycje/szukaj/");
            $(this).czyscTabeleSzukania();
            $("#tabelkaSzukaj").html("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");

            $(this).pobiezWynikiSzukania(response);
        },
        error: function (jqXHR, exception) {
            var msg = $(this).getErrorMessage(jqXHR, exception);
            $("#modalSzukajDialog").addClass("czerwonyText");
			$("#modalSzukajDialog").html(msg);
			$("#szukajDialog").modal("show");
			setTimeout(function () {$('#zamknijDialogSzukaj').focus();}, 500);
        	},
    	});
    }
    
    $.fn.pobiezWynikiSzukania = function(response) {
    	if (response.length == 0){
    			$("#modalSzukajDialog").addClass("czerwonyText");
    			$("#modalSzukajDialog").html("Nie ma takiej pozycji w bazie");
    			$("#brakWynikow").html("<b>Nie ma takiej pozycji w bazie</b>");
    			$("#szukajDialog").modal("show");
				setTimeout(function () {$('#zamknijDialogSzukaj').focus();}, 500);
    	}
    	
    	else {
		    	for (i = 0; i < response.length ; i++) {
		    			$(this).wyswietlPozycjeSzukania(response[i]);
		    	}
    	}
    }
    
    $.fn.wyswietlPozycjeSzukania = function(pozycja) {
	 	$("#tabelkaSzukaj tr:first").before("<tr><td>"+pozycja.id+"</td>"+
	 			"<td>"+pozycja.nazwa_towaru+"</td>"+
	 			"<td>"+pozycja.cena_brutto+"</td>"+
	 			"<td>"+pozycja.cena_netto+"</td>"+
	 			"<td>"+pozycja.jednostka+"</td>"+
	 			"<td>"+pozycja.ilosc+"</td>"+
	 			"<td>"+pozycja.uzytkownik+"</td>"+

	 			"<td>"+


                				"<input type="+"text" + " "+ "id="+"zmieniloscPoSzukaj"+
                				" style=" + "width:30px;color:DodgerBlue;padding:1px;border-radius:4px;padding-left:5px;margin-top:-2px;font:bold" +" >" +


                				"</td>"+
                				"<td id="+"ilosctdPoSzukaj"+"           >"+

                				"<button id="+"zmienIloscBazaPoSzukaj"+" type="+"button"+
                				" style=" + "width:60px;color:DodgerBlue;background-color:LightGray;padding:1px;border-radius:4px"+
                				" <span></span>"+
                				"<b>&nbsp;Dodaj&nbsp;</b><span" +"></span></button>"


                				+"</td></tr>"


	 		);
    }
	
    $.fn.czyscTabeleSzukania = function (){
		$("#tabelkaSzukaj").html("");
    }
	
	$.fn.wyczyscZaawansowane = function (){
		console.log("czyscimy zaawansowane");

		$("#zaaNazwaTowaru").prop("checked", false);
		$("#zaaUzytkownik").prop("checked", false);
		$("#zaaCenaNetto").prop("checked", false);
		$("#zaaCenaBrutto").prop("checked", false);
		$("#zaaIlosc").prop("checked", false);
		$("#zaaNazwaTowaruPole").val("");
		$("#zaaUzytkownikPole").val("");
		$("#zaaCenaNettoPole").val("");
		$("#zaaCenaBruttoPole").val("");
		$("#zaaIloscPole").val("");
		$("#zaaCenaBruttoRadio1").prop("checked", true);
		$("#zaaCenaNettoRadio1").prop("checked", true);
		$("#zaaIloscRadio1").prop("checked", true);
		
		$("#zaaNazwaTowaruPole").removeClass("czerwony");	
		$("#zaaUzytkownikPole").removeClass("czerwony");	
		$("#zaaCenaNettoPole").removeClass("czerwony");	
		$("#zaaCenaBruttoPole").removeClass("czerwony");	
		$("#zaaIloscPole").removeClass("czerwony");	
	}
	
	$('#zamknijDialogSzukaj').click(function(){
		$("#szukajPozycji").focus();
	});


      $.fn.aktualizujHistoriePoZmienIloscPoSzukaj = function(pozycja){
    		console.log("aktualizuj historie wpisow po szukaj: " +pozycja);
    		var nrPozycji = pozycja.id;

    		var nowyRow =
    	 		"<td>"+pozycja.id+"</td>"+
    	 		"<td>"+pozycja.nazwa_towaru+"</td>"+
    	 		"<td>"+pozycja.cena_brutto+"</td>"+
    	 		"<td>"+pozycja.cena_netto+"</td>"+
    	 		"<td>"+pozycja.jednostka+"</td>"+
    			"<td>"+pozycja.ilosc +"</td>"+
    			"<td>"+pozycja.uzytkownik +"</td>"+

    			"<td>"+

    				"<input type="+"text" + " "+ "id="+"zmieniloscPoSzukaj"+
    				" style=" + "width:30px;color:DodgerBlue;padding:1px;border-radius:4px;padding-left:5px;margin-top:-2px;font:bold" +" >" +

    				"</td>"+
    				"<td id="+"ilosctdPoSzukaj"+"           >"+

    				"<button id="+"zmienIloscBazaPoSzukaj"+" type="+"button"+
    				" style=" + "width:60px;color:DodgerBlue;background-color:LightGray;padding:1px;border-radius:4px"+
    				" <span></span>"+
    				"<b>&nbsp;Dodaj&nbsp;</b><span" +"></span></button>"


    				+"</td></tr>"


    		console.log(" nowy row "+ nowyRow);
    		$("#tabelkaSzukaj td").filter(function() {
    			return $(this).text() == nrPozycji;
    			}).parent('tr').html(nowyRow);

    	}


   $.fn.aktualizujHistoriePoZmienIlosc = function(pozycja){
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


				+"</td></tr>"


		console.log(" nowy row "+ nowyRow);
		$("#tabelka td").filter(function() {
			return $(this).text() == nrPozycji;
			}).parent('tr').html(nowyRow);

	}


	var nowaIosc = "";
	var pozycjaNr = "";
	$(document).on("focus","#ilosctdPoSzukaj", function() { // czyli KLIK na kolumne z przyciskiem DODAJ zmiana ilosci historii
		//$(this).css("background-color":"orange");
		nowaIosc = $(this).prev().children().val();
		console.log("!!!!! nowa " + nowaIosc)
		pozycjaNr = $(this).prev().prev().prev().prev().prev().prev().prev().prev().text(); // pobranie pozycji z tabelki
		console.log("!!! pozycjaNr " + pozycjaNr)

	});

	$(document).on( "click", "#zmienIloscBazaPoSzukaj", function(){ // przycisk dodaj ilosc
		$("#modalSzukajDialog").removeClass("czerwonyText");
    	$("#modalSzukajDialog").html("");

			var nowaIloscPowalidacji = $(this).walidujLiczbe(nowaIosc);
			console.log("yyyyyyy" + nowaIloscPowalidacji);
			console.log("yyyyyyy pozycjaNr " + pozycjaNr);

			if ( isNaN(nowaIloscPowalidacji) || nowaIloscPowalidacji == 0 ){
				$("#modalSzukajDialog").addClass("czerwonyText");
            	$("#modalSzukajDialog").html("To nie jest liczba dodatnia !");
            	$("#szukajDialog").modal("show");
				setTimeout(function () {$('#zamknijDialogSzukaj').focus();}, 500);
			}
			else{
			//  rest na update ilosci dla numeru pozycji
			$(this).wyslijZmienIloscPozycjiJSONnaSzukaj(pozycjaNr, nowaIloscPowalidacji )
			}
			$("#tabelkaSzukaj tr").removeClass( "historiaPozycja" );

	});

  // json dodaj ilosc do pozycji na szukaj
   $.fn.wyslijZmienIloscPozycjiJSONnaSzukaj = function(pozycja, iloscDoDodania) {

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
    	$("#modalSzukajDialog").removeClass("czerwonyText");
    	$("#modalSzukajDialog").html("");

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
                $("#modalSzukajDialog").addClass("zielonyText");
            	$("#modalSzukajDialog").html("Pozycja numer: "+ response.id +" zmieniona w bazie o "+ iloscDoDodania);
            	$("#szukajDialog").modal("show");
				$(this).aktualizujHistoriePoZmienIloscPoSzukaj(response);
				$(this).aktualizujHistoriePoZmienIlosc(response);

				console.log("z bazy ilosc po zmianie to :" + response.ilosc)
				setTimeout(function () {$('#zamknijDialogZapisz').focus();}, 500);
        },
        error: function (jqXHR, exception) {
        	console.log("a jednak");
        	console.log("e: "+exception);
            $(this).removeClass( "historiaPozycja" );
            var msg = $(this).getErrorMessage(jqXHR, exception);
            $("#modalSzukajDialog").addClass("czerwonyText");
			$("#modalSzukajDialog").html(msg);
			$('#szukajDialog').modal("show");
			setTimeout(function () {$('#zamknijDialogSzukaj').focus();}, 1000);
        	},
    	});
    }





});
$(document).ajaxStop($.unblockUI); 