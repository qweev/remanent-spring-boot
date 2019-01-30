package aniela.remanent.pdf.report.netto;

import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;

import java.util.ArrayList;
import java.util.List;

public final class PozycjeReportNettoFactory {

    private PozycjeReportNettoFactory() {

    }

    public static PozycjaDoRaportuNetto createPozycjaDoRaportuNetto(int number) {
        PozycjaDoRaportuNetto pozycjaDoRaportuNetto = new PozycjaDoRaportuNetto();
        pozycjaDoRaportuNetto.setPozycjaWRaporcie(number);
        pozycjaDoRaportuNetto.setNazwaTowaru("Jakis towar");
        pozycjaDoRaportuNetto.setIlosc(3);
        pozycjaDoRaportuNetto.setCenaNetto(3.11);
        pozycjaDoRaportuNetto.setJednostka("szt");
        pozycjaDoRaportuNetto.setSumaNetto(pozycjaDoRaportuNetto.getCenaNetto(), pozycjaDoRaportuNetto.getIlosc());
        return pozycjaDoRaportuNetto;
    }

    public static List<PozycjaDoRaportuNetto> generateListOfPozycjaDoRaportuNetto(int amountToCreate) {

        List<PozycjaDoRaportuNetto> positions = new ArrayList<>(amountToCreate);
        for (int pointer = 1; pointer <= amountToCreate; pointer++) {
            positions.add(createPozycjaDoRaportuNetto(pointer));
        }
        return positions;
    }
}
