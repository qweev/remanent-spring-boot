package aniela.remanent.Integration;

import aniela.remanent.pozycje.bazaDanych.PozycjaBazy;
import aniela.remanent.util.HostAndPortResolver;
import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static aniela.remanent.Integration.IntegrationConfig.PORT;
import static org.junit.Assert.assertEquals;

public class Raport_Netto_Rest_Intergation {

    @Test
    public void poprawneLogowanie() {
        String login = "admin";
        String haslo = "admin";
        String urlLogowanie = HostAndPortResolver.determineHostAndPort(PORT) + "/remanent/rest/raport/zaloguj/" + login + "/" + haslo;

        ResponseEntity<String> response = restRequest(urlLogowanie, null, HttpMethod.GET, MediaType.TEXT_PLAIN, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody());

    }

    @Test
    public void zlyUzytkownikPrzyLogowaniu() {
        String login = "zlyadmin";
        String haslo = "admin";
        String urlLogowanie = HostAndPortResolver.determineHostAndPort(PORT) + "/remanent/rest/raport/zaloguj/" + login + "/" + haslo;

        ResponseEntity<String> response = restRequest(urlLogowanie, null, HttpMethod.GET, MediaType.TEXT_PLAIN, String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("NOK", response.getBody());
    }

    @Test
    public void zleHAsloPrzyLogowaniu() {
        String login = "admin";
        String haslo = "zlehaslo";
        String urlLogowanie = HostAndPortResolver.determineHostAndPort(PORT) + "/remanent/rest/raport/zaloguj/" + login + "/" + haslo;

        ResponseEntity<String> response = restRequest(urlLogowanie, null, HttpMethod.GET, MediaType.TEXT_PLAIN, String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("NOK", response.getBody());
    }


    @Test
    public void generujStatystyki() {
        String urlStatysyka = HostAndPortResolver.determineHostAndPort(PORT) + "/remanent/rest/raport/statystyki";

        ResponseEntity<String> response = restRequest(urlStatysyka, null, HttpMethod.GET, MediaType.TEXT_PLAIN, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(false, response.getBody().isEmpty());
    }


    @Test
    public void pobierzZeroweCeny() {
        String urlZeroweCeny = HostAndPortResolver.determineHostAndPort(PORT) + "/remanent/rest/raport/zeroweCeny";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        TestRestTemplate restTemplate = new TestRestTemplate();


        ResponseEntity<List<PozycjaBazy>> response = restTemplate.exchange(urlZeroweCeny, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<PozycjaBazy>>() {
                });
        List<PozycjaBazy> znalezione = response.getBody();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(20, znalezione.size());
    }


    @Test
    public void utworzPlikRemanentNetto() {
        String urlPlikRemanent = HostAndPortResolver.determineHostAndPort(PORT) + "/remanent/rest/raport/excel/remanent";

        ResponseEntity<String> response = restRequest(urlPlikRemanent, null, HttpMethod.GET, MediaType.TEXT_PLAIN, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().contains("plik zapisany w lokacji"));
    }


    @Test
    public void utworzPlikRemanentBrutto() {
        String urlPlikRemanent = HostAndPortResolver.determineHostAndPort(PORT) + "/remanent/rest/raport/excel/brutto/remanent";

        ResponseEntity<String> response = restRequest(urlPlikRemanent, null, HttpMethod.GET, MediaType.TEXT_PLAIN, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().contains("plik zapisany w lokacji"));
    }


    private <T> ResponseEntity<T> restRequest(String URL, String data, HttpMethod method, MediaType type, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(type);
        HttpEntity<String> entity = new HttpEntity<String>(data, headers);
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<T> response = restTemplate.exchange(URL, method, entity, responseType);

        return response;
    }
}



