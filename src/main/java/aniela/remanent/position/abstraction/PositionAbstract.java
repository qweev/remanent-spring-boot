package aniela.remanent.position.abstraction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class PositionAbstract implements PositionInterface {
    
    protected int pozyzjaWRaporcie;
    protected String nazwaTowaru;
    protected String jednostka;
    protected double cenaNetto;
    protected double cenaBrutto;
    protected double ilosc;

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

    protected double suma;

    public double getCenaBrutto() {
        return cenaBrutto;
    }

    public double getIlosc() {
        return ilosc;
    }

    public void setIlosc(double ilosc) {
        this.ilosc = ilosc;
    }

    public void setCenaBrutto(double cenaBrutto) {
        this.cenaBrutto = cenaBrutto;
    }

    public void setSuma(double cenaNetto, double ilosc) {
        BigDecimal suma = new BigDecimal(cenaNetto * ilosc);
        suma = suma.setScale(2, RoundingMode.HALF_UP);
        double sn = suma.doubleValue();
        this.suma = sn;
    }

    public double getSuma() {
        return suma;
    }
}
