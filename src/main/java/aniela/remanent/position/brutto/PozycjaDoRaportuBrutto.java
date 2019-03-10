package aniela.remanent.position.brutto;


import aniela.remanent.position.abstraction.PositionAbstract;

public class PozycjaDoRaportuBrutto extends PositionAbstract {

    public PozycjaDoRaportuBrutto() {
    }

    @Override
    public String toString() {
        return "{suma:" + suma +
                ", cenaBrutto:" + cenaBrutto + ",nazwaTowaru:" +
                nazwaTowaru + ", jednostka:" + jednostka +
                ", ilosc:" + ilosc + ", pozyzjaWRaporcie:" + pozyzjaWRaporcie + "}";
    }


}
