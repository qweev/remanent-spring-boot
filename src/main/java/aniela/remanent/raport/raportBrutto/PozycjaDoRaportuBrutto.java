package aniela.remanent.raport.raportBrutto;


import aniela.remanent.raport.abstraction.PositionAbstract;

public class PozycjaDoRaportuBrutto extends PositionAbstract {

    private double cenaBrutto;

    public PozycjaDoRaportuBrutto() {
    }

    @Override
    public String toString() {
        return "{cenaNetto:" + cenaNetto +
                ", cenaBrutto:" + cenaBrutto + ",nazwaTowaru:" +
                nazwaTowaru + ", jednostka:" + jednostka +
                ", ilosc:" + ilosc + ", pozyzjaWRaporcie:" + pozyzjaWRaporcie + "}";
    }

    public double getCenaBrutto() {
        return cenaBrutto;
    }

    public void setCenaBrutto(double cenaBrutto) {
        this.cenaBrutto = cenaBrutto;
    }


}
