$.fn.getErrorMessage = function(jqXHR, exception) {
    var msg = '';
	    if (jqXHR.status === 0) {
	        msg = 'Brak połączenia z bazą.';
		} else if (jqXHR.status == 401) {
	        msg = 'Zły użytkownik lub hasło. [401]';
		} else if (jqXHR.status == 404) {
	        msg = 'Nie ma takiej strony. [404]';
		} else if (jqXHR.status == 410) {
	        msg = 'Nie ma takiej pozycji. [410]';
	    } else if (jqXHR.status == 500) {
	        msg = 'Błąd serwera [500].';
	    } else if (exception === 'parsererror') {
	        msg = 'Parsowanie JSON nieudane.';
	    } else if (exception === 'timeout') {
	       msg = 'Time out error.';
	    } else if (exception === 'abort') {
	        msg = 'Ajax request aborted.';
	    } else {
	        msg = 'Nieznany błąd.\n' + jqXHR.responseText;
	    }
    return msg;
	}



$.fn.konwertujDoLiczby = function(val) {
	var stringVal = val.toString();
	var stringValCorrected = stringVal.replace(",",".");
	var numberVal = parseFloat(stringValCorrected);
	return numberVal;
	}


$.fn.walidujLiczbe = function(liczba) {
	var value = "";
	var wynik = "";
	console.log("liczba do walidacji  " +liczba);
	value =  liczba.replace(",","\.");
	var regex = /^[0-9]+\.{0,1}[0-9]{0,2}$/;
	var wynikRegex = regex.test(value);
	console.log("regex wynik " +wynikRegex);
	
		if (wynikRegex == true){
			wynik = $(this).konwertujDoLiczby(value);
			return wynik;
		}
		else {
			return NaN;
		}
	
	}



$.fn.walidujNumer = function(liczba) {
	var value = "";
	var wynik = "";
	value =  liczba.replace(",","\.");
	var regex = /^[0-9]+[0-9]{0,}$/;
	var wynikRegex = regex.test(value);
	console.log("regex wynik " +wynikRegex);
	
		if (wynikRegex == true){
			wynik = $(this).konwertujDoLiczby(value);
			return wynik;
		}
		else {
			return NaN;
		}
	
	}


