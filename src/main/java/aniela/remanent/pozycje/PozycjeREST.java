package aniela.remanent.pozycje;

import aniela.remanent.pozycje.bazaDanych.PozycjaBazy;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PozycjeREST {
    private final static Logger LOG = Logger.getLogger(PozycjeREST.class);

    @Autowired
    private BazaDAO baza;

    @PostMapping(path = "/remanent/rest/pozycje/dodajIloscDoPozycji/{numerPozycji}/{ilosc}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity dodajIloscDoPozycji(@PathVariable("numerPozycji") int numerPozycji,
                                              @PathVariable("ilosc") double ilosc) {
        LOG.info("dodawanie ilosci do pozycji");
        PozycjaBazy pozycja = baza.dodajIloscDoPozycjiDoBazy(numerPozycji, ilosc);
        double iloscRound = Math.round((pozycja.getIlosc() + ilosc) * 100) / 100.0d; // do dwoch miejsc po przecinku dla weba
        pozycja.setIlosc(iloscRound);
        System.out.println("do weba idzie : " + pozycja.getIlosc());
        return ResponseEntity.status(HttpStatus.OK).body(pozycja);
    }

    @PostMapping(path = "/remanent/rest/pozycje/dodaj",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity dodajPozycje(@RequestBody PozycjaBazy pozycja) {
        LOG.info("dodawanie pozycji");
        Integer numerPozycji = baza.dodajPozycjeDoBazy(pozycja);
        LOG.info("zwrocony numer dodanej pozycji: " + numerPozycji);
        return ResponseEntity.status(HttpStatus.OK).body(numerPozycji.toString());
    }


    @PostMapping(path = "/remanent/rest/pozycje/zmien/wyslijDoZmiany",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity zmienPozycje(@RequestBody PozycjaBazy pozycja) {
        LOG.info("zmiana pozycji");
        Integer numerPozycji = baza.zmienPozycjeWBazie(pozycja);
        LOG.info("zwrocony numer dodanej pozycji: " + numerPozycji);
        return ResponseEntity.status(HttpStatus.OK).body(numerPozycji.toString());
    }

    @GetMapping(path = "/remanent/rest/pozycje/zmien/pobierzDoZmien/{numer}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity pobierzPozycjeDoZmiany(@PathVariable("numer") int numer) {
        LOG.info("pobrany numer pozycji do zmiany : " + numer);
        PozycjaBazy pozycjaDoZmien = baza.pobierzPozycjeDoZmien(numer);
        if (pozycjaDoZmien == null) {
            LOG.info("zwrocono pobrany numer pozycji do zmiany : NULL ");
            return ResponseEntity.status(HttpStatus.GONE).build();
        } else
            LOG.info("zwrocono pobrany numer pozycji do zmiany : " + pozycjaDoZmien.getId());
        return ResponseEntity.status(HttpStatus.OK).body(pozycjaDoZmien);
    }


    @GetMapping(path = "/remanent/rest/pozycje/szukaj/{pobranaNazwa}/{radioValue}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity szukajPozycji(@PathVariable("pobranaNazwa") String pobranaNazwa,
                                        @PathVariable("radioValue") String radioValue
    ) {
        LOG.info("szukaj nazwy : " + pobranaNazwa);
        LOG.info("szukaj wybor: " + radioValue);
        List<PozycjaBazy> wynikiSzukaniaBaza = baza.listaZnalezionychPozycjiWBazie(pobranaNazwa, radioValue);
        LOG.info("wyszukano pozycji: " + wynikiSzukaniaBaza.size());
        return ResponseEntity.status(HttpStatus.OK).body(wynikiSzukaniaBaza);
    }


    @GetMapping(path = "/remanent/rest/pozycje/usun/szukaj/{numerDoUsuniecia}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity pobierzPozycjeDoUsuniecia(@PathVariable("numerDoUsuniecia") int numerDoUsuniecia) {
        LOG.info("pobrany numer pozycji do usuniecia : " + numerDoUsuniecia);
        PozycjaBazy pozycjaDoUsuniecia = baza.pobierzPozycjeDoZmien(numerDoUsuniecia);
        if (pozycjaDoUsuniecia == null) {
            LOG.info("zwrocono pobrany numer pozycji do zmiany : NULL ");
            return ResponseEntity.status(HttpStatus.GONE).build();
        } else
            LOG.info("zwrocono pobrany numer pozycji do usuniecia : " + pozycjaDoUsuniecia.getId());
        return ResponseEntity.status(HttpStatus.OK).body(pozycjaDoUsuniecia);
    }

    @GetMapping(path = "/remanent/rest/pozycje/usun/{numerDoUsunieciaZBazy}")
    public ResponseEntity usunPozycje(@PathVariable("numerDoUsunieciaZBazy") int numerDoUsunieciaZBazy) {
        LOG.info("pobrany numer pozycji do usuniecia : " + numerDoUsunieciaZBazy);
        Integer numer = baza.usunPozycjeZBazy(numerDoUsunieciaZBazy);
        LOG.info("zwrocono numer usunietej pozycji : " + numer);
        return ResponseEntity.status(HttpStatus.OK).body(numer.toString());
    }


    @GetMapping(path = "/remanent/rest/pozycje/szukaj/zaawansowany",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity szukanieZaawansowane(@RequestParam("towar") String towar,
                                               @RequestParam("uzytkownik") String uzytkownik,
                                               @RequestParam("cenaNetto") double cenaNetto,
                                               @RequestParam("radioCenaNetto") int radioNetto,
                                               @RequestParam("cenaBrutto") double cenaBrutto,
                                               @RequestParam("radioCenaBrutto") int radioBrutto,
                                               @RequestParam("ilosc") double ilosc,
                                               @RequestParam("radioIlosc") int radioIlosc
    ) {
        System.out.print("REST PARAMS zaawas szukanie ++++!!!!! + =" + towar + " " + uzytkownik + " " + cenaNetto + " " + radioNetto + " " + cenaBrutto + " " + radioBrutto + " " + ilosc + " " + radioIlosc);
        LOG.info("zaawansowane szukanie: " + towar + " " + uzytkownik + " " + cenaNetto + " " + radioNetto + " " + cenaBrutto + " " + radioBrutto + " " + ilosc + " " + radioIlosc);
        List<PozycjaBazy> result = baza.advancedSearch(towar, uzytkownik, cenaNetto, radioNetto, cenaBrutto, radioBrutto, ilosc, radioIlosc);
        if (result.size() == 0) {
            LOG.info("szukanie zaawansowane nic nie znalazlo");
            return ResponseEntity.status(HttpStatus.GONE).build();
        } else
            return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // http://localhost:8080/api/foos?id=1,2,3
    @PostMapping(path = "/remanent/rest/pozycje/merge")
    public ResponseEntity mergePositions(@RequestParam List<Integer> ids) {
        if (ids != null && ids.size() < 2) {
            LOG.info("Merging cannot be done due to insufficient  number of IDS: " + ids + " " + ids.size());
            return ResponseEntity.status(HttpStatus.OK).body("Merging cannot be executed: " + ids);
        }
        int masterIndex = this.baza.merge(ids);
        return ResponseEntity.status(HttpStatus.OK).body("Merged positions to: " + masterIndex);
    }


}