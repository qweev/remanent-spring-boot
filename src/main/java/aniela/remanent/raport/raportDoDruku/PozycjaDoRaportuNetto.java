package aniela.remanent.raport.raportDoDruku;

import aniela.remanent.raport.abstraction.PositionAbstract;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class PozycjaDoRaportuNetto extends PositionAbstract {

    private double sumaNetto;

    public PozycjaDoRaportuNetto() {
    }

    @Override
    public String toString() {
        return "{cenaNetto:" + cenaNetto +
                ", sumaNetto:" + sumaNetto + ",nazwaTowaru:" +
                nazwaTowaru + ", jednostka:" + jednostka +
                ", ilosc:" + ilosc + ", pozyzjaWRaporcie:" + pozyzjaWRaporcie + "}";
    }

    public double getCenaNetto() {
        return cenaNetto;
    }

    public double getSumaNetto() {
        return sumaNetto;
    }

    public void setSumaNetto(double cenaNetto, double ilosc) {
        BigDecimal suma = new BigDecimal(cenaNetto * ilosc);
        suma = suma.setScale(2, RoundingMode.HALF_UP);
        double sn = suma.doubleValue();
        this.sumaNetto = sn;
    }
}
