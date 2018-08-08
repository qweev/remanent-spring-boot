$(document).ready(function(){
	
	$("#zmienJednostkaLista li a").on("click", function(){
	    $("#zmienJednostka").text($(this).html());
	    
	});
	
	//walidacja pol
	var validacjaZmienTowar = "OK";
	var validacjaZmienCenaB = "OK";
	var validacjaZmienCenaN = "OK";
	var validacjaZmienIlosc = "OK";
	var validacjaNumerPozycji = "OK";
	var numerPozycjiDoZmiany = 0 ;
	
	$("#zmienTowar").focusout(function() {
		if ( $("#zmienTowar").val().trim().length > 51 | $("#zmienTowar").val().trim().length == 0 | $("#zmienTowar").val().trim() == $("#zmienTowar").val().trim().toUpperCase() ) {
				$("#zmienTowar").addClass("czerwony");
				validacjaZmienTowar = "NOK";
		}
		else {
				$("#zmienTowar").removeClass("czerwony");
				validacjaZmienTowar = "OK";
			}
	});
	
	$("#zmienCenaB").focusout(function() {
		var zmienCenaB = $("#zmienCenaB").val().trim();
		var walidacjaZmienCenaB = $(this).walidujLiczbe(zmienCenaB);
		
		if ( isNaN(walidacjaZmienCenaB) ) {
			$("#zmienCenaB").addClass("czerwony");
			validacjaZmienCenaB = "NOK";
		}
		else {
			$("#zmienCenaB").removeClass("czerwony");
			validacjaZmienCenaB = "OK";
		}
	});
	
	$("#zmienCenaN").focusout(function() {
		var zmienCenaN = $("#zmienCenaN").val().trim();
		var walidacjaZmienCenaN = $(this).walidujLiczbe(zmienCenaN);
		
		if ( isNaN(walidacjaZmienCenaN) ) {
			$("#zmienCenaN").addClass("czerwony");
			validacjaZmienCenaN = "NOK";
		}
		else {
			$("#zmienCenaN").removeClass("czerwony");
			validacjaZmienCenaN = "OK";
			}
	});
	
	$("#zmienIlosc").focusout(function() {
		var zmienIlosc = $("#zmienIlosc").val().trim();
		var walidacjaZmienIlosc = $(this).walidujLiczbe(zmienIlosc);
		
		if ( isNaN(walidacjaZmienIlosc) ) {
			$("#zmienIlosc").addClass("czerwony");
			validacjaZmienIlosc = "NOK";
		}
		else {
			$("#zmienIlosc").removeClass("czerwony");
			validacjaZmienIlosc = "OK";
		}
	});

	$("#wpiszNumerPozycji").focusout(function() {
		var numerDoZmiany = $("#wpiszNumerPozycji").val().trim();
		var walidacjaNumerDoZmiany = $(this).walidujNumer(numerDoZmiany);
		
		if ( isNaN(walidacjaNumerDoZmiany) | walidacjaNumerDoZmiany <= 0 ) {
			$('#wpiszNumerPozycji').addClass("czerwony");
			$('#pobierzPozycjePoNumerzePrzycisk').addClass("czerwony");
			validacjaNumerPozycji = "NOK";
		}
		else {
			$("#wpiszNumerPozycji").removeClass("czerwony");
			$("#pobierzPozycjePoNumerzePrzycisk").removeClass("czerwony");
			validacjaNumerPozycji = "OK";
		}
	});
	
	
	//waliduj numer i pobierz pozycje po numerze
	$("#pobierzPozycjePoNumerzePrzycisk").click(function(){
		numerPozycjiDoZmiany = $("#wpiszNumerPozycji").val().trim();
		var walidacjaNumerPozycjiDoZmiany = $(this).walidujNumer(numerPozycjiDoZmiany);
		
    	$("#modalZmienDialog").removeClass("czerwonyText");
    	$("#modalZmienDialog").removeClass("zielonyText");
    	$("#wpiszNumerPozycji").removeClass("czerwony");
    	$("pobierzPozycjePoNumerzePrzycisk").removeClass("czerwony");

		if ( isNaN(walidacjaNumerPozycjiDoZmiany) | $("#wpiszNumerPozycji").val().trim().length == 0 | walidacjaNumerPozycjiDoZmiany <= 0 ) {
			var info = "Nieprawidłowy numer pozycji !";
			$("#modalZmienDialog").addClass("czerwonyText").html(info);
			$("#wpiszNumerPozycji").addClass("czerwony");
			$("#pobierzPozycjePoNumerzePrzycisk").addClass("czerwony");
			$("#zmienDialog").modal("show");
			validacjaNumerPozycji = "NOK";
			return 0;
		}
		else {
    		$(this).pobierzPozycjeNumerJSON();
		}
	});
	
	// waliduj pola i zapisz zmiany do bazy
    $("#zapiszZmianyDoBazy").click(function(){
    	$("#zmienJednostka").removeClass("czerwony");
    	$("#modalZmienDialog").removeClass("czerwonyText");
    	$("#modalZmienDialog").removeClass("zielonyText");
    	if(validacjaZmienTowar == "NOK" | validacjaZmienCenaB == "NOK" | validacjaZmienCenaN == "NOK" | validacjaZmienIlosc == "NOK" ){
    		$("#modalZmienDialog").addClass("czerwonyText");
    		var info ="!!! Niepoprawny wpis w jednym z pol !!!" + "<br/>"+ "wpisz jeszcze raz ...";
    		$("#modalZmienDialog").html(info);	
    		if (validacjaZmienTowar == "NOK") {
    			$("#zmienTowar").addClass("czerwony");
    		}
    		if (validacjaZmienCenaB == "NOK") {
    			$("#zmienCenaB").addClass("czerwony");
    		}
    		if (validacjaZmienCenaN == "NOK") {
    			$("#zmienCenaN").addClass("czerwony");
    		}
    		if (validacjaZmienIlosc == "NOK") {
    			$("#zmienIlosc").addClass("czerwony");
    		}
    		return 0;
    	}
    	if(($("#zmienTowar").val().trim().length == 0) | ($("#zmienCenaB").val().trim().length == 0) | ($("#zmienCenaN").val().trim().length == 0) | ($("#zmienIlosc").val().trim().length == 0) | ($("#zmienIlosc").val() <= 0)  ){
    		var info ="!!! Niepoprawny wpis w jednym z pol !!!" + "<br/>"+ "wpisz jeszcze raz ...";
    		$("#modalZmienDialog").addClass("czerwonyText").html(info);
    		if ($("#zmienTowar").val().trim().length == 0) {
    			$("#zmienTowar").addClass("czerwony");
    		}
    		if ($("#zmienCenaB").val().trim().length == 0) {
    			$("#zmienCenaB").addClass("czerwony");
    		}
    		if ($("#zmienCenaN").val().trim().length == 0) {
    			$("#zmienCenaN").addClass("czerwony");
    		}
    		if ($("#zmienIlosc").val().trim().length == 0 |  ($("#zmienIlosc").val().trim() <= 0)) {
    			$("#zmienIlosc").addClass("czerwony");
    		}
    		return 0;
    	}
    	else {
    		$(this).wyslijZmianyJSON();
    	}
	});

	
// JSON WYSLIJ POZYCJE DO ZMIANY
	$.fn.wyslijZmianyJSON = function() {

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
    
    	$("#modalZmienDialog").removeClass("czerwonyText");
    	$("#modalZmienDialog").removeClass("zielonyText");
    	$("#modalZmienDialog").html("");
		var pola = $(this).zbierzPolaDoZmiany(); 
    	console.log("zebrane pola: "+pola);
		console.log("znumerPozycjiDoZmiany wyslany : "+ numerPozycjiDoZmiany);
    	console.log("wyslam JSON do: /remanent/rest/pozycje/zmien/wyslijDoZmiany");
    	
    $.ajax({
    	type: 'POST',
    	contentType: "application/json",
    	dataType: 'json',
    	data: pola,
        url: '/remanent/rest/pozycje/zmien/wyslijDoZmiany',
        success: function (response) {
        	console.log("OK odebrany JSON z: /remanent/rest/pozycje/zmien/wyslijDoZmiany");
			console.log("znumerPozycjiDoZmiany otrzymany : "+ response);
            if (response == numerPozycjiDoZmiany ){
                $("#modalZmienDialog").addClass("zielonyText");
            	$("#modalZmienDialog").html("Pozycja numer: "+ numerPozycjiDoZmiany +" zmieniona w bazie");
            	$("#zmienDialog").modal("show");
            	$("#ukryte").removeClass("in");
				$(this).aktualizujHistoriePoZmien();
            }
            else {
            	$("#modalZmienDialog").addClass("czerwonyText");
            	$("#modalZmienDialog").html("Otrzymany numer pozycji jest inny od wysłanego !");
            	$("#zmienDialog").modal("show");
            }
            
        },
        error: function (jqXHR, exception) {
        	$("#modalZmienDialog").addClass("czerwonyText");
            var msg = $(this).getErrorMessage(jqXHR, exception);
			$("#modalZmienDialog").html(msg);
			$("#zmienDialog").modal("show");
        	},
    	});
		
	}
	
// JSON POBIERZ POZYCJE o NUMERZE
    $.fn.pobierzPozycjeNumerJSON = function() {

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
    	
    	$("#brakPozycjiDiv").removeClass("in");
    	$("#modalZmienDialog").removeClass("czerwonyText");
    	$("#modalZmienDialog").html("");
    	console.log("wyslam JSON do: /remanent/rest/pozycje/zmien/pobierzDoZmien/");
    	
    $.ajax({
    	type: 'GET',
        url: '/remanent/rest/pozycje/zmien/pobierzDoZmien/'+numerPozycjiDoZmiany,
        success: function (response) {
        	console.log("OK odebrany JSON z: /remanent/rest/pozycje/zmien/pobierzDoZmien/");
            if ( response.id == 0 ){
            	$("#modalZmienDialog").addClass("czerwonyText");
            	$("#modalZmienDialog").html("Brak pozycji o takim numerze !");
            	$("#ukryte").removeClass("in");
            	$("#zmienDialog").modal("show");
            }
            else {
            	$(this).wyswietlPozycjeDoZmiany(response);
                $("#ukryte").addClass("in");
            }
            
        },
        error: function (jqXHR, exception) {
        	$("#modalZmienDialog").addClass("czerwonyText");
            var msg = $(this).getErrorMessage(jqXHR, exception);
			$("#modalZmienDialog").html(msg);
			$("#zmienDialog").modal("show");
        	},
    	});
    }	
	
	
	$.fn.aktualizujHistoriePoZmien = function(){
		console.log("aktualizuj historie wpisow");
		var nrPozycji = $("#wpiszNumerPozycji").val().trim();
		var towarNaZywca = $("#zmienTowar").val().trim();
		var towar = towarNaZywca.charAt(0).toUpperCase() + towarNaZywca.slice(1);
		
		var d = new Date();
		var h = d.getHours();
		var m = d.getMinutes();
		if (m < 10) { m = "0"+m;}
		
		var nowyRow = "<td>"+h+" : "+m+"</td><td>"+nrPozycji+"</td>"+
	 		"<td>"+towar+"</td>"+
	 		"<td>"+$("#zmienCenaB").val().trim()+"</td>"+
	 		"<td>"+$("#zmienCenaN").val().trim()+"</td>"+
	 		"<td>"+$("#zmienJednostka").text()+"</td>"+
	 		"<td>"+$("#zmienIlosc").val().trim()+"</td></tr>"
			
		console.log("akt his nowy row "+ nowyRow);
		$("#tabelka td").filter(function() {
			return $(this).text() == nrPozycji;
			}).parent('tr').html(nowyRow);
		
	}
	
    $.fn.wyswietlPozycjeDoZmiany = function(response) {
    	$("#zmienTowar").val(response.nazwa_towaru);
    	$("#zmienCenaB").val(response.cena_brutto);
    	$("#zmienCenaN").val(response.cena_netto);
    	$("#zmienIlosc").val(response.ilosc);
    	$("#zmienJednostka").text(response.jednostka);
    	$("#zmienUzytkownik").val(response.uzytkownik);
    }
    
	$.fn.zbierzPolaDoZmiany = function() {
		var nazwaTowaruDoZmiany = $("#zmienTowar").val().trim();
		var cenaBruttoDoZmiany = $("#zmienCenaB").val().trim();
		var cenaNettoDoZmiany = $("#zmienCenaN").val().trim();
		var iloscDoZmiany = $("#zmienIlosc").val().trim();
		var jednostkaDoZmiany = $("#zmienJednostka").text().trim();
		var uzytkownikDoZmiany = $("#zmienUzytkownik").val().trim();
		
		cenaBruttoDoZmiany = $(this).konwertujDoLiczby(cenaBruttoDoZmiany);
		cenaNettoDoZmiany = $(this).konwertujDoLiczby(cenaNettoDoZmiany);
		iloscDoZmiany = $(this).konwertujDoLiczby(iloscDoZmiany);
		console.log("nowe do zmiany: "+nazwaTowaruDoZmiany+" "+cenaBruttoDoZmiany );
		
		var pola = '{"cena_netto":"'+cenaNettoDoZmiany+'","cena_brutto":"'+cenaBruttoDoZmiany+'","nazwa_towaru":"'+nazwaTowaruDoZmiany+'","jednostka":"'+jednostkaDoZmiany+'","ilosc":"'+iloscDoZmiany+'","uzytkownik":"'+uzytkownikDoZmiany+'","id":"'+numerPozycjiDoZmiany+'"}';
		return pola;
	}
	
});
$(document).ajaxStop($.unblockUI); 
