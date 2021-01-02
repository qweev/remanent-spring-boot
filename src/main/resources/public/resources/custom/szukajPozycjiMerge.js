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

	
	$("#zaaSzukajPrzycisk").click(function(){
			$(this).szukajDplikatow();
	});


	$("#mergujPrzycisk").click(function(){
    		console.log("MergujIdki");

    	$(this).mergujDziadu();

    	});
	
	//FUNKCJE

	//JSON zaawansowane szukanie
	$.fn.szukajDplikatow = function() {

                    $.ajax({
                        	type: 'get',
                            url: '/remanent/rest/pozycje/merge',
                            success: function (response) {
                                console.log("Pobrano duplikaty");
                                pobiezWynikiSzukania(response);

                            },
                            error: function (jqXHR, exception) {
                                var msg = $(this).getErrorMessage(jqXHR, exception);;
                    			setTimeout(function () {$('#zamknijDialogSzukaj').focus();}, 1000);
                            	},
                        	});

	}

    //Merge
	$.fn.mergujDziadu = function() {
            var message = "ids=";
            var grid = document.getElementById("szukajTabelka");
            var checkBoxes = grid.getElementsByTagName("input");
            for (var i = 0; i < checkBoxes.length; i++) {
                        if (checkBoxes[i].checked) {
                            var row = checkBoxes[i].parentNode.parentNode;
                            console.log("Checkbox value: "+checkBoxes[i].value);
                            message = message + checkBoxes[i].value +",";
                            var pointer = checkBoxes.length-1;
                        }
            }
            var urlToBeSent = message.slice(0, -1);
            console.log(urlToBeSent);
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
                	type: 'POST',
                    url: '/remanent/rest/pozycje/merge?'+urlToBeSent,
                    success: function (response) {
                        console.log("Merge wykonano");
                        var options = $(this).zbierzZaaOpcje();
                        $(this).wyslijZaaSzukanieJSON(options);
                    },
                    error: function (jqXHR, exception) {
                        var msg = $(this).getErrorMessage(jqXHR, exception);;
            			setTimeout(function () {$('#zamknijDialogSzukaj').focus();}, 1000);
                    	},
                	});
    	}

// JSON	do szukania zaawansowanego
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
                    "<input type="+"checkbox" + " "+ " id="+pozycja.id+ " value="+pozycja.id+""+
                	" >" +
                "</td>"+

             "</tr>"


	 		);
    }
	



});
$(document).ajaxStop($.unblockUI); 