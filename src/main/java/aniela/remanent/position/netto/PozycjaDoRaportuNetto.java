package aniela.remanent.position.netto;

import aniela.remanent.position.abstraction.PositionAbstract;

public class PozycjaDoRaportuNetto extends PositionAbstract {



    public PozycjaDoRaportuNetto() {
    }

    @Override
    public String toString() {
        return "{cenaNetto:" + cenaNetto +
                ", sumaNetto:" + suma + ",nazwaTowaru:" +
                nazwaTowaru + ", jednostka:" + jednostka +
                ", ilosc:" + ilosc + ", pozyzjaWRaporcie:" + pozyzjaWRaporcie + "}";
    }


}
