package aniela.remanent.IT_REST_Pozycje;


import aniela.FireFoxConfiguration;
import aniela.remanent.Application;
import aniela.remanent.pozycje.bazaDanych.PozycjaBazy;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {FireFoxConfiguration.class})
@SpringBootTest(classes = Application.class)
public class Pozycje_REST_IT {


    @Test
    public void dodajPozycje() throws JSONException {
        String jsonPozycja = pozycjaJSON(null, "towar restowy","30.2","40.4","restowy","szt." ,"40.9");
        String urlDodajPozycje = "http://localhost:8080/remanent/rest/pozycje/dodaj";
        ResponseEntity<String>  response = restRequest(urlDodajPozycje, jsonPozycja,
                HttpMethod.POST, MediaType.APPLICATION_JSON_UTF8, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(false, response.getBody().isEmpty());

    }


    @Test
    public void zmienPozycje() throws JSONException {
        String jsonPozycjaDoZmiany = pozycjaJSON("5", "towar zmieniony","50.2","50.4","restowy","szt." ,"50.9");
        String urlZmienPozycje = "http://localhost:8080/remanent/rest/pozycje/zmien/wyslijDoZmiany";
        String urlPobierzZmienionaPozycje = "http://localhost:8080/remanent/rest/pozycje/usun/szukaj/5";


        restRequest(urlZmienPozycje, jsonPozycjaDoZmiany,
                HttpMethod.POST, MediaType.APPLICATION_JSON_UTF8, String.class);
        ResponseEntity<PozycjaBazy>  responsePobierz = restRequest(urlPobierzZmienionaPozycje, null,
                HttpMethod.GET, MediaType.TEXT_PLAIN, PozycjaBazy.class);


        assertEquals(HttpStatus.OK, responsePobierz.getStatusCode());
        assertEquals("towar zmieniony", responsePobierz.getBody().getNazwa_towaru());
        assertEquals(50.2,responsePobierz.getBody().getCena_netto(),0);
        assertEquals(50.4,responsePobierz.getBody().getCena_brutto(),0);
        assertEquals("restowy",responsePobierz.getBody().getUzytkownik());
        assertEquals("szt.",responsePobierz.getBody().getJednostka());
        assertEquals(50.9,responsePobierz.getBody().getIlosc(),0);
        assertEquals(5 ,responsePobierz.getBody().getId());
    }


    @Test
    public void szukajPozycjiPoNazwieTowaru(){
        String doWyszukania = "szukaj pozycji";
        String url = "http://localhost:8080/remanent/rest/pozycje/szukaj/"+ doWyszukania + "/nazwa";
        HttpHeaders headers = new HttpHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<List<PozycjaBazy>>  responseSzukaj = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<PozycjaBazy>>(){});
        List<PozycjaBazy> znalezione  = responseSzukaj.getBody();


        assertEquals(HttpStatus.OK, responseSzukaj.getStatusCode());
        assertEquals(doWyszukania , znalezione.get(0).getNazwa_towaru());


        // mozna tez odebrac jako ResponseEntity<String> i zrobic tak
//        JSONArray jsonArray = new JSONArray(responseSzukaj.getBody());
//        for (int i = 0; i < jsonArray.length(); i++)
//        {
//            JSONObject jsonObj = jsonArr.getJSONObject(i);
//            System.out.println(" +++++++++++ ------------- SSSSSSSS  " + jsonObj);
//        }
    }


    @Test
    public void szukajNieistniejacejPozycji() {
        String nieistniejącaPozycja = "szukaj pozycji_co_nie_istnieje";
        String url = "http://localhost:8080/remanent/rest/pozycje/szukaj/"+ nieistniejącaPozycja + "/nazwa";
        HttpHeaders headers = new HttpHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<List<PozycjaBazy>>  responseSzukaj = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<PozycjaBazy>>(){});
        List<PozycjaBazy> znalezione  = responseSzukaj.getBody();


        assertEquals(HttpStatus.OK, responseSzukaj.getStatusCode());
        assertEquals(0, znalezione.size());
    }


    @Test
    public void szukajPozycjiPoUzytkowniku(){
        String doWyszukania = "user 1";
        String url = "http://localhost:8080/remanent/rest/pozycje/szukaj/"+ doWyszukania + "/uzytkownik";
        HttpHeaders headers = new HttpHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<List<PozycjaBazy>>  responseSzukaj = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<PozycjaBazy>>(){});
        List<PozycjaBazy> znalezione  = responseSzukaj.getBody();


        assertEquals(HttpStatus.OK, responseSzukaj.getStatusCode());
        assertEquals(doWyszukania , znalezione.get(0).getUzytkownik());


        // mozna tez odebrac jako ResponseEntity<String> i zrobic tak
