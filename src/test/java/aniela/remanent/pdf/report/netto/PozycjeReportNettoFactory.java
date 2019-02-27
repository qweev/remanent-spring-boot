package aniela.remanent.pdf.report.netto;

import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportuNetto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public final class PozycjeReportNettoFactory {

    private static String LONG_UNIT_NAME = "JakiśćTowarŻŹŹćźóęąĄĘÓłŁślóÓjvvńóóęttTWYÓóÓPRWTWAIU";

    private Map<Integer, Integer> amountMapping = new HashMap<>();
    private Map<Integer, Double> nettoMapping;
    private Map<Integer, String> unitMapping;
    private Map<Integer, String> positionNames;

    public PozycjeReportNettoFactory() {
        IntStream.range(1, 9).forEach(value -> {
            amountMapping.putIfAbsent(value, value);
        });
        nettoMapping = fillNetto();
        unitMapping = fillUnits();
        positionNames = getPositions();
    }


    public List<PozycjaDoRaportuNetto> generateListOfPozycjaDoRaportuNettoContentSame(int amountToCreate) {
        List<PozycjaDoRaportuNetto> positions = new ArrayList<>(amountToCreate);
        IntStream.range(1, amountToCreate).forEach(value -> {
            positions.add(createPozycjaDoRaportuNetto(value));
        });
        return positions;
    }

    public List<PozycjaDoRaportuNetto> generateListOfPozycjaDoRaportuNettoContentRandom(int amountToCreate) {
        List<PozycjaDoRaportuNetto> positions = new ArrayList<>(amountToCreate);
        IntStream.range(1, amountToCreate).forEach(value -> {
            positions.add(createPozycjaDoRaportuNettoRandom(value));
        });
        return positions;
    }

    public List<PozycjaDoRaportuNetto> generateListOfPozycjaDoRaportuNettoContentMixed(int amountToCreate) {
        List<PozycjaDoRaportuNetto> positions = new ArrayList<>(amountToCreate);
        IntStream.range(1, amountToCreate).forEach(value -> {

            if(value%2 == 0)
                positions.add(createPozycjaDoRaportuNettoRandom(value));
            else
                positions.add(createPozycjaDoRaportuNetto(value));
        });
        return positions;
    }




    private PozycjaDoRaportuNetto createPozycjaDoRaportuNetto(int number) {
        PozycjaDoRaportuNetto pozycjaDoRaportuNetto = new PozycjaDoRaportuNetto();
        pozycjaDoRaportuNetto.setPozycjaWRaporcie(number);
        pozycjaDoRaportuNetto.setNazwaTowaru(LONG_UNIT_NAME);
        pozycjaDoRaportuNetto.setIlosc(ThreadLocalRandom.current().nextInt(1,20));
        pozycjaDoRaportuNetto.setCenaNetto(3.11);
        pozycjaDoRaportuNetto.setJednostka("szt");
        pozycjaDoRaportuNetto.setSumaNetto(pozycjaDoRaportuNetto.getCenaNetto(), pozycjaDoRaportuNetto.getIlosc());
        return pozycjaDoRaportuNetto;
    }


    private PozycjaDoRaportuNetto createPozycjaDoRaportuNettoRandom(int number) {
        PozycjaDoRaportuNetto pozycjaDoRaportuNetto = new PozycjaDoRaportuNetto();
        pozycjaDoRaportuNetto.setPozycjaWRaporcie(number);
        pozycjaDoRaportuNetto.setNazwaTowaru(positionNames.getOrDefault(ThreadLocalRandom.current().nextInt(1,4),"Wartosc domyslna dla nazwy"));
        pozycjaDoRaportuNetto.setIlosc(generatePseudoDouble());
        pozycjaDoRaportuNetto.setCenaNetto(generatePseudoDouble());
        pozycjaDoRaportuNetto.setJednostka(unitMapping.getOrDefault(ThreadLocalRandom.current().nextInt(1,4),"XXX"));
        pozycjaDoRaportuNetto.setSumaNetto(pozycjaDoRaportuNetto.getCenaNetto(), pozycjaDoRaportuNetto.getIlosc());
        return pozycjaDoRaportuNetto;
    }


    private Map<Integer, String> fillUnits() {
        Map<Integer, String> units = new HashMap<>();
        units.put(1, "szt.");
        units.put(2, "kpl.");
        units.put(3, "mb.");
        units.put(3, "op.");
        return units;
    }

    private Map<Integer, Double> fillNetto() {
        Map<Integer, Double> mapping = new HashMap<>();
        IntStream.range(1, 9).forEach(value -> {
            double doubleRandom = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1, 245)).setScale(2,
                RoundingMode.HALF_UP)
                .doubleValue();
            mapping.putIfAbsent(value, doubleRandom);
        });
        return mapping;
    }

    private Map<Integer, String> getPositions() {
        Map<Integer, String> positions = new HashMap<>();
        positions.put(1, LONG_UNIT_NAME);
        positions.put(2, "cos dluzszego");
        positions.put(3, "Znakow to tu bedzie jakies 29");
        return positions;
    }

    public double generatePseudoDouble(){
        double  result = Math.random() + ThreadLocalRandom.current().nextDouble(1,9);
        return  BigDecimal.valueOf(result).setScale(2,RoundingMode.HALF_UP).doubleValue();
    }
}
