package aniela.remanent.position.abstraction;

import aniela.remanent.report.type.ReportType;

public interface Position {

    int getPozyzjaWRaporcie();

    void setPozycjaWRaporcie(int pozyzjaWRaporcie);

    String getNazwaTowaru();

    void setNazwaTowaru(String nazwaTowaru);

    String getJednostka();

    void setJednostka(String jednostka);

    double getCenaNetto();

    void setCenaNetto(double cenaNetto);

    double getCenaBrutto();

    void setCenaBrutto(double cenaBrutto);

    double getIlosc();

    void setIlosc(double ilosc);


    default double getPrice(Position position, ReportType reportType) {
        double price = 0;
        switch (reportType) {
            case NETTO:
                price = position.getCenaNetto();
                break;
            case BRUTTO:
                price = position.getCenaBrutto();
                break;
        }
        return price;
    }

    double getSuma();

}