//        JSONArray jsonArray = new JSONArray(responseSzukaj.getBody());
//        for (int i = 0; i < jsonArray.length(); i++)
//        {
//            JSONObject jsonObj = jsonArr.getJSONObject(i);
//            System.out.println(" +++++++++++ ------------- SSSSSSSS  " + jsonObj);
//        }
    }


    @Test
    public void pobierzPozycjeDoZmiany() {
    String numerPozycjiDoZmiany = "2";
    String url = "http://localhost:8080/remanent/rest/pozycje/zmien/pobierzDoZmien/"+numerPozycjiDoZmiany;

    ResponseEntity<PozycjaBazy> response = restRequest(url, null, HttpMethod.GET, MediaType.TEXT_PLAIN, PozycjaBazy.class);

    assertEquals(HttpStatus.OK, response.getStatusCode() );
    assertEquals(2, response.getBody().getId());
    assertEquals("pobierz Pozycje Do Zmiany", response.getBody().getNazwa_towaru());
    }


    @Test
    public void pobierzNieistniejace() {
        String numerPozycjiDoZmiany = "9999";
        String url = "http://localhost:8080/remanent/rest/pozycje/zmien/pobierzDoZmien/"+numerPozycjiDoZmiany;

        ResponseEntity<PozycjaBazy> response = restRequest(url, null, HttpMethod.GET, MediaType.TEXT_PLAIN, PozycjaBazy.class);

        assertEquals(HttpStatus.GONE, response.getStatusCode() );

    }


    @Test
    public void pobierzPozycjeDoUsuniecia() {
        String url = "http://localhost:8080/remanent/rest/pozycje/usun/szukaj/3";

        ResponseEntity<PozycjaBazy> response = restRequest(url, null, HttpMethod.GET, MediaType.TEXT_PLAIN, PozycjaBazy.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("pobierz Pozycje Do Usuniecia", response.getBody().getNazwa_towaru());
    }


    @Test
    public void pobierzNieistniejacaPozycjeDoUsuniecia() {
        String url = "http://localhost:8080/remanent/rest/pozycje/usun/szukaj/9999";

        ResponseEntity<PozycjaBazy> response = restRequest(url, null, HttpMethod.GET, MediaType.TEXT_PLAIN, PozycjaBazy.class);
        assertEquals(HttpStatus.GONE, response.getStatusCode() );
    }


    @Test
    public void usunPozycje() {
        String url = "http://localhost:8080/remanent/rest/pozycje/usun/9";

        ResponseEntity<String> response = restRequest(url, null, HttpMethod.GET, MediaType.TEXT_PLAIN, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("9", response.getBody().toString());

    }


    @Test
    public void szukanieZaawansowaneWszystkieParametry() {
        String url = "http://localhost:8080/remanent/rest/pozycje/szukaj/zaawansowany";
        String opcje = "?towar=zaawansowane&uzytkownik=zaawansowany&cenaNetto=2&radioCenaNetto=3&cenaBrutto=2&radioCenaBrutto=2&ilosc=9&radioIlosc=3";
        HttpHeaders headers = new HttpHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<List<PozycjaBazy>>  responseSzukajZaa = restTemplate.exchange(url+opcje, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<PozycjaBazy>>(){});

        List<PozycjaBazy> znalezione = responseSzukajZaa.getBody();

        assertEquals(HttpStatus.OK,responseSzukajZaa.getStatusCode());
        assertEquals(2, znalezione.size());
    }



    @Test
    public void szukanieZaawansowaneWszystkieParametryBrakWynikow() {
        String url = "http://localhost:8080/remanent/rest/pozycje/szukaj/zaawansowany";
        String opcje = "?towar=zaawansowane&uzytkownik=zaawansowany&cenaNetto=2&radioCenaNetto=3&cenaBrutto=2&radioCenaBrutto=2&ilosc=9&radioIlosc=1";
        HttpHeaders headers = new HttpHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<List<PozycjaBazy>>  responseSzukajZaa = restTemplate.exchange(url+opcje, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<PozycjaBazy>>(){});

        assertEquals(HttpStatus.GONE,responseSzukajZaa.getStatusCode());
    }



    @Test
    public void szukanieZaawansowaneWszystkieLiczby() {
        String url = "http://localhost:8080/remanent/rest/pozycje/szukaj/zaawansowany";
        String opcje = "?towar=zaawansowane&uzytkownik=zaawansowany&cenaNetto=300&radioCenaNetto=2&cenaBrutto=410&radioCenaBrutto=3&ilosc=5&radioIlosc=2";
        HttpHeaders headers = new HttpHeaders();
        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<List<PozycjaBazy>>  responseSzukajZaa = restTemplate.exchange(url+opcje, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<PozycjaBazy>>(){});

        List<PozycjaBazy> znalezione = responseSzukajZaa.getBody();

        assertEquals(HttpStatus.OK,responseSzukajZaa.getStatusCode());
        assertEquals(1, znalezione.size());
    }


    private <T> ResponseEntity<T> restRequest(String URL, String data, HttpMethod method,  MediaType type, Class<T> responseType ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(type);
        HttpEntity<String> entity = new HttpEntity<String>(data, headers);
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<T>  response = restTemplate.exchange(URL, method, entity, responseType );

        return response;
    }


    private String pozycjaJSON(String id,String towar, String cenaN, String cenaB, String uzytkownik, String jednostka, String ilosc) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("nazwa_towaru",towar);
        json.put("cena_netto",cenaN);
        json.put("cena_brutto",cenaB);
        json.put("uzytkownik",uzytkownik);
        json.put("jednostka",jednostka);
        json.put("ilosc",ilosc);
        return json.toString();
    }

}
