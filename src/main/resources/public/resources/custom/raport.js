$(document).ready(function(){
	
		// filtr na tabelke
		$("#szukajTabelkaZeroweCeny").val(""); //wyczusc na refresh
	 $("#szukajTabelkaZeroweCeny").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#tabelkaZeroweCeny tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	
	$("#zaloguj").click(function(){
		$("#modalLogowanie").html("");
		$("#modalLogowanie").removeClass("czerwonyText");
		
		$(this).loginJSON();
	});
	
	
	$("#generujRemanent").click(function(){
		
		$(this).generujExcelaJSON();
	});
	
	$("#generujRemanentBrutto").click(function(){
		
		$(this).generujExcelaBruttoJSON();
	});
	
	$("#generujZeroweCeny").click(function(){
		
		$(this).pobierzZeroweCenyJSON();
	});
	
	$("#generujStatystyki").click(function(){
		
		$(this).pobierzStatystykiJSON();
	});
	
	
	// FUNKCJE
	
	//JSON
	$.fn.pobierzZeroweCenyJSON = function(){
		$("#modalZeroweCeny").html("");
		$("#modalZeroweCeny").removeClass("czerwonyText");
		
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
    	type: 'GET',
        url: '/remanent/rest/raport/zeroweCeny',
        success: function (response) {
        	console.log("sukces json");
        	console.log("zeroweCeny response ");
        	
            $(this).czyscTabeleZeroweCeny();
            $("#tabelkaZeroweCeny").html("<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
            $(this).pobiezWynikiSzukaniaZer(response);
        	
        },
        error: function (jqXHR, exception) {
        	console.log("error zerowe ceny json ");
            var msg = $(this).getErrorMessage(jqXHR, exception);
            $("#modalZeroweCeny").addClass("czerwonyText");
			$("#modalZeroweCeny").html(msg);
			$("#modalZeroweCenyDialog").modal("show");
        	},
    	});
		
	}
	
	
	//JSON
	$.fn.pobierzStatystykiJSON = function(){
		$("#modalStatystyki").html("");
		$("#modalStatystyki").removeClass("czerwonyText");
		
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
    	type: 'GET',
        url: '/remanent/rest/raport/statystyki',
        success: function (response) {
        	console.log("sukces json");
        	console.log("statystyki response "+response);
        	
        	$("#tabelkaStatystyki").html(response);
        	
        },
        error: function (jqXHR, exception) {
        	console.log("error loginJSON json ");
            var msg = $(this).getErrorMessage(jqXHR, exception);
            $("#modalStatystyki").addClass("czerwonyText");
			$("#modalStatystyki").html(msg);
			$("#modalStatystykiDialog").modal("show");
        	},
    	});
		
	}
	
	
	// JSON
	$.fn.loginJSON = function(){	
		$("#modalLogowanie").html("");
		$("#modalLogowanie").removeClass("czerwonyText");
		
		var user = $("#user").val();
		var pass = $("#pass").val();
		
		if ( (user  == "" )|| (pass == "")){
			console.log("pusty login lub haslo");
			$("#modalLogowanieDialog").modal("show");
			$("#modalLogowanie").addClass("czerwonyText");
			$("#modalLogowanie").html("Zły użytkownik lub hasło. <br>  Wpisz jeszcze raz.");
		} else {
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
    	type: 'GET',
        url: '/remanent/rest/raport/zaloguj/'+user+"/"+pass,
        success: function (response) {
        	console.log("sukces json");
        	console.log("login response "+response);
        	$(this).sprawdzDostep(response);
        },
        error: function (jqXHR, exception) {
        	console.log("error loginJSON json ");
            var msg = $(this).getErrorMessage(jqXHR, exception);
            $("#modalLogowanie").addClass("czerwonyText");
			$("#modalLogowanie").html(msg);
			$("#modalLogowanieDialog").modal("show");
        	},
    	});
	}
	
	}
	

	
	$.fn.sprawdzDostep = function(response){	
		var dostep = response;
		
		if ( (dostep =="OK") ){
			$("#divLogowanie").addClass("collapse");
			$("#divZalogujPrzycisk").addClass("collapse");
			
			$("#divStatystyki").removeClass("collapse");
			$("#divGenerujRaport").removeClass("collapse");
			$("#divInputSciezka").removeClass("collapse");
			$("#divPrzysiskRemanent").removeClass("collapse");
			$("#divGenerujZeroweCeny").removeClass("collapse");
			$("#divZeroweCeny").removeClass("collapse");
			$("#divInputSciezkaBrutto").removeClass("collapse");
			$("#divPrzysiskRemanentBrutto").removeClass("collapse");
		}
		else {
			console.log("login NOK");
			$("#modalLogowanieDialog").modal("show");
			$("#modalLogowanie").addClass("czerwonyText");
			$("#modalLogowanie").html("Zły użytkownik lub hasło. <br>  Wpisz jeszcze raz.");
		}
		
	}
	

	// JSON
	$.fn.generujExcelaJSON = function(){	
		$("#modalExcel").html("");
		$("#modalExcel").removeClass("czerwonyText");
		var sciezka = $("#sciezka").val();
		
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
    	type: 'GET',
        url: '/remanent/rest/raport/excel/netto/'+sciezka,
        success: function (response) {
        	console.log("sciezka json");
        	console.log("sciezka response "+response);
        	var sciezka = response;
			$("#modalExcel").html(sciezka);
			$("#modalExcelDialog").modal("show");
        	
        },
        error: function (jqXHR, exception) {
        	console.log("error loginJSON json ");
            var msg = $(this).getErrorMessage(jqXHR, exception);
            $("#modalExcel").addClass("czerwonyText");
			$("#modalExcel").html(msg);
			$("#modalExcelDialog").modal("show");
        	},
    	});

		}
	
	// JSON brutto
	$.fn.generujExcelaBruttoJSON = function(){	
		$("#modalExcelBrutto").html("");
		$("#modalExcelBrutto").removeClass("czerwonyText");
		var sciezkaBrutto = $("#sciezkaBrutto").val();
		
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
    	type: 'GET',
        url: '/remanent/rest/raport/excel/brutto/'+sciezkaBrutto,
        success: function (response) {
        	console.log("sciezka brutto json");
        	console.log("sciezka brutto response "+response);
        	var sciezkaBrutto = response;
			$("#modalExcelBrutto").html(sciezkaBrutto);
			$("#modalExcelDialogBrutto").modal("show");
        	
        },
        error: function (jqXHR, exception) {
        	console.log("error loginJSON json ");
            var msg = $(this).getErrorMessage(jqXHR, exception);
            $("#modalExcelBrutto").addClass("czerwonyText");
			$("#modalExcelBrutto").html(msg);
			$("#modalExcelDialogBrutto").modal("show");
        	},
    	});

		}
	
	
	$.fn.pobierzDate = function(){	
		var d = new Date();
		var month = d.getMonth()+1;
		var day = d.getDate();
		var year = d.getFullYear();
		var data = "_"+year+"_"+month+"_"+d;
		}

    $.fn.czyscTabeleZeroweCeny = function (){
    	$("#tabelkaZeroweCeny").html("");
    }
	
    
    $.fn.pobiezWynikiSzukaniaZer = function(response) {
    	if (response.length == 0){
    			$("#modalZeroweCeny").addClass("czerwonyText");
    			$("#modalZeroweCeny").html("Nie ma takiej pozycji w bazie");
    			$("#modalZeroweCenyDialog").modal("show");
    	}
    	
    	else {
		    	for (i = 0; i < response.length ; i++) {
		    			$(this).wyswietlPozycjeSzukaniaZer(response[i]);
		    	}
    	}
    }
    
    $.fn.wyswietlPozycjeSzukaniaZer = function(pozycja) {
	 	$("#tabelkaZeroweCeny tr:first").before("<tr><td>"+pozycja.id+"</td>"+
	 			"<td>"+pozycja.nazwa_towaru+"</td>"+
	 			"<td>"+pozycja.cena_brutto+"</td>"+
	 			"<td>"+pozycja.cena_netto+"</td>"+
	 			"<td>"+pozycja.jednostka+"</td>"+
	 			"<td>"+pozycja.ilosc+"</td>"+
	 			"<td>"+pozycja.uzytkownik+"</td></tr>"
	 		);
    }

});
$(document).ajaxStop($.unblockUI); 