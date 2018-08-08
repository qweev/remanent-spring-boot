package aniela.remanent.raport.raportDoDruku;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class PozycjaDoRaportu {

	private int pozyzjaWRaporcie;
	private String nazwaTowaru;
	private String jednostka;
	private double cenaNetto;
	private double ilosc;
	private double sumaNetto;
	
	public PozycjaDoRaportu(){}
	
	@Override
	public String toString() {
        return "{cenaNetto:" + cenaNetto +
                ", sumaNetto:" + sumaNetto + ",nazwaTowaru:" +
				nazwaTowaru + ", jednostka:" + jednostka + 
				", ilosc:" + ilosc+ ", pozyzjaWRaporcie:" + pozyzjaWRaporcie +"}";
	}
	
	public int getPozyzjaWRaporcie() {
		return pozyzjaWRaporcie;
	}

	public void setPozycjaWRaporcie(int pozyzjaWRaporcie) {
		this.pozyzjaWRaporcie = pozyzjaWRaporcie;
	}

	public String getNazwaTowaru() {
		return nazwaTowaru;
	}

	public void setNazwaTowaru(String nazwaTowaru) {
		this.nazwaTowaru = nazwaTowaru;
	}

	public String getJednostka() {
		return jednostka;
	}

	public void setJednostka(String jednostka) {
		this.jednostka = jednostka;
	}

	public double getCenaNetto() {
		return cenaNetto;
	}

	public void setCenaNetto(double cenaNetto) {
		this.cenaNetto = cenaNetto;
	}

	public double getIlosc() {
		return ilosc;
	}

	public void setIlosc(double ilosc) {
		this.ilosc = ilosc;
	}

	public double getSumaNetto() {
		return sumaNetto;
	}

	public void setSumaNetto(double cenaNetto, double ilosc) {
		BigDecimal suma = new BigDecimal( cenaNetto * ilosc );
		suma = suma.setScale(2, RoundingMode.HALF_UP);
	    double sn = suma.doubleValue();
		this.sumaNetto = sn;
	}
}
