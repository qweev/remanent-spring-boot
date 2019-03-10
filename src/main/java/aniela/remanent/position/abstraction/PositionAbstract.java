package aniela.remanent.position.abstraction;

public abstract class PositionAbstract implements PositionInterface {
    
    protected int pozyzjaWRaporcie;
    protected String nazwaTowaru;
    protected String jednostka;
    protected double cenaNetto;
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

    public double getIlosc() {
        return ilosc;
    }

    public void setIlosc(double ilosc) {
        this.ilosc = ilosc;
    }

}
