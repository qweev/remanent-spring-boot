package aniela.remanent.pozycje.bazaDanych;

import javax.persistence.*;
import java.util.Objects;

// uzyj LOMBOK adnotacji

@Entity
@Table(name = "pozycje")
public class PozycjaBazy  {
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "cena_netto")
	private double cena_netto;
	@Column(name = "cena_brutto")
	private double cena_brutto;
	@Column(name = "nazwa_towaru")
	private String nazwa_towaru;
	@Column(name = "jednostka")
	private String jednostka;
	@Column(name = "ilosc")
	private double ilosc;
	@Column(name = "uzytkownik")
	private String uzytkownik;

	public PozycjaBazy(){}
	
	@Override
	public String toString() {
		return "{cena_netto:" + cena_netto +
				", cena_brutto:" + cena_brutto + ",nazwa_towaru:" +
				nazwa_towaru + ", jednostka:" + jednostka +
				", ilosc:" + ilosc + ", uzytkownik:" + uzytkownik + ", id:" + id + "}";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCena_netto() {
		return cena_netto;
	}

	public void setCena_netto(double cena_netto) {
		this.cena_netto = cena_netto;
	}

	public double getCena_brutto() {
		return cena_brutto;
	}

	public void setCena_brutto(double cena_brutto) {
		this.cena_brutto = cena_brutto;
	}

	public String getNazwa_towaru() {
		return nazwa_towaru;
	}

	public void setNazwa_towaru(String nazwa_towaru) {
		this.nazwa_towaru = nazwa_towaru;
	}

	public String getJednostka() {
		return jednostka;
	}

	public void setJednostka(String jednostka) {
		this.jednostka = jednostka;
	}

	public double getIlosc() {
		return ilosc;
	}

	public void setIlosc(double ilosc) {
		this.ilosc = ilosc;
	}

	public String getUzytkownik() {
		return uzytkownik;
	}

	public void setUzytkownik(String uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	public int getHashCen() {
		return Objects.hash(this.cena_brutto, this.cena_netto);
	}

}
