$(document).ready(function(){

// tabelka szukaj
        $("#numerKolumnaWSzukaj").click(function(){
            $(this).sortTableByNumbers("szukajTabelka", 0);
        });

        $("#nazwaTowaruKolumnaWSzukaj").click(function(){
            $(this).sortTableByNames("szukajTabelka", 1);
        });

        $("#cenaBruttoKolumnaWSzukaj").click(function(){
            $(this).sortTableByNumbers("szukajTabelka", 2);
        });

        $("#cenaNettoKolumnaWSzukaj").click(function(){
            $(this).sortTableByNumbers("szukajTabelka", 3);
        });

        $("#jednostkaKolumnaWSzukaj").click(function(){
            $(this).sortTableByNames("szukajTabelka", 4);
        });

        $("#iloscKolumnaWSzukaj").click(function(){
            $(this).sortTableByNumbers("szukajTabelka", 5);
        });

        $("#uzytkownikKolumnaWSzukaj").click(function(){
            $(this).sortTableByNames("szukajTabelka", 6);
        });



});