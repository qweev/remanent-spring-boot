$(document).ready(function(){
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
        	},
    	});
    }
    
    $.fn.pobiezWynikiSzukania = function(response) {
    	if (response.length == 0){
    			$("#modalSzukajDialog").addClass("czerwonyText");
    			$("#modalSzukajDialog").html("Nie ma takiej pozycji w bazie");
    			$("#brakWynikow").html("<b>Nie ma takiej pozycji w bazie</b>");
    			$("#szukajDialog").modal("show");
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
	 			"<td>"+pozycja.uzytkownik+"</td></tr>"
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
	
    
});
$(document).ajaxStop($.unblockUI); 